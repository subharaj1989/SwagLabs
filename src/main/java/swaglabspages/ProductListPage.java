package swaglabspages;


import java.util.ArrayList;
import java.util.Comparator;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.collect.Ordering;

import utils.AlphaNumericComparator;

public class ProductListPage {

	WebDriver driver;
	@FindBy(xpath="//*[@id=\"header_container\"]//span[text()=\"Products\"]")
	private WebElement lblProduct;
	
	@FindBy(id="react-burger-menu-btn")
	private WebElement btnMenu;
	
	@FindBy(id="logout_sidebar_link")
	private WebElement lnkLogout;
	
	@FindBy(xpath="//*[@id=\"header_container\"]/div[2]//select[@class=\"product_sort_container\"]")
	private WebElement ddProductSort;
	
	@FindBy(xpath="//*[@id=\"inventory_container\"]//div[@class=\"inventory_item_name\"]")
	private List<WebElement> lproductname;
	
	@FindBy(xpath="//*[@id=\"inventory_container\"]//div[@class=\"inventory_item_price\"]")
	private List<WebElement> lproductprice;
	public ProductListPage(WebDriver driver)
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
		
		
		lnkLogout.click();
	}
	
	public boolean Sort(String value)
	{
		List<String> lproduct_vaues=new ArrayList<String>();
		boolean bSorted=false;
		Select sProductsort=new Select(ddProductSort);
		sProductsort.selectByVisibleText(value);
		if(value.equals("Name (A to Z)"))
		{
			for(int i=0;i<lproductname.size();i++)
			{
				lproduct_vaues.add(lproductname.get(i).getText());
				
			}
			if (lproduct_vaues.stream().sorted().collect(Collectors.toList()).equals(lproduct_vaues))
			{
				bSorted=true;
			}
		}
		
		if(value.equals("Name (Z to A)"))
		{
			for(int i=0;i<lproductname.size();i++)
			{
				lproduct_vaues.add(lproductname.get(i).getText());
				
			}
			List<String> u=new ArrayList<String>();
			u.addAll(lproduct_vaues);
			Collections.sort(u,Collections.reverseOrder());
			if(lproduct_vaues.equals(u))
			{
				bSorted=true;
			}
			/*
			 * if
			 * (lproduct_vaues.stream().sorted(Comparator.reverseOrder()).collect(Collectors
			 * .toList()).equals(lproduct_vaues)) { bSorted=true; }
			 */
			
		}
		
		if(value.equals("Price (low to high)"))
		{
			for(int i=0;i<lproductprice.size();i++)
			{
				lproduct_vaues.add(lproductprice.get(i).getText());
				
			}
			if(lproduct_vaues.stream().sorted(new AlphaNumericComparator()).collect(Collectors.toList()).equals(lproduct_vaues))
			{
				bSorted=true;
			}
			
		}
		
		if(value.equals("Price (high to low)"))
		{
			for(int i=0;i<lproductprice.size();i++)
			{
				lproduct_vaues.add(lproductprice.get(i).getText());
				
			}
			if(lproduct_vaues.stream().sorted(new AlphaNumericComparator().reversed()).collect(Collectors.toList()).equals(lproduct_vaues))
			{
				bSorted=true;
			}
			
		}
		return bSorted;
	}
	
	public String[] product_select(String sSelectedProduct)
	{
		String[] product_details= new String[2];
		
		for(WebElement s:lproductname)
		{
			if(s.getText().equals(sSelectedProduct))
			{
				
				WebElement p=s.findElement(By.xpath("./parent::a/parent::div/following-sibling::div/div"));
				 product_details[0]= s.getText();
				 product_details[1]=p.getText().replace("$","").trim();
				s.click();
				break;
			}
		}
		return product_details;
		
	}
	
	
}
