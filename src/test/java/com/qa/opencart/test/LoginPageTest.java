package com.qa.opencart.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constant.AppConstant;

public class LoginPageTest extends BaseTest{

	@Test(priority=1)
	public void navigationTest() {
		
		loginPage = homePage.navigateToLoginPage();

		String actualLoginPageTitle = loginPage.getLoginPageTitlePage();
		
		System.out.println("Actual LoginPage Title :"+actualLoginPageTitle);
		
		Assert.assertEquals(actualLoginPageTitle, AppConstant.LOGIN_PAGE_TITLE);
		
	}
	
	@Test(priority =2)
	public void forgotPasswordLinkExist() {
		
		Assert.assertTrue(loginPage.isForgotpasssword());
	}
	
	@Test(priority =3)
	public void appLoginTest() throws InterruptedException {
		
		Assert.assertTrue(loginPage.doLogin(prop.getProperty("username"), 
				prop.getProperty("password")));
		
	}
	
	
}
