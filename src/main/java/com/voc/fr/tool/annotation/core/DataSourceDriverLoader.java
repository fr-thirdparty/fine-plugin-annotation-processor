package com.voc.fr.tool.annotation.core;

import com.voc.fr.tool.annotation.EnableFinePlugin;
import com.voc.fr.tool.annotation.Module;
import com.voc.fr.tool.api.FineModule;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 为解决大数据套件的冲突问题，提供一个拦截器接口用于拦截目标驱动的
 * classLoader 直接设置到dataSource中
 *
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2019/08/31 16:37
 */
@Retention(RetentionPolicy.SOURCE)
@Target({ElementType.TYPE})
@Module(FineModule.CORE)
@EnableFinePlugin(auto = true)
public @interface DataSourceDriverLoader {

    /**
     * @return Class
     */
    Class<?> value() default Void.class;

}
