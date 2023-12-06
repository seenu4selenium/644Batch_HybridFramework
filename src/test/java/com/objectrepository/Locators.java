package com.objectrepository;

import org.openqa.selenium.By;

//Here QA will store Application locators
public class Locators {

	// public final By pagename_ElementName_ElementType = By.xpath("Give Xpath
	// value");
	// public final By signUp_Firstname_Editbox = By.name("firstName");

	public final By fblogin_Email_Editbox = By.name("email");
	public final By fblogin_Password_Editbox = By.name("pass");
	public final By fblogin_Login_Button = By.name("login");
	//--------------------------<<<<<<<<<<ORANGEHRM--------------->>>>>>>>>>>>>>>>>>>>>>>>>>>
		public final By orangehrm_username= By.xpath("//input[@placeholder='Username']");
		public final By orangehrm_password= By.xpath("//input[@placeholder='Password']");
		public final By orangehrm_submit= By.xpath("//button[@type='submit']");
		public final By orangehrm_userdropdown= By.xpath("//p[@class='oxd-userdropdown-name']");
		public final By orangehrm_logout= By.linkText("Logout");
		
//-------------<<<<<<<<<<<STQA WEBSITE------>>>>>>>>>>>>>>>
		public final By stqa_parent_newwindow= By.linkText("Click to open new Window");
		public final By stqa_child_newwindow= By.linkText("New Message Window");
}
