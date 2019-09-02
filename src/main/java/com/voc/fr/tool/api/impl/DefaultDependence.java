package com.voc.fr.tool.api.impl;

import com.voc.fr.tool.api.IAttribute;
import com.voc.fr.tool.api.IDependence;
import com.voc.fr.tool.api.INode;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2019/09/02 17:27
 */
@Getter
@Setter

public class DefaultDependence implements IDependence {

    private List<INode> items;

    @Override
    public List<INode> getItems() {
        return this.items;
    }

    @Override
    public void addItem(INode node) {
        if (this.items == null) {
            this.items = new ArrayList<>();
        }
        this.items.add(node);
    }

    @Override
    public void addItem(String type, String key) {
        IAttribute typeAttribute = DefaultAttribute.of("type", type, 1);
        IAttribute keyAttribute = DefaultAttribute.of("key", key, 2);
        INode node = DefaultNode.of(IDependence.DEPENDENCE_ITEM_TAG, typeAttribute, keyAttribute);
        this.addItem(node);
    }
}
