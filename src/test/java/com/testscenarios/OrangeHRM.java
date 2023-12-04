package com.testscenarios;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.utilities.CommonFunctions;

public class OrangeHRM extends CommonFunctions {

	@BeforeClass // pre-condition
	public void beforeClass() {
		System.out.println("@BeforeClass block");
		chromeBrowserLaunch();
	}

	@AfterClass // post-condition
	public void afterClass() throws Exception {
		System.out.println("@AfterClass block");
		takeScreenshot("FBlogin");
		// close the browser
		// driver.close();
		driver.quit();
	}

	@Test
	public void fblogin() throws Exception {
		// design the code from here
//		1. OrangeHRM login with valid creadentials
//		https://opensource-demo.orangehrmlive.com/web/index.php/auth/login
//		Username : Admin
//		Password : admin123
//		2. After login, click on Profile icon
//		3. Click on Logout text (Last option)
	
		
		
	}

}
