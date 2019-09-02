package com.voc.fr.tool.api.impl;

import com.voc.fr.tool.api.INote;
import com.voc.fr.tool.api.IPluginBaseInfo;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
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
     * 主包名，需要涵盖所有在xml中描述的类，默认 com.fr.plugin
     */
    private String mainPackage;

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
     * 插件版本
     */
    private String version;

    /**
     * 插件针对的报表版本，一般来说，需要保持向后兼容
     */
    private String envVersion;

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

    /**
     * 添加了功能点记录的类
     */
    private List<String> functionRecorder;

    /**
     * 插件生命周期类接入点
     */
    private Object lifecycleMonitor;

    /**
     * 插件优先依赖目录,用于解决插件依赖jar和主工程中jar冲突问题
     */
    private Object preferPackages;

    /**
     * 插件依赖信息接入点
     */
    private Object dependence;

    /**
     * 插件自定义属性
     */
    private Object attributes;

    /**
     * 插件安装后会执行的一些文件移动操作
     */
    private Object moveAfterInstall;

    /**
     * 插件售价
     */
    private Integer price;

    @Override
    public void addChangeNote(INote note) {
        this.changeNotes.add(note);
    }

    public PluginBaseInfo() {
        this.active = true;
        this.version = "0.0.1";
        this.changeNotes = new TreeSet<>();
        this.price = 0;
    }

}