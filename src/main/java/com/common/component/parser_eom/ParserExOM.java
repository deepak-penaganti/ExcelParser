package com.common.component.parser_eom;

import java.io.InputStream;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.common.component.parser_eom.annotation.Column;
import com.common.component.parser_eom.util.ApplicationConstants;
import com.common.component.parser_eom.util.EachFieldCallback;
import com.common.component.parser_eom.util.ReflectionUtils;

/**
 * This class defines the excel parsing utilities and converting it to Java
 * Object list. ParserExOM named for Parser Excel Object Mapper
 * 
 * @author Deloitte
 *
 */
public class ParserExOM {

	private final InputStream inputStream;
	private Class classReference;
	private int fileTypeCode;

	private ParserExOM(InputStream inputStream, int fileTypeCode) {
		this.inputStream = inputStream;
		this.fileTypeCode = fileTypeCode;
	}

	private static ParserExOM mapFromExcel(InputStream inputStream, int fileTypeCode) {
		return new ParserExOM(inputStream, fileTypeCode);
	}

	public ParserExOM toObjectOf(Class classReference) {
		this.classReference = classReference;
		return this;
	}

	private String getValueByName(String name, Row row, Map<String, Integer> cells) {
		if (cells.get(name) == null) {
			return null;
		}

		Cell cell = row.getCell(cells.get(name));
		return getCellValue(cell);
	}

	private void mapName2Index(String name, Row row, Map<String, Integer> cells, boolean isMappedByUser) {
		int index = findIndexCellByName(name, row, isMappedByUser);
		if (index != -1) {
			cells.put(name, index);
		}
	}

	private void readExcelHeader(final Row row, final Map<String, Integer> cells) throws Throwable {
		ReflectionUtils.eachFields(classReference, new EachFieldCallback() {

			public void each(Field field, String name) throws Throwable {
				
				boolean isMappedByUser = false;
				if(field.isAnnotationPresent(Column.class)) {
					isMappedByUser = true;
				}
				mapName2Index(name, row, cells, isMappedByUser);
			}
		});
	}

	private Object readExcelContent(final Row row, final Map<String, Integer> cells) throws Throwable {
		final Object instance = classReference.newInstance();
		ReflectionUtils.eachFields(classReference, new EachFieldCallback() {

			public void each(Field field, String name) throws Throwable {
				ReflectionUtils.setValueOnField(instance, field, getValueByName(name, row, cells));
			}
		});

		return instance;
	}

	public <T> List<T> map() throws Throwable {

		List<T> items = new ArrayList<T>();

		try {
			Iterator<Row> rowIterator;
			int numberOfSheets;
			Workbook workbook;

			if (this.fileTypeCode == ApplicationConstants.CODE_XLS) {
				workbook = new HSSFWorkbook(this.inputStream);
				numberOfSheets = workbook.getNumberOfSheets();
			} else { // Excel 2007+
				workbook = new XSSFWorkbook(this.inputStream);
				numberOfSheets = workbook.getNumberOfSheets();
			}

			for (int index = 0; index < numberOfSheets; index++) {
				Sheet sheet = workbook.getSheetAt(index);
				rowIterator = sheet.iterator();

				Map<String, Integer> nameIndexMap = new HashMap<String, Integer>();
				while (rowIterator.hasNext()) {
					Row row = rowIterator.next();
					if (row.getRowNum() == 0) {
						readExcelHeader(row, nameIndexMap);
					} else {
						items.add((T) readExcelContent(row, nameIndexMap));
					}
				}
			}
		} finally {
			if (inputStream != null) {
				inputStream.close();
			}
		}

		return items;
	}

	private int findIndexCellByName(String name, Row row, boolean isMappedByUser) {

		Iterator<Cell> iterator = row.cellIterator();
		while (iterator.hasNext()) {
			Cell cell = iterator.next();
			
			String cellValue = (isMappedByUser) ? getCellValue(cell) : getCellValue(cell).trim().replaceAll("\\s", "");
			
			if (cellValue.equalsIgnoreCase(name)) {
				return cell.getColumnIndex();
			}
		}

		return -1;
	}

	private String getCellValue(Cell cell) {
		if (cell == null) {
			return null;
		}

		String value = "";
		switch (cell.getCellType()) {
		case Cell.CELL_TYPE_BOOLEAN:
			value += String.valueOf(cell.getBooleanCellValue());
			break;
		case Cell.CELL_TYPE_NUMERIC:
			if (DateUtil.isCellDateFormatted(cell)) {
				value += cell.getDateCellValue().toString();
			} else {
				value += new BigDecimal(cell.getNumericCellValue()).toString();
			}

			break;
		case Cell.CELL_TYPE_STRING:
			value += cell.getStringCellValue();
			break;
		}

		return value;
	}

	/**
	 * Fetches the list of objects(classReference) from the input Excel file.
	 * 
	 * @param absoluteFilePath [String]
	 * @param classReference
	 * @return List<T>
	 */
	public static <T> List<T> getExcelObjectList(InputStream inputStream, Class classReference, int fileTypeCode)
			throws Throwable {
		List<T> excelObjectList = null;

		excelObjectList = ParserExOM.mapFromExcel(inputStream, fileTypeCode).toObjectOf(classReference).map();

		return excelObjectList;
	}
}
