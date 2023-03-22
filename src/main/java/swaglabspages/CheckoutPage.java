package swaglabspages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckoutPage {

	WebDriver driver;
	@FindBy(xpath="//*[@id=\"header_container\"]//span[contains(text(),\"Checkout\")]")
	WebElement lblCheckoutTitle;
	
	@FindBy(id="first-name")
	WebElement txtFirstname;
	@FindBy(id="last-name")
	WebElement txtLastname;
	@FindBy(id="postal-code")
	WebElement txtpostalcode;
	@FindBy(id="continue")
	WebElement btnContinueCheckout;
	
	@FindBy(xpath="//*[@id=\"checkout_info_container\"]//div[@class=\"error-message-container error\"]/h3[@data-test=\"error\"]")
	WebElement lblErrormessage;
	
	
	
	public CheckoutPage(WebDriver driver) 
	{
		this.driver=driver;
	   PageFactory.initElements(driver, this);
	}


	public String getCheckoutPageTitle()
	{
		return lblCheckoutTitle.getText();
	}
	
	public void EnterCheckoutDetails(String firstname,String lastname,String postalcode)
	{
		txtFirstname.clear();
		txtFirstname.sendKeys(firstname);
		txtLastname.clear();
		txtLastname.sendKeys(lastname);
		txtpostalcode.clear();
		txtpostalcode.sendKeys(postalcode);
		btnContinueCheckout.click();
		
	}
	
	public String getErrormessage()
	{
		return lblErrormessage.getText();
	}
}
