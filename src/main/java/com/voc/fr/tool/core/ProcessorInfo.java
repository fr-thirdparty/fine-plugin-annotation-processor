package com.voc.fr.tool.core;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.lang.annotation.Annotation;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2020/04/28 14:05
 */
@Getter
@Setter
@AllArgsConstructor
public class ProcessorInfo {
    private Class<? extends Annotation> annotation;

    private int priority;
}
