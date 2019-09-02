package com.voc.fr.tool.annotation.core;

import com.voc.fr.tool.annotation.Module;
import com.voc.fr.tool.api.FineModule;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 对数据集初次取数的结果进行再加工（过滤、汇总、拆解、重构），
 * 甚至是对满足某些条件的数据集进行监控（消息、日志、邮件）等等场景
 *
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2019/08/31 16:42
 */
@Retention(RetentionPolicy.SOURCE)
@Target({ElementType.TYPE})
@Module(FineModule.CORE)
public @interface DSModifyProvider {

    /**
     * @return Class
     */
    Class<?> value() default Void.class;

}
