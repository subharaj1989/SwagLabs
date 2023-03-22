package swaglabs;

import org.junit.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import swaglabspages.CartPage;
import swaglabspages.CheckoutPage;
import swaglabspages.ProductListPage;

public class CartTests {
	
	CartPage pgCart;
	ProductListPage pgProduct;
	CheckoutPage pgCheckout;

	
	@Test(dependsOnGroups = "Login")
	public void BackToProductList()
	{
		pgCart=new CartPage(Hooks.driver);
		pgProduct=new ProductListPage(Hooks.driver);
		pgProduct.clickCart();
		pgCart.ClickContinueShopping();
		Assert.assertTrue(pgProduct.getProductpageTitle().equals("Products"));
	}
	
	@Test(dependsOnGroups = "AddProductsToCart",dataProvider = "ProductList", dataProviderClass = ProductpageTests.class)
	public void clickCheckOut(String sProductName)
	{
		pgCart=new CartPage(Hooks.driver);
		pgProduct=new ProductListPage(Hooks.driver);
		pgProduct.clickCart();
		String name_cart=pgCart.getProductName(sProductName);
		if(sProductName.equals(name_cart))
		{
		Assert.assertEquals(pgCart.CheckoutValidation(), true);
		pgCart.ClickCheckout();
		pgCheckout=new CheckoutPage(Hooks.driver);
		
		Assert.assertTrue(pgCheckout.getCheckoutPageTitle().contains("Checkout: Your Information"));
		}
		else if(name_cart.equals("NO product"))
		{
			Assert.assertEquals(pgCart.CheckoutValidation(), false);
		}
	}
	
	
	
	
	
	
	
}
