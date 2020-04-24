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

//    /**
//     * 处理被注解的元素
//     *
//     * @param pluginXmlContext 处理上下文
//     * @param element          被处理元素
//     * @param typeElement      注解元素
//     * @param processingEnv    处理环境
//     * @see #process4PluginXmlContext(IPluginXmlContext, Element, TypeElement)
//     * @deprecated
//     */
//    void process(IPluginXmlContext pluginXmlContext, Element element, TypeElement typeElement, ProcessingEnvironment processingEnv);

//    /**
//     * 处理被注解的元素
//     *
//     * @param pluginXmlContext 处理上下文
//     * @param element          被处理元素
//     * @param typeElement      注解元素
//     */
//    void process4PluginXmlContext(IPluginXmlContext pluginXmlContext, Element element, TypeElement typeElement);

    /**
     * 是否是指定的注解类型
     *
     * @param elements 元素类型
     * @return boolean
     */
    boolean support(TypeElement elements);

//    /**
//     * 是否是指定的注解类型中的任何一个
//     *
//     * @param elements 元素类型
//     * @return boolean
//     */
//    boolean supportAny(TypeElement[] elements);

    /**
     * 获取插件模块接口标签
     *
     * @param typeElement 接口注解
     * @return 标签名称
     */
    String getModuleTag(TypeElement typeElement);

}
