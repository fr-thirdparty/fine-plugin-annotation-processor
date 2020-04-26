package com.voc.fr.tool.core.processor;

import com.voc.fr.tool.annotation.design.TableDataDefineProvider;
import com.voc.fr.tool.api.impl.BaseAnnotationProcessor;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2019/09/01 10:50
 */
@Component
public class TableDataProcessor extends BaseAnnotationProcessor {

    @Override
    public Class<? extends Annotation> getAnnotationClass() {
        return TableDataDefineProvider.class;
    }

}
