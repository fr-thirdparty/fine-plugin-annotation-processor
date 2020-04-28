package com.voc.fr.tool.annotation.plugin;

import com.voc.fr.tool.annotation.EnableFinePlugin;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2019/03/14 16:16
 */
@Retention(RetentionPolicy.SOURCE)
@Target({ElementType.TYPE, ElementType.METHOD})
@EnableFinePlugin
public @interface ChangeNotes {

    /**
     * 更新记录
     *
     * @return ChangeNote
     */
    ChangeNote[] value() default {};

}
