package com.testscenarios;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.time.Duration;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import com.utilities.CommonFunctions;

public class ReadMultipleTestDataFromExcel extends CommonFunctions {

	@Test
	public void hrm_login() throws Exception {
		String results;
		String excelPath = projectDir + "\\src\\test\\resources\\testdata\\ExeclTestdata.xlsx";
		FileInputStream fi = new FileInputStream(excelPath);
		Workbook wb = new XSSFWorkbook(fi);
		Sheet ws = wb.getSheet("HRM");
		chromeBrowserLaunch();
		getURL("HRM_URL");

		for (int i = 1; i < 7; i++) {
			Row r = ws.getRow(i);
			Cell username = r.getCell(0);
			Cell password = r.getCell(1);
			System.out.println(username);
			System.out.println(password);
			driver.findElement(loc.hrm_username_Editbox).sendKeys(username.getStringCellValue());
			driver.findElement(loc.hrm_password_Editbox).sendKeys(password.getStringCellValue());
			clickByAnyLocator(loc.hrm_Login_Button);

			// Verify Error message is displayed or not
			// if error message is displayed,then print In-Valid credentials in console
			// else run the program to click on logout button
			if (driver.findElements(loc.hrm_ErrorMsg).size() > 0) {
				results = "In-Valid credentials";

				// Send the results to 2nd column
				Cell resuData = r.createCell(2);
				resuData.setCellValue(results);

			} else {
				clickByAnyLocator(loc.hrm_profilepicture_icon);
				// implicitWait(30);
				Thread.sleep(5000);
				clickByAnyLocator(loc.hrm_Logout_linkText);
				Thread.sleep(5000);
				results = "Valid credentials";

				// Send the results to 2nd column
				Cell resuData = r.createCell(2);
				resuData.setCellValue(results);
			}

			System.out.println("****************************************");
			System.out.println("Given username & password  results is : " + results);
			System.out.println("****************************************");

		}
		// Create Result Headers
		Row res = ws.createRow(0);
		Cell usernameLabel = res.createCell(0);
		usernameLabel.setCellValue("UserName");
		Cell passwordLabel = res.createCell(1);
		passwordLabel.setCellValue("Password");
		Cell resultLabel = res.createCell(2);
		resultLabel.setCellValue("Results");

		// Send the total results to Existing excel sheet
		FileOutputStream fo = new FileOutputStream(excelPath);
		wb.write(fo);
		wb.close();
		fo.close();
	}
}
