package com.voc.fr.tool.api.impl;

import com.voc.fr.tool.annotation.Module;
import com.voc.fr.tool.annotation.decision.LoginPageProvider;
import com.voc.fr.tool.api.*;
import com.voc.fr.tool.util.AnnotationValueUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.DeclaredType;
import javax.lang.model.type.MirroredTypeException;
import java.lang.annotation.Annotation;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2019/08/31 20:18
 */
public abstract class BaseAnnotationProcessor implements IAnnotationProcessor {

    private IPluginXmlContext pluginXmlContext;

    private ProcessingEnvironment processingEnv;

    /**
     * 注解处理器日志记录器
     */
    protected final static Logger logger = LoggerFactory.getLogger("PluginXmlProcessor");

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
                logger.debug("Annotation：[{}] {}", annotation.getSimpleName(), annotation.getQualifiedName());
            }
            for (Element element : env.getElementsAnnotatedWith(annotation)) {
                process(pluginXmlContext, element, annotation, processingEnv);
            }
        }
    }

    @Override
    public void process(IPluginXmlContext pluginXmlContext, Element element, TypeElement typeElement, ProcessingEnvironment processingEnv) {
        String xmlTag = this.getXmlTag(element, typeElement, this.getAnnotationClass());
        if (element.getKind() == ElementKind.CLASS) {
            Object value = AnnotationValueUtils.getQualifiedClassName(element, getAnnotationClass(), "value");
            logger.error("{}", value);
            LoginPageProvider provider = element.getAnnotation(LoginPageProvider.class);
            String qualifiedClassName;

            try {
                Class<?> clazz = provider.value();
                qualifiedClassName = clazz.getCanonicalName();
            } catch (MirroredTypeException mte) {
                DeclaredType classTypeMirror = (DeclaredType) mte.getTypeMirror();
                TypeElement classTypeElement = (TypeElement) classTypeMirror.asElement();
                qualifiedClassName = classTypeElement.getQualifiedName().toString();
            }

            /*如果 value 为 Void.class，则 value 值取当前被标记类元素的类完全限定名*/
            if (Void.class.getCanonicalName().equals(qualifiedClassName)) {
                qualifiedClassName = element.asType().toString();
            }

            pluginXmlContext.addImplementation(xmlTag, DefaultClassInfo.of(this.getAnnotationClass(), qualifiedClassName));
        }
    }

    public String getClassName() {
        return "";
    }

    @Override
    public String getXmlTag(Element element, TypeElement typeElement, Class<? extends Annotation> annotationClass) {
        return this.getXmlTag(typeElement);
    }

    @Override
    public String getXmlTag(TypeElement typeElement) {
        Module module = typeElement.getAnnotation(Module.class);
        if (module != null && this.getModule() != null) {
            FineModule fineModule = module.value();
            return this.getModule().getTagName(fineModule);
        }
        return null;
    }

    @Override
    public Object getAnnotationValue(Element element, Class<? extends Annotation> annotationClass, String key) {
        return AnnotationValueUtils.getAnnotationValue(element, annotationClass, key);
    }

    @Override
    public boolean support(TypeElement annotation) {
        if (getAnnotationClass() != null) {
            return this.support(annotation, getAnnotationClass());
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
