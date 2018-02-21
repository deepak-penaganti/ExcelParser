package com.common.component.parser_eom.converter;

import java.math.BigDecimal;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class BooleanTypeConverterTest {
	
	BooleanTypeConverter converter = new BooleanTypeConverter();
	
	@Test
	public void testBooleanTypeConverter() {
		Object objectVal = null;
		Boolean booleanVal = new Boolean(true);
		String stringVal = "Hello";
		BigDecimal bigDecimalVal1 = new BigDecimal(1);
		BigDecimal bigDecimalVal2 = new BigDecimal(2);
		Integer integerVal = new Integer(1);
		Assert.assertEquals(converter.convert(objectVal, null), Boolean.FALSE);
		Assert.assertEquals(converter.convert(booleanVal, null), Boolean.TRUE);
		Assert.assertEquals(converter.convert(stringVal, null), Boolean.FALSE);
		Assert.assertEquals(converter.convert(bigDecimalVal1, null), Boolean.TRUE);
		Assert.assertEquals(converter.convert(bigDecimalVal2, null), Boolean.FALSE);
		Assert.assertEquals(converter.convert(integerVal, null), Boolean.TRUE);
	}

}
