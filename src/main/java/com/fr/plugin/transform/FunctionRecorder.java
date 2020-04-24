package com.fr.plugin.transform;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2020/04/24 20:05
 */
@Retention(RetentionPolicy.CLASS)
@Target({ElementType.TYPE})
public @interface FunctionRecorder {
    String id() default "";

    String localeKey() default "";
}
