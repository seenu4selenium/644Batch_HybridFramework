package com.testscenarios;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.objectrepository.Locators;
import com.utilities.CommonFunctions;

public class OrangeHRM extends CommonFunctions {

	@BeforeClass // pre-condition
	public void beforeClass() {
		System.out.println("@BeforeClass block");
		chromeBrowserLaunch();
	}

	@AfterMethod
	public void afterMethod(ITestResult res) throws Exception {
		screenshotWithStatus(res);
	}

	@AfterClass // post-condition
	public void afterClass() throws Exception {
		System.out.println("@AfterClass block");
		// takeScreenshot("OrangeHRM");
		// close the browser
		// driver.close();
		driver.quit();
	}

	@Test
	public void ohrm_login() throws Exception {
		// design the code from here
//		1. OrangeHRM login with valid creadentials
//		https://opensource-demo.orangehrmlive.com/web/index.php/auth/login
//		Username : Admin
//		Password : admin123
//		2. After login, click on Profile icon
//		3. Click on Logout text (Last option)

		getURL("HRM_URL");
		sendKeysByAnyLocator(loc.hrm_username_Editbox, "Username");
		sendKeysByAnyLocator(loc.hrm_password_Editbox, "Password");
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
