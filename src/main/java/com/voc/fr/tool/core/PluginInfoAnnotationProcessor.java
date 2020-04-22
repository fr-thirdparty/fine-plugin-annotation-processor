package com.voc.fr.tool.core;

import com.voc.fr.tool.annotation.plugin.PluginInfo;
import com.voc.fr.tool.api.FineVersion;
import com.voc.fr.tool.api.IPluginBaseInfo;
import com.voc.fr.tool.api.IPluginXmlContext;
import com.voc.fr.tool.api.impl.BaseAnnotationProcessor;
import com.voc.fr.tool.util.FineVersionHelp;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.Ordered;
import org.springframework.core.PriorityOrdered;
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
 * @time 2019/04/02 11:32
 */
@Component
public class PluginInfoAnnotationProcessor extends BaseAnnotationProcessor implements PriorityOrdered {

    @Override
    public Class<? extends Annotation> getAnnotationClass() {
        return PluginInfo.class;
    }

    @Override
    public void process(IPluginXmlContext pluginXmlContext, Element element, TypeElement typeElement, ProcessingEnvironment processingEnv) {
        if (element.getKind() == ElementKind.CLASS) {
            PluginInfo info = element.getAnnotation(PluginInfo.class);

            /* 设置插件使用报表版本，供后续处理器使用 */
            FineVersion fineVersion = FineVersionHelp.fromEnvVersion(info.envVersion());
            pluginXmlContext.setFineVersion(fineVersion);

            IPluginBaseInfo pluginInfo = pluginXmlContext.getPluginBaseInfo();
            pluginInfo.setId(info.id());
            pluginInfo.setName(info.name());
            pluginInfo.setActive(info.active());
            if (StringUtils.isNotEmpty(info.version())) {
                pluginInfo.setVersion(info.version());
            }
            pluginInfo.setEnvVersion(info.envVersion());
            pluginInfo.setVendor(info.vendor());
            pluginInfo.setJarTime(info.jarTime());
            pluginInfo.setDescription(info.description());


//            String qualifiedClassName;
//            try {
//                Class<?> clazz = info.functionRecorder();
//                qualifiedClassName = clazz.getCanonicalName();
//            } catch (MirroredTypeException mte) {
//                DeclaredType classTypeMirror = (DeclaredType) mte.getTypeMirror();
//                TypeElement classTypeElement = (TypeElement) classTypeMirror.asElement();
//                qualifiedClassName = classTypeElement.getQualifiedName().toString();
//            }
//            /*如果 functionRecorder 不为 Void.class，则 取取其类元素的类完全限定名*/
//            if (!Void.class.getCanonicalName().equals(qualifiedClassName)) {
//                pluginInfo.setFunctionRecorder(qualifiedClassName);
//            }

            pluginInfo.setMainPackage(info.mainPackage());
            pluginInfo.setPrice(info.price());
        }
    }

    /**
     * 优先处理
     *
     * @return int
     */
    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE;
    }

}
