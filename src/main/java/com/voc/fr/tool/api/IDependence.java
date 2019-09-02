package com.voc.fr.tool.api;

import java.util.List;

/**
 * 插件之间也依赖
 *
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2019/09/02 15:37
 */
public interface IDependence {

    /**
     * 依赖标签
     */
    String DEPENDENCE_TAG = "dependence";

    /**
     * 依赖项目标签
     */
    String DEPENDENCE_ITEM_TAG = "item";

    /**
     * 获取依赖项
     *
     * @return INode
     */
    List<INode> getItems();

    /**
     * 添加依赖项
     *
     * @param node INode
     */
    void addItem(INode node);

    /**
     * 添加依赖项
     *
     * @param type String
     * @param key  String
     */
    void addItem(String type, String key);

}
