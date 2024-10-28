package org.osamaikhlas.testUtils;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.time.Duration;
import java.util.Properties;

import org.osamaikhlas.pageObjects.android.FormPage;
import org.osamaikhlas.utils.AppiumUtils;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;


import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumServiceBuilder;


public class AndroidBaseTest extends AppiumUtils {
	public AndroidDriver driver;
	public AppiumServiceBuilder serviceBuilder;
	public FormPage formPage;
	
	

	
	@BeforeClass (alwaysRun = true)
	public void ConfigureAppium() throws IOException {
		Properties prop = new Properties();
		FileInputStream fis=new FileInputStream(System.getProperty("user.dir")+"//src//main//java//org//osamaikhlas//resources//data.properties");
		prop.load(fis);
		
		String ipAddress= System.getProperty("ipAddress")!=null ?  System.getProperty("ipAddress") : prop.getProperty("ipAddress");
		String port = prop.getProperty("port");
		String androidDeviceName = prop.getProperty("AndroidDeviceName");
		serviceBuilder = startAppiumServer(ipAddress, Integer.parseInt(port));
		

		UiAutomator2Options options = new UiAutomator2Options();		
		options.setDeviceName(androidDeviceName);
		options.setApp(System.getProperty("user.dir") + "//src//test//java//resources//General-Store.apk");
		options.setChromedriverExecutable("//Users//oikhlas//Downloads//chromedriver_mac64//chromedriver");
		
		driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), options);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		formPage = new FormPage(driver);
	}
	
	
	
	@AfterClass (alwaysRun = true)
	public void tearDown() {
		driver.quit();	
		serviceBuilder.build().stop();
		closeEyes();
	}

}
