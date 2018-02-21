package com.common.component.parser_eom.converter;

import java.math.BigDecimal;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class IntegerTypeConverterTest {

	@Test
	public void testIntegerTypeConverter() {
		IntegerTypeConverter converter = new IntegerTypeConverter();		
		BigDecimal decimal = new BigDecimal(100);
		Integer intValue = new Integer(100);
		String stringValue = new String("Hello");
		Object val = null;
		Assert.assertNull(converter.convert(val));
		Assert.assertEquals((int)converter.convert(decimal, null), 100);
		Assert.assertEquals((int)converter.convert(intValue, null),100);
		Assert.assertNull(converter.convert(stringValue, null));
	}
}
