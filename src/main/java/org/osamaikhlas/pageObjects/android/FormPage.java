package org.osamaikhlas.pageObjects.android;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.osamaikhlas.utils.AndroidGesture;
import org.testng.Assert;

import io.appium.java_client.android.Activity;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;


public class FormPage extends AndroidGesture {
	
	AndroidDriver driver;
	
	public FormPage(AndroidDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	
	@AndroidFindBy(id = "com.androidsample.generalstore:id/nameField")
	private WebElement nameField;
	
	@AndroidFindBy(id = "com.androidsample.generalstore:id/radioFemale")
	private WebElement genderFemaleOption;
	
	@AndroidFindBy(id = "com.androidsample.generalstore:id/radioMale")
	private WebElement genderMaleOption;
	
	@AndroidFindBy(id = "com.androidsample.generalstore:id/spinnerCountry")
	private WebElement countryDropDown;
	
	@AndroidFindBy(id = "com.androidsample.generalstore:id/btnLetsShop")
	private WebElement letsShopBtn;
	
	@AndroidFindBy(xpath = "(//android.widget.Toast)[1]")
	private WebElement toastMessageError;
	
	@AndroidFindBy(xpath = "(//android.widget.Toast)[1]")
	private List<WebElement> toastMessageErrorCount;
	
	
	public void setNameField(String name) {
		nameField.sendKeys(name);
		driver.hideKeyboard();
	}
	
	public void setGender(String gender) {
		if(gender == "female") {
			genderFemaleOption.click();
		}
		else
		{
			genderMaleOption.click();
		}
	}
	
	public void selectCountry(String country) {
	
		countryDropDown.click();
		scrollUntilTextIsVisibleAction(country);
		driver.findElement(By.xpath("//android.widget.TextView[@text='"+country+"']")).click();	
	}
	public ProductCatalog clickLetsShop() {
		letsShopBtn.click();
		return new ProductCatalog(driver);
		
	}
	
	public void verifyToastMessage(String message) {
		String toastMessage = toastMessageError.getAttribute("name");
		Assert.assertEquals(toastMessage, message);
	}
	public void checkForToastMessage() {
		Assert.assertTrue(toastMessageErrorCount.size()<1);
	}
	
	@SuppressWarnings("deprecation")
	public void setActivity() {
		Activity activity = new Activity("com.androidsample.generalstore","com.androidsample.generalstore.MainActivity");
		activity.setAppWaitPackage("com.androidsample.generalstore");
	    activity.setAppWaitActivity("com.androidsample.generalstore.MainActivity");
		driver.startActivity(activity);
	}
	
	
	

}
