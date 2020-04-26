package com.voc.fr.tool.annotation.core;

import com.voc.fr.tool.annotation.EnabledSupportedAnnotation;
import com.voc.fr.tool.annotation.Module;
import com.voc.fr.tool.api.FineModule;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 希望在导出操作之前，加入一些自定义操作，可以用这个接口实现。比如导出的文件进行自定义加密
 *
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2019/08/31 16:42
 */
@Retention(RetentionPolicy.SOURCE)
@Target({ElementType.TYPE})
@Module(FineModule.CORE)
@EnabledSupportedAnnotation
public @interface ExportHandleProvider {

    /**
     * @return Class
     */
    Class<?> value() default Void.class;

}
