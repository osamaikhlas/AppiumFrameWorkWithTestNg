package org.osamaikhlas.testUtils;

import java.io.IOException;

import org.osamaikhlas.utils.AppiumUtils;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import io.appium.java_client.AppiumDriver;


public class Listeners extends AppiumUtils implements ITestListener{
	ExtentReports extent = ExtentReporterNG.getReporterObject();
	ExtentTest test;
	AppiumDriver driver;

	
	@Override
	public void onTestStart(ITestResult result) {
	test = extent.createTest(result.getMethod().getMethodName());

	  }

	@Override
	public void onTestSuccess(ITestResult result) {
		test.log(Status.PASS, "Test Passed");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		test.log(Status.FAIL, "Test Failed");
		test.fail(result.getThrowable());
		
		try {
			driver = (AppiumDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
			} 
			catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException
						| SecurityException e1) {
					// TODO Auto-generated catch block
			e1.printStackTrace();
			}
			 
				
		try {
			test.addScreenCaptureFromPath(getScreenShot(result.getMethod().getMethodName(), driver),result.getMethod().getMethodName());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
	  }

	@Override
	public void onTestSkipped(ITestResult result) {
		test.log(Status.SKIP, "Test Skipped");
	  }

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
	    // not implemented
	  }

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {
	    onTestFailure(result);
	  }

	@Override
	public void onStart(ITestContext context) {
	    // not implemented
	  }

	@Override
	public void onFinish(ITestContext context) {
        extent.flush();
	  }

}
