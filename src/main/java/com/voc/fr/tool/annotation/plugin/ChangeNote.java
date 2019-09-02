package com.voc.fr.tool.annotation.plugin;

import com.voc.fr.tool.annotation.EnabledSupportedAnnotation;

import java.lang.annotation.*;

/**
 * 更新日志标签
 * Created with IntelliJ IDEA.
 *
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2019/03/12 21:57
 */
@Retention(RetentionPolicy.SOURCE)
@Target({ElementType.TYPE, ElementType.METHOD})
@Repeatable(ChangeNotes.class)
@EnabledSupportedAnnotation
public @interface ChangeNote {

    /**
     * 修改日期
     *
     * @return 日期字符串
     */
    String dateOf() default "";

    /**
     * 版本
     *
     * @return 版本号
     */
    String version() default "";

    /**
     * 改动内容
     *
     * @return 更新日志内容数组
     */
    String[] content() default {};

}
