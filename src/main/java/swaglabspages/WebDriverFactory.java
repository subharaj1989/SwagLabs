package swaglabspages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class WebDriverFactory {

	static WebDriver driver;
	 public static  WebDriver driver_initailize()
	 {
		 WebDriverManager.chromedriver().setup();
		 ChromeOptions options=new ChromeOptions();
			options.addArguments("--remote-allow-origins=*");
		 driver=new ChromeDriver(options);
		
		 return driver;
	 }
}
