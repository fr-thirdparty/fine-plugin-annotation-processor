package com.voc.fr.tool.api;

import java.io.Serializable;
import java.util.Set;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2019/08/30 18:23
 */
public interface IPluginBaseInfo extends Serializable {

    /**
     * 获取插件 ID
     *
     * @return String
     */
    String getId();

    /**
     * 设置插件 ID
     *
     * @param id String
     */
    void setId(String id);

    /**
     * 主包名，需要涵盖所有在xml中描述的类，默认 com.fr.plugin
     *
     * @return String
     * @since 9.0
     */
    String getMainPackage();

    /**
     * 设置主包
     *
     * @param mainPackage String
     */
    void setMainPackage(String mainPackage);

    /**
     * 获取插件的名字
     *
     * @return String
     */
    String getName();

    /**
     * 设置插件名称
     *
     * @param name String
     */
    void setName(String name);

    /**
     * 插件是否启用
     *
     * @return boolean
     */
    boolean isActive();

    /**
     * 设置是否激活插件
     *
     * @param active boolean
     */
    void setActive(boolean active);

    /**
     * 插件是否在插件管理器中隐藏
     *
     * @return boolean
     */
    boolean isHidden();

    /**
     * 设置是否隐藏插件
     *
     * @param hidden boolean
     */
    void setHidden(boolean hidden);


    /**
     * 插件售价
     *
     * @return int
     */
    int getPrice();

    /**
     * 设置插件售价
     *
     * @param price int
     */
    void setPrice(int price);

    /**
     * 插件版本
     *
     * @return String
     */
    String getVersion();

    /**
     * 设置插件版本
     *
     * @param version String
     */
    void setVersion(String version);

    /**
     * 插件针对的报表版本，一般来说，需要保持向后兼容
     *
     * @return String
     */
    String getEnvVersion();

    /**
     * 设置环境版本
     *
     * @param envVersions String
     */
    void setEnvVersion(String envVersions);

    /**
     * 插件适配的移动端版本,仅移动端插件需要该属性
     *
     * @return String
     * @since 10.0
     */
    String getAppVersion();

    /**
     * 设置插件适配的移动端版本
     *
     * @param appVersion String
     */
    void setAppVersion(String appVersion);

    /**
     * 插件开发者
     *
     * @return String
     */
    String getVendor();

    /**
     * 设置插件开发者
     *
     * @param vendor String
     */
    void setVendor(String vendor);

    /**
     * 插件适配的jar版本
     *
     * @return String
     */
    String getJarTime();

    /**
     * 设置 JAR 包时间
     *
     * @param jarTime String
     */
    void setJarTime(String jarTime);

    /**
     * 插件的简要描述信息
     *
     * @return String
     */
    String getDescription();

    /**
     * 设置插件描述
     *
     * @param description String
     */
    void setDescription(String description);

    /**
     * 插件更新日志
     *
     * @return Set<INote>
     */
    Set<INote> getChangeNotes();

    /**
     * 设置插件更新日志
     *
     * @param changeNotes Set<INote>
     */
    void setChangeNotes(Set<INote> changeNotes);

    /**
     * 添加更新日志
     *
     * @param note INote
     */
    void addChangeNote(INote note);


//    Object getLifecycleMonitor();
//
//    Object getPreferPackages();
//
//    Object getDependence();
//
//    Object getAttributes();
//
//    Object getMoveAfterInstall();
}
