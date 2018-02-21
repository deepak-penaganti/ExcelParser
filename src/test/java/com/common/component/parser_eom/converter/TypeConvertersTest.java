package com.common.component.parser_eom.converter;

import java.util.Map;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class TypeConvertersTest {
	
	@Test
	public void testTypeConverterTest() {
		Map<Class, TypeConverter> converter = TypeConverters.getConverter();		
		TypeConverter booleanConverter = converter.get(Boolean.class);
		Assert.assertNotNull(booleanConverter);	
		Assert.assertEquals(true, booleanConverter instanceof BooleanTypeConverter);
	}
}

