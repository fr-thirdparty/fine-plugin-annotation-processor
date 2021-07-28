package com.voc.fr.tool.utils;

import com.voc.fr.tool.annotation.plugin.ChangeNote;
import com.voc.fr.tool.annotation.plugin.ChangeNotes;
import com.voc.fr.tool.api.IPluginXmlContext;
import com.voc.fr.tool.api.impl.AbstractAnnotationProcessor;
import com.voc.fr.tool.api.impl.DefaultNote;
import lombok.Getter;
import lombok.Setter;

import javax.lang.model.element.Element;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2019/03/15 10:53
 */
public class ChangeNoteUtils {

    /**
     * 解析更新日志
     *
     * @param pluginXmlContext IPluginXmlContext
     * @param element          Element
     */
    public static void resolverChangeNote(IPluginXmlContext pluginXmlContext, Element element) {
        DefaultNote note = AnnotationUtils.forBean(element, ChangeNote.class, DefaultNote.class);
        resolverChangeNote(pluginXmlContext, note);
    }

    /**
     * 解析更新日志
     *
     * @param pluginXmlContext IPluginXmlContext
     * @param element          Element
     */
    public static void resolverChangeNotes(IPluginXmlContext pluginXmlContext, Element element) {
        Notes notes = AnnotationUtils.forBean(element, ChangeNotes.class, Notes.class);
        for (DefaultNote note : notes.value) {
            resolverChangeNote(pluginXmlContext, note);
        }
    }

    @Getter
    @Setter
    private static class Notes {
        private DefaultNote[] value;
    }

    private static void resolverChangeNote(IPluginXmlContext pluginXmlContext, DefaultNote note) {
        AbstractAnnotationProcessor.logger.debug(note.toString());
        pluginXmlContext.getPluginBaseInfo().addChangeNote(note);
    }

}
