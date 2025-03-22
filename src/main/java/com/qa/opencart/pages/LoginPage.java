package com.qa.opencart.pages;

import com.microsoft.playwright.Page;

public class LoginPage {
	
	private Page page;
	
	// String Locators
	private String emailId = "//input[@id='input-email']";
	private String password = "//input[@id='input-password']";
	private String loginButn = "//input[@type='submit']";
	private String forgotpassword = "(//a[normalize-space()='Forgotten Password'])[1]";
	private String logoutLink ="//a[@class='list-group-item'][normalize-space()='Logout']";
	
	
	//2.Create Constructor
	public LoginPage(Page page) {
		this.page=page;
	}
	
	//3.Page Action and method
	public String getLoginPageTitlePage() {
		return page.title();
	}
	
	public boolean isForgotpasssword() {
		return page.isVisible(forgotpassword);
	}
	
	public boolean  doLogin(String appUsername,String appPassword) throws InterruptedException {
		System.out.println("App crede :"+ appUsername + ":" + appPassword);
		page.fill(emailId, appUsername);
		page.fill(password, appPassword);
		page.click(loginButn);
		Thread.sleep(2000);
		if(page.isVisible(logoutLink)) {
			page.click(logoutLink);
			return true;
		} 
		else {
			System.out.println("Logout Link Not Visible");
			return false;
		}
		
	}

}
