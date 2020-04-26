package com.voc.fr.tool.utils;

import com.voc.fr.tool.api.*;
import com.voc.fr.tool.api.impl.*;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.junit.Test;

import java.io.IOException;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2019/09/02 12:02
 */
public class PluginXmlHelperTest {

    @Test
    public void write() throws IOException {
        IPluginXmlContext pluginXmlContext = new DefaultPluginXmlContext();
        IPluginBaseInfo pluginInfo = pluginXmlContext.getPluginBaseInfo();
        pluginInfo.setId("com.voc.demo");
        pluginInfo.setName("测试插件");
        pluginInfo.setVersion("1.0.0");
        pluginInfo.setEnvVersion("10.0");
        pluginInfo.setDescription("测试插件描述");
        pluginInfo.setVendor("coffee377");
        pluginInfo.setMainPackage("com.voc.fr.plugin");
        pluginInfo.addChangeNote(DefaultNote.of("2019-09-01", "初始化"));
        pluginInfo.addChangeNote(DefaultNote.of("2018-09-02", "初始化2"));
        pluginInfo.addChangeNote(DefaultNote.of("2017-09-03", "初始化3"));

        IAttribute attribute1 = DefaultAttribute.of("name", "AGE", 1);
        IAttribute attribute2 = DefaultAttribute.of("description", "AGE()计算年龄", 2);

        pluginXmlContext.addImplementation("extra-decision",
                DefaultClassInfoNode.of("LoginPageProvider",
                        "com.voc.fr.plugin.login.VisualLoginPage"));

        pluginXmlContext.addImplementation("extra-core",
                DefaultClassInfoNode.of("LocaleFinder",
                        "com.voc.fr.plugin.login.VisualLoginLocalFinder"));

        pluginXmlContext.addImplementation("extra-core",
                DefaultClassInfoNode.of("FunctionDefineProvider",
                        "com.voc.fr.plugin.function.expand.Age", attribute1, attribute2));

        IPluginExtraInfo pluginExtraInfo = pluginXmlContext.getPluginExtraInfo();
        IDependence dependence = new DefaultDependence();
        dependence.addItem("A", "B");
        dependence.addItem("C", "D");
        pluginExtraInfo.setDependence(dependence);

        Element element = DocumentHelper.createElement("encode");
        element.addAttribute("name", "你好，世界");
        pluginExtraInfo.setAttributes(element);


        PluginXmlHelper.write(System.out, pluginXmlContext);
    }
}
