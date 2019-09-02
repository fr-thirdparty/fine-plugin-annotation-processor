package com.voc.fr.tool.api;


/**
 * 接口实现类信息
 *
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2019/08/30 16:57
 */
public interface IClassInfo extends INode {

    /**
     * 实现类的完全限定名
     *
     * @return String
     */
    String getCanonicalName();

}
