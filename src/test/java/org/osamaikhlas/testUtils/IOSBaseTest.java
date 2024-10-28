package org.osamaikhlas.testUtils;

import org.testng.annotations.BeforeClass;


import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.time.Duration;
import java.util.Properties;

import org.osamaikhlas.pageObjects.ios.HomePage;
import org.osamaikhlas.utils.AppiumUtils;
import org.testng.annotations.AfterClass;

import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.options.XCUITestOptions;
import io.appium.java_client.service.local.AppiumServiceBuilder;

public class IOSBaseTest extends AppiumUtils {
	public IOSDriver driver;
	public AppiumServiceBuilder serviceBuilder;
	public HomePage homePage;
	
	
	
	@BeforeClass
	public void ConfigureAppium() throws IOException {
		Properties prop = new Properties();
		FileInputStream fis=new FileInputStream(System.getProperty("user.dir")+"//src//main//java//org//osamaikhlas//resources//data.properties");
		prop.load(fis);
		String ipAddress= System.getProperty("ipAddress")!=null ?  System.getProperty("ipAddress") : prop.getProperty("ipAddress");
		String port = prop.getProperty("port");
		String iphoneDeviceName = prop.getProperty("IphoneDeviceName");
		String iosVersion = prop.getProperty("IosVersion");
		serviceBuilder = startAppiumServer(ipAddress, Integer.parseInt(port));
		
		XCUITestOptions options= new XCUITestOptions();
		options.setDeviceName(iphoneDeviceName);
		options.setApp("/Users/oikhlas/Desktop/UIKitCatalog.app");
		options.setPlatformVersion(iosVersion);
		options.setWdaLaunchTimeout(Duration.ofSeconds(20));
		
		driver = new IOSDriver(new URL("http://127.0.0.1:4723"), options);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		homePage =new HomePage(driver);
	}
	
	
	
	
	
	@AfterClass
	public void tearDown() {
		driver.quit();	
		serviceBuilder.build().stop();
		closeEyes();
	}

}
