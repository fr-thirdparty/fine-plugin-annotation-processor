package com.fr.record.analyzer;

import java.lang.annotation.*;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2020/04/24 19:50
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Inherited
public @interface EnableMetrics {
}
