package com.testscenarios;

import org.testng.annotations.Test;

import com.utilities.CommonFunctions;

import org.testng.annotations.BeforeClass;
import org.openqa.selenium.By;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;

public class StqaTools extends CommonFunctions {
  @Test
  public void stqa() throws Exception {
	  getURL("Stqa_url");
	  Thread.sleep(2000); 
	  clickByAnyLocator(loc.stqa_parent_newwindow);
	  
	 Thread.sleep(2000);
	 navigateToPopupWindow();
	 Thread.sleep(2000);
	 clickByAnyLocator(loc.stqa_child_newwindow);
	 Thread.sleep(3000);
	 takeScreenshot("StqaTools");
	 
  }
  @BeforeClass
  public void beforeClass() {
	  System.out.println("@BeforeClass block");
		chromeBrowserLaunch();
  }

  @AfterClass
  public void afterClass() throws Exception {
	  System.out.println("@AfterClass block");
		//takeScreenshot("StqaTools");
		// close the browser
		driver.close();
		driver.quit();
		
  }

  

}
