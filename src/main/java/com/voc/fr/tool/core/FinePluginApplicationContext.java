package com.voc.fr.tool.core;

import com.voc.fr.tool.api.impl.DefaultPluginXmlContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.annotation.processing.ProcessingEnvironment;

import static com.voc.fr.tool.api.impl.AbstractAnnotationProcessor.PLUGIN_XML_CONTEXT_PROPERTY;
import static com.voc.fr.tool.api.impl.AbstractAnnotationProcessor.PROCESSING_ENV_PROPERTY;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2020/04/28 09:29
 */
public class FinePluginApplicationContext extends AnnotationConfigApplicationContext {

    public FinePluginApplicationContext(ProcessingEnvironment processingEnv, Class<?>... annotatedClasses) {
        super();
        this.getBeanFactory().registerSingleton(PROCESSING_ENV_PROPERTY, processingEnv);
        this.getBeanFactory().registerSingleton(PLUGIN_XML_CONTEXT_PROPERTY, new DefaultPluginXmlContext());
        super.register(annotatedClasses);
        super.refresh();
    }

}
