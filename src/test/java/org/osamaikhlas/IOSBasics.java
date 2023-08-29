package org.osamaikhlas;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.osamaikhlas.pageObjects.ios.AlertViews;
import org.osamaikhlas.testUtils.IOSBaseTest;


public class IOSBasics extends IOSBaseTest {
	
	@Test
	public void IOSBasicTests() throws InterruptedException {
		
		// xpath, classname, IOS, iosclasschain, IOSPredicateString, accessibilityId, id

		AlertViews alertViews = homePage.selectAlertViews();
		alertViews.fillTextLabel("Hello");
		String actualMessage = alertViews.getConfirmMessage();
		AssertJUnit.assertEquals(actualMessage, "A message should be a short, complete sentence.");
		alertViews.okConfirmMessage();

		
	}

}

