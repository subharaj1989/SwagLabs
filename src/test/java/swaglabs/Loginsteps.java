package swaglabs;

import java.util.ArrayList;
import java.util.Properties;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import junit.framework.Assert;
import swaglabspages.ConfigReader;
import swaglabspages.LoginPage;
import swaglabspages.ProductPage;

public class Loginsteps {

	LoginPage pglogin;
	ProductPage pgProduct;
	Properties prop;
	
	@DataProvider(name="UserData")
	public Object[] UserValues()
	{
		prop=ConfigReader.readproperty();
		//ArrayList<String> aUserName=new ArrayList<String>();
		String s=prop.getProperty("user");
		String[] users;
		
			users=s.split(",");
			
		
		return users;
		
	}
	
	
	@Test(dataProvider= "UserData")
	public void Login(String user)
	{
		pglogin=new LoginPage(Hooks.driver);
		pgProduct=new ProductPage(Hooks.driver);
		String password=prop.getProperty("password");
		String[] errormessages=pglogin.Login_flow(user,password);
		if(errormessages[0]!=null)
		{
			Assert.assertTrue( errormessages[0].contains("Username is required"));
		}
		
			else if(errormessages[1]!=null)
			{
				Assert.assertTrue( errormessages[1].contains("Password is required"));
			}
			else if(errormessages[2]!=null)
			{
				Assert.assertTrue( errormessages[2].contains("Username and password do not match"));
			}
			else
			{
				
				String title=pgProduct.getProductpageTitle();
				Assert.assertEquals("Products", title);
				pgProduct.logout();
			}
		 
		
		
		
		
	}
	
   
	
}
