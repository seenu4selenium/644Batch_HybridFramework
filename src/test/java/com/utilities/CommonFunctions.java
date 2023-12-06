package com.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
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
import org.testng.ITestResult;
import java.util.Set;
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
	public void screenshotWithStatus(ITestResult res) throws Exception {
		String projectDir = System.getProperty("user.dir");
		String screenshotPath = projectDir + "\\screenshots\\";
		String className = res.getTestClass().getName().trim(); // OrangeHRM
		String methodName = res.getName().trim();
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
	/************** Alert Handle *************************/
	public void alertHandleByAccept() {
		//Alert alert = driver.switchTo().alert();
		String alertText = driver.switchTo().alert().getText();
		System.out.println("Alert text is: " + alertText);
		//To click on OK button / Yes button
		driver.switchTo().alert().accept();
	}

	public void alertHandleByDismiss() {
		//Alert alert = driver.switchTo().alert();
		String alertText = driver.switchTo().alert().getText();
		System.out.println("Alert text is: " + alertText);
		//To click on Cancel button / No button
		driver.switchTo().alert().dismiss();
	}
	/************
	 * popupHandle
	 * 
	 * @throws InterruptedException
	 *********************************/
	public void popupHandleToCloseChildWindow() throws InterruptedException {
		// get the main windown name
		String mainWindowName = driver.getWindowHandle();
		System.out.println("mainWindowName:" + mainWindowName);

		Set<String> allWindowNames = driver.getWindowHandles();// 4
		System.out.println("allWindowNames:" + allWindowNames);

		// Close the child window (popups)
		// for (int i = 0; i < array.length; i++) { }
		for (String childWindowName : allWindowNames) {
			// validate the window name is parent window /Child window?
			if (!mainWindowName.equals(childWindowName)) {
				// switch to child window
				driver.switchTo().window(childWindowName);
				Thread.sleep(3000);
				// Close my child window
				driver.close();
			}
		}
		// move cursor point to back to mainwindow
		driver.switchTo().window(mainWindowName);
	}

	public void navigateToPopupWindow() throws InterruptedException {
		// get the main windown name
		String mainWindowName = driver.getWindowHandle();
		System.out.println("mainWindowName:" + mainWindowName);
		Set<String> allWindowNames = driver.getWindowHandles();
		java.util.Set<String> allWindowNames1 = driver.getWindowHandles();// 4
		System.out.println("allWindowNames1:" + allWindowNames1);

		// Close the child window (popups)
		// for (int i = 0; i < array.length; i++) { }
		for (String string : allWindowNames1) {
			
			// validate the window name is parent window /Child window?
			if (!mainWindowName.equals(string)) {
				// switch to the child window
				driver.switchTo().window(string);
				Thread.sleep(3000);
			}
		}
driver.manage().window().maximize();
	}

	/*********** SwithchToWindow using Tab ***************************/
	public void switchToNewTab() {		
		ArrayList<String> allTabs = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(allTabs.get(1));
	}

	/***********
	 * SwithchToWindow using Tab then close the New Tab againg back to Parent Window
	 ***************************/
	public void switchAndCloseNewTab() {
		// Get the current window handle
		String parentWindow = driver.getWindowHandle();
		// Switch to New tab [chilld window]
		ArrayList<String> allTabs = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(allTabs.get(1));
		// Close the newly Opened Window[chilld window]
		driver.close();
		// Switch back to original window[parentWindow]
		driver.switchTo().window(parentWindow);
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
