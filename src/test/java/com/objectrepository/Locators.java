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

	//OrangeHRM locators
	public final By hrm_username_Editbox = By.name("username");
	public final By hrm_password_Editbox = By.name("password");
	public final By hrm_Login_Button = By.xpath("//*[@type='submit']");	
	public final By hrm_profilepicture_icon = By.xpath("//*[contains(@class,'oxd-userdropdown-icon')]");
	public final By hrm_Logout_linkText = By.linkText("Logout");

	
	//clg week live locators
	public final By clg_CountryofCitizenship_dropdown = By.id("country");
	public final By clg_iama_dropdown = By.name("attendeeType");

	//DemoAlert page locators
	public final By alert_alertButton = By.id("alertButton");
	public final By alert_timerAlertButton = By.id("timerAlertButton");
	public final By alert_confirmButton = By.id("confirmButton");

	//JRI Locators
	public final By jri_Facebook_button = By.xpath("//*[@title='Sign In with Facebook']");
	public final By jri_Facebook_popup_loginButton = By.name("login");
	public final By jri_Facebook_popup_emailEditbox = By.name("email");

	

}
