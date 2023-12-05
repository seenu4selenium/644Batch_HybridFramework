package com.testscenarios;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.Set;

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

public class AdvanceConcepts extends CommonFunctions {

	@BeforeClass // pre-condition
	public void beforeClass() {
		System.out.println("@BeforeClass block");
		chromeBrowserLaunch();
	}

	// @AfterMethod
	public void afterMethod(ITestResult res) throws Exception {
		screenshotWithStatus(res);
	}

	// @AfterClass // post-condition
	public void afterClass() throws Exception {
		System.out.println("@AfterClass block");
		// takeScreenshot("FBlogin");
		// close the browser
		// driver.close();
		driver.quit();
	}

	// @Test
	public void tc_01_AlretHandling() throws Exception {
		getURL("Alert_URL");
		clickByAnyLocator(loc.alert_alertButton);
		// Click on Ok button on Alert
		alertHandleByAccept();
		// Click on 3rd ClickMe button
		clickByAnyLocator(loc.alert_confirmButton);
		alertHandleByDismiss();
	}

	@Test
	public void tc_02_PopUpHandling() throws Exception {
		getURL("JRI_URL");
		System.out.println(driver.getWindowHandle());
		clickByAnyLocator(loc.jri_Facebook_button);
		Thread.sleep(4000);
		//Switch to child window
		navigateToPopupWindow();
		//type email then click on login button on Popupwindow
		sendKeysByAnyLocator(loc.jri_Facebook_popup_emailEditbox, "fbEmail");
		clickByAnyLocator(loc.jri_Facebook_popup_loginButton);
	
		//close only popup window
		driver.close();//only current window will close
		//driver.quit();//all the related windows will close
		
	}

}
