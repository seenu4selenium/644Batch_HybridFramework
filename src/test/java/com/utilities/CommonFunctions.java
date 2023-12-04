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
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.ITestResult;

import com.objectrepository.Locators;

public class CommonFunctions {
	// Here QA will design the re-usable Scripts for any project
	public WebDriver driver;
	public String ScreenshotFolder = ".\\screenshots\\";
	public String screenshotPath;
	public String className;
	public String methodName;

	public FileInputStream fi;
	public Actions actions;
	public JavascriptExecutor js;

	public String propertyFile = "QA_Environment_TestData.properties";
	public String projectDir = System.getProperty("user.dir");

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

	/***
	 * SendKeys by using any Locator
	 * 
	 * @throws Exception
	 ******/
	public void sendKeysByAnyLocator(By locator, String inputData) throws Exception {
		WebElement ele = driver.findElement(locator);
		// verify the Given Locator is displayed on current web page?
		if (driver.findElements(locator).size() > 0) {
			System.out.println("****" + locator + " is displayed on screen");
			highlightElement(ele);
			// Check the given web element is Enabled state?
			if (ele.isEnabled()) {
				highlightElement(ele);
				System.out.println("****" + locator + " is enabled state");
				// Clear the existing data from Edit box if any?
				highlightElement(ele);
				ele.clear();
				// send the test data to edit box
				highlightElement(ele);
				ele.sendKeys(prop.getProperty(inputData));
			} else {
				System.out.println("****" + locator + " is NOT enabled state");
			}
		} else {
			System.out.println("****" + locator + " is NOT displayed on screen, please check the locator");
		}
	}

	/***
	 * Click by using any Locator on Button/link/radiobutton/Checkbox/image/...
	 * 
	 * @throws Exception
	 ******/
	public void clickByAnyLocator(By locator) throws Exception {
		WebElement ele = driver.findElement(locator);
		// verify the Given Locator is displayed on current web page?
		if (driver.findElements(locator).size() > 0) {
			highlightElement(ele);
			System.out.println("****" + locator + " is displayed on screen");
			// Check the given web element is Enabled state?
			if (ele.isEnabled()) {
				System.out.println("****" + locator + " is enabled state");
				// click on the web element
				highlightElement(ele);
				ele.click();
			} else {
				System.out.println("****" + locator + " is NOT enabled state");
			}
		} else {
			System.out.println("****" + locator + " is NOT displayed on screen, please check the locator");
		}
	}

	public void implicitWait(int waitTime) {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(waitTime));
		System.out.println("Implicit wait method used***");
	}

	public void highlightElement(WebElement element) throws InterruptedException {
		try {
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			for (int i = 0; i < 1; i++) {
				executor.executeScript("arguments[0].style.border='7px groove green'", element);
				Thread.sleep(200);
				executor.executeScript("arguments[0].style.border=''", element);
			}
		} catch (Exception e) {
			System.out.println("Exception - " + e.getMessage());
		}
	}

	public void screenshotWithStatus(ITestResult res) throws Exception {
		projectDir = System.getProperty("user.dir");
		screenshotPath = projectDir + "\\screenshots\\";
		className = res.getTestClass().getName().trim(); // OrangeHRM
		methodName = res.getName().trim();
		// STATUS_PackageName.ClassName_MethodName_Timestamp.PNG
		if (res.getStatus() == ITestResult.SUCCESS) {
			File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			FileHandler.copy(scrFile,
					new File(screenshotPath + "PASS_" + className + "_" + methodName + "_" + timeStamp() + ".PNG"));
		}
		if (res.getStatus() == ITestResult.FAILURE) {
			File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			FileHandler.copy(scrFile,
					new File(screenshotPath + "FAIL_" + className + "_" + methodName + "_" + timeStamp() + ".PNG"));
		}
		if (res.getStatus() == ITestResult.SKIP) {
			File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			FileHandler.copy(scrFile,
					new File(screenshotPath + "SKIP_" + className + "_" + methodName + "_" + timeStamp() + ".PNG"));
		}
	}

	/****************** Dropdown selection **************************************/
	public void selectByVisibleText(By locator, String visibleText) {
		WebElement element = driver.findElement(locator);
		if (driver.findElements(locator).size() > 0) {
			if (element.isEnabled()) {
				Select dropdown = new Select(element);
				dropdown.selectByVisibleText(prop.getProperty(visibleText));
			} else {
				System.out.println("The webelement is NOT Enabled, please check**************");
			}
		} else {
			System.out.println("The webelement is NOT displayed, please check**************");
		}

	}
}
