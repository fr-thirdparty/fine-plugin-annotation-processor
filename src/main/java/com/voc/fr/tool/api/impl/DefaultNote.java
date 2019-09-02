package com.voc.fr.tool.api.impl;

import com.voc.fr.tool.api.INote;
import lombok.*;
import org.jetbrains.annotations.NotNull;

import java.time.LocalDate;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2019/03/18 19:03
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class DefaultNote implements INote {

    private String dateOf;

    private String version;

    private String content;

    @Override
    public int compareTo(@NotNull INote o) {
        /*按日期降序排列*/
        return LocalDate.parse(o.getDateOf()).compareTo(LocalDate.parse(this.getDateOf()));
    }

    public static DefaultNote of(String dateOf, String version, String content) {
        return new DefaultNote(dateOf, version, content);
    }

}