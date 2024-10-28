package org.osamaikhlas.utils;

import java.util.HashMap;
import java.util.Map;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.Assert;


import io.appium.java_client.ios.IOSDriver;

public class IOSGesture extends AppiumUtils {
	
IOSDriver driver;

	
	public IOSGesture(IOSDriver driver) {
		this.driver = driver;
	}
	

public void longPressIOS(WebElement ele, int duration) {
		
		Map <String,Object>params = new HashMap<>();
		params.put("element", ((RemoteWebElement)ele).getId());
		params.put("duration", duration);
		driver.executeScript("mobile:touchAndHold", params);
		
	}
	
	public void scrollIOS(WebElement ele, String direction) {
		
		Map <String,Object>params = new HashMap<>();
		params.put("element", ((RemoteWebElement)ele).getId());
		params.put("direction", direction);
		driver.executeScript("mobile:scroll", params);
		
	}
	
	public void sliderIOS(WebElement ele, String percent) {
		ele.sendKeys(percent); //setValue
		Assert.assertEquals(percent, ele.getAttribute("value"));
		
	}
	
	public void launchApp(String bundleId) {
		
		Map <String,Object>params = new HashMap<>();
		params.put("bundleId", bundleId);
		driver.executeScript("mobile:launchApp", params);
		
	}
	public void swipeIOS( String direction) {
		
		Map <String,Object>params = new HashMap<>();
		params.put("direction", direction);
		driver.executeScript("mobile:swipe", params);
		
	}
}
