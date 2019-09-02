package com.voc.fr.tool;

import com.google.auto.service.AutoService;
import com.voc.fr.tool.annotation.EnabledSupportedAnnotation;
import com.voc.fr.tool.api.IAnnotationProcessor;
import com.voc.fr.tool.api.IPluginXmlContext;
import com.voc.fr.tool.util.FineVersionHelp;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.reflections.Reflections;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.OrderComparator;

import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic;
import java.io.File;
import java.lang.annotation.Annotation;
import java.util.*;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2019/08/31 19:42
 */
@Slf4j
@AutoService(Processor.class)
@SupportedOptions({"pluginVersion", "pluginXmlDir", "pluginFineVersion"})
public class DefaultPluginXmlProcessor extends AbstractProcessor {

    private ApplicationContext applicationContext;
    private IPluginXmlContext pluginXmlContext;
    private String pluginVersion;
    private String pluginXmlDir;
    private ClassLoader originClassLoader;

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        Set<String> supportedAnnotationTypes = new LinkedHashSet<>();
        Reflections reflections = new Reflections("com.voc.fr.tool.annotation");
        reflections.getSubTypesOf(Annotation.class).stream().filter(
                aClass -> {
                    EnabledSupportedAnnotation supportedAnnotation = aClass.getAnnotation(EnabledSupportedAnnotation.class);
                    return supportedAnnotation != null && supportedAnnotation.value();
                }
        ).map(Class::getName).sorted().forEach(supportedAnnotationTypes::add);
        return supportedAnnotationTypes;
    }

    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }

    /**
     * 初始化逻辑
     *
     * @param processingEnv ProcessingEnvironment
     */
    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
        this.pluginVersion = processingEnv.getOptions().get("pluginVersion");
        this.pluginXmlDir = processingEnv.getOptions().get("pluginXmlDir");
        if (StringUtils.isEmpty(pluginVersion)) {
            processingEnv.getMessager().printMessage(Diagnostic.Kind.ERROR, "请确认插件版本参数设置是否正确 compilerArgs -ApluginVersion=[value]");
        }
        if (StringUtils.isEmpty(pluginXmlDir)) {
            processingEnv.getMessager().printMessage(Diagnostic.Kind.ERROR, "请确认插件描述文件存放目录参数设置是否正确 compilerArgs -ApluginXmlDir=[value]");
        }
        if (log.isInfoEnabled()) {
            log.info("插件版本号：{} , 插件描述文件目录：{}", pluginVersion, pluginXmlDir);
        }
        this.intiClassLoader();
        applicationContext = new AnnotationConfigApplicationContext(Config.class);
        this.pluginXmlContext = applicationContext.getBean(IPluginXmlContext.class);

        String pluginFineVersion = processingEnv.getOptions().get("pluginFineVersion");
        if (StringUtils.isNotEmpty(pluginFineVersion)) {
            this.pluginXmlContext.setFineVersion(FineVersionHelp.fromVersion(pluginFineVersion));
        }
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        if (StringUtils.isEmpty(pluginVersion) || StringUtils.isEmpty(pluginXmlDir)) {
            return false;
        }
        /* 遍历 annotations 获取 annotation 进行处理 */
        for (TypeElement typeElement : annotations) {
            //扫描获取 IAnnotationProcessor 相关的类，处理实例
            Map<String, IAnnotationProcessor> processorMap = this.applicationContext.getBeansOfType(IAnnotationProcessor.class);
            List<IAnnotationProcessor> processors = new ArrayList<>(processorMap.values());
            OrderComparator.sort(processors);
            processors.forEach(processor -> {
                processor.setContext(this.pluginXmlContext);
                processor.setProcessingEnv(this.processingEnv);
                processor.process(typeElement, roundEnv);
            });

        }
        /* 处理结束再生产 plugin.xml */
        if (roundEnv.processingOver()) {
            try {
                this.pluginXmlContext.generate(new File(pluginXmlDir), this.processingEnv.getFiler());
                Thread.currentThread().setContextClassLoader(originClassLoader);
            } catch (Exception e) {
                this.processingEnv.getMessager().printMessage(Diagnostic.Kind.ERROR, e.getMessage());
                return false;
            }
        }

        return true;
    }

    private void intiClassLoader() {
        originClassLoader = Thread.currentThread().getContextClassLoader();
        ClassLoader classLoader = this.getClass().getClassLoader();
        Thread.currentThread().setContextClassLoader(classLoader);
    }

}
