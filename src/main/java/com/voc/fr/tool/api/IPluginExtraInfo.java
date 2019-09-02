package com.voc.fr.tool.api;


import org.dom4j.Element;

/**
 * 插件额外信息
 *
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2019/09/02 09:44
 */
public interface IPluginExtraInfo {

    /**
     * 插件依赖
     *
     * @return IDependence
     */
    IDependence getDependence();

    /**
     * 设置插件依赖
     *
     * @param dependence IDependence
     */
    void setDependence(IDependence dependence);

    /**
     * 自定义的配置属性
     *
     * @return Element
     */
    Element getAttributes();

    /**
     * 设置自定义配置
     *
     * @param element Element
     */
    void setAttributes(Element element);

//    lifecycle-monitor
//    prefer-packages
//    move-after-install
}
