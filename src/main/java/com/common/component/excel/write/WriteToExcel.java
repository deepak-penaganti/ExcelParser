package com.common.component.excel.write;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.JSONArray;

import com.github.wnameless.json.flattener.JsonFlattener;

public class WriteToExcel {


	/**
	 * Writes the JSON Array to Excel Workbook.
	 * 
	 * @param jsonArray
	 *            [JSONArray]
	 * @return workbook [org.apache.poi.xssf.usermodel.XSSFWorkbook]
	 */
	public static XSSFWorkbook write(JSONArray jsonArray) {
		XSSFWorkbook workbook = new XSSFWorkbook();
		Sheet sheet = workbook.createSheet();
		Row dataRow = null;
		Row headerRow = null;

		Map<String, Integer> headerIndexMap = new HashMap<String, Integer>();
		Set<String> headersSet = new HashSet<String>();
		int headerColumnCount = 0;
		int headerRowCount = 0;
		Cell headerCell = null;
		headerRow = sheet.createRow(headerRowCount);

		// Converts JSON Array to a map
		// More details : https://github.com/wnameless/json-flattener
		Map<String, Object> jsonMap = JsonFlattener.flattenAsMap(jsonArray.toString());
		populateHeaders(jsonMap, headersSet);

		// Populating the header row and creating the index map
		for (String header : headersSet) {
			headerCell = headerRow.createCell(headerColumnCount);
			headerCell.setCellValue(header);
			headerIndexMap.put(header, headerColumnCount);
			headerColumnCount++;
		}
		populateDataRows(jsonMap, sheet, dataRow, headerIndexMap);
		return workbook;
	}

	/**
	 * Populates the unique headers.
	 * 
	 * @param jsonMap
	 *            [Map<String, Object>]
	 * @param headersSet
	 * 			  [Set<String>]
	 * @return void
	 */
	private static void populateHeaders(Map<String, Object> jsonMap, Set<String> headersSet) {
		Set<String> jsonKeySet = jsonMap.keySet();
		for (String jsonKey : jsonKeySet) {
			String[] splitKeys = jsonKey.split("\\.");
			headersSet.add(splitKeys[1]);
		}
	}

	/**
	 * Populates the data values under proper headers.
	 * 
	 * @param jsonMap
	 *            [Map<String, Object>]
	 * @param sheet [Sheet]
	 * 
	 * @param dataRow [Row]
	 * 
	 * @param headerIndexMap [Map<String, Integer>]
	 *            
	 * @return void
	 */
	private static void populateDataRows(Map<String, Object> jsonMap, Sheet sheet, Row dataRow,
			Map<String, Integer> headerIndexMap) {
		Iterator<Entry<String, Object>> it = jsonMap.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<String, Object> entry = (Map.Entry<String, Object>) it.next();
			String[] header = entry.getKey().split("\\.");
			int columnNum = headerIndexMap.get(header[1]);
			// Extracting the proper row number from the key string
			int rowNum = Integer.parseInt(entry.getKey().substring(1, 2)) + 1;
			if (sheet.getRow(rowNum) == null) {
				dataRow = sheet.createRow(rowNum);
			}
			Cell dataCell = dataRow.createCell(columnNum);
			dataCell.setCellValue(entry.getValue().toString());
		}
	}
}
