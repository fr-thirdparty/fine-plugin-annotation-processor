package com.voc.fr.tool.api.impl;

import com.voc.fr.tool.api.IAttribute;
import lombok.*;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2019/08/30 17:13
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class DefaultAttribute implements IAttribute {

    /**
     * 属性名称
     */
    private String name;

    /**
     * 属性值
     */
    private String value;

    /**
     * 优先级
     */
    private int order;

    @Override
    public int getOrder() {
        return this.order;
    }

    @Override
    public boolean isClassAttribute() {
        return CLASS_ATTRIBUTE_NAME.equals(name);
    }

    public static IAttribute of(String name, String value, int order) {
        return new DefaultAttribute(name, value, order);
    }

}
