package com.voc.fr.tool.api.impl;

import com.voc.fr.tool.api.IAttribute;
import com.voc.fr.tool.api.INode;
import lombok.*;
import org.jetbrains.annotations.NotNull;
import org.springframework.core.OrderComparator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2019/09/02 16:36
 */
@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class DefaultNode implements INode {

    /**
     * 接口标签名称
     */
    private String tagName;

    /**
     * 接口其他属性
     */
    private List<IAttribute> attributes;

    @Override
    public void addAttributes(IAttribute attribute) {
        if (attributes == null) {
            attributes = new ArrayList<>();
        }
        if (!attributes.contains(attribute)) {
            attributes.add(attribute);
        }
    }

    @Override
    public int compareTo(@NotNull INode o) {
        /*按接口名称升序排序*/
        int result = this.tagName.compareTo(o.getTagName());
        if (this.attributes != null && o.getAttributes() != null
                && this.attributes.size() <= o.getAttributes().size()) {
            OrderComparator.sort(this.attributes);
            OrderComparator.sort(o.getAttributes());
            for (int i = 0; i < this.attributes.size(); i++) {
                IAttribute a1 = this.attributes.get(i);
                IAttribute a2 = o.getAttributes().get(i);
                result += Integer.compare(a1.getOrder(), a2.getOrder());
                result += a1.getName().compareTo(a2.getName());
                result += a1.getValue().compareTo(a2.getValue());
            }
        }
        return result;
    }

    public static INode of(String tagName, IAttribute... attributes) {
        List<IAttribute> attributeList = new ArrayList<>(Arrays.asList(attributes));
        return new DefaultNode(tagName, attributeList);
    }

}
