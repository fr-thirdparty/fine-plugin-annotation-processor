package com.voc.fr.tool.annotation.decision;

import com.voc.fr.tool.annotation.EnableFinePlugin;
import com.voc.fr.tool.annotation.Module;
import com.voc.fr.tool.api.FineModule;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 用于决策平台自定义登录页的接口，开发着可以使用他来自定义自己想要的登录页
 *
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2019/08/31 16:53
 */
@Retention(RetentionPolicy.SOURCE)
@Target({ElementType.TYPE})
@Module(FineModule.DECISION)
@EnableFinePlugin(auto = true)
public @interface LoginPageProvider {

    /**
     * @return Class
     */
    Class<?> value() default Void.class;

}
