package com.voc.fr.tool.core.processor;

import com.voc.fr.tool.annotation.plugin.ChangeNotes;
import com.voc.fr.tool.api.IPluginXmlContext;
import com.voc.fr.tool.api.impl.BaseAnnotationProcessor;
import com.voc.fr.tool.util.AnnotationValueUtils;
import org.springframework.stereotype.Component;

import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.TypeElement;
import java.lang.annotation.Annotation;
import java.util.Map;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2019/08/31 23:05
 */
@Component
public class ChangeNotesProcessor extends BaseAnnotationProcessor {

    @Override
    public Class<? extends Annotation> getAnnotationClass() {
        return ChangeNotes.class;
    }

    @Override
    protected void process4PluginXmlContext(IPluginXmlContext pluginXmlContext, Element element, TypeElement typeElement) {
        if (element.getKind() == ElementKind.CLASS || element.getKind() == ElementKind.METHOD) {
            Map<String, Object> changeNotes = AnnotationValueUtils.getAllReflectedValues(element, getAnnotationClass());
//            if (changeNotes.value().length > 0) {
//                for (ChangeNote changeNote : changeNotes.value()) {
//                    ChangeNoteUtils.resolver(changeNote, pluginXmlContext);
//                }
//            }
        }
    }

//    @Override
//    public void process(IPluginXmlContext pluginXmlContext, Element element, TypeElement typeElement, ProcessingEnvironment processingEnv) {
//        if (element.getKind() == ElementKind.CLASS || element.getKind() == ElementKind.METHOD) {
//            ChangeNotes changeNotes = element.getAnnotation(ChangeNotes.class);
//            if (changeNotes.value().length > 0) {
//                for (ChangeNote changeNote : changeNotes.value()) {
//                    ChangeNoteUtils.resolver(changeNote, pluginXmlContext);
//                }
//            }
//        }
//    }

}
