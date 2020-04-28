package com.voc.fr.tool.api;

import org.springframework.core.Ordered;

import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.TypeElement;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2019/08/31 20:33
 */
public interface IAnnotationProcessor extends Ordered {

    /**
     * 获取处理环境
     *
     * @return ProcessingEnvironment
     */
    ProcessingEnvironment getProcessingEnv();

    /**
     * 获取插件上下文
     *
     * @return IPluginXmlContext
     */
    IPluginXmlContext getPluginXmlContext();

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
     * 是否是指定的注解类型
     *
     * @param elements 元素类型
     * @return boolean
     */
    boolean support(TypeElement elements);

    /**
     * 获取插件模块接口标签
     *
     * @param typeElement 接口注解
     * @return 标签名称
     */
    String getModuleTag(TypeElement typeElement);

}
