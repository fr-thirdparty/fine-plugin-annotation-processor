package com.voc.fr.tool.core;

import com.voc.fr.tool.annotation.EnableFinePlugin;
import com.voc.fr.tool.api.IAnnotationProcessor;
import com.voc.fr.tool.api.IPluginXmlContext;
import org.jetbrains.annotations.NotNull;
import org.reflections.Reflections;
import org.springframework.beans.BeansException;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.OrderComparator;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2020/04/27 08:25
 */
@Component
public class ProcessorBeanDefinitionRegistry implements BeanDefinitionRegistryPostProcessor, ApplicationContextAware {
    public static final String PLUGIN_XML_CONTEXT_PROPERTY = "pluginXmlContext";
    private ApplicationContext applicationContext;
    private final Set<Class<? extends Annotation>> autoProcessors;

    public ProcessorBeanDefinitionRegistry() {
        Reflections reflections = new Reflections("com.voc.fr.tool.annotation");
        this.autoProcessors = reflections.getSubTypesOf(Annotation.class).stream().filter(
                aClass -> {
                    EnableFinePlugin finePlugin = aClass.getAnnotation(EnableFinePlugin.class);
                    return finePlugin != null && finePlugin.value() && finePlugin.auto();
                }
        ).collect(Collectors.toSet());
    }

    @Override
    public void postProcessBeanDefinitionRegistry(@NotNull BeanDefinitionRegistry registry) throws BeansException {
        /* 1. 自动注入的处理器 */
        for (Class<? extends Annotation> annotation : this.autoProcessors) {
            BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition(ClassInfoAnnotationProcessor.class);
            GenericBeanDefinition beanDefinition = (GenericBeanDefinition) builder.getRawBeanDefinition();
            beanDefinition.getConstructorArgumentValues().addIndexedArgumentValue(0, annotation);
            beanDefinition.setAutowireMode(GenericBeanDefinition.AUTOWIRE_BY_TYPE);
            MutablePropertyValues propertyValues = beanDefinition.getPropertyValues();
            propertyValues.addPropertyValue(PLUGIN_XML_CONTEXT_PROPERTY, this.getPluginXmlContext());
            /* 注册 bean 定义 */
            registry.registerBeanDefinition(annotation.getSimpleName() + "Processor", beanDefinition);
        }
        /* 2. 手动注入 */
        BeanDefinition beanDefinition = registry.getBeanDefinition("changeNoteProcessor");
        System.out.println(beanDefinition);
    }

    @Override
    public void postProcessBeanFactory(@NotNull ConfigurableListableBeanFactory beanFactory) throws BeansException {
        IPluginXmlContext pluginXmlContext = this.getPluginXmlContext();
        Map<String, IAnnotationProcessor> processorMap = this.applicationContext.getBeansOfType(IAnnotationProcessor.class);
        List<IAnnotationProcessor> processors = new ArrayList<>(processorMap.values());
        OrderComparator.sort(processors);
        Set<String> definitions = processorMap.keySet();
        definitions.forEach(definitionName -> {
            GenericBeanDefinition definition = (GenericBeanDefinition) beanFactory.getBeanDefinition(definitionName);
//            MutablePropertyValues propertyValues = definition.getPropertyValues();
//            if (propertyValues.getPropertyValue(PLUGIN_XML_CONTEXT_PROPERTY) == null) {
//                propertyValues.addPropertyValue(PLUGIN_XML_CONTEXT_PROPERTY, pluginXmlContext);
//            }

//            propertyValues.addPropertyValue("processingEnv", null);
            definition.setScope(BeanDefinition.SCOPE_SINGLETON);
        });
        processors.forEach(pluginXmlContext::addProcessor);

    }

    @Override
    public void setApplicationContext(@NotNull ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    private IPluginXmlContext getPluginXmlContext() {
        return this.applicationContext.getBean(IPluginXmlContext.class);
    }
}
