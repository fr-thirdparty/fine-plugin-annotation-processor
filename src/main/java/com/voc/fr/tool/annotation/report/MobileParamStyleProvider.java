package com.voc.fr.tool.annotation.report;

import com.voc.fr.tool.annotation.EnableFinePlugin;
import com.voc.fr.tool.annotation.Module;
import com.voc.fr.tool.api.FineModule;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 扩展移动端参数面板样式，例如顶部、悬浮等
 *
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2019/08/31 17:09
 */
@Retention(RetentionPolicy.SOURCE)
@Target({ElementType.TYPE})
@Module(FineModule.REPORT)
@EnableFinePlugin(auto = true)
public @interface MobileParamStyleProvider {

    /**
     * @return Class
     */
    Class<?> value() default Void.class;

}
