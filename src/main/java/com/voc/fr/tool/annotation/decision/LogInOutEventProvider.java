package com.voc.fr.tool.annotation.decision;

import com.voc.fr.tool.annotation.Module;
import com.voc.fr.tool.api.FineModule;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 监听登录登出事件，允许在登录、登出后自定义功能
 *
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2019/08/31 16:53
 */
@Retention(RetentionPolicy.SOURCE)
@Target({ElementType.TYPE})
@Module(FineModule.DECISION)
public @interface LogInOutEventProvider {

    /**
     * @return Class
     */
    Class<?> value() default Void.class;

}
