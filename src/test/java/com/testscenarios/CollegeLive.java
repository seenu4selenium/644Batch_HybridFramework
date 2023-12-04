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
import org.openqa.selenium.support.ui.Select;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.objectrepository.Locators;
import com.utilities.CommonFunctions;

public class CollegeLive extends CommonFunctions {

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
		getURL("clg_URL");
		// new Select(driver.findElement(By.id("sdgs"))).selectByVisibleText("adsgdg");
		selectByVisibleText(loc.clg_CountryofCitizenship_dropdown, "country_DropdownValue");
		selectByVisibleText(loc.clg_iama_dropdown, "iAMa_DropdownValue");
	}

}
