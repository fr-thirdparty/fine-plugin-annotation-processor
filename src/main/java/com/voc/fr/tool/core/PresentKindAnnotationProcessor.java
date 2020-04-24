//package com.voc.fr.tool.core;
//
//import com.voc.fr.tool.annotation.design.PresentKindProvider;
//import com.voc.fr.tool.api.IPluginXmlContext;
//import com.voc.fr.tool.api.impl.BaseAnnotationProcessor;
//import com.voc.fr.tool.api.impl.DefaultClassInfoNode;
//import org.springframework.stereotype.Component;
//
//import javax.annotation.processing.ProcessingEnvironment;
//import javax.lang.model.element.Element;
//import javax.lang.model.element.ElementKind;
//import javax.lang.model.element.TypeElement;
//import javax.lang.model.type.DeclaredType;
//import javax.lang.model.type.MirroredTypeException;
//import java.lang.annotation.Annotation;
//
///**
// * @author Wu Yujie
// * @email coffee377@dingtalk.com
// * @time 2019/09/01 11:20
// */
//@Component
//public class PresentKindAnnotationProcessor extends BaseAnnotationProcessor {
//
//    @Override
//    public Class<? extends Annotation> getAnnotationClass() {
//        return PresentKindProvider.class;
//    }
//
//    @Override
//    public void process(IPluginXmlContext pluginXmlContext, Element element, TypeElement typeElement, ProcessingEnvironment processingEnv) {
//        String xmlTag = this.getXmlTag(element, typeElement, PresentKindProvider.class);
//        if (logger.isDebugEnabled()) {
//            logger.debug("XmlTag: {}", xmlTag);
//        }
//
//        if (element.getKind() == ElementKind.CLASS) {
//            PresentKindProvider present = element.getAnnotation(PresentKindProvider.class);
//
//            String qualifiedClassName;
//
//            try {
//                Class<?> clazz = present.value();
//                qualifiedClassName = clazz.getCanonicalName();
//            } catch (MirroredTypeException mte) {
//                DeclaredType classTypeMirror = (DeclaredType) mte.getTypeMirror();
//                TypeElement classTypeElement = (TypeElement) classTypeMirror.asElement();
//                qualifiedClassName = classTypeElement.getQualifiedName().toString();
//            }
//
//            /*如果 value 为 Void.class，则 value 值取当前被标记类元素的类完全限定名*/
//            if (Void.class.getCanonicalName().equals(qualifiedClassName)) {
//                qualifiedClassName = element.asType().toString();
//            }
//            String value = qualifiedClassName;
//
//            logger.debug("形态实现类：{}", value);
//
//            pluginXmlContext.addImplementation(xmlTag, DefaultClassInfoNode.of(PresentKindProvider.class, value));
//
//        }
//    }
//
//}
