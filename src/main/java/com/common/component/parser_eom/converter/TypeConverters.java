package com.common.component.parser_eom.converter;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Deliotte
 */
public class TypeConverters {

    private static Map<Class, TypeConverter> converter;

    private static void registerConverter() {
        converter = new HashMap<Class, TypeConverter>();
        converter.put(String.class, new StringTypeConverter());
        converter.put(Integer.class, new IntegerTypeConverter());
        converter.put(Double.class, new DoubleTypeConverter());
        converter.put(Boolean.class, new BooleanTypeConverter());
        converter.put(Date.class, new DateTypeConverter());
        converter.put(Float.class, new FloatTypeConverter());
        converter.put(int.class, new IntegerTypeConverter());
        converter.put(double.class, new DoubleTypeConverter());
        converter.put(float.class, new FloatTypeConverter());
        converter.put(boolean.class, new BooleanTypeConverter());
    }

    public static Map<Class, TypeConverter> getConverter() {
        if (converter == null) {
            registerConverter();
        }

        return converter;
    }
}
