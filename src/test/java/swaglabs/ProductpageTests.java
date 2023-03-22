package swaglabs;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import swaglabspages.CartPage;
import swaglabspages.LoginPage;
import swaglabspages.ProductDetailPage;
import swaglabspages.ProductListPage;

@Listeners(listeners.TestNGListeners.class)
public class ProductpageTests {
	
	LoginPage pglogin;
	ProductListPage pgProduct;
	ProductDetailPage pgDetail;
	CartPage pgCart;
	
	/*
	 * { pglogin=new LoginPage(Hooks.driver); pgProduct=new
	 * ProductListPage(Hooks.driver); pgDetail=new ProductDetailPage(Hooks.driver);
	 * }
	 */
	 
	Properties prop;
	
	
	@Test(groups="Login")
	public void Login()
	{
		pglogin=new LoginPage(Hooks.driver);
		String[] error=pglogin.Login_flow("standard_user", "secret_sauce");
		
	}
	
	@DataProvider(name="SortBy")
	public Object[] sortbyvalue()
	{
		String[] sSortbyValue;
		prop=Hooks.prop;
		String s=prop.getProperty("sort");
		sSortbyValue=s.split(",");
		return sSortbyValue;
		
	}
	

	
	  @Test( dependsOnMethods={"Login"},dataProvider = "SortBy",enabled=false)
	  public void  ProductSorted(String value)
	  { 
		 
		  pgProduct=new ProductListPage(Hooks.driver);
	      boolean bsorted=pgProduct.Sort(value); 
	      Assert.assertTrue(bsorted); }
	 
	
	@DataProvider(name="ProductList")
	public Object[] ProductList()
	{
		prop=Hooks.prop;
		String s=prop.getProperty("SelectedProduct");
		String[] list=s.split(",");
		return list;
		
	}
	
	@Test(dependsOnMethods={"Login"},dataProvider="ProductList",enabled=false)
	public void SelectedProduct_Compare(String product)
	{
		
		pgProduct=new ProductListPage(Hooks.driver);
		pgDetail=new ProductDetailPage(Hooks.driver);
		String[] product_details=pgProduct.product_select(product);
		boolean compare=pgDetail.CompareNameandPrice(product_details);
		Assert.assertTrue(compare);
		pgDetail.ClickBackbutton();
		 
	}
	
	@Test(dependsOnMethods= {"Login"},dataProvider="ProductList",enabled=false)
	public void ButtonNameChange(String sProductName)
	{
		boolean bnamechange;
		WebElement button;
		pgProduct=new ProductListPage(Hooks.driver);
		button=pgProduct.ClickAddToCart(sProductName);
		bnamechange=pgProduct.NameChangeValidation(button,"Remove");
		Assert.assertTrue(bnamechange);
		button=pgProduct.ClickRemove(sProductName);
		bnamechange=pgProduct.NameChangeValidation(button,"Add to cart");
		Assert.assertTrue(bnamechange);
	}
	
	@Test(dependsOnMethods= {"Login"},dataProvider="ProductList",enabled=false)
	public void AddToCartBadgeUpdate(String sProductName)
	{
		int Cartcount;
		int ProductAddedCount;
		WebElement button;
		pgProduct=new ProductListPage(Hooks.driver);
		 button=pgProduct.ClickAddToCart(sProductName);
		 Cartcount=pgProduct.getCart_count();
		 ProductAddedCount=pgProduct.getRemove_count(button);
		 Assert.assertTrue(Cartcount==ProductAddedCount);
	}

	 @Test(dependsOnMethods= {"Login"},dataProvider="ProductList",groups="AddProductsToCart")
	 public void CartProductValidation(String sProductName)
	 {
		 pgProduct=new ProductListPage(Hooks.driver);
		 pgCart=new CartPage(Hooks.driver);
		 pgProduct.ClickAddToCart(sProductName);
		 pgProduct.clickCart();
		 
		 String name_cart=pgCart.getProductName(sProductName);
		 
		 Assert.assertTrue(name_cart.equals(sProductName) || name_cart.equals("NO product"));
		 pgCart.ClickContinueShopping();
	 }
	 
	 @Test(dependsOnMethods= {"Login"},dataProvider="ProductList",enabled=false)
	 public void CartRemoveValidation(String sProductName)
	 {
		 pgProduct=new ProductListPage(Hooks.driver);
		 pgProduct.ClickRemove(sProductName);
		 pgProduct.clickCart();
		 pgCart=new CartPage(Hooks.driver);
		 String name_cart=pgCart.getProductName(sProductName);
		 System.out.println("Cart name:"+name_cart);
		 System.out.println("Product_name"+sProductName);
		 Assert.assertTrue(name_cart.equals(""));
		 pgCart.ClickContinueShopping();
	 }
	
}
