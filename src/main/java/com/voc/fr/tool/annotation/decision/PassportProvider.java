package com.voc.fr.tool.annotation.decision;

import com.voc.fr.tool.annotation.Module;
import com.voc.fr.tool.api.FineModule;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 用于提供除了用户名密码认证/ldap认证/http认证之外的用户自定义的认证方式
 *
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2019/08/31 16:53
 */
@Retention(RetentionPolicy.SOURCE)
@Target({ElementType.TYPE})
@Module(FineModule.DECISION)
public @interface PassportProvider {

    /**
     * @return Class
     */
    Class<?> value() default Void.class;

}
