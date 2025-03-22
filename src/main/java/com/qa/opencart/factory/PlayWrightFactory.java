package com.qa.opencart.factory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Properties;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public class PlayWrightFactory {
	
	Playwright playwrite;
	Browser browser;
	BrowserContext browserContext;
	Page page;
	
	Properties pro;
	
	private static ThreadLocal<Browser> tlBrowser = new ThreadLocal<>();
	private static ThreadLocal<BrowserContext> tlBrowserContext = new ThreadLocal<>();
	private static ThreadLocal<Page> tlPage = new ThreadLocal<>();
	private static ThreadLocal<Playwright> tlPlaywright = new ThreadLocal<>();
	
	
	public static Playwright getPlaywright() {
		return tlPlaywright.get();
	}
	public static Browser getBrowser() {
		return tlBrowser.get();
	}
	public static BrowserContext getBrowserContext() {
		return tlBrowserContext.get();
	}
	public static Page getPage() {
		return tlPage.get();
	}
	
	
	
	
	public Page initBrowser(Properties pro) {
		
		String browserName = pro.getProperty("browser");
		System.out.println("browser Name is :"+ browserName);
		
		tlPlaywright.set(Playwright.create());
//		playwrite = Playwright.create();
		
		switch (browserName) {
		case "chromium":
//		 browser = playwrite.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
		 tlBrowser.set(getPlaywright().chromium().launch(new BrowserType.LaunchOptions().setHeadless(false)));
			break;

		case "firefox":
	//		 browser = playwrite.firefox().launch(new BrowserType.LaunchOptions().setHeadless(false));
			 tlBrowser.set(getPlaywright().firefox().launch(new BrowserType.LaunchOptions().setHeadless(false)));
				break;
				
		case "safari":
			// browser = playwrite.webkit().launch(new BrowserType.LaunchOptions().setHeadless(false));
			 tlBrowser.set(getPlaywright().webkit().launch(new BrowserType.LaunchOptions().setHeadless(false)));
				break;
				
		case "chrome":
		//	 browser = playwrite.chromium().launch(new BrowserType.LaunchOptions().setChannel("chrome").setHeadless(false));
			 tlBrowser.set(getPlaywright().chromium().launch(new BrowserType.LaunchOptions().setChannel("chrome").setHeadless(false)));
				break;
			
		default:
			System.out.println("Please pass the browser name..... ");
			break;
		}
		
		
		tlBrowserContext.set(getBrowser().newContext());
//		browserContext = browser.newContext();
	
		tlPage.set(getBrowserContext().newPage());
//		page = browserContext.newPage();
		
		
		getPage().navigate(pro.getProperty("url").trim());
	//	page.navigate(pro.getProperty("url").trim());
		
		return getPage();
		
	}
	
	public Properties inti_prop() {
		
		try {
			FileInputStream inp = new FileInputStream("./src/test/resource/Config.properties");
			pro = new Properties();
			pro.load(inp);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return pro;
		
	}
	
	
	public static String takeScreenshot() {
		String path = System.getProperty("user.dir")+"/screenshot/"+System.currentTimeMillis()+".png";
		
		getPage().screenshot(new Page.ScreenshotOptions()
				.setPath(Paths.get(path))
				.setFullPage(true));
		return path;
		
	}
	
	

}
