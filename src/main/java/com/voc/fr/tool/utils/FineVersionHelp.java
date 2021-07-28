package com.voc.fr.tool.utils;

import com.voc.fr.tool.api.FineVersion;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2019/09/01 13:07
 */
public class FineVersionHelp {

    public static final String V8 = "8.0";
    public static final String V9 = "9.0";
    public static final String V10 = "10.0";

    public static FineVersion fromEnvVersion(String envVersion) {
        String v = envVersion.trim();
        // TODO: 2021/7/28 14:49 获取最新版本
        if (v.matches(V8)) {
            return FineVersion.V8;
        } else if (v.matches(V9)) {
            return FineVersion.V9;
        } else if (v.matches(V10)) {
            return FineVersion.V10;
        }
        return FineVersion.V10;
    }

    public static FineVersion fromVersion(String version) {
        String v = version.trim();
        if (v.startsWith(V8)) {
            return FineVersion.V8;
        } else if (v.startsWith(V9)) {
            return FineVersion.V9;
        } else if (v.startsWith(V10)) {
            return FineVersion.V10;
        }
        return FineVersion.V10;
    }

}
