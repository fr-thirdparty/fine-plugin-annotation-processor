package com.voc.fr.tool.annotation.plugin;

import com.voc.fr.tool.annotation.EnabledSupportedAnnotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2019/03/18 18:45
 */
@Retention(RetentionPolicy.SOURCE)
@Target({ElementType.TYPE})
@EnabledSupportedAnnotation
public @interface PluginInfo {

    /**
     * 插件ID
     */
    String id() default "com.voc.fr.plugin.demo";

    /**
     * 插件名称
     */
    String name() default "Demo";

    /**
     * 插件售价
     */
    int price() default 0;

    /**
     * 插件是否在插件管理器中隐藏
     *
     * @return boolean
     */
    boolean hidden() default false;

    /**
     * 插件是否启用
     */
    boolean active() default true;

    /**
     * 插件版本（此项值为空则从编译参数中获取，否则使用此值）
     */
    String version() default "";

    /**
     * 插件对应报表版本
     */
    String envVersion() default "8.0";

    /**
     * 插件开发者
     */
    String vendor() default "coffee377";

    /**
     * 插件需要指定的日期之后的jar包
     */
    String jarTime() default "2017-06-10";

    /**
     * 插件的功能描述
     */
    String description() default "演示插件";

    /**
     * 添加了功能点记录的类
     *
     * @since 9.0
     */
    Class[] functionRecorder() default {};

    /**
     * 主包名，需要涵盖所有在xml中描述的类
     *
     * @since 9.0
     */
    String mainPackage() default "";

}
