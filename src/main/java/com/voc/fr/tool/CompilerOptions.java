package com.voc.fr.tool;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import javax.annotation.processing.ProcessingEnvironment;
import javax.tools.Diagnostic;
import java.text.MessageFormat;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2020/04/22 09:22
 */
@Slf4j
@Getter
@Setter
public class CompilerOptions {
    public static final String ENV_VERSION_OPTION = "fine.env.version";
    public static final String PLUGIN_VERSION_OPTION = "fine.plugin.version";
    public static final String PLUGIN_XML_DIR_OPTION = "fine.plugin.xml.dir";

    private ProcessingEnvironment processingEnv;

    /**
     * 插件适配的报表版本
     */
    private String envVersion;

    /**
     * 插件版本
     */
    private String pluginVersion;

    /**
     * 插件描述文件目录
     */
    private String pluginXmlDir;

    private boolean valid;

    public CompilerOptions(ProcessingEnvironment processingEnv) {
        this.processingEnv = processingEnv;
    }

    public boolean check() {
        this.envVersion = processingEnv.getOptions().get(ENV_VERSION_OPTION);
        this.pluginVersion = processingEnv.getOptions().get(PLUGIN_VERSION_OPTION);
        this.pluginXmlDir = processingEnv.getOptions().get(PLUGIN_XML_DIR_OPTION);
        if (StringUtils.isEmpty(envVersion)) {
            String message = MessageFormat.format("请确认插件使用环境参数是否正确 compilerArgs -A{0}=[value]", ENV_VERSION_OPTION);
            processingEnv.getMessager().printMessage(Diagnostic.Kind.ERROR, message);
            return false;
        }
        if (StringUtils.isEmpty(pluginVersion)) {
            String message = MessageFormat.format("请确认插件版本参数设置是否正确 compilerArgs -A{0}=[value]", PLUGIN_VERSION_OPTION);
            processingEnv.getMessager().printMessage(Diagnostic.Kind.ERROR, message);
            return false;
        }
        if (StringUtils.isEmpty(pluginXmlDir)) {
            String message = MessageFormat.format("请确认插件版本参数设置是否正确 compilerArgs -A{0}=[value]", PLUGIN_XML_DIR_OPTION);
            processingEnv.getMessager().printMessage(Diagnostic.Kind.ERROR, message);
            return false;
        }
        if (log.isInfoEnabled()) {
            log.info(this.toString());
        }
        this.valid = true;
        return true;
    }


    @Override
    public String toString() {
        return MessageFormat.format("环境版本号：{0}, 插件版本号：{1} , 插件描述文件目录：{2}",
                this.envVersion, this.pluginVersion, this.pluginXmlDir);
    }
}
