package com.voc.fr.tool.core;

import com.voc.fr.tool.annotation.EnableFinePlugin;
import com.voc.fr.tool.api.IAction;
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

import javax.annotation.processing.ProcessingEnvironment;
import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.voc.fr.tool.api.impl.AbstractAnnotationProcessor.PLUGIN_XML_CONTEXT_PROPERTY;
import static com.voc.fr.tool.api.impl.AbstractAnnotationProcessor.PROCESSING_ENV_PROPERTY;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2020/04/27 08:25
 */
@Component
public class ProcessorBeanDefinitionRegistry implements BeanDefinitionRegistryPostProcessor, ApplicationContextAware {

    private ApplicationContext applicationContext;
    private final List<ProcessorInfo> processorInfos;

    public ProcessorBeanDefinitionRegistry() {
        Reflections reflections = new Reflections("com.voc.fr.tool.annotation");
        this.processorInfos = reflections.getSubTypesOf(Annotation.class).stream().filter(
                aClass -> {
                    EnableFinePlugin finePlugin = aClass.getAnnotation(EnableFinePlugin.class);
                    return finePlugin != null && finePlugin.value() && finePlugin.auto();
                }
        ).map(aClass -> new ProcessorInfo(aClass, aClass.getAnnotation(EnableFinePlugin.class).priority()))
                .collect(Collectors.toList());
    }

    @Override
    public void postProcessBeanDefinitionRegistry(@NotNull BeanDefinitionRegistry registry) throws BeansException {
        /* 1. 手动注入的处理器,即通过注解注入的实例 */
        this.manualInjection(registry);

        /* 2. 自动注入的处理器 */
        this.autoInjection(registry);
    }

    @Override
    public void postProcessBeanFactory(@NotNull ConfigurableListableBeanFactory beanFactory) throws BeansException {
        IPluginXmlContext pluginXmlContext = this.getPluginXmlContext();
        Map<String, IAnnotationProcessor> processorMap = this.applicationContext.getBeansOfType(IAnnotationProcessor.class);
        List<IAnnotationProcessor> processors = new ArrayList<>(processorMap.values());
        OrderComparator.sort(processors);
        processors.forEach(pluginXmlContext::addProcessor);
    }

    @Override
    public void setApplicationContext(@NotNull ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @NotNull
    private IPluginXmlContext getPluginXmlContext() {
        return this.applicationContext.getBean(IPluginXmlContext.class);
    }

    @NotNull
    private ProcessingEnvironment getProcessingEnv() {
        return this.applicationContext.getBean(ProcessingEnvironment.class);
    }

    @SafeVarargs
    private final void processorBeanDefinitionConfig(@NotNull GenericBeanDefinition beanDefinition, IAction<GenericBeanDefinition>... action) {
        beanDefinition.setAutowireMode(GenericBeanDefinition.AUTOWIRE_BY_TYPE);

        beanDefinition.setScope(BeanDefinition.SCOPE_SINGLETON);
        beanDefinition.setDependsOn(PROCESSING_ENV_PROPERTY, PLUGIN_XML_CONTEXT_PROPERTY);

        MutablePropertyValues propertyValues = beanDefinition.getPropertyValues();
        propertyValues.addPropertyValue(PLUGIN_XML_CONTEXT_PROPERTY, this.getPluginXmlContext());
        propertyValues.addPropertyValue(PROCESSING_ENV_PROPERTY, this.getProcessingEnv());
        Arrays.asList(action).forEach(
                a -> a.execute(beanDefinition)
        );
    }

    private void manualInjection(@NotNull BeanDefinitionRegistry registry) {
        String[] beanDefinitionNames = registry.getBeanDefinitionNames();

        for (String beanDefinitionName : beanDefinitionNames) {
            BeanDefinition beanDefinition = registry.getBeanDefinition(beanDefinitionName);
            if (beanDefinition instanceof GenericBeanDefinition) {
                String beanClassName = beanDefinition.getBeanClassName();
                try {
                    Class<?> aClass = Class.forName(beanClassName);
                    if (IAnnotationProcessor.class.isAssignableFrom(aClass)) {
                        this.processorBeanDefinitionConfig((GenericBeanDefinition) beanDefinition);
                    }
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }

            }

        }

    }

    private void autoInjection(@NotNull BeanDefinitionRegistry registry) {
        for (ProcessorInfo processorInfo : this.processorInfos) {
            String beanName = processorInfo.getAnnotation().getSimpleName() + "Processor";
            BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition(ClassInfoAnnotationProcessor.class);
            GenericBeanDefinition beanDefinition = (GenericBeanDefinition) builder.getRawBeanDefinition();
            this.processorBeanDefinitionConfig(beanDefinition, genericBeanDefinition -> {
                        genericBeanDefinition.getConstructorArgumentValues().addIndexedArgumentValue(0, processorInfo.getAnnotation());
                        genericBeanDefinition.getConstructorArgumentValues().addIndexedArgumentValue(1, processorInfo.getPriority());
                    }
            );

            registry.registerBeanDefinition(beanName, beanDefinition);
        }
    }

}
