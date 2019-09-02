package com.voc.fr.tool.util;

import com.voc.fr.tool.api.FineVersion;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2019/09/01 13:07
 */
public class FineVersionHelp {

    public static FineVersion fromEnvVersion(String envVersion) {
        String v = envVersion.trim();
        if (v.matches("8.0")) {
            return FineVersion.V8;
        } else if (v.matches("9.0")) {
            return FineVersion.V9;
        } else if (v.matches("10.0")) {
            return FineVersion.V10;
        }
        return FineVersion.V10;
    }

    public static FineVersion fromVersion(String version) {
        String v = version.trim();
        if (v.startsWith("8.0")) {
            return FineVersion.V8;
        } else if (v.startsWith("9.0")) {
            return FineVersion.V9;
        } else if (v.startsWith("10.0")) {
            return FineVersion.V10;
        }
        return FineVersion.V10;
    }

}
