package swaglabs;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import driverfactory.WebDriverFactory;
import utils.ConfigReader;

public class Hooks {

	
	public static WebDriver driver;
	public static Properties prop;
	@BeforeTest
	public void setup()
	{
		driver=WebDriverFactory.driver_initailize();
		 driver.get("https://www.saucedemo.com/");
		prop= ConfigReader.readproperty();
	}
	
	
	
	  @AfterTest
	  public void Driverclose()
	  { 
		 // driver.close(); 
	  }
	 
}
