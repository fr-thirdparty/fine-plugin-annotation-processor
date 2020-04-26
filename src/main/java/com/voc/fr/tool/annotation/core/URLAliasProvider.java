package com.voc.fr.tool.annotation.core;

import com.voc.fr.tool.annotation.EnabledSupportedAnnotation;
import com.voc.fr.tool.annotation.Module;
import com.voc.fr.tool.api.FineModule;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 简化插件中web请求接口使用的url，同时可以让请求的url不依赖于插件id
 *
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2019/08/31 16:42
 */
@Retention(RetentionPolicy.SOURCE)
@Target({ElementType.TYPE})
@Module(FineModule.CORE)
@EnabledSupportedAnnotation
public @interface URLAliasProvider {

    /**
     * @return Class
     */
    Class<?> value() default Void.class;

}
