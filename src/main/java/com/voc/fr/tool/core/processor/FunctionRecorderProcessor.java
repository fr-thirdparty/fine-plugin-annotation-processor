package com.voc.fr.tool.core.processor;

import com.fr.plugin.transform.FunctionRecorder;
import com.fr.record.analyzer.EnableMetrics;
import com.voc.fr.tool.api.FineVersion;
import com.voc.fr.tool.api.IClassInfoNode;
import com.voc.fr.tool.api.IPluginXmlContext;
import com.voc.fr.tool.api.impl.BaseAnnotationProcessor;
import com.voc.fr.tool.api.impl.DefaultClassInfoNode;
import org.springframework.stereotype.Component;

import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import java.lang.annotation.Annotation;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2020/04/21 17:56
 */
@Component
public class FunctionRecorderProcessor extends BaseAnnotationProcessor {

    @Override
    public Class<? extends Annotation> getAnnotationClass() {
        FineVersion fineVersion = pluginXmlContext.getFineVersion();
        switch (fineVersion) {
            case V8:
            case V9:
                return FunctionRecorder.class;
            case V10:
            default:
                return EnableMetrics.class;
        }
    }

    @Override
    protected void process4PluginXmlContext(IPluginXmlContext pluginXmlContext, Element element, TypeElement typeElement) {
        IClassInfoNode classInfoNode = DefaultClassInfoNode.of("function-recorder", element.asType().toString());
        pluginXmlContext.addFunctionRecorder(classInfoNode);
    }
}
