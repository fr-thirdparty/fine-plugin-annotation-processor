package com.voc.fr.tool;

import com.google.auto.service.AutoService;
import com.voc.fr.tool.annotation.EnableFinePlugin;
import com.voc.fr.tool.api.FineVersion;
import com.voc.fr.tool.api.IAnnotationProcessor;
import com.voc.fr.tool.api.IPluginXmlContext;
import com.voc.fr.tool.utils.FineVersionHelp;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.reflections.Reflections;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic;
import java.io.File;
import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import static com.voc.fr.tool.CompilerOptions.*;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2019/08/31 19:42
 */
@Slf4j
@AutoService(Processor.class)
@SupportedOptions({ENV_VERSION_OPTION, PLUGIN_VERSION_OPTION, PLUGIN_XML_DIR_OPTION})
@Component
public class DefaultPluginXmlProcessor extends AbstractProcessor {

    private ClassLoader originClassLoader;
    private final Reflections reflections = new Reflections("com.voc.fr.tool.annotation");
    private CompilerOptions options;
    private IPluginXmlContext pluginXmlContext;

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        Set<String> supportedAnnotationTypes = new LinkedHashSet<>(Arrays.asList(Config.FINE_FUNCTION_RECORDER));
        this.reflections.getSubTypesOf(Annotation.class).stream().filter(
                aClass -> {
                    EnableFinePlugin finePlugin = aClass.getAnnotation(EnableFinePlugin.class);
                    return finePlugin != null && finePlugin.value();
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
        this.initClassLoader();
        super.init(processingEnv);
        this.initApp(processingEnv);
    }

    private void initApp(ProcessingEnvironment processingEnv) {
        this.options = new CompilerOptions(processingEnv);
        if (!options.check()) {
            return;
        }
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(Config.class);
        applicationContext.getBeanFactory().registerSingleton("processingEnv", processingEnv);
        this.pluginXmlContext = applicationContext.getBean(IPluginXmlContext.class);
        Map<String, IAnnotationProcessor> processorMap = applicationContext.getBeansOfType(IAnnotationProcessor.class);

        if (StringUtils.isNotEmpty(options.getEnvVersion())) {
            this.pluginXmlContext.setFineVersion(FineVersionHelp.fromVersion(options.getEnvVersion()));
        } else {
            this.pluginXmlContext.setFineVersion(FineVersion.V10);
        }
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        if (!this.options.isValid()) {
            return false;
        }

        /* 遍历 annotations 获取 annotation 进行处理 */
        for (TypeElement typeElement : annotations) {
            this.pluginXmlContext.getProcessors().forEach(processor -> {
                processor.setPluginXmlContext(this.pluginXmlContext);
                processor.setProcessingEnv(this.processingEnv);
                processor.process(typeElement, roundEnv);
            });
        }

        /* 处理结束再生产 plugin.xml */
        if (roundEnv.processingOver()) {
            try {
                this.pluginXmlContext.generate(new File(options.getPluginXmlDir()), this.processingEnv.getFiler());
                Thread.currentThread().setContextClassLoader(originClassLoader);
            } catch (Exception e) {
                this.processingEnv.getMessager().printMessage(Diagnostic.Kind.ERROR, e.getMessage());
                return false;
            }
        }

        return true;
    }

    private void initClassLoader() {
        originClassLoader = Thread.currentThread().getContextClassLoader();
        ClassLoader classLoader = this.getClass().getClassLoader();
        Thread.currentThread().setContextClassLoader(classLoader);
    }

}
