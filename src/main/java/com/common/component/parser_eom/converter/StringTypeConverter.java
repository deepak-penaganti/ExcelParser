package com.common.component.parser_eom.converter;

public class StringTypeConverter implements TypeConverter<String> {

    public String convert(Object value, String... pattern) {
        if (value instanceof String) {
            return ((String) value).trim();
        }
        
        return null;
    }

}
