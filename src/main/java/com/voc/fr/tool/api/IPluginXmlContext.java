package com.voc.fr.tool.api;

import javax.annotation.processing.Filer;
import java.io.File;
import java.util.List;
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
     * 设置插件信息
     *
     * @param info IPluginBaseInfo
     */
    void setPluginBaseInfo(IPluginBaseInfo info);

    /**
     * 获取接口实现类
     *
     * @return Map
     */
    Map<String, Set<IClassInfoNode>> getImplementation();

    /**
     * 添加接口实现类
     *
     * @param moduleXmlTag 接口所属模块标签名称
     * @param classInfo    接口实现类信息
     */
    void addImplementation(String moduleXmlTag, IClassInfoNode classInfo);

    /**
     * 功能点记录类
     *
     * @return List<IClassInfo>
     */
    Set<IClassInfoNode> getFunctionRecorder();

    /**
     * 添加功能点记录类
     *
     * @param classInfo IClassInfo
     */
    void addFunctionRecorder(IClassInfoNode classInfo);

    /**
     * 插件额外信息
     *
     * @return IPluginExtraInfo
     */
    IPluginExtraInfo getPluginExtraInfo();

    /**
     * 获取所有实现处理器
     *
     * @return List<IAnnotationProcessor>
     */
    List<IAnnotationProcessor> getProcessors();

    /**
     * 添加处理器实现
     *
     * @param processor IAnnotationProcessor
     */
    void addProcessor(IAnnotationProcessor processor);

    /**
     * 生成 plugin.xml 文件
     *
     * @param destDir 输出目录
     * @param filer   Filer
     * @throws Exception 异常
     */
    void generate(File destDir, Filer filer) throws Exception;

}
