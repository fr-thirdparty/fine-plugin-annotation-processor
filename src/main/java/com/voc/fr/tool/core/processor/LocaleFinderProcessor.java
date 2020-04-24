package com.voc.fr.tool.core.processor;

import com.voc.fr.tool.annotation.core.LocaleFinder;
import com.voc.fr.tool.api.impl.BaseAnnotationProcessor;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2019/03/15 11:16
 */
@Component
public class LocaleFinderProcessor extends BaseAnnotationProcessor {

    @Override
    public Class<? extends Annotation> getAnnotationClass() {
        return LocaleFinder.class;
    }

}
