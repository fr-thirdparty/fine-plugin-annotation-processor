package com.voc.fr.tool.core.processor;

import com.voc.fr.tool.annotation.plugin.PluginInfo;
import com.voc.fr.tool.api.FineVersion;
import com.voc.fr.tool.api.IPluginBaseInfo;
import com.voc.fr.tool.api.IPluginXmlContext;
import com.voc.fr.tool.api.impl.AbstractAnnotationProcessor;
import com.voc.fr.tool.utils.AnnotationUtils;
import com.voc.fr.tool.utils.FineVersionHelp;
import org.springframework.core.Ordered;
import org.springframework.core.PriorityOrdered;
import org.springframework.stereotype.Component;

import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.TypeElement;
import java.lang.annotation.Annotation;
import java.util.Map;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2019/04/02 11:32
 */
@Component
public class PluginInfoProcessor extends AbstractAnnotationProcessor implements PriorityOrdered {

    @Override
    public Class<? extends Annotation> getAnnotationClass() {
        return PluginInfo.class;
    }

    @Override
    public void process4PluginXmlContext(IPluginXmlContext pluginXmlContext, Element element, TypeElement typeElement) {
        if (element.getKind() == ElementKind.CLASS) {
            Map<String, Object> info = AnnotationUtils.getValues(element, getAnnotationClass());

            IPluginBaseInfo pluginInfo = pluginXmlContext.getPluginBaseInfo().from(info);

            /* 设置插件使用报表版本，供后续处理器使用 */
            FineVersion fineVersion = FineVersionHelp.fromEnvVersion(pluginInfo.getEnvVersion());
            pluginXmlContext.setFineVersion(fineVersion);
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
