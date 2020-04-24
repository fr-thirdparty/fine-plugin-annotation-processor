package com.voc.fr.tool.api.impl;

import com.voc.fr.tool.api.IAttribute;
import com.voc.fr.tool.api.IClassInfoNode;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.commons.lang3.StringUtils;

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
public class DefaultClassInfoNode extends DefaultNode implements IClassInfoNode {

    /**
     * 类的完全限定名
     */
    private String canonicalName;

    public DefaultClassInfoNode(String tagName, String canonicalName, List<IAttribute> attributes) {
        super(tagName, attributes);
        this.canonicalName = canonicalName;
        if (StringUtils.isNotEmpty(canonicalName)) {
            this.addAttributes(DefaultAttribute.of(IAttribute.CLASS_ATTRIBUTE_NAME, canonicalName, Integer.MIN_VALUE));
        }
    }

    public static IClassInfoNode of(String tagName, String canonicalName, IAttribute... attributes) {
        List<IAttribute> attributeList = new ArrayList<>(Arrays.asList(attributes));
        return new DefaultClassInfoNode(tagName, canonicalName, attributeList);
    }

    /**
     * 仅限用于接口名和注解名一致的情况
     *
     * @param annotationClass Class<? extends Annotation>
     * @param canonicalName   String canonicalName
     * @param attributes      IAttribute[]
     * @return IClassInfo
     */
    public static IClassInfoNode of(Class<? extends Annotation> annotationClass, String canonicalName, IAttribute... attributes) {
        return of(annotationClass.getSimpleName(), canonicalName, attributes);
    }

}
