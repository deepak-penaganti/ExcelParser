package com.common.component.parser_eom.converter;

import java.sql.Timestamp;
import java.util.Date;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;


@RunWith(MockitoJUnitRunner.class)
public class DateTypeConverterTest {

	@Test
	public void testDateTypeConverter() {
		DateTypeConverter converter = new DateTypeConverter();
		Object value = null;
		Timestamp timeStampVal = new Timestamp(100);
		Date dateVal = new Date(100);
		String stringVal = new String("10/2/2016");
		String pattern = "mm/dd/yyyy";
		Date date1 = converter.convert(value, null);
		Date date2 = converter.convert(timeStampVal, null);
		Date date3 =converter.convert(dateVal, null);
		Date date4 =converter.convert(stringVal, pattern);
		Double doubleVal = new Double(10);
		Date date5 = converter.convert(doubleVal, null);
		String stringValue = new String("Hello1234");
		Date date6 =converter.convert(stringVal, null);
		Assert.assertNull(date1);
		Assert.assertEquals(date2.getTime(),100);
		Assert.assertEquals(date3.getTime(),100);
		Assert.assertEquals(date4.getTime(),1451673600000l);
		Assert.assertNull(date5);
		Assert.assertNull(date6);
	}
}

