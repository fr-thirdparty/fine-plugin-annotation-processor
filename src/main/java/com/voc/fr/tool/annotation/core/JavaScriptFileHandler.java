package com.voc.fr.tool.annotation.core;


import com.voc.fr.tool.annotation.EnabledSupportedAnnotation;
import com.voc.fr.tool.annotation.Module;
import com.voc.fr.tool.api.FineModule;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2019/03/13 08:52
 */
@Retention(RetentionPolicy.SOURCE)
@Target({ElementType.TYPE})
@Module(FineModule.CORE)
public @interface JavaScriptFileHandler {

    /**
     * 国际化实现类
     *
     * @return Class<?>[]
     */
    Class<?>[] value() default Void.class;

}
