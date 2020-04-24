//package com.voc.fr.tool.core;
//
//import com.voc.fr.tool.annotation.design.ServerTableDataDefineProvider;
//import com.voc.fr.tool.annotation.design.TableDataDefineProvider;
//import com.voc.fr.tool.api.IPluginXmlContext;
//import com.voc.fr.tool.api.impl.BaseAnnotationProcessor;
//import com.voc.fr.tool.api.impl.DefaultClassInfoNode;
//import com.voc.fr.tool.util.AnnotationValueUtils;
//import org.springframework.stereotype.Component;
//
//import javax.annotation.processing.ProcessingEnvironment;
//import javax.lang.model.element.Element;
//import javax.lang.model.element.ElementKind;
//import javax.lang.model.element.TypeElement;
//import java.lang.annotation.Annotation;
//
///**
// * @author Wu Yujie
// * @email coffee377@dingtalk.com
// * @time 2019/09/01 10:50
// */
//@Component
//public class TableDataAnnotationProcessor extends BaseAnnotationProcessor {
//
//    @Override
//    public Class<? extends Annotation> getAnnotationClass() {
//        return TableDataDefineProvider.class;
//    }
//
//    @Override
//    public boolean support(TypeElement annotation) {
//        return super.support(annotation) || this.support(annotation, ServerTableDataDefineProvider.class);
//    }
//
//    @Override
//    public void process(IPluginXmlContext pluginXmlContext, Element element, TypeElement typeElement, ProcessingEnvironment processingEnv) {
//        String xmlTag = this.getXmlTag(element, typeElement, TableDataDefineProvider.class);
//        if (logger.isDebugEnabled()) {
//            logger.debug("XmlTag: {}", xmlTag);
//        }
//
//        if (element.getKind() == ElementKind.CLASS) {
//            TableDataDefineProvider tableData = element.getAnnotation(TableDataDefineProvider.class);
//            if (tableData != null) {
//                String qualifiedClassName;
//                Object obj = AnnotationValueUtils.getQualifiedClassName(element, TableDataDefineProvider.class, "value");
//                if (obj != null) {
//                    qualifiedClassName = obj.toString();
//                } else {
//                    qualifiedClassName = element.asType().toString();
//                }
//                xmlTag = this.getXmlTag(element, typeElement, TableDataDefineProvider.class);
//                pluginXmlContext.addImplementation(xmlTag, DefaultClassInfoNode.of(TableDataDefineProvider.class, qualifiedClassName));
//            }
//            ServerTableDataDefineProvider serverTableData = element.getAnnotation(ServerTableDataDefineProvider.class);
//            if (serverTableData != null) {
//                String qualifiedClassName;
//                Object obj = AnnotationValueUtils.getQualifiedClassName(element, ServerTableDataDefineProvider.class, "value");
//                if (obj != null) {
//                    qualifiedClassName = obj.toString();
//                } else {
//                    qualifiedClassName = element.asType().toString();
//                }
//                xmlTag = this.getXmlTag(element, typeElement, TableDataDefineProvider.class);
//                pluginXmlContext.addImplementation(xmlTag, DefaultClassInfoNode.of(ServerTableDataDefineProvider.class, qualifiedClassName));
//            }
//        }
//    }
//
//}
