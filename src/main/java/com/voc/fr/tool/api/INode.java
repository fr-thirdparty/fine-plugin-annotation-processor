package com.voc.fr.tool.api;

import javax.annotation.Nullable;
import java.util.List;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2019/09/02 16:32
 */
public interface INode extends Comparable<INode> {

    /**
     * 接口标签名称
     *
     * @return String
     */
    String getTagName();

    /**
     * 获取接口其他属性
     *
     * @return IAttribute
     */
    @Nullable
    List<IAttribute> getAttributes();

    /**
     * 添加属性
     *
     * @param attribute IAttribute
     */
    void addAttributes(IAttribute attribute);

}
