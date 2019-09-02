package com.voc.fr.tool.api.impl;

import com.voc.fr.tool.api.IAttribute;
import com.voc.fr.tool.api.IClassInfo;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2019/08/30 17:28
 */
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@ToString
public class DefaultClassInfo extends DefaultNode implements IClassInfo {

    /**
     * 类的完全限定名
     */
    private String canonicalName;

    public DefaultClassInfo(String tagName, String canonicalName, List<IAttribute> attributes) {
        super(tagName, attributes);
        this.canonicalName = canonicalName;
        this.addAttributes(DefaultAttribute.of(IAttribute.CLASS_ATTRIBUTE, canonicalName, Integer.MIN_VALUE));
    }

    public static IClassInfo of(String tagName, String canonicalName, IAttribute... attributes) {
        List<IAttribute> attributeList = new ArrayList<>(Arrays.asList(attributes));
        return new DefaultClassInfo(tagName, canonicalName, attributeList);
    }

    /**
     * 仅限用于接口名和注解名一致的情况
     *
     * @param annotationClass Class<? extends Annotation>
     * @param canonicalName   String canonicalName
     * @param attributes      IAttribute[]
     * @return IClassInfo
     */
    public static IClassInfo of(Class<? extends Annotation> annotationClass, String canonicalName, IAttribute... attributes) {
        return of(annotationClass.getSimpleName(), canonicalName, attributes);
    }

}