package com.voc.fr.tool.api.impl;

import com.voc.fr.tool.api.*;
import com.voc.fr.tool.util.PluginXmlHelper;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.annotation.processing.Filer;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

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
    private Set<IClassInfo> functionRecorder;

    @Setter
    private IPluginExtraInfo pluginExtraInfo;

    private Map<String, Set<IClassInfo>> implementation;

    public DefaultPluginXmlContext() {
        this.pluginBaseInfo = new PluginBaseInfo();
        this.pluginExtraInfo = new PluginExtraInfo();
    }

    @Override
    public void addImplementation(String moduleXmlTag, IClassInfo classInfo) {
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
    public void addFunctionRecorder(IClassInfo classInfo) {
        if (this.functionRecorder == null) {
            this.functionRecorder = new TreeSet<>();
        }
        this.functionRecorder.add(classInfo);
    }

    @Override
    public void generate(File destDir, Filer filer) throws Exception {
        File file = new File(destDir.getAbsolutePath(), "plugin.xml");
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        PluginXmlHelper.write(fileOutputStream, this);
    }

}