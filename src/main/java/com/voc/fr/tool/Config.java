package com.voc.fr.tool;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2019/08/31 20:00
 */
@Configuration
@ComponentScan(value = {"com.voc.fr.tool"})
public class Config {

    public static final String[] FINE_FUNCTION_RECORDER = {
            "com.fr.record.analyzer.EnableMetrics",
            "com.fr.plugin.transform.FunctionRecorder"
    };


}
