//package com.voc.fr.tool.util;
//
//import com.voc.fr.tool.api.FineModule;
//import com.voc.fr.tool.entity.ClassInfo;
//import com.voc.fr.tool.entity.FunctionClassInfo;
//import com.voc.fr.tool.entity.PluginBaseInfo;
//import lombok.extern.slf4j.Slf4j;
//import org.junit.BeforeClass;
//import org.junit.Test;
//
//import java.io.FileWriter;
//import java.io.IOException;
//import java.util.*;
//
///**
// * Created with IntelliJ IDEA.
// *
// * @author Wu Yujie
// * @email coffee377@dingtalk.com
// * @time 2019/04/01 11:33
// */
//@Slf4j
//public class BeetlUtilsTest {
//
//    private static Map<String, Object> model = new HashMap<>(2);
//
//    @BeforeClass
//    public static void beforeClass() {
//        PluginBaseInfo pluginInfo = new PluginBaseInfo();
//        pluginInfo.setId("测试ID");
//        pluginInfo.setName("测试名称");
//        pluginInfo.setPrice(1000);
//        pluginInfo.setEnvVersion("8.0");
//        pluginInfo.setVendor("coffee377");
//        pluginInfo.setJarTime("2017-06-10");
//        pluginInfo.setDescription("选择框形态插件");
//        pluginInfo.addChangeNote(new FineModule.DefaultNote("2019-03-30", "1.0.0", "测试"));
//        pluginInfo.addChangeNote(new FineModule.DefaultNote("2019-04-01", "1.0.1", "测试2"));
//        Map<String, Set<ClassInfo>> interfaceImpl = new TreeMap<>();
//        interfaceImpl.put("extra-core", new TreeSet<>());
//        interfaceImpl.fromEnvVersion("extra-core").add(ClassInfo.of("LocaleFinder", "com.voc.fr.plugin.present.SelectPresentLocaleFinder"));
//        if (!interfaceImpl.containsKey("extra-core")) {
//            interfaceImpl.put("extra-core", new TreeSet<>());
//        }
//        interfaceImpl.fromEnvVersion("extra-core").add(FunctionClassInfo.of("FunctionDefineProvider", "com.voc.fr.plugin.function.JsonExpression", "JSONPATH", "JSONPATH(object,string)：返回JSON表达式解析内容"));
//        if (!interfaceImpl.containsKey("extra-designer")) {
//            interfaceImpl.put("extra-designer", new TreeSet<>());
//        }
//        interfaceImpl.fromEnvVersion("extra-designer").add(ClassInfo.of("PresentKindProvider", "com.voc.fr.plugin.present.SelectPresentKind"));
//        model.put("plugin", pluginInfo);
//        model.put("interface", interfaceImpl);
//    }
//
//    @Test
//    public void outTemplate() {
//        BeetlUtils.outTemplate(model, System.out, "plugin.txt");
//    }
//
//    @Test
//    public void outTemplate2Writer() throws IOException {
//        FileWriter writer = new FileWriter("plugin.xml");
//        BeetlUtils.outTemplate2Writer(model, writer, "plugin.txt");
//    }
//
//    @Test
//    public void outTemplate2String() {
//        log.info(BeetlUtils.outTemplate2String(model, "plugin.txt"));
//    }
//
//}
