package com.voc.fr.tool.annotation.design;

import com.voc.fr.tool.annotation.EnableFinePlugin;
import com.voc.fr.tool.annotation.Module;
import com.voc.fr.tool.api.FineModule;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 服务器数据集
 *
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2019/04/03 13:48
 */
@Retention(RetentionPolicy.SOURCE)
@Target({ElementType.TYPE})
@Module(FineModule.DESIGN)
@EnableFinePlugin(auto = true)
public @interface ServerTableDataDefineProvider {

    /**
     * 服务器数据集接口实现类
     *
     * @return Class
     */
    Class<?> value() default Void.class;

}
