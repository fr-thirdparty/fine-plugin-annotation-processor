package com.voc.fr.tool.core;

import com.voc.fr.tool.annotation.plugin.ChangeNote;
import com.voc.fr.tool.api.IPluginXmlContext;
import com.voc.fr.tool.api.impl.BaseAnnotationProcessor;
import com.voc.fr.tool.util.ChangeNoteUtils;
import org.springframework.stereotype.Component;

import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.TypeElement;
import java.lang.annotation.Annotation;

/**
 * Created with IntelliJ IDEA.
 *
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2019/03/15 10:01
 */
@Component
public class ChangeNoteAnnotationProcessor extends BaseAnnotationProcessor {

    @Override
    public Class<? extends Annotation> getAnnotationClass() {
        return ChangeNote.class;
    }

    @Override
    public void process(IPluginXmlContext pluginXmlContext, Element element, TypeElement typeElement, ProcessingEnvironment processingEnv) {
        if (element.getKind() == ElementKind.CLASS || element.getKind() == ElementKind.METHOD) {
            ChangeNote changeNote = element.getAnnotation(ChangeNote.class);
            ChangeNoteUtils.resolver(changeNote, pluginXmlContext);
        }
    }

}
