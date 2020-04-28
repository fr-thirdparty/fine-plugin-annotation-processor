package com.voc.fr.tool.core;

import com.voc.fr.tool.api.IAttribute;
import com.voc.fr.tool.api.IClassInfoNode;
import com.voc.fr.tool.api.IPluginXmlContext;
import com.voc.fr.tool.api.impl.AbstractAnnotationProcessor;
import com.voc.fr.tool.api.impl.DefaultAttribute;
import com.voc.fr.tool.api.impl.DefaultClassInfoNode;
import com.voc.fr.tool.utils.AnnotationUtils;

import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.TypeElement;
import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2020/04/27 08:38
 */
public class ClassInfoAnnotationProcessor extends AbstractAnnotationProcessor {
    private final Class<? extends Annotation> annotation;

    @Override
    public Class<? extends Annotation> getAnnotationClass() {
        return this.annotation;
    }

    public ClassInfoAnnotationProcessor(Class<? extends Annotation> annotation) {
        this.annotation = annotation;
    }

    @Override
    public void process4PluginXmlContext(IPluginXmlContext pluginXmlContext, Element element, TypeElement typeElement) {
        if (element.getKind() == ElementKind.CLASS) {
            this.process4Class(pluginXmlContext, element, typeElement);
        }
    }


    protected void process4Class(IPluginXmlContext pluginXmlContext, Element element, TypeElement typeElement) {
        Map<String, Object> values = AnnotationUtils.getValues(element, getAnnotationClass());

        List<IClassInfoNode> infoNodes = annotationValue2ClassInfoNode(values, "value");

        String moduleTag = this.getModuleTag(typeElement);

        infoNodes.forEach(classInfoNode -> pluginXmlContext.addImplementation(moduleTag, classInfoNode));
    }

    /**
     * 注解信息转IClassInfoNode
     *
     * @param values   注解信息
     * @param classKey 类对应的键值
     * @return IClassInfoNode
     */
    protected List<IClassInfoNode> annotationValue2ClassInfoNode(Map<String, Object> values, String classKey) {
        IAttribute[] attributes = values.entrySet()
                .stream()
                .filter(entry -> !entry.getKey().equals(classKey))
                .map(entry -> DefaultAttribute.of(entry.getKey(), entry.getValue().toString(), 0))
                .toArray(IAttribute[]::new);

        List<String> canonicalNames = new ArrayList<>();
        Object object = values.get(classKey);
        if (object.getClass().isArray()) {
            Object[] arr = (Object[]) object;
            for (Object o : arr) {
                canonicalNames.add(o.toString());
            }
        } else {
            canonicalNames.add(object.toString());
        }
        return canonicalNames.stream()
                .map(canonicalName -> DefaultClassInfoNode.of(this.getAnnotationClass(), canonicalName, attributes))
                .collect(Collectors.toList());
    }

    ;
}
