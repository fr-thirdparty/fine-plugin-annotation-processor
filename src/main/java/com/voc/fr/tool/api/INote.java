package com.voc.fr.tool.api;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2019/08/30 18:10
 */
public interface INote extends Comparable<INote> {

    /**
     * 日期
     *
     * @return String
     */
    String getDateOf();

    /**
     * 版本
     *
     * @return String
     */
    String getVersion();

    /**
     * 内容
     *
     * @return String
     */
    String getContent();

}
