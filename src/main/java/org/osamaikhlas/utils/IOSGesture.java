package org.osamaikhlas.utils;

import java.util.HashMap;
import java.util.Map;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import io.appium.java_client.ios.IOSDriver;

public class IOSGesture extends AppiumUtils { private static final Logger logger = LoggerFactory.getLogger(IOSGesture.class);
IOSDriver driver;

	
	public IOSGesture(IOSDriver driver) {
		this.driver = driver;
	}
	

public void longPressIOS(WebElement ele, int duration) {
		if (ele == null) {
		throw new IllegalArgumentException("WebElement cannot be null");
		}
		Map <String,Object>params = new HashMap<>();
		params.put("element", ((RemoteWebElement)ele).getId());
		params.put("duration", duration);
		driver.executeScript("mobile:touchAndHold", params);
		
	}
	
	public void scrollIOS(WebElement ele, String direction) {
		if (ele == null) {
			throw new IllegalArgumentException("WebElement cannot be null");
		}
		Map <String,Object>params = new HashMap<>();
		params.put("element", ((RemoteWebElement)ele).getId());
		params.put("direction", direction);
		driver.executeScript("mobile:scroll", params);
		
	}
	
	public void sliderIOS(WebElement ele, String percent) {
		if (ele == null) {
			throw new IllegalArgumentException("WebElement cannot be null");
		}
		try {
			ele.sendKeys(percent); //setValue
			Assert.assertEquals(percent, ele.getAttribute("value"));
		} catch (Exception e) {
			System.err.println("Error setting slider value: " + e.getMessage());
			// Additional logging or error handling can be added here
		}
	}
	
	public void launchApp(String bundleId) {
		
		Map <String,Object>params = new HashMap<>();
		params.put("bundleId", bundleId);
		driver.executeScript("mobile:launchApp", params);
		logger.info("App launched with bundleId: {}", bundleId);


	}
	public void swipeIOS( String direction) {
		Map <String,Object>params = new HashMap<>();
		params.put("direction", direction);
		driver.executeScript("mobile:swipe", params);
		
	}
}
