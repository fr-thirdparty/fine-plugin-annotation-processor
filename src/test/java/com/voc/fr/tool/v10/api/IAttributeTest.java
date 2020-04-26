package com.voc.fr.tool.v10.api;

import com.voc.fr.tool.api.IAttribute;
import com.voc.fr.tool.api.impl.DefaultAttribute;
import org.junit.Test;
import org.springframework.core.OrderComparator;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2019/08/30 17:51
 */
public class IAttributeTest {

    @Test
    public void order() {
        List<IAttribute> attributes = new ArrayList<>();

        attributes.add(DefaultAttribute.of("name", "1", 4));
        attributes.add(DefaultAttribute.of("name", "2", 3));
        attributes.add(DefaultAttribute.of("name", "3", 2));
        attributes.add(DefaultAttribute.of("name", "4", 1));

        OrderComparator.sort(attributes);

        System.out.println(attributes);
    }

}
