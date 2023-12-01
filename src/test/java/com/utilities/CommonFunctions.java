package com.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.safari.SafariDriver;

import com.objectrepository.Locators;

public class CommonFunctions {
	// Here QA will design the re-usable Scripts for any project
	public WebDriver driver;
	public String ScreenshotFolder = ".\\screenshots\\";
	// Create Locators class object
	public Locators loc = new Locators();

	// create Properties java class object
	public Properties prop = new Properties();

	/** Browser launch methods **/
	public void chromeBrowserLaunch() {
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		System.out.println("*****Chrome Browser Launched Successfully");
	}

	public void firefoxBrowserLaunch() {
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		System.out.println("*****firefox Browser Launched Successfully");
	}

	public void edgeBrowserLaunch() {
		driver = new EdgeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		System.out.println("*****edge Browser Launched Successfully");
	}

	public void safariBrowserLaunch() {
		driver = new SafariDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		System.out.println("*****safari Browser Launched Successfully");
	}

	/*** Date & time */
	public String timeStamp() {
		Date currentDateAndTime = new Date();
		DateFormat df = new SimpleDateFormat("yyyyMMMdd_HHmmss");
		String timeStamp = df.format(currentDateAndTime);
		System.out.println("*****timeStamp is: " + timeStamp);
		return timeStamp;
	}

	public void takeScreenshot(String fileName) throws Exception {
		File myScreenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileHandler.copy(myScreenshot, new File(ScreenshotFolder + fileName + timeStamp() + ".png"));
		System.out.println("*****screenshot placed to screenshotfolder Successfully");
	}

	/** Get URL ***/
	public void getURL(String URL) throws Exception {
		FileInputStream fi = new FileInputStream(".\\src\\test\\resources\\testdata\\inputs.properties");
		// load the property file into a local object
		prop.load(fi);
		driver.get(prop.getProperty(URL));
		System.out.println("*****URL placed on Browser  Successfully");
	}

	/*** SendKeys by using any Locator ******/
	public void sendKeysByAnyLocator(By locator, String inputData) {
		WebElement ele = driver.findElement(locator);
		// verify the Given Locator is displayed on current web page?
		if (driver.findElements(locator).size() > 0) {
			System.out.println("****"+locator+" is displayed on screen");
			// Check the given web element is Enabled state?
			if (ele.isEnabled()) {
				System.out.println("****"+locator+" is enabled state");
				// Clear the existing data from Edit box if any?
				ele.clear();
				//send the test data to edit box
				ele.sendKeys(prop.getProperty(inputData));				
			} else {
				System.out.println("****"+locator+" is NOT enabled state");
			}
		} else {
			System.out.println("****"+locator+" is NOT displayed on screen, please check the locator");
		}		
	}
	
	/*** Click by using any Locator on Button/link/radiobutton/Checkbox/image/... ******/
	public void clickByAnyLocator(By locator) {
		WebElement ele = driver.findElement(locator);
		// verify the Given Locator is displayed on current web page?
		if (driver.findElements(locator).size() > 0) {
			System.out.println("****"+locator+" is displayed on screen");
			// Check the given web element is Enabled state?
			if (ele.isEnabled()) {
				System.out.println("****"+locator+" is enabled state");
				// click on the web element
				ele.click();
			} else {
				System.out.println("****"+locator+" is NOT enabled state");
			}
		} else {
			System.out.println("****"+locator+" is NOT displayed on screen, please check the locator");
		}
	}
}
