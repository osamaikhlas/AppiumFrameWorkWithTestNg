package org.osamaikhlas.pageObjects.ios;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.osamaikhlas.utils.IOSGesture;

import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class AlertViews extends IOSGesture {
	
	IOSDriver driver;
	
	public AlertViews(IOSDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	
	//driver.findElement(AppiumBy.accessibilityId("Alert Views")).click();
	@iOSXCUITFindBy(iOSClassChain="**/XCUIElementTypeStaticText[`label == \"Text Entry\"`]")
	private WebElement textEntryMenu;
	
	@iOSXCUITFindBy(iOSClassChain="**/XCUIElementTypeTextField")
	private WebElement textBox;
	
	@iOSXCUITFindBy(iOSClassChain="**/XCUIElementTypeButton[`label == \"OK\"`]")
	private WebElement okBtn;
	
	@iOSXCUITFindBy(iOSNsPredicate="label == \"Confirm / Cancel\"")
	private WebElement confirmCancelMenu;
	
	@iOSXCUITFindBy(iOSNsPredicate="type == 'XCUIElementTypeStaticText' AND value BEGINSWITH[c] 'A message'")
	private WebElement confirmMessage;
	
	@iOSXCUITFindBy(iOSNsPredicate="label == \"Confirm\"")
	private WebElement submitBtn;
	
	
	public void fillTextLabel(String name) {
		textEntryMenu.click();
		textBox.sendKeys(name);
		okBtn.click();
	}
	
	public String getConfirmMessage() {
		confirmCancelMenu.click();
		return confirmMessage.getText();
	}
	public void okConfirmMessage() {	
		submitBtn.click();
}

	

}
