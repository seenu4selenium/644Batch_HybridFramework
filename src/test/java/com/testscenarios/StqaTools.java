package com.testscenarios;

import org.testng.annotations.Test;

import com.utilities.CommonFunctions;

import org.testng.annotations.BeforeClass;
import org.openqa.selenium.By;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;

public class StqaTools extends CommonFunctions {
	 String childwindowname;
	 String childwindowname_1;
  @Test
  public void stqa() throws Exception {
	  getURL("Stqa_url");
	  Thread.sleep(2000); 
	  clickByAnyLocator(loc.stqa_parent_newwindow);  
	 Thread.sleep(2000);
	 clickByAnyLocator(loc.stqa_child_newwindow);
	 navigateToPopupWindow();
	 Thread.sleep(4000);
	 
	 takeScreenshot("StqaTools");
	// 
	 
	 //popupHandleToCloseChildWindow();
	 /*Thread.sleep(3000);
	 driver.switchTo().window(childwindowname).close();
	 Thread.sleep(3000);*/
	 
	 //childwindowname_1 = driver.getWindowHandle();
	 //System.out.println("Print the current windowhandle"+childwindowname_1);
	//driver.switchTo().window(childwindowname_1).close();
	
	
	 
	 
	
	
	 
  }
  @BeforeClass
  public void beforeClass() {
	  System.out.println("@BeforeClass block");
		chromeBrowserLaunch();
  }

  @AfterClass
  public void afterClass() throws Exception {
	  System.out.println("@AfterClass block");
	 
	  switchAndCloseNewTab();
	// driver.close();
		driver.quit();
		
  }

  

}
