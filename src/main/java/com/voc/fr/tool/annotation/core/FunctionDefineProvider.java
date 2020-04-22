package com.voc.fr.tool.annotation.core;


import com.voc.fr.tool.annotation.EnabledSupportedAnnotation;
import com.voc.fr.tool.annotation.Module;
import com.voc.fr.tool.api.FineModule;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 函数扩展的接口
 *
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2019/03/13 09:06
 */
@Retention(RetentionPolicy.SOURCE)
@Target({ElementType.TYPE})
@Module(FineModule.CORE)
@EnabledSupportedAnnotation
public @interface FunctionDefineProvider {

    /**
     * 函数实现类
     *
     * @return Class
     */
    Class<?> value() default Void.class;

    /**
     * 函数名称
     *
     * @return String
     */
    String name();

    /**
     * 函数描述
     *
     * @return String
     */
    String description() default "";

}
