package com.voc.fr.tool.api.impl;

import com.voc.fr.tool.annotation.Module;
import com.voc.fr.tool.api.*;
import com.voc.fr.tool.util.AnnotationValueUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.TypeElement;
import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2019/08/31 20:18
 */
public abstract class BaseAnnotationProcessor implements IAnnotationProcessor {

    protected IPluginXmlContext pluginXmlContext;

    protected ProcessingEnvironment processingEnv;

    /**
     * 注解处理器日志记录器
     */
    public final static Logger logger = LoggerFactory.getLogger("PluginXmlProcessor");

    @Override
    public void setContext(IPluginXmlContext pluginXmlContext) {
        this.pluginXmlContext = pluginXmlContext;
    }

    @Override
    public void setProcessingEnv(ProcessingEnvironment processingEnv) {
        this.processingEnv = processingEnv;
    }

    @Override
    public IModule getModule() {
        FineVersion version = pluginXmlContext.getFineVersion();
        switch (version) {
            case V8:
                return new F8Module();
            case V9:
                return new F9Module();
            case V10:
            default:
                return new F10Module();
        }
    }

    @Override
    public void process(TypeElement annotation, RoundEnvironment env) {
        if (support(annotation)) {
            if (logger.isInfoEnabled()) {
                logger.info("@{}", annotation.getSimpleName());
            }
            if (logger.isDebugEnabled()) {
                logger.debug("Annotation：{}", annotation.getQualifiedName());
            }
            for (Element element : env.getElementsAnnotatedWith(annotation)) {
                this.process4PluginXmlContext(pluginXmlContext, element, annotation);
            }
        }
    }

    protected void process4PluginXmlContext(IPluginXmlContext pluginXmlContext, Element element, TypeElement typeElement) {
        this.processClass(pluginXmlContext, element, typeElement);
    }

    protected void processClass(IPluginXmlContext pluginXmlContext, Element element, TypeElement typeElement) {
        if (element.getKind() == ElementKind.CLASS) {

            Map<String, Object> values = AnnotationValueUtils.getAllReflectedValues(element, getAnnotationClass());

            List<IClassInfoNode> infoNodes = annotationValue2ClassInfoNode(values, "value");

            String moduleTag = this.getModuleTag(typeElement);

            infoNodes.forEach(classInfoNode -> pluginXmlContext.addImplementation(moduleTag, classInfoNode));
        }
    }

    /**
     * 注解信息转IClassInfoNode
     *
     * @param values   注解信息
     * @param classKey 类对应的键值
     * @return IClassInfoNode
     */
    protected List<IClassInfoNode> annotationValue2ClassInfoNode(Map<String, Object> values, String classKey) {
        IAttribute[] attributes = values.entrySet()
                .stream()
                .filter(entry -> !entry.getKey().equals(classKey))
                .map(entry -> DefaultAttribute.of(entry.getKey(), entry.getValue().toString(), 0))
                .toArray(IAttribute[]::new);

        List<String> canonicalNames = new ArrayList<>();
        Object object = values.get(classKey);
        if (object.getClass().isArray()) {
            Object[] arr = (Object[]) object;
            for (Object o : arr) {
                canonicalNames.add(o.toString());
            }
        } else {
            canonicalNames.add(object.toString());
        }
        return canonicalNames.stream()
                .map(canonicalName -> DefaultClassInfoNode.of(this.getAnnotationClass(), canonicalName, attributes))
                .collect(Collectors.toList());
    }

    ;

    @Override
    public String getModuleTag(TypeElement typeElement) {
        Module module = typeElement.getAnnotation(Module.class);
        if (module != null && this.getModule() != null) {
            FineModule fineModule = module.value();
            return this.getModule().getTagName(fineModule);
        }
        return null;
    }

    @Override
    public boolean support(TypeElement element) {
        if (getAnnotationClass() != null) {
            return this.support(element, getAnnotationClass());
        }
        return false;
    }

    @Override
    public int getOrder() {
        return 0;
    }

    /**
     * 注解类
     *
     * @return Class<? extends Annotation>
     */
    public abstract Class<? extends Annotation> getAnnotationClass();

    /**
     * 是否是指定的注解类型
     *
     * @param annotation      元素类型
     * @param annotationClass 类类型
     * @return boolean
     */
    protected boolean support(TypeElement annotation, Class<? extends Annotation> annotationClass) {
        return annotation.getQualifiedName().contentEquals(annotationClass.getCanonicalName());
    }

}
