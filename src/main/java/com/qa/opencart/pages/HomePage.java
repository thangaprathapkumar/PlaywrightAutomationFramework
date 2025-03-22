package com.qa.opencart.pages;

import com.microsoft.playwright.Page;

public class HomePage {

	private Page page;
	
	private String seach = "input[name='search']";
	private String searchIcon ="div#search button";
	private String searchPageHeader ="div#content h1";
	
	private String myAccountpage = "//span[text()='My Account']";
	private String LoginLink = "a:text('Login')";
	
	
	/**
	 *  playwright Linktext we can write like this -> a:text('Login');
	 * @param page
	 */
	
	public HomePage(Page page) {
		this.page = page;
	}
	
	public String getHomePageTitle() {
		
		String title=page.title();
		System.out.println("The Title is :"+ title);
		return title;
		
	}
	
	public String getHomePageURL() {
		String url=page.url();
		System.out.println("The URL is :"+ url);
		return url;
	}
	
	public String doSearch(String productname) {
		page.fill(seach, productname);
		page.click(searchIcon);
		String headertext= page.textContent(searchPageHeader);
		System.out.println("The Header is :"+headertext);
		return headertext;
	}
	
	public LoginPage navigateToLoginPage() {
		page.click(myAccountpage);
		page.click(LoginLink);
		return new LoginPage(page);
		
	}
	
}
