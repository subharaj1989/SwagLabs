package swaglabspages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductDetailPage {

	
	WebDriver driver;
	@FindBy(xpath="//*[@id=\"inventory_item_container\"]//div[@class=\"inventory_details_name large_size\"]")
	private WebElement lblProductName;
	
	@FindBy(xpath="//*[@id=\"inventory_item_container\"]//div[@class=\"inventory_details_price\"]")
	private WebElement lblProductPrice;
	
	@FindBy(id="back-to-products")
	private WebElement btnBacktoProducts;
	public ProductDetailPage(WebDriver driver)
	{
		this.driver=driver;
    	PageFactory.initElements(driver, this);
	}
	public boolean CompareNameandPrice(String[] productDetails)
	{
		boolean bcompare=false;
		if((productDetails[0].equals(lblProductName.getText())&& (productDetails[1].equals(lblProductPrice.getText()))))
				{
			       bcompare=true;
				}
		
		return bcompare;
	}
	
	public void ClickBackbutton()
	{
		btnBacktoProducts.click();
	}
	
}
