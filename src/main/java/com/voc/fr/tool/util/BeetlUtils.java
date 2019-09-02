package com.voc.fr.tool.util;

import lombok.extern.slf4j.Slf4j;
import org.beetl.core.Configuration;
import org.beetl.core.GroupTemplate;
import org.beetl.core.Template;
import org.beetl.core.cache.LocalCache;
import org.beetl.core.resource.ClasspathResourceLoader;

import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2019/03/29 18:35
 */
@Slf4j
public class BeetlUtils {

    /**
     * Beetl 模板存储路径
     */
    private final static String TEMPLATE_PATH_PREFIX = "/templates/";
    private static GroupTemplate groupTemplate;

    static {
        Configuration conf = null;
        ClasspathResourceLoader resourceLoader = new ClasspathResourceLoader(TEMPLATE_PATH_PREFIX);
        try {
            conf = Configuration.defaultConfiguration();
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }

//        groupTemplate = new GroupTemplate(resourceLoader, conf);
        groupTemplate = new GroupTemplate(resourceLoader, conf, BeetlUtils.class.getClassLoader());
//        groupTemplate.setProgramCache(new LocalCache());

    }


    /**
     * 输出模板
     *
     * @param model        数据模型
     * @param outputStream 输出流
     * @param templateName 模板名称
     */
    public static void outTemplate(Map<String, Object> model, OutputStream outputStream, String templateName) {
        Template template = groupTemplate.getTemplate(templateName);
        template.binding(model);
        template.renderTo(outputStream);
    }

    /**
     * 输出模板
     *
     * @param model        数据模型
     * @param writer       Writer
     * @param templateName 模板名称
     */
    public static void outTemplate2Writer(Map<String, Object> model, Writer writer, String templateName) {
        Template template = groupTemplate.getTemplate(templateName);
        template.binding(model);
        template.renderTo(writer);
    }

    /**
     * 输出模板
     *
     * @param model        数据模型
     * @param templateName 模板名称
     * @return String
     */
    public static String outTemplate2String(Map<String, Object> model, String templateName) {
        Template template = groupTemplate.getTemplate(templateName);
        template.binding(model);
        return template.render();
    }

}
