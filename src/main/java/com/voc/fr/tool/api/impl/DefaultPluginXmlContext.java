package com.voc.fr.tool.api.impl;

import com.voc.fr.tool.api.*;
import com.voc.fr.tool.utils.PluginXmlHelper;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.annotation.processing.Filer;
import java.io.File;
import java.io.FileOutputStream;
import java.util.*;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2019/08/30 18:24
 */
@Getter
@Component
public class DefaultPluginXmlContext implements IPluginXmlContext {

    @Setter
    private FineVersion fineVersion;

    @Setter
    private IPluginBaseInfo pluginBaseInfo;

    @Setter
    private Set<IClassInfoNode> functionRecorder;

    @Setter
    private IPluginExtraInfo pluginExtraInfo;

    private List<IAnnotationProcessor> processors;

    private Map<String, Set<IClassInfoNode>> implementation;

    public DefaultPluginXmlContext() {
        this.pluginBaseInfo = new PluginBaseInfo();
        this.pluginExtraInfo = new PluginExtraInfo();
    }

    @Override
    public void addImplementation(String moduleXmlTag, IClassInfoNode classInfo) {
        if (StringUtils.isNotEmpty(moduleXmlTag)) {
            if (this.implementation == null) {
                this.implementation = new TreeMap<>();
            }
            if (!this.implementation.containsKey(moduleXmlTag)) {
                this.implementation.put(moduleXmlTag, new TreeSet<>());
            }
            this.implementation.get(moduleXmlTag).add(classInfo);
        }
    }

    @Override
    public void addFunctionRecorder(IClassInfoNode classInfo) {
        if (classInfo == null) {
            return;
        }
        if (this.functionRecorder == null) {
            this.functionRecorder = new TreeSet<>();
        }
        this.functionRecorder.add(classInfo);
    }

    @Override
    public List<IAnnotationProcessor> getProcessors() {
        return new ArrayList<>(this.processors);
    }

    @Override
    public void addProcessor(IAnnotationProcessor processor) {
        if (this.processors == null) {
            this.processors = new LinkedList<>();
        }
        this.processors.add(processor);
    }

    @Override
    public void generate(File destDir, Filer filer) throws Exception {
        File file = new File(destDir.getAbsolutePath(), "plugin.xml");
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        PluginXmlHelper.write(fileOutputStream, this);
    }

}
