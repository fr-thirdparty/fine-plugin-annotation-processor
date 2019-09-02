package com.voc.fr.tool.api;

import javax.annotation.processing.Filer;
import java.io.File;
import java.util.Map;
import java.util.Set;

/**
 * plugin.xml 上下文
 *
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2019/08/30 17:20
 */
public interface IPluginXmlContext {

    /**
     * 使用报表版本
     *
     * @return FineVersion
     */
    FineVersion getFineVersion();

    /**
     * 使用报表版本
     *
     * @param fineVersion FineVersion
     */
    void setFineVersion(FineVersion fineVersion);

    /**
     * 获取插件基本信息
     *
     * @return PluginInfo
     */
    IPluginBaseInfo getPluginBaseInfo();

    /**
     * 获取接口实现类
     *
     * @return Map
     */
    Map<String, Set<IClassInfo>> getImplementation();

    /**
     * 添加接口实现类
     *
     * @param moduleXmlTag 接口所属模块标签名称
     * @param classInfo    接口实现类信息
     */
    void addImplementation(String moduleXmlTag, IClassInfo classInfo);

    /**
     * 功能点记录类
     *
     * @return List<IClassInfo>
     */
    Set<IClassInfo> getFunctionRecorder();

    /**
     * 添加功能点记录类
     *
     * @param classInfo IClassInfo
     */
    void addFunctionRecorder(IClassInfo classInfo);

    /**
     * 插件额外信息
     *
     * @return IPluginExtraInfo
     */
    IPluginExtraInfo getPluginExtraInfo();

    /**
     * 生成 plugin.xml 文件
     *
     * @param destDir 输出目录
     * @param filer   Filer
     * @throws Exception 异常
     */
    void generate(File destDir, Filer filer) throws Exception;

}
