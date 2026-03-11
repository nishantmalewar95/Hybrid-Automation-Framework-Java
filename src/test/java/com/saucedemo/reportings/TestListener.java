package com.saucedemo.reportings;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener {
	//when test getting start
	public void onTestStart(ITestResult result) {
		System.out.println("Test Started: "+ result.getName());
	}
	
	//when test getting fail 
	public void onTestFailure(ITestResult result) {
		System.out.println("Test Failed! Taking Screenshot...");
		// Here the logic for the screenshot will come
	}
	
	public void onFinish(ITestContext context) {
		System.out.println("All tests finished. Flushing report...");
	
	}

}
