package com.voc.fr.tool.annotation.core;

import com.voc.fr.tool.annotation.EnabledSupportedAnnotation;
import com.voc.fr.tool.annotation.Module;
import com.voc.fr.tool.api.FineModule;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 可以根据需要在 finedb 中新建表并进行数据的读写
 *
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2019/08/31 16:42
 */
@Retention(RetentionPolicy.SOURCE)
@Target({ElementType.TYPE})
@Module(FineModule.CORE)
@EnabledSupportedAnnotation
public @interface DBAccessProvider {

    /**
     * @return Class
     */
    Class<?> value() default Void.class;

}
