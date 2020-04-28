package com.voc.fr.tool.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 标记帆软开放接口
 *
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2020/04/27 11:53
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.ANNOTATION_TYPE})
public @interface EnableFinePlugin {
    /**
     * 注解处理器是否支持注解的处理
     *
     * @return boolean
     */
    boolean value() default true;

    /**
     * 是否自动注入实例
     *
     * @return boolean
     */
    boolean auto() default false;

    /**
     * 处理器执行优先级
     *
     * @return int
     */
    int priority() default 0;

}
