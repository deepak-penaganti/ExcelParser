package com.common.component.parser_eom.converter;

import java.math.BigDecimal;

public class FloatTypeConverter implements TypeConverter<Float> {

    public Float convert(Object value, String... pattern) {
        if (value == null) {
            return null;
        }

        if (value instanceof Float) {
            return (Float) value;
        }

        if (value instanceof String) {
            try {
                return Float.valueOf(((String) value).trim());
            } catch (Exception ex) {
                return null;
            }
        }

        if (value instanceof BigDecimal) {
            return ((BigDecimal) value).floatValue();
        }

        return null;
    }

}
