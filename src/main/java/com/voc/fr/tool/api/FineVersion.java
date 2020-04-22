package com.voc.fr.tool.api;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2019/08/31 17:33
 */
@Getter
@AllArgsConstructor
public enum FineVersion {

    V8("8.0"),
    V9("9.0"),
    V10("10.0");

    private final String version;

}
