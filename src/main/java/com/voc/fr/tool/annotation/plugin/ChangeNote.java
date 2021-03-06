package com.voc.fr.tool.annotation.plugin;

import com.voc.fr.tool.annotation.EnableFinePlugin;

import java.lang.annotation.*;

/**
 * 更新日志标签
 *
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2019/03/12 21:57
 */
@Retention(RetentionPolicy.SOURCE)
@Target({ElementType.TYPE, ElementType.METHOD})
@Repeatable(ChangeNotes.class)
@EnableFinePlugin
public @interface ChangeNote {

    /**
     * 修改日期
     *
     * @return 日期字符串
     */
    String dateOf() default "";

    /**
     * 日期格式
     *
     * @return 日期格式字符串
     */
    String format() default "yyyy-MM-dd";

    /**
     * 改动内容
     *
     * @return 更新日志内容数组
     */
    String[] contents() default {};

}
