package com.testscenarios;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

import com.utilities.CommonFunctions;

public class ReadDataFromExcel extends CommonFunctions {
	@Test
	public void f() throws Exception {
		// read the test data from Excel sheet
		// Get the Project location
		String excelPath = projectDir + "\\src\\test\\resources\\testdata\\ExeclTestdata.xlsx";
		FileInputStream fi = new FileInputStream(excelPath);
		Workbook wb = new XSSFWorkbook(fi);
		Sheet ws = wb.getSheet("Sheet1");
		Row r = ws.getRow(3);
		Cell c = r.getCell(0);

		Row r1 = ws.getRow(5);
		Cell c1 = r1.getCell(1);

		Row r2 = ws.getRow(1);
		Cell c2 = r2.getCell(2);
		// 3rd row, 0th column value, want to Print into console
		System.out.println(c.getStringCellValue());
		System.out.println(c1.getStringCellValue());
		System.out.println(c2.getStringCellValue());

//		chromeBrowserLaunch();
//		getURL("HRM_URL");
//		sendKeysByAnyLocator(loc.hrm_username_Editbox, "Username");
//		sendKeysByAnyLocator(loc.hrm_password_Editbox, "Password");
//		clickByAnyLocator(loc.hrm_Login_Button);
//		// implicitWait(30);
//		Thread.sleep(5000);
//		clickByAnyLocator(loc.hrm_profilepicture_icon);
//		// implicitWait(30);
//		Thread.sleep(5000);
//		clickByAnyLocator(loc.hrm_Logout_linkText);
//		Thread.sleep(5000);
	}

	@Test
	public void hrm_login() throws Exception {
		// read the test data from Excel sheet
				// Get the Project location
				String excelPath = projectDir + "\\src\\test\\resources\\testdata\\ExeclTestdata.xlsx";
				FileInputStream fi = new FileInputStream(excelPath);
				Workbook wb = new XSSFWorkbook(fi);
				Sheet ws = wb.getSheet("HRM");
				
				Row r = ws.getRow(1);
				Cell username = r.getCell(0);
				Cell password = r.getCell(1);
				System.out.println(username);
				System.out.println(password);
			
		

				chromeBrowserLaunch();
				getURL("HRM_URL");
				
				driver.findElement(loc.hrm_username_Editbox).sendKeys(username.getStringCellValue());
				driver.findElement(loc.hrm_password_Editbox).sendKeys(password.getStringCellValue());

				clickByAnyLocator(loc.hrm_Login_Button);
				// implicitWait(30);
				Thread.sleep(5000);
				clickByAnyLocator(loc.hrm_profilepicture_icon);
				// implicitWait(30);
				Thread.sleep(5000);
				clickByAnyLocator(loc.hrm_Logout_linkText);
				Thread.sleep(5000);
	}
}
