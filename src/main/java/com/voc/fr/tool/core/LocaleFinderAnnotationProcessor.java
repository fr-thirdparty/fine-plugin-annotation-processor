package com.voc.fr.tool.core;

import com.voc.fr.tool.annotation.core.LocaleFinder;
import com.voc.fr.tool.api.IPluginXmlContext;
import com.voc.fr.tool.api.impl.BaseAnnotationProcessor;
import com.voc.fr.tool.api.impl.DefaultClassInfo;
import com.voc.fr.tool.util.AnnotationValueUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.TypeElement;
import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2019/03/15 11:16
 */
@Component
public class LocaleFinderAnnotationProcessor extends BaseAnnotationProcessor {

    @Override
    public Class<? extends Annotation> getAnnotationClass() {
        return LocaleFinder.class;
    }

    @Override
    public void process(IPluginXmlContext pluginXmlContext, Element element, TypeElement typeElement, ProcessingEnvironment processingEnv) {
        String xmlTag = this.getXmlTag(element, typeElement, LocaleFinder.class);
        if (logger.isDebugEnabled() && StringUtils.isNotEmpty(xmlTag)) {
            logger.debug("XmlTag: {}", xmlTag);
        }

        if (element.getKind() == ElementKind.CLASS) {
            List<String> value = new ArrayList<>();
            Object obj = AnnotationValueUtils.getQualifiedClassName(element, LocaleFinder.class, "value");
            if (obj instanceof List) {
                List list = (List) obj;
                for (Object o : list) {
                    value.add(o.toString());
                }
            } else {
                value.add(element.asType().toString());
            }

            if (logger.isDebugEnabled()) {
                logger.debug("LocaleFinder：{}", value);
            }

            value.forEach(
                    s -> pluginXmlContext.addImplementation(xmlTag, DefaultClassInfo.of(LocaleFinder.class, s))
            );

        }
    }

}
