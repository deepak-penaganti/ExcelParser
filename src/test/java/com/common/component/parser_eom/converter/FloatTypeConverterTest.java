package com.common.component.parser_eom.converter;

import java.math.BigDecimal;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class FloatTypeConverterTest {
	private static final double DELTA = 1e-15;
	
	@Test
	public void testFloatTypeConverter() {
		FloatTypeConverter converter = new FloatTypeConverter();
		BigDecimal decimal = new BigDecimal(100);
		Float floatValue = new Float(100);
		String stringValue = new String("Hello");
		Object val = null;
		Assert.assertNull(converter.convert(val));
		Assert.assertEquals((float)converter.convert(decimal, null), 100, DELTA);
		Assert.assertEquals((float)converter.convert(floatValue, null),100, DELTA);
		Assert.assertNull(converter.convert(stringValue, null));
	}

}
