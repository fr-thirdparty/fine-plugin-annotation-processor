package com.voc.fr.tool.api.impl;

import com.voc.fr.tool.annotation.Module;
import com.voc.fr.tool.api.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import java.lang.annotation.Annotation;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2019/08/31 20:18
 */
public abstract class AbstractAnnotationProcessor implements IAnnotationProcessor {

    protected IPluginXmlContext pluginXmlContext;

    protected ProcessingEnvironment processingEnv;

    /**
     * 注解处理器日志记录器
     */
    public final static Logger logger = LoggerFactory.getLogger("PluginXmlProcessor");

    @Override
    public void setPluginXmlContext(IPluginXmlContext pluginXmlContext) {
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
        this.verify(this.pluginXmlContext, this.processingEnv);
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

    protected void verify(IPluginXmlContext pluginXmlContext, ProcessingEnvironment processingEnv) {
        Assert.notNull(pluginXmlContext, "pluginXmlContext 不能为空");
        Assert.notNull(processingEnv, "processingEnv 不能为空");
    }

    /**
     * 插件描述文件处理上下环境
     *
     * @param pluginXmlContext IPluginXmlContext
     * @param element          Element
     * @param typeElement      TypeElement
     */
    public abstract void process4PluginXmlContext(IPluginXmlContext pluginXmlContext, Element element, TypeElement typeElement);

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
