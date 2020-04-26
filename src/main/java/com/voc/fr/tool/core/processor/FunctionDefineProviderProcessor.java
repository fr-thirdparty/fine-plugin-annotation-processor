package com.voc.fr.tool.core.processor;

import com.voc.fr.tool.annotation.core.FunctionDefineProvider;
import com.voc.fr.tool.api.impl.BaseAnnotationProcessor;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2019/08/31 23:19
 */
@Component
public class FunctionDefineProviderProcessor extends BaseAnnotationProcessor {

    @Override
    public Class<? extends Annotation> getAnnotationClass() {
        return FunctionDefineProvider.class;
    }

}
