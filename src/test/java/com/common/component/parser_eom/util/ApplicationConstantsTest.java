package com.common.component.parser_eom.util;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ApplicationConstantsTest {
	
	@Test
	public void testApplicationConstants() {
		
		String VALID_DATE_FORMAT = "MMddyyyy";
		String JAVA_DATE_TYPE = "java.util.Date";
		Assert.assertEquals(ApplicationConstants.CODE_XLS,1);
		Assert.assertEquals(ApplicationConstants.CODE_XLSX,2);
		Assert.assertEquals(ApplicationConstants.JAVA_DATE_TYPE,JAVA_DATE_TYPE);
		Assert.assertEquals(ApplicationConstants.VALID_DATE_FORMAT,VALID_DATE_FORMAT);
		
	}

}
