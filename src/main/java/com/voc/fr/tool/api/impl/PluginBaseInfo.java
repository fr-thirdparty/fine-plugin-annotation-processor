package com.voc.fr.tool.api.impl;

import com.alibaba.fastjson.JSON;
import com.voc.fr.tool.api.INote;
import com.voc.fr.tool.api.IPluginBaseInfo;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2019/03/17 18:43
 */
@Getter
@Setter
public class PluginBaseInfo implements IPluginBaseInfo {

    /**
     * 插件的唯一标识符
     */
    private String id;

    /**
     * 主包名，需要涵盖所有在xml中描述的类，默认 com.voc.fr.plugin
     */
    private String mainPackage = "com.voc.fr.plugin";

    /**
     * 插件的名字
     */
    private String name;

    /**
     * 插件是否启用
     */
    private boolean active;

    /**
     * 插件是否在插件管理器中隐藏
     */
    private boolean hidden;

    /**
     * 插件售价
     */
    private int price;

    /**
     * 插件版本
     */
    private String version;

    /**
     * 插件针对的报表版本，一般来说，需要保持向后兼容
     */
    private String envVersion = "10.0";

    /**
     * 插件适配的移动端版本
     */
    private String appVersion;

    /**
     * 插件适配的jar版本
     */
    private String jarTime;

    /**
     * 插件开发者
     */
    private String vendor;

    /**
     * 插件的简要描述信息
     */
    private String description;

    /**
     * 插件更新日志
     */
    private Set<INote> changeNotes;

    @Override
    public void addChangeNote(INote note) {
        if (this.changeNotes == null) {
            this.changeNotes = new TreeSet<>();
        }
        this.changeNotes.add(note);
    }

    @Override
    public IPluginBaseInfo from(Map<String, Object> annotationValue) {
        byte[] bytes = JSON.toJSONBytes(annotationValue);
        PluginBaseInfo info = JSON.parseObject(bytes, PluginBaseInfo.class);
        BeanUtils.copyProperties(info, this, "changeNotes");
        return this;
    }

}
