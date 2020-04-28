package com.voc.fr.tool.annotation.decision;

import com.voc.fr.tool.annotation.EnableFinePlugin;
import com.voc.fr.tool.annotation.Module;
import com.voc.fr.tool.api.FineModule;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 用于决策平台自定义主题外观的接口，开发者可以使用该接口，实现多种多样的主题外观，符合每个用户不同的审美观
 *
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2019/08/31 16:53
 */
@Retention(RetentionPolicy.SOURCE)
@Target({ElementType.TYPE})
@Module(FineModule.DECISION)
@EnableFinePlugin(auto = true)
public @interface ThemeVariousProvider {

    /**
     * @return Class
     */
    Class<?> value() default Void.class;

}
