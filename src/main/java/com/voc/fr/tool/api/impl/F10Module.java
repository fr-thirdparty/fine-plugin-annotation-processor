package com.voc.fr.tool.api.impl;

import com.voc.fr.tool.api.IModule;
import com.voc.fr.tool.api.FineModule;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2019/08/31 17:36
 */
public class F10Module implements IModule {

    @Override
    public String getTagName(FineModule module) {
        switch (module) {
            case CORE:
                return "extra-core";
            case DECISION:
                return "extra-decision";
            case SCHEDULE:
                return "extra-schedule";
            case MOBILE:
                return "extra-mobile";
            case REPORT:
                return "extra-report";
            case FORM:
                return "extra-form";
            case CHART:
                return "extra-chart";
            case DESIGN:
                return "extra-designer";
            case CHART_DESIGN:
                return "extra-chart-designer";
            case ANALYSE:
                return "extra-analyse";
            default:
                return null;
        }
    }

}
