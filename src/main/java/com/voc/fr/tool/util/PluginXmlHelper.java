package com.voc.fr.tool.util;

import com.voc.fr.tool.api.*;
import org.apache.commons.lang3.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;
import org.springframework.core.OrderComparator;

import java.io.IOException;
import java.io.OutputStream;
import java.util.*;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2019/09/02 09:58
 */
public class PluginXmlHelper {

    /**
     * 写出文件
     *
     * @param outputStream     OutputStream
     * @param pluginXmlContext IPluginXmlContext
     * @throws IOException IO 异常
     */
    public static void write(OutputStream outputStream, IPluginXmlContext pluginXmlContext) throws IOException {
        Document pluginXmlDocument = createPluginXmlDocument();
        Element rootElement = pluginXmlDocument.getRootElement();
        addBaseInfo(rootElement, pluginXmlContext.getPluginBaseInfo());
        addImplementation(rootElement, pluginXmlContext.getImplementation());
        if (pluginXmlContext.getFunctionRecorder() != null) {
            addFunctionRecorder(rootElement, pluginXmlContext.getFunctionRecorder().iterator());
        }
        addExtraInfo(rootElement, pluginXmlContext.getPluginExtraInfo());
        OutputFormat format = OutputFormat.createPrettyPrint();
        XMLWriter writer = new XMLWriter(outputStream, format);
        writer.write(pluginXmlDocument);
        writer.close();
    }

    private static Document createPluginXmlDocument() {
        Document document = DocumentHelper.createDocument();
        document.addElement("plugin");
        return document;
    }

    private static void addElement(Element element, ElementType type, String name, String value) {
        if (value != null) {
            if (ElementType.TEXT.equals(type)) {
                element.addElement(name).addText(value);
            } else if (ElementType.CDATA.equals(type)) {
                element.addElement(name).addCDATA(value);
            }
        }
    }

    private static void addBaseInfo(Element parentElement, IPluginBaseInfo pluginBaseInfo) {
        if (pluginBaseInfo != null) {
            addElement(parentElement, ElementType.TEXT, "id", pluginBaseInfo.getId());
            addElement(parentElement, ElementType.CDATA, "name", pluginBaseInfo.getName());
            addElement(parentElement, ElementType.TEXT, "active", pluginBaseInfo.isActive() ? "yes" : "no");
            if (pluginBaseInfo.isHidden()) {
                addElement(parentElement, ElementType.TEXT, "hidden", "yes");
            }
            addElement(parentElement, ElementType.TEXT, "version", pluginBaseInfo.getVersion());
            addElement(parentElement, ElementType.TEXT, "env-version", pluginBaseInfo.getEnvVersion());
            addElement(parentElement, ElementType.TEXT, "app-version", pluginBaseInfo.getAppVersion());
            addElement(parentElement, ElementType.TEXT, "vendor", pluginBaseInfo.getVendor());
            if (StringUtils.isNotEmpty(pluginBaseInfo.getMainPackage())) {
                addElement(parentElement, ElementType.TEXT, "main-package", pluginBaseInfo.getMainPackage());
            }
            addElement(parentElement, ElementType.TEXT, "jartime", pluginBaseInfo.getJarTime());
            if (pluginBaseInfo.getPrice() > 0) {
                addElement(parentElement, ElementType.TEXT, "price", String.valueOf(pluginBaseInfo.getPrice()));
            }
            addElement(parentElement, ElementType.CDATA, "description", pluginBaseInfo.getDescription());
            addChangeNotes(parentElement, pluginBaseInfo.getChangeNotes());
        }
    }

    private static void addChangeNotes(Element parentElement, Set<INote> changeNotes) {
        List<INote> notes = new ArrayList<>(changeNotes);
        StringBuilder sb = new StringBuilder();
        if (notes.size() > 1) {
            sb.append("\n\t");
        }
        for (int i = 0; i < notes.size(); i++) {
            INote iNote = notes.get(i);
            if (i > 0) {
                sb.append("\n\t");
            }
            sb.append("<p>").append("[").append(iNote.getDateOf()).append("] ")
                    .append(iNote.getContent()).append("</p>");
            if (i > 0 && i == notes.size() - 1) {
                sb.append("\n");
            }
        }
        addElement(parentElement, ElementType.CDATA, "change-notes", sb.toString());
    }

    /**
     * 接口实现
     *
     * @param parentElement   Element
     * @param implementations Map<String, Set<IClassInfo>>
     */
    private static void addImplementation(Element parentElement, Map<String, Set<IClassInfo>> implementations) {
        if (implementations != null && !implementations.isEmpty()) {
            for (Map.Entry<String, Set<IClassInfo>> entry : implementations.entrySet()) {
                String module = entry.getKey();
                if (StringUtils.isNotEmpty(module)) {
                    Element moduleElement = parentElement.addElement(module);
                    Set<IClassInfo> classInfos = entry.getValue();
                    classInfos.forEach(
                            classInfo -> addElement(moduleElement, classInfo)
                    );
                }
            }
        }
    }

    /**
     * 功能点记录
     *
     * @param parentElement     Element
     * @param classInfoIterator Iterator<IClassInfo>
     */
    private static void addFunctionRecorder(Element parentElement, Iterator<IClassInfo> classInfoIterator) {
        if (classInfoIterator != null && classInfoIterator.hasNext()) {
            IClassInfo classInfo = classInfoIterator.next();
            addElement(parentElement, classInfo);
        }
    }

    private static void addExtraInfo(Element parentElement, IPluginExtraInfo pluginExtraInfo) {
        if (pluginExtraInfo != null) {
            addDependence(parentElement, pluginExtraInfo.getDependence());
            addAttributes(parentElement, pluginExtraInfo.getAttributes());
        }
    }

    private static void addAttributes(Element parentElement, Element attributes) {
        if (attributes != null) {
            parentElement.addElement("attributes").add(attributes);
        }
    }

    /**
     * 添加插件依赖
     *
     * @param parentElement Element
     * @param dependence    IDependence
     */
    private static void addDependence(Element parentElement, IDependence dependence) {
        if (dependence != null) {
            List<INode> items = dependence.getItems();
            if (items != null && !items.isEmpty()) {
                Element dependenceElement = parentElement.addElement(IDependence.DEPENDENCE_TAG);
                items.forEach(
                        node -> addElement(dependenceElement, node)
                );
            }
        }
    }

    private enum ElementType {
        TEXT, CDATA
    }

    private static void addElement(Element parentElement, INode node) {
        if (node != null && StringUtils.isNotEmpty(node.getTagName())) {
            Element element = parentElement.addElement(node.getTagName());
            List<IAttribute> attributes = node.getAttributes();
            if (attributes != null) {
                OrderComparator.sort(attributes);
                attributes.forEach(
                        iAttribute -> element.addAttribute(iAttribute.getName(), iAttribute.getValue())
                );
            }
        }

    }

}
