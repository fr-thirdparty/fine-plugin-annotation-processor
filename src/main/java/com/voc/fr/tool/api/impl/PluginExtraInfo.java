package com.voc.fr.tool.api.impl;

import com.voc.fr.tool.api.IDependence;
import com.voc.fr.tool.api.IPluginExtraInfo;
import lombok.Getter;
import lombok.Setter;
import org.dom4j.Element;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2019/09/02 15:34
 */
@Getter
@Setter
public class PluginExtraInfo implements IPluginExtraInfo {

    private IDependence dependence;

    private Element attributes;

}
