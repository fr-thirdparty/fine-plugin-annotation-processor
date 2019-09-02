package com.voc.fr.tool.annotation.core;

import com.voc.fr.tool.annotation.Module;
import com.voc.fr.tool.api.FineModule;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 插件中需要向服务器端发起web请求，可以使用这个接口来实现
 *
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2019/08/31 16:42
 */
@Retention(RetentionPolicy.SOURCE)
@Target({ElementType.TYPE})
@Module(FineModule.CORE)
public @interface HttpHandlerProvider {

    /**
     * @return Class
     */
    Class<?> value() default Void.class;

}
