package com.common.component.parser_eom.converter;

public interface TypeConverter<T> {

    T convert(Object value, String... pattern);
}
