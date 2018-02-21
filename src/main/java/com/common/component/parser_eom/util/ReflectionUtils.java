package com.common.component.parser_eom.util;

import static com.common.component.parser_eom.util.CollectionUtils.isEmpty;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Map;

import com.common.component.parser_eom.annotation.Column;
import com.common.component.parser_eom.converter.TypeConverter;
import com.common.component.parser_eom.converter.TypeConverters;

public class ReflectionUtils {
    
    private static String toUpperCaseFirstCharacter(String str) {
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }

    public static void setValueOnField(Object instance, Field field, Object value) throws Exception {
        Class clazz = instance.getClass();
        String setMethodName = "set" + toUpperCaseFirstCharacter(field.getName());

        for (Map.Entry<Class, TypeConverter> entry : TypeConverters.getConverter().entrySet()) {
            if (field.getType().equals(entry.getKey())) {
                Method method = clazz.getDeclaredMethod(setMethodName, entry.getKey());
                Column column = field.getAnnotation(Column.class);
                
                String tempDatePattern ="";
                String pattern = null;
                if(ApplicationConstants.JAVA_DATE_TYPE.equals(field.getType().getName()))   
				{
                	tempDatePattern = ApplicationConstants.VALID_DATE_FORMAT;
				}
                
                if(column == null)
                {
                	if(ApplicationConstants.JAVA_DATE_TYPE.equals(field.getType().getName()))
                	{
                		pattern = tempDatePattern;
                		value = ((String) value).replaceAll("/", "");
                	}
                	else
                		pattern = null;
                }
                else
                {
                	pattern = column.pattern();
                }
					
                Object objVal = entry.getValue().convert(value, pattern);
                
                if(objVal != null) {                	
                	method.invoke(instance, objVal);
                }
            }
        }
    }

    public static void eachFields(Class clazz, EachFieldCallback callback) throws Throwable {
        Field[] fields = clazz.getDeclaredFields();
        if (!isEmpty(fields)) {
            for (Field field : fields) {
                callback.each(
                        field,
                        field.isAnnotationPresent(Column.class)
                        ? field.getAnnotation(Column.class).name()
                        : field.getName()
                );
            }
        }
    }
}
