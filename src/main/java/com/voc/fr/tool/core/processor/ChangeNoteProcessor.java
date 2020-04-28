package com.voc.fr.tool.core.processor;

import com.voc.fr.tool.annotation.plugin.ChangeNote;
import com.voc.fr.tool.api.IPluginXmlContext;
import com.voc.fr.tool.api.impl.AbstractAnnotationProcessor;
import com.voc.fr.tool.utils.ChangeNoteUtils;
import org.springframework.stereotype.Component;

import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.TypeElement;
import java.lang.annotation.Annotation;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2019/03/15 10:01
 */
@Component
public class ChangeNoteProcessor extends AbstractAnnotationProcessor {

    @Override
    public Class<? extends Annotation> getAnnotationClass() {
        return ChangeNote.class;
    }

    @Override
    public void process4PluginXmlContext(IPluginXmlContext pluginXmlContext, Element element, TypeElement typeElement) {
        if (element.getKind() == ElementKind.CLASS || element.getKind() == ElementKind.METHOD) {
            ChangeNoteUtils.resolverChangeNote(pluginXmlContext, element);
        }
    }

}
