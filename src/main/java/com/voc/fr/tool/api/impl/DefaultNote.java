package com.voc.fr.tool.api.impl;

import com.voc.fr.tool.api.INote;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;

import java.text.MessageFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2019/03/18 19:03
 */
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class DefaultNote implements INote {

    private String dateOf;

    private String format = "yyyy-MM-dd";

    private String[] contents;

    public DefaultNote(String dateOf, String[] contents) {
        this.dateOf = dateOf;
        this.contents = contents;
    }

    @Override
    public int compareTo(@NotNull INote o) {
        /*按日期降序排列*/
        return LocalDate.parse(o.getDateOf(), DateTimeFormatter.ofPattern(o.getFormat()))
                .compareTo(LocalDate.parse(this.getDateOf(), DateTimeFormatter.ofPattern(this.getFormat())));
    }

    public static DefaultNote of(String dateOf, String... content) {
        return new DefaultNote(dateOf, content);
    }

    @Override
    public String getContent() {
        if (contents == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < contents.length; i++) {
            if (StringUtils.isEmpty(contents[i])) {
                continue;
            }
            sb.append(contents[i]);
            if (i < contents.length - 1) {
                sb.append(" ");
            }
        }
        return sb.toString();
    }

    @Override
    public String toString() {
        return MessageFormat.format("日期：{0} | 更新日志：{1}", this.dateOf, this.getContent());
    }

}
