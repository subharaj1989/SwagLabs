package swaglabspages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CartPage {
    WebDriver driver;
	@FindBy(xpath="//div[@class=\"inventory_item_name\"]")
	private List<WebElement> lProductName;
	
	@FindBy(id="continue-shopping")
	WebElement btnContinueShopping;
	
	@FindBy(id="checkout")
	WebElement btnCheckout;
	public CartPage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	public int getCartSize()
	{
		return lProductName.size();
	}
	public String getProductName(String sProductName)
	{
		String name="";
		if (lProductName.size()==0)
		{
			name="NO product";
		}
		for(WebElement w:lProductName)
		{
			
			 if(w.getText().equals(sProductName))
			 {
				 name=w.getText();
			 break;
			 }
			
		}
		return name;
	}
	
	public void ClickContinueShopping()
	{
		btnContinueShopping.click();
	}
	
	public void ClickCheckout()
	{
		
		btnCheckout.click();
	}
	
	public boolean CheckoutValidation()
	{
		boolean benabled;
		benabled=btnCheckout.isEnabled();
		return benabled;
		
	}
	
}
