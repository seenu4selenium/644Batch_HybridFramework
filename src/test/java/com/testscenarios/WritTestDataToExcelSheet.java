package com.testscenarios;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.Test;

import com.utilities.CommonFunctions;

public class WritTestDataToExcelSheet extends CommonFunctions {
	@Test
	public void f() throws Exception {
		// Create New excle sheet
		String excelPath = projectDir + "\\src\\test\\resources\\testdata\\NewExeclTestdata.xlsx";
		FileOutputStream fo = new FileOutputStream(excelPath);
		Workbook ww = new XSSFWorkbook();
		Sheet s = ww.createSheet("Output");

		// prepare the code wht data you want to send to Excel sheet
		Row r = s.createRow(2);
		Cell test = r.createCell(3);
		// 2row 3coloumn
		test.setCellValue("Selenium");

		// Push the created code to Excle sheet
		ww.write(fo);
		ww.close();
		fo.close();

	}
}
