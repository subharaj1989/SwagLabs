package swaglabs;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import swaglabspages.WebDriverFactory;

public class Hooks {

	
	public static WebDriver driver;
	@BeforeTest
	public void Driversetup()
	{
		driver=WebDriverFactory.driver_initailize();
		 driver.get("https://www.saucedemo.com/");
	}
	
	
	
	  @AfterTest
	  public void Driverclose()
	  { 
		  driver.close(); 
	  }
	 
}
