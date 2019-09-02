package com.voc.fr.tool.api;

import org.springframework.core.Ordered;

import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import java.lang.annotation.Annotation;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2019/08/31 20:33
 */
public interface IAnnotationProcessor extends Ordered {

    /**
     * 插件描述文件上下文
     *
     * @param pluginXmlContext IPluginXmlContext
     */
    void setContext(IPluginXmlContext pluginXmlContext);

    /**
     * 处理环境
     *
     * @param processingEnv ProcessingEnvironment
     */
    void setProcessingEnv(ProcessingEnvironment processingEnv);

    /**
     * 接口所属模块
     *
     * @return IModule
     */
    IModule getModule();

    /**
     * 指定注解处理器
     *
     * @param annotation 注解
     * @param env        运行环境
     */
    void process(TypeElement annotation, RoundEnvironment env);

    /**
     * 处理被注解的元素
     *
     * @param pluginXmlContext 处理上下文
     * @param element          被处理元素
     * @param typeElement      注解元素
     * @param processingEnv    处理环境
     */
    void process(IPluginXmlContext pluginXmlContext, Element element, TypeElement typeElement, ProcessingEnvironment processingEnv);

    /**
     * 是否是指定的注解类型
     *
     * @param annotation 元素类型
     * @return boolean
     */
    boolean support(TypeElement annotation);

    /**
     * 获取插件接口标签
     *
     * @param element         元素
     * @param typeElement     接口注解
     * @param annotationClass 注解类
     * @return 标签名称
     */
    String getXmlTag(Element element, TypeElement typeElement, Class<? extends Annotation> annotationClass);

    /**
     * 获取元素上注解的值
     *
     * @param element         元素
     * @param annotationClass 注解类
     * @param key             键名（注解内方法名）
     * @return Object
     */
    Object getAnnotationValue(Element element, Class<? extends Annotation> annotationClass, String key);

}
