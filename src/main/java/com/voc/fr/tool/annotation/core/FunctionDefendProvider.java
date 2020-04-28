package com.voc.fr.tool.annotation.core;

import com.voc.fr.tool.annotation.EnableFinePlugin;
import com.voc.fr.tool.annotation.Module;
import com.voc.fr.tool.api.FineModule;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 拦截特定的函数执行，并返回自定义的函数，根据自定义函数的实现返回该值
 *
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2019/08/31 16:42
 */
@Retention(RetentionPolicy.SOURCE)
@Target({ElementType.TYPE})
@Module(FineModule.CORE)
@EnableFinePlugin(auto = true)
public @interface FunctionDefendProvider {

    /**
     * @return Class
     */
    Class<?> value() default Void.class;

}
