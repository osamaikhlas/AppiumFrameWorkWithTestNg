package org.osamaikhlas.utils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.applitools.eyes.selenium.Eyes;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.service.local.AppiumServiceBuilder;



public class AppiumUtils {
	
	
	public AppiumServiceBuilder serviceBuilder;
	public Eyes eyes;
	
	
	public AppiumUtils() {
		eyes = new Eyes();
        eyes.setApiKey("2NOx9h7A8zXqGf0NEWOrJ2m105co9ChIIF11WYHBLtwr8110"); 
	}
	

	public Double getFormattedAmount(String amount) {
		Double price = Double.parseDouble(amount.substring(1));
		return price;

	}
	
	public void waitForElementToAppear(WebElement ele, String waitForText, AppiumDriver driver) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.attributeContains(ele, "text", waitForText));
		
	}
	
	public AppiumServiceBuilder startAppiumServer(String ipAddress, int port) {
		serviceBuilder = new AppiumServiceBuilder();
		serviceBuilder.usingAnyFreePort();
		serviceBuilder.usingDriverExecutable(new File("//Users//oikhlas//.nvm//versions//node//v18.16.1//bin//node"));
		serviceBuilder.withAppiumJS(new File("//Users//oikhlas//.nvm//versions//node//v18.16.1/bin//appium"));
		HashMap<String, String> environment = new HashMap<String, String>();
		environment.put("PATH", "/usr/local/bin:" + System.getenv("PATH"));
		serviceBuilder.withEnvironment(environment);   
		serviceBuilder.withIPAddress(ipAddress).usingPort(port);
		serviceBuilder.build().start();
		return serviceBuilder;
	}
	
	public List<HashMap<String, String>> getJsonData(String jsonFilePath) throws IOException{
		String jsonContent = FileUtils.readFileToString(new File(jsonFilePath), StandardCharsets.UTF_8);
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, String>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String, String>>>(){
			
		});
		return data;
	}
	
	public String getScreenShot(String testCaseName, AppiumDriver driver) throws IOException {
		
		File source = driver.getScreenshotAs(OutputType.FILE);
		String destinationFile = System.getProperty("user.dir")+"//reports//"+testCaseName+".png";
		FileUtils.copyFile(source,new File(destinationFile));
		return destinationFile;
		
	}
	
	  // Parent method to open Eyes for a test
    public void openAndroidEyes(AndroidDriver driver, String appName, String testName) {
        eyes.open(driver, appName, testName);
    }
       
    public void openIosEyes(IOSDriver driver, String appName, String testName) {
        eyes.open(driver, appName, testName);
    }

    // Parent method to close Eyes after a test
    public void closeEyes() {
        eyes.close();
    }
	
	

}
