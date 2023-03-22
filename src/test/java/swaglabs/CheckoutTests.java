package swaglabs;

import java.util.HashMap;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.Properties;

import org.junit.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import swaglabspages.CartPage;
import swaglabspages.CheckoutPage;
import swaglabspages.ProductListPage;

public class CheckoutTests {

	CheckoutPage pgCheckout;
	ProductListPage pgProduct;
	CartPage pgCart;
	
	@DataProvider(name="CheckoutDetails")
	public Iterator<Object[]> CheckoutDetails()
	{
		Properties prop=Hooks.prop;
		String[] Firstname=prop.getProperty("Firstname").split(",");
		String[] Lastname=prop.getProperty("Lastname").split(",");
		String[] postalcode=prop.getProperty("postalcode").split(",");
		//HashMap<String,String> h=new HashMap<String,String>();
		ArrayList<Object []> l=new ArrayList<Object[]>();
		for(int i=0; i<Firstname.length;i++)
		{
			
			l.add(new Object[] {Firstname[i],Lastname[i],postalcode[i]});
		}
		return l.iterator();
		
	}
	@Test(dependsOnGroups = "AddProductsToCart",dataProvider ="CheckoutDetails")
	public void CheckOutValidation(String firstname,String lastname,String postalcode )
	{
		pgProduct=new ProductListPage(Hooks.driver);
		pgProduct.clickCart();
		pgCart=new CartPage(Hooks.driver);
		pgCart.ClickCheckout();
		pgCheckout=new CheckoutPage(Hooks.driver);
		pgCheckout.EnterCheckoutDetails(firstname, lastname, postalcode);
		if(firstname.equals(""))
		{
			Assert.assertEquals(pgCheckout.getErrormessage(),"Error: First Name is required");
		}
		else if(lastname.equals(""))
		{
			Assert.assertEquals(pgCheckout.getErrormessage(),"Error: Last Name is required");
		}
		
		else if(postalcode.equals(""))
		{
			Assert.assertEquals(pgCheckout.getErrormessage(),"Error: Postal Code is required");
		}
	}
}
