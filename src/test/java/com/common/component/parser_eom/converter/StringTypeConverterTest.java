package com.common.component.parser_eom.converter;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class StringTypeConverterTest {
	
	@Test
	public void testStringTypeConverter() {
		StringTypeConverter converter = new StringTypeConverter();
		String value = "test";
		Assert.assertEquals(converter.convert(value, null), value);
		Assert.assertNull(converter.convert(null, null));
	}

}
