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
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.objectrepository.Locators;
import com.utilities.CommonFunctions;

public class FBlogin extends CommonFunctions {

	@BeforeClass // pre-condition
	public void beforeClass() {
		System.out.println("@BeforeClass block");
		chromeBrowserLaunch();
	}

	@Test
	public void fblogin() throws Exception {
		getURL("FB_URL");
		sendKeysByAnyLocator(loc.fblogin_Email_Editbox, "FB_Email");
		sendKeysByAnyLocator(loc.fblogin_Password_Editbox, "FB_Password");
		clickByAnyLocator(loc.fblogin_Login_Button);

		// driver.findElement(loc.fblogin_Email_Editbox).sendKeys(prop.getProperty("FB_Email"));
		// driver.findElement(loc.fblogin_Password_Editbox).sendKeys(prop.getProperty("FB_Password"));
		// driver.findElement(loc.fblogin_Login_Button).click();
	}

	@AfterClass // post-condition
	public void afterClass() throws Exception {
		System.out.println("@AfterClass block");
		takeScreenshot("FBlogin");
		// close the browser
		// driver.close();
		driver.quit();
	}

}
