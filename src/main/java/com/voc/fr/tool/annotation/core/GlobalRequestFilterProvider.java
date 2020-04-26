package com.voc.fr.tool.annotation.core;

import com.voc.fr.tool.annotation.EnabledSupportedAnnotation;
import com.voc.fr.tool.annotation.Module;
import com.voc.fr.tool.api.FineModule;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 提供和过滤器（Filter）同等的效果，可以直接在插件代码中使用，不必要配置web.xml，
 * 和EmbedRequestFilterProvider接口的区别在于，可以自由添加url-pattern。
 *
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2019/08/31 16:42
 */
@Retention(RetentionPolicy.SOURCE)
@Target({ElementType.TYPE})
@Module(FineModule.CORE)
@EnabledSupportedAnnotation
public @interface GlobalRequestFilterProvider {

    /**
     * @return Class
     */
    Class<?> value() default Void.class;

}
