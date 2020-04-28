package com.voc.fr.tool.annotation.report;

import com.voc.fr.tool.annotation.EnableFinePlugin;
import com.voc.fr.tool.annotation.Module;
import com.voc.fr.tool.api.FineModule;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 接口只用于模板消息插件，给微信/钉钉等插件使用来实现发消息
 *
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2019/08/31 17:09
 */
@Retention(RetentionPolicy.SOURCE)
@Target({ElementType.TYPE})
@Module(FineModule.REPORT)
@EnableFinePlugin(auto = true)
public @interface MessageObjectProvider {

    /**
     * @return Class
     */
    Class<?> value() default Void.class;

}
