package com.common.component.parser_eom.converter;

import java.math.BigDecimal;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;


@RunWith(MockitoJUnitRunner.class)
public class DoubleTypeConverterTest {
	
	private static final double DELTA = 1e-15;
	@Test
	public void testDoubleTypeConverter() {
		DoubleTypeConverter converter = new DoubleTypeConverter();
		BigDecimal decimal = new BigDecimal(100);
		Double doubleValue = new Double(100);
		String stringValue = new String("Hello");
		Object val = null;
		Assert.assertNull(converter.convert(val));
		Assert.assertEquals((double)converter.convert(decimal, null), 100, DELTA);
		Assert.assertEquals((double)converter.convert(doubleValue, null),100, DELTA);
		Assert.assertNull(converter.convert(stringValue, null));
	}

}
