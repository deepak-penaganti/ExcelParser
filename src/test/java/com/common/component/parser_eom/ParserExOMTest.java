package com.common.component.parser_eom;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import com.common.component.parser_eom.util.ApplicationConstants;

import junit.framework.Assert;

@RunWith(MockitoJUnitRunner.class)
public class ParserExOMTest {

	@Test
	public void testParserExOMTest() throws Throwable {
		InputStream ExcelFileToRead = new FileInputStream("src\\test\\java\\com\\common\\component\\parser_eom\\test.xls");
		List<TestClass> result = ParserExOM.getExcelObjectList(ExcelFileToRead, TestClass.class, ApplicationConstants.CODE_XLS);	
		Assert.assertEquals(result.get(0).getId(), 1);
		String userName = "Rohit";
		Assert.assertEquals(result.get(0).getUserName(), userName);
	}
}
