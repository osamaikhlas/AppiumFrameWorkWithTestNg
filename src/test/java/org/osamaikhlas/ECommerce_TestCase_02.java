package org.osamaikhlas;

import org.osamaikhlas.testUtils.AndroidBaseTest;
import org.testng.annotations.Test;


public class ECommerce_TestCase_02 extends AndroidBaseTest{
	
		
	@Test
	public void fillFormNegativeFlow()  {
		formPage.setGender("female");
		formPage.selectCountry("Argentina");
		formPage.clickLetsShop();
		formPage.verifyToastMessage("Please enter your name");
	}
	
	@Test
	public void fillFormPostiveFlow()  {
		formPage.setNameField("Ossama Ikhlas");
		formPage.setGender("female");
		formPage.selectCountry("Argentina");
		formPage.clickLetsShop();
		formPage.checkForToastMessage();
	}

}
