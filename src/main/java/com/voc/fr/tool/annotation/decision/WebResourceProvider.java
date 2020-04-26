package com.voc.fr.tool.annotation.decision;

import com.voc.fr.tool.annotation.EnabledSupportedAnnotation;
import com.voc.fr.tool.annotation.Module;
import com.voc.fr.tool.api.FineModule;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 用于给已有的web组件，增加附加的JS或CSS的的接口
 *
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2019/08/31 16:53
 */
@Retention(RetentionPolicy.SOURCE)
@Target({ElementType.TYPE})
@Module(FineModule.DECISION)
@EnabledSupportedAnnotation
public @interface WebResourceProvider {

    /**
     * @return Class
     */
    Class<?> value() default Void.class;

}
