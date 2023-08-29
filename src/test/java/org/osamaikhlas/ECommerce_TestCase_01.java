package org.osamaikhlas;


import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.osamaikhlas.pageObjects.android.CartPage;
import org.osamaikhlas.pageObjects.android.ProductCatalog;
import org.osamaikhlas.testUtils.AndroidBaseTest;

public class ECommerce_TestCase_01 extends AndroidBaseTest{
	
	@Test(dataProvider = "getData", groups= {"Smoke"})
	public void fillForm(HashMap<String, String> input) throws InterruptedException  {
		
		formPage.setNameField(input.get("name"));
		formPage.setGender(input.get("gender"));
		formPage.selectCountry(input.get("country"));
		ProductCatalog productCatalog = formPage.clickLetsShop();
		
		
		productCatalog.addItemToCartByIndexValue(0);
		productCatalog.addItemToCartByIndexValue(0);
		CartPage cartPage = productCatalog.gotoCartPage();
		
		Double totalAmount = cartPage.calculateTotalAmount();
		cartPage.verifyTotalSum(totalAmount);
		cartPage.checkTermsAndCondition();
		cartPage.checkout();	
	}
	@DataProvider
	public Object[][] getData() throws IOException {
		
		List<HashMap<String, String>> data =getJsonData(System.getProperty("user.dir")+"//src//test//java//org//osamaikhlas//testData//eCommerce.json");
		return new Object[][] {{data.get(0)}};
		
	}

}
