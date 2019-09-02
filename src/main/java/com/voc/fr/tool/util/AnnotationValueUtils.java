package com.voc.fr.tool.util;

import com.sun.tools.javac.code.Attribute;
import lombok.extern.slf4j.Slf4j;

import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.AnnotationValue;
import javax.lang.model.element.Element;
import javax.lang.model.element.ExecutableElement;
import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2019/04/03 15:25
 */
@Slf4j
public class AnnotationValueUtils {

    /**
     * @param element         元素
     * @param annotationClass 注解类
     * @param key             键名（注解内方法名）
     * @return Object
     */
    public static Object getAnnotationValue(Element element, Class<? extends Annotation> annotationClass, String key) {
        AnnotationMirror annotationMirror = getAnnotationMirror(element, annotationClass);
        AnnotationValue annotationValue = getAnnotationValue(annotationMirror, key);
        return getAnnotationValue(annotationValue);
    }


    /**
     * @param element         元素
     * @param annotationClass 注解类
     * @param key             键名（注解内方法名）
     * @return Object
     */
    public static Object getQualifiedClassName(Element element, Class<? extends Annotation> annotationClass, String key) {
        Object annotationValue = getAnnotationValue(element, annotationClass, key);
        if (annotationValue instanceof List) {
            List<String> s = new ArrayList<>();
            List values = (List) annotationValue;
            for (Object o : values) {
                if (o instanceof Attribute.Class) {
                    Attribute.Class c = (Attribute.Class) o;
                    s.add(c.classType.toString());
                }
            }
            return s;
        } else if (annotationValue != null) {
            return annotationValue.toString();
        }
        return null;
    }


    /**
     * 获取元素指定注解的信息
     *
     * @param element         元素
     * @param annotationClass 注解类
     * @return AnnotationMirror
     */
    public static AnnotationMirror getAnnotationMirror(Element element, Class<? extends Annotation> annotationClass) {
        for (AnnotationMirror annotationMirror : element.getAnnotationMirrors()) {
            if (annotationMirror.getAnnotationType().toString().equals(annotationClass.getName())) {
                return annotationMirror;
            }
        }
        return null;
    }

    /**
     * 获取注解指定键的值
     *
     * @param annotationMirror AnnotationMirror
     * @param key              键名（注解内方法名）
     * @return AnnotationValue
     */
    public static AnnotationValue getAnnotationValue(AnnotationMirror annotationMirror, String key) {
        if (annotationMirror != null) {
            for (Map.Entry<? extends ExecutableElement, ? extends AnnotationValue> entry : annotationMirror.getElementValues().entrySet()) {
                if (entry.getKey().getSimpleName().toString().equals(key)) {
                    return entry.getValue();
                }
            }
        }
        return null;
    }

    /**
     * 获取注解值
     *
     * @param annotationValue AnnotationValue
     * @return Object
     */
    public static Object getAnnotationValue(AnnotationValue annotationValue) {
        return annotationValue != null ? annotationValue.getValue() : null;
    }

}
