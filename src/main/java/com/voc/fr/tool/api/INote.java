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
     * 日期格式
     *
     * @return String
     */
    String getFormat();

    /**
     * 内容数组
     *
     * @return String[]
     */
    String[] getContents();

    /**
     * 内容
     *
     * @return String
     */
    String getContent();

}
