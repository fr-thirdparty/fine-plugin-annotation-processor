<#-- @ftlvariable name="interface" type="java.util.Map<java.lang.String, java.util.Set<com.voc.fr.tool.entity.FunctionClassInfo>>" -->
<#-- @ftlvariable name="plugin" type="com.voc.fr.tool.api.FineModule.PluginBaseInfo" -->
<?xml version="1.0" encoding="UTF-8"?>
<plugin>
    <id>${plugin.id!}</id>
    <name><![CDATA[${plugin.name!}]]></name>
    <active>${plugin.active!?string('yes','no')}</active>
    <version>${plugin.version!}</version>
    <env-version>${plugin.envVersion!}</env-version>
    <vendor>${plugin.vendor!}</vendor>
    <jartime>${plugin.jarTime!}</jartime>
    <price>${plugin.price!?string('#')}</price>
    <description><![CDATA[${plugin.description!}]]></description>
    <change-notes><![CDATA[
<#list plugin.changeNotes as change>
        <p>[${change.dateOf}] ${change.content}</p>
</#list>
        ]]>
    </change-notes>
<#if plugin.mainPackage?? && "" != plugin.mainPackage>
    <main-package>${plugin.mainPackage!}</main-package>
</#if>
<#if plugin.functionRecorder?? && "" != plugin.functionRecorder>
    <function-recorder class="${plugin.functionRecorder!}"/>
</#if>
<#list interface?keys as key>
    <${key}>
<#list interface["${key}"] as info>
<#if 'ClassInfo' == info.getClass().getSimpleName()>
        <${info.interfaceTagName} class="${info.classCanonicalName}"/>
<#else>
        <${info.interfaceTagName} class="${info.classCanonicalName}" name="${info.name!}" description="${info .description!}"/>
</#if>
</#list>
    </${key}>
</#list>
</plugin>
