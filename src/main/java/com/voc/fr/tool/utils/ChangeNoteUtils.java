package com.voc.fr.tool.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.voc.fr.tool.annotation.plugin.ChangeNote;
import com.voc.fr.tool.annotation.plugin.ChangeNotes;
import com.voc.fr.tool.api.IPluginXmlContext;
import com.voc.fr.tool.api.impl.BaseAnnotationProcessor;
import com.voc.fr.tool.api.impl.DefaultNote;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

import javax.lang.model.element.Element;
import java.util.Map;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2019/03/15 10:53
 */
public class ChangeNoteUtils {

    /**
     * ChangeNote 注解处理
     *
     * @param changeNote       ChangeNote
     * @param pluginXmlContext 上下文
     */
    public static void resolver(Map<String, Object> changeNote, IPluginXmlContext pluginXmlContext) {
        if (changeNote != null) {
            String dateOf = (String) changeNote.get("dateOf");
            String dateFormat = (String) changeNote.get("format");
            Object[] contents = (Object[]) changeNote.get("content");
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < contents.length; i++) {
                if (StringUtils.isEmpty(contents[i].toString())) {
                    continue;
                }
                sb.append(contents[i].toString());
                if (i < contents.length - 1) {
                    sb.append(" ");
                }
            }
            DefaultNote note = new DefaultNote(dateOf, new String[]{sb.toString()});
            if (StringUtils.isNotEmpty(dateFormat)) {
                note.setFormat(dateFormat);
            }
            BaseAnnotationProcessor.logger.debug(note.toString());
            pluginXmlContext.getPluginBaseInfo().addChangeNote(note);
        }
    }

    public static void resolverChangeNote(IPluginXmlContext pluginXmlContext, Element element) {
        Map<String, Object> values = AnnotationUtils.getValues(element, ChangeNote.class);
        resolverChangeNote(pluginXmlContext, values);
//        byte[] bytes = JSONObject.toJSONBytes(values);
//        DefaultNote note = JSON.parseObject(bytes, DefaultNote.class);
//        DefaultNote note = AnnotationUtils.forBean(element, ChangeNote.class, DefaultNote.class);
//        BaseAnnotationProcessor.logger.debug(note.toString());
//        pluginXmlContext.getPluginBaseInfo().addChangeNote(note);
    }

    private static void resolverChangeNote(IPluginXmlContext pluginXmlContext, Map<String, Object> map) {
        byte[] bytes = JSONObject.toJSONBytes(map);
        DefaultNote note = JSON.parseObject(bytes, DefaultNote.class);
        BaseAnnotationProcessor.logger.debug(note.toString());
        pluginXmlContext.getPluginBaseInfo().addChangeNote(note);
    }

    public static void resolverChangeNotes(IPluginXmlContext pluginXmlContext, Element element) {
        Map<String, Object> changeNotes = AnnotationUtils.getValues(element, ChangeNotes.class);
//        byte[] bytes = JSONObject.toJSONBytes(changeNotes);
//        Notes object = JSON.parseObject(bytes, Notes.class);
//        Notes notes = AnnotationUtils.forBean(element, ChangeNotes.class, Notes.class);
//        for (Object o : notes.value) {
//            if (o instanceof Map) {
//                Map<String, Object> map = (Map<String, Object>) o;
//                resolverChangeNote(pluginXmlContext, map);
//            }
//        }
        System.out.println("s");
//        Map<String,Object> value = (Map<String, Object>) changeNotes.get("value");
//        for (Map.Entry<String, Object> entry : changeNotes.entrySet()) {
//            resolverChangeNote(pluginXmlContext, entry);
//        }
    }

    @Getter
    @Setter
    private class Notes {
        private Object[] value;
    }

}
