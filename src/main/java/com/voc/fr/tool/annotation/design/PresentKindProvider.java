package com.voc.fr.tool.annotation.design;

import com.voc.fr.tool.annotation.EnabledSupportedAnnotation;
import com.voc.fr.tool.annotation.Module;
import com.voc.fr.tool.api.FineModule;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 在设计器中提供更多类型的形态扩展
 *
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2019/03/29 17:27
 */
@Retention(RetentionPolicy.SOURCE)
@Target({ElementType.TYPE})
@Module(FineModule.DESIGN)
@EnabledSupportedAnnotation
public @interface PresentKindProvider {

    /**
     * 形态插件实现类
     *
     * @return Class
     */
    Class<?> value() default Void.class;

}
