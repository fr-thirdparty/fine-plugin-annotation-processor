package com.voc.fr.tool.api;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2019/08/30 16:38
 */
@Getter
public enum FineModule {

    CORE("核心模块"),

    DECISION("10.0 平台模块"),

    PLATFORM("8.0 ~ 9.0 平台模块"),

    SCHEDULE("定时调度模块"),

    MOBILE("移动端模"),

    REPORT("报表模块"),

    FORM("决策报表模块"),

    CHART("图表模块"),

    DESIGN("设计器模块"),

    CHART_DESIGN("设计器图表模块"),

    ANALYSE("BI模块");

    FineModule(String name) {
        this.name = name;
    }

    private String name;


}
