package com.voc.fr.tool.api.impl;

import com.voc.fr.tool.api.INote;
import lombok.*;
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

    private String content;

    public DefaultNote(String dateOf, String content) {
        this.dateOf = dateOf;
        this.content = content;
    }

    @Override
    public int compareTo(@NotNull INote o) {
        /*按日期降序排列*/
        return LocalDate.parse(o.getDateOf(), DateTimeFormatter.ofPattern(o.getFormat()))
                .compareTo(LocalDate.parse(this.getDateOf(), DateTimeFormatter.ofPattern(this.format)));
    }

    public static DefaultNote of(String dateOf, String content) {
        return new DefaultNote(dateOf, content);
    }

    @Override
    public String toString() {
        return MessageFormat.format("日期：{0} | 更新日志：{1}", this.dateOf, this.content);
    }
}
