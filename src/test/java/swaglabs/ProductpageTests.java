package swaglabs;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import swaglabspages.LoginPage;
import swaglabspages.ProductDetailPage;
import swaglabspages.ProductListPage;

@Listeners(listeners.TestNGListeners.class)
public class ProductpageTests {
	
	LoginPage pglogin;
	ProductListPage pgProduct;
	ProductDetailPage pgDetail;
	
	/*
	 * { pglogin=new LoginPage(Hooks.driver); pgProduct=new
	 * ProductListPage(Hooks.driver); pgDetail=new ProductDetailPage(Hooks.driver);
	 * }
	 */
	 
	Properties prop;
	
	@Test
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
	

	
	  @Test( dependsOnMethods={"Login"},dataProvider = "SortBy")
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
	
	@Test(dependsOnMethods={"Login"},dataProvider="ProductList")
	public void SelectedProduct_Compare(String product)
	{
		
		pgProduct=new ProductListPage(Hooks.driver);
		pgDetail=new ProductDetailPage(Hooks.driver);
		String[] product_details=pgProduct.product_select(product);
		pgDetail.CompareNameandPrice(product_details);
		pgDetail.ClickBackbutton();
		 
	}
}
