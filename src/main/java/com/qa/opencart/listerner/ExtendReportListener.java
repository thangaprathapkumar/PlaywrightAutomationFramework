package com.qa.opencart.listerner;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.qa.opencart.factory.PlayWrightFactory;

public class ExtendReportListener implements ITestListener {
	
	private static final String OUTPUT_FOLDER = "./build/";
	
	private static final String FILE_NAME = "TestExecutionReport.html";
	
	private static ExtentReports extent = init();
	public static ThreadLocal<ExtentTest> test = new ThreadLocal<ExtentTest>();
	private static ExtentReports extendreports;
	
	private static ExtentReports init() {
		
		Path path = Paths.get(OUTPUT_FOLDER);
		if(!Files.exists(path)) {
			try {
				Files.createDirectories(path);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		extendreports = new ExtentReports();
		ExtentSparkReporter reporter = new ExtentSparkReporter(OUTPUT_FOLDER + FILE_NAME);
		reporter.config().setReportName("OPen cart Automation Results");
		extendreports.attachReporter(reporter);
		extendreports.setSystemInfo("System", "MAC");
		extendreports.setSystemInfo("AUTHOR", "THANGAPRATHAPKUMAR");
		return extendreports;
		
	}
	
	public synchronized void onStart(ITestContext context) { 
		
		System.out.println("Test Suite started");
		
	}
	
	public synchronized void onFinish(ITestContext context) { 
		
		System.out.println("Test Suite ending");
		extent.flush();
		test.remove();
		
	}
	
   public synchronized void onTestStart(ITestResult result) {
	   
	   String methodName = result.getMethod().getMethodName();
	   String qualifiedName = result.getMethod().getQualifiedName();
	   int last = qualifiedName.lastIndexOf(".");
	   int mid = qualifiedName.substring(0,last).lastIndexOf(".");
	   String className = qualifiedName.substring(mid+1,last);
	   
	   System.out.println(methodName+" Started ..");
	   ExtentTest extentTest = extent.createTest(result.getMethod().getMethodName(),result.getMethod().getDescription());
	   extentTest.assignCategory(result.getTestContext().getSuite().getName());
	   extentTest.assignCategory(className);
	   test.set(extentTest);
	   
   }
   
   public synchronized void onTestSuccess(ITestResult result) {
	   
	   System.out.println(result.getMethod().getMethodName()+"Passed . . !");
	   test.get().pass("Test Passed ");
	   test.get().pass(result.getThrowable(),MediaEntityBuilder.createScreenCaptureFromPath(PlayWrightFactory.takeScreenshot()).build());
	   
   }
   
 public synchronized void onTestFailure(ITestResult result) {
	   
	   System.out.println(result.getMethod().getMethodName()+"Passed . . !");
	   test.get().pass("Test Passed ");
	   test.get().pass(result.getThrowable(),MediaEntityBuilder.createScreenCaptureFromPath(PlayWrightFactory.takeScreenshot()).build());
   }
  
}
