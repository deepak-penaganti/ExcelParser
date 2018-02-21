package com.common.component.excel.write;

import java.util.HashMap;
import java.util.Map;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.JSONArray;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class WriteToExcelTest {
	
	@Test
	public void testWriteToExcel() {
		JSONArray jsonArray = new JSONArray();
		Map<Integer, Integer> test = new HashMap<Integer, Integer>();
		test.put(1, 1);
		test.put(1,2);
        for (int i = 0; i < 10; i++)
        {
          jsonArray.put(i, test);
        }
        XSSFWorkbook book = WriteToExcel.write(jsonArray);
        Assert.assertNotNull(book);
	}

}
