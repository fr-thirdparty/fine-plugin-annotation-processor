package com.voc.fr.tool.api;

import org.springframework.core.Ordered;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2019/08/30 16:32
 */
public interface IAttribute extends Ordered {

    /**
     * 类属性
     */
    String CLASS_ATTRIBUTE_NAME = "class";

    /**
     * 属性名称
     *
     * @return String
     */
    String getName();

    /**
     * 属性值
     *
     * @return String
     */
    String getValue();

    /**
     * 是否是类属性
     *
     * @return boolean
     */
    boolean isClassAttribute();

}
