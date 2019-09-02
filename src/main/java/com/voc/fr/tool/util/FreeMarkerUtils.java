package com.voc.fr.tool.util;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;

import java.io.*;
import java.nio.charset.Charset;
import java.util.Map;

/**
 * FreeMarker 模板片段渲染工具
 *
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2017/01/16 18:14
 */
@Slf4j
public class FreeMarkerUtils {

    /**
     * FreeMarker 模板存储路径
     */
    private final static String TEMPLATE_PATH_PREFIX = "/templates/";

    /**
     * 负责管理 FreeMarker 模板的 Configuration 实例
     */
    private static Configuration cfg;

    static {
        /*创建一个 FreeMarker 实例*/
        cfg = new Configuration(Configuration.VERSION_2_3_28);
        cfg.setDefaultEncoding("UTF-8");

        /*指定 FreeMarker 模板文件的默认位置*/
        cfg.setClassForTemplateLoading(FreeMarkerUtils.class, TEMPLATE_PATH_PREFIX);
    }

    /**
     * 输出模板
     *
     * @param model        数据模型
     * @param outputStream 输出流
     * @param templateName 模板名称
     */
    public static void outTemplate(Map<String, Object> model, OutputStream outputStream, String templateName) {
        Writer writer = new OutputStreamWriter(outputStream);
        outTemplate2Writer(model, writer, templateName);
    }

    /**
     * 输出模板
     *
     * @param model        数据模型
     * @param writer       Writer
     * @param templateName 模板名称
     */
    public static void outTemplate2Writer(Map<String, Object> model, Writer writer, String templateName) {
        /*定义模板文件*/
        Template template;
        try {
            /*获取模板文件*/
            template = cfg.getTemplate(templateName);
            if (log.isDebugEnabled()) {
                log.debug("模板所在位置：[{}{}]", TEMPLATE_PATH_PREFIX, template.getName());
            }
            /*合并数据模型和模板，并将结果输出到out中*/
            try {
                /*往模板里写数据*/
                template.process(model, writer);
            } catch (TemplateException e) {
                log.error(e.getMessage(), e);
            }
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
    }

    /**
     * 输出模板
     *
     * @param model        数据模型
     * @param templateName 模板名称
     * @return byte[]
     */
    public static byte[] outTemplate2Bytes(Map<String, Object> model, String templateName) {
        ByteArrayOutputStream bas = new ByteArrayOutputStream();
        outTemplate(model, bas, templateName);
        return bas.toByteArray();
    }

    /**
     * 输出模板
     *
     * @param model        数据模型
     * @param templateName 模板名称
     * @return InputStream
     */
    public static InputStream outTemplate2InputStream(Map<String, Object> model, String templateName) {
        return new ByteArrayInputStream(outTemplate2Bytes(model, templateName));
    }

    /**
     * 输出模板
     *
     * @param model        数据模型
     * @param templateName 模板名称
     * @return String
     */
    public static String outTemplate2String(Map<String, Object> model, String templateName) {
        String result = null;
        try {
            result = IOUtils.toString(outTemplate2InputStream(model, templateName), Charset.defaultCharset());
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
        return result;
    }

}
