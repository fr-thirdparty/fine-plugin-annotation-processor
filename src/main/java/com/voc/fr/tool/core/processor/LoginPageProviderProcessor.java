package com.voc.fr.tool.core.processor;

import com.voc.fr.tool.annotation.decision.LoginPageProvider;
import com.voc.fr.tool.api.impl.BaseAnnotationProcessor;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2020/04/21 16:17
 */
@Component
public class LoginPageProviderProcessor extends BaseAnnotationProcessor {

    @Override
    public Class<? extends Annotation> getAnnotationClass() {
        return LoginPageProvider.class;
    }

}
