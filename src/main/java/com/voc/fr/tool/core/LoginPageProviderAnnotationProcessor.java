package com.voc.fr.tool.core;

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
public class LoginPageProviderAnnotationProcessor extends BaseAnnotationProcessor {

    @Override
    public Class<? extends Annotation> getAnnotationClass() {
        return LoginPageProvider.class;
    }

//    @Override
//    public void process(IPluginXmlContext pluginXmlContext, Element element, TypeElement typeElement, ProcessingEnvironment processingEnv) {
//        String xmlTag = this.getXmlTag(element, typeElement, LoginPageProvider.class);
//        if (element.getKind() == ElementKind.CLASS) {
//            LoginPageProvider provider = element.getAnnotation(LoginPageProvider.class);
//            String qualifiedClassName;
//
//            try {
//                Class<?> clazz = provider.value();
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
//
//            String value = qualifiedClassName;
//
//            pluginXmlContext.addImplementation(xmlTag, DefaultClassInfo.of(LoginPageProvider.class, value));
//        }
//    }
}
