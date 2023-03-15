package swaglabspages;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProductPage {

	WebDriver driver;
	@FindBy(xpath="//*[@id=\"header_container\"]//span[text()=\"Products\"]")
	private WebElement lblProduct;
	
	@FindBy(id="react-burger-menu-btn")
	private WebElement btnMenu;
	
	@FindBy(id="logout_sidebar_link")
	private WebElement lnkLogout;
	
	public ProductPage(WebDriver driver)
	{
		this.driver=driver;
    	PageFactory.initElements(driver, this);
	}
	
	public String getProductpageTitle()
	{
		return lblProduct.getText();
	}
	
	public void logout()
	{
		btnMenu.click();
		WebDriverWait w=new WebDriverWait(driver,Duration.ofSeconds(10));
		w.until(ExpectedConditions.elementToBeClickable(lnkLogout));
		
		lnkLogout.click();
	}
}
