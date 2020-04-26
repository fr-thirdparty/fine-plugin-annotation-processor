package com.voc.fr.tool.core.processor;

import com.voc.fr.tool.annotation.design.PresentKindProvider;
import com.voc.fr.tool.api.impl.BaseAnnotationProcessor;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2019/09/01 11:20
 */
@Component
public class PresentKindProcessor extends BaseAnnotationProcessor {

    @Override
    public Class<? extends Annotation> getAnnotationClass() {
        return PresentKindProvider.class;
    }

}
