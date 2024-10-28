package org.osamaikhlas;

import org.osamaikhlas.testUtils.AndroidBaseTest;
import org.testng.annotations.Test;



public class ECommerce_TestCase_02 extends AndroidBaseTest{	
	@Test
	public void fillFormNegativeFlow()  {
		openAndroidEyes(driver, "Android App", "Android Test 2");
			formPage.setGender("female");
			formPage.selectCountry("Argentina");
			formPage.clickLetsShop();
			formPage.verifyToastMessage("Please enter your name");
			eyes.checkWindow("Initial view");
			eyes.close();
	}
	
	@Test
	public void fillFormPostiveFlow()  {
		openAndroidEyes(driver, "Anndroid App", "Android Test 3");
		formPage.setNameField("Ossama Ikhlas");
		formPage.setGender("female");
		formPage.selectCountry("Argentina");
		formPage.clickLetsShop();
		formPage.checkForToastMessage();
		eyes.checkWindow("Add to Cart Page");
		
	}

}
