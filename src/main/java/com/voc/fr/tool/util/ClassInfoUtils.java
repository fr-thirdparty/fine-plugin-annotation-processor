package com.voc.fr.tool.util;


import com.voc.fr.tool.api.IAttribute;
import com.voc.fr.tool.api.IClassInfoNode;
import com.voc.fr.tool.api.impl.DefaultAttribute;
import com.voc.fr.tool.api.impl.DefaultClassInfoNode;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2020/04/21 18:42
 */
public class ClassInfoUtils {

    public static IClassInfoNode of() {
        IAttribute attribute = DefaultAttribute.of("", "", 0);
        return DefaultClassInfoNode.of("", "");
    }

}
