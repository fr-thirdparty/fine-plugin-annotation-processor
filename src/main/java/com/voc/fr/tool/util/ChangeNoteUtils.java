package com.voc.fr.tool.util;

import com.voc.fr.tool.annotation.plugin.ChangeNote;
import com.voc.fr.tool.api.IPluginXmlContext;
import com.voc.fr.tool.api.impl.DefaultNote;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

/**
 * Created with IntelliJ IDEA.
 *
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2019/03/15 10:53
 */
@Slf4j
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
            String version = changeNote.version();
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
            log.debug("日期：{} | 版本：{} | 更新日志：{}", dateOf, version, sb.toString());
            pluginXmlContext.getPluginBaseInfo().getChangeNotes().add(new DefaultNote(dateOf, version, sb.toString()));
        }
    }

}
