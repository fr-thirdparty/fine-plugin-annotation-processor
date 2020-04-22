package com.voc.fr.tool.util;

import com.voc.fr.tool.annotation.plugin.ChangeNote;
import com.voc.fr.tool.api.IPluginXmlContext;
import com.voc.fr.tool.api.impl.BaseAnnotationProcessor;
import com.voc.fr.tool.api.impl.DefaultNote;
import org.apache.commons.lang3.StringUtils;

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
    public static void resolver(ChangeNote changeNote, IPluginXmlContext pluginXmlContext) {
        if (changeNote != null) {
            String dateOf = changeNote.dateOf();
            String dateFormat = changeNote.format();
            String[] content = changeNote.content();
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < content.length; i++) {
                if (StringUtils.isEmpty(content[i])) {
                    continue;
                }
                sb.append(content[i]);
                if (i < content.length - 1) {
                    sb.append(" ");
                }
            }
            DefaultNote note = new DefaultNote(dateOf, sb.toString());
            if (StringUtils.isNotEmpty(dateFormat)) {
                note.setFormat(dateFormat);
            }
            BaseAnnotationProcessor.logger.debug(note.toString());
            pluginXmlContext.getPluginBaseInfo().addChangeNote(note);
        }
    }

}
