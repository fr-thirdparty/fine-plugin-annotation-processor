package com.voc.fr.tool.annotation.decision;

import com.voc.fr.tool.annotation.EnableFinePlugin;
import com.voc.fr.tool.annotation.Module;
import com.voc.fr.tool.api.FineModule;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 监听平台初始化事件，用于在初始化前（前端初始化页面出现时）做一些操作
 *
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2019/08/31 16:53
 */
@Retention(RetentionPolicy.SOURCE)
@Target({ElementType.TYPE})
@Module(FineModule.DECISION)
@EnableFinePlugin(auto = true)
public @interface DecisionInitEventProvider {

    /**
     * @return Class
     */
    Class<?> value() default Void.class;

}
