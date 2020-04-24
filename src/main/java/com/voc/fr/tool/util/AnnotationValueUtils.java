package com.voc.fr.tool.util;

import com.sun.tools.javac.code.Attribute;
import com.sun.tools.javac.code.Scope;
import com.sun.tools.javac.code.Symbol;
import com.sun.tools.javac.util.Pair;
import lombok.extern.slf4j.Slf4j;

import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.Element;
import java.lang.annotation.Annotation;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2019/04/03 15:25
 */
@Slf4j
public class AnnotationValueUtils {

    public static Attribute.Compound getAttributeCompound(Element element, Class<? extends Annotation> annotationClass) {
        for (AnnotationMirror annotationMirror : element.getAnnotationMirrors()) {
            if (annotationMirror instanceof Attribute.Compound
                    && annotationMirror.getAnnotationType().toString().equals(annotationClass.getName())) {
                return (Attribute.Compound) annotationMirror;
            }
        }
        return null;
    }

    public static Map<Symbol.MethodSymbol, Attribute> getAllValues(Attribute.Compound compound) {
        LinkedHashMap<Symbol.MethodSymbol, Attribute> map = new LinkedHashMap<>();
        if (compound == null) {
            return map;
        }

        Symbol.ClassSymbol classSymbol = (Symbol.ClassSymbol) compound.type.tsym;

        /* 1. 默认值 */
        for (Scope.Entry entry = classSymbol.members().elems; entry != null; entry = entry.sibling) {
            if (entry.sym.kind == 16) {
                Symbol.MethodSymbol methodSymbol = (Symbol.MethodSymbol) entry.sym;
                Attribute attribute = methodSymbol.getDefaultValue();
                if (attribute != null) {
                    map.put(methodSymbol, attribute);
                }
            }
        }

        /* 2. 定义的值 */
        for (Pair<Symbol.MethodSymbol, Attribute> pair : compound.values) {
            map.put(pair.fst, pair.snd);
        }

        return map;
    }

    public static Object getValue(Element element, Attribute attribute, boolean isClassArray) {
        if (attribute instanceof Attribute.Constant) {
            Attribute.Constant constantAttribute = (Attribute.Constant) attribute;
            return constantAttribute.getValue();
        } else if (attribute instanceof Attribute.Enum) {
            Attribute.Enum enumAttribute = (Attribute.Enum) attribute;
            return enumAttribute.getValue().toString();
        } else if (attribute instanceof Attribute.Class) {
            Attribute.Class classAttribute = (Attribute.Class) attribute;
            String qualifiedClassName = classAttribute.getValue().toString();
            /* 如果 value 为 Void.class，则 value 值取当前被标记类元素的类完全限定名*/
            if (Void.class.getCanonicalName().equals(qualifiedClassName)
                    || Void.TYPE.getCanonicalName().equals(qualifiedClassName)) {
                qualifiedClassName = element.asType().toString();
            }
            return qualifiedClassName;
        } else if (attribute instanceof Attribute.Array) {
            Attribute.Array arrayAttribute = (Attribute.Array) attribute;
            List<Attribute> values = arrayAttribute.getValue();
            Object[] objects = values.stream().map(attr -> getValue(element, attr, isClassArray)).toArray();
            if (objects.length == 0 && isClassArray) {
                return new String[]{element.asType().toString()};
            }
            return objects;
        } else if (attribute instanceof Attribute.Error) {
            return null;
        }
        return null;
    }

    public static Map<String, Object> getAllReflectedValues(Element element, Class<? extends Annotation> annotationClass) {
        Attribute.Compound compound = getAttributeCompound(element, annotationClass);
        Map<Symbol.MethodSymbol, Attribute> map = getAllValues(compound);
        Map<String, Object> result = new LinkedHashMap<>();
        for (Map.Entry<Symbol.MethodSymbol, Attribute> entry : map.entrySet()) {
            Symbol.MethodSymbol methodSymbol = entry.getKey();
            boolean isClassArray = "java.lang.Class<?>[]".equals(methodSymbol.getReturnType().toString());
            String key = methodSymbol.name.toString();
            Object value = getValue(element, entry.getValue(), isClassArray);
            result.put(key, value);
        }
        return result;
    }


}
