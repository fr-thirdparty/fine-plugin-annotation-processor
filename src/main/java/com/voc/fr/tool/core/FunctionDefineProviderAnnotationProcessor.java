package com.voc.fr.tool.core;

import com.voc.fr.tool.annotation.core.FunctionDefineProvider;
import com.voc.fr.tool.api.IPluginXmlContext;
import com.voc.fr.tool.api.impl.BaseAnnotationProcessor;
import com.voc.fr.tool.api.impl.DefaultAttribute;
import com.voc.fr.tool.api.impl.DefaultClassInfo;
import org.springframework.stereotype.Component;

import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.DeclaredType;
import javax.lang.model.type.MirroredTypeException;
import java.lang.annotation.Annotation;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2019/08/31 23:19
 */
@Component
public class FunctionDefineProviderAnnotationProcessor extends BaseAnnotationProcessor {

    @Override
    public Class<? extends Annotation> getAnnotationClass() {
        return FunctionDefineProvider.class;
    }

    @Override
    public void process(IPluginXmlContext pluginXmlContext, Element element, TypeElement typeElement, ProcessingEnvironment processingEnv) {
        String xmlTag = this.getXmlTag(element, typeElement, FunctionDefineProvider.class);
        if (logger.isDebugEnabled()) {
            logger.debug("XmlTag: {}", xmlTag);
        }

        if (element.getKind() == ElementKind.CLASS) {
            FunctionDefineProvider function = element.getAnnotation(FunctionDefineProvider.class);
            String qualifiedClassName;

            try {
                Class<?> clazz = function.value();
                qualifiedClassName = clazz.getCanonicalName();
            } catch (MirroredTypeException mte) {
                DeclaredType classTypeMirror = (DeclaredType) mte.getTypeMirror();
                TypeElement classTypeElement = (TypeElement) classTypeMirror.asElement();
                qualifiedClassName = classTypeElement.getQualifiedName().toString();
            }

            /*如果 value 为 Void.class，则 value 值取当前被标记类元素的类完全限定名*/
            if (Void.class.getCanonicalName().equals(qualifiedClassName)) {
                qualifiedClassName = element.asType().toString();
            }

            String value = qualifiedClassName;
            String name = function.name();
            String description = function.description();

            if (logger.isDebugEnabled()) {
                logger.debug("函数实现类：{} 函数名：{} 函数描述：{}", value, name, description);
            }

            pluginXmlContext.addImplementation(xmlTag, DefaultClassInfo.of(FunctionDefineProvider.class, value,
                    DefaultAttribute.of("name", name, 0),
                    DefaultAttribute.of("description", description, 1)));
//            Set<ClassInfo> classInfos = this.getContext().getInterfaceImpl().fromEnvVersion(xmlTag);
//            logger.error("{}",classInfos);
        }
    }

}
