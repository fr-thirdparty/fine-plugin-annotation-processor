package com.voc.fr.tool.annotation.design;

import com.voc.fr.tool.annotation.Module;
import com.voc.fr.tool.api.FineModule;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * HyperlinkProvider
 *
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2019/03/17 19:50
 */
@Retention(RetentionPolicy.SOURCE)
@Target({ElementType.TYPE})
@Module(FineModule.DESIGN)
public @interface HyperlinkProvider {

    /**
     * 超链接类型实现类
     *
     * @return Class
     */
    Class<?> value() default Void.class;

}
