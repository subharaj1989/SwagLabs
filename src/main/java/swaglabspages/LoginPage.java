package swaglabspages;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class LoginPage {

	WebDriver driver;
	 List<String> users=new ArrayList<String>();
	boolean flag_validUser;
	@FindBy(id="user-name")
	private WebElement txtUsername;
	@FindBy(id="password")
	private WebElement txtPassword;
	@FindBy(id="login-button")
	private WebElement btnLoginButton;
	@FindBy(xpath="//*[@id=\"login_credentials\"]/text()")
	private List<String> lblValidUsers;
	@FindBy(xpath="//*[@id=\"root\"]//div[@class=\"login_password\"]")
	private WebElement lblValidPassword;
	@FindBy(xpath="//*[@id=\"login_credentials\"]")
	private WebElement ValidUsers;
	@FindBy(xpath="//*[@id=\"login_button_container\"]//div[@class=\"error-message-container error\"]/h3")
	private WebElement lblErrormessage;
	
    public LoginPage(WebDriver driver)
    {
    	this.driver=driver;
    	PageFactory.initElements(driver, this);
    	
    	
    }
    
   
	
    
    public boolean Valid_Users(String UserEntered,String PasswordEntered)
    {
    	
    	String[] s=ValidUsers.getText().split("\\r?\\n|\\r");
    	users=Arrays.asList(s);
    	
    	
    	if(users.contains(UserEntered) && lblValidPassword.getText().contains(PasswordEntered))
    	{
    		flag_validUser=true;
    	}
		return flag_validUser;
    }
    
    public String[] Login_flow(String user, String password)
    {
        flag_validUser=false;
    	String errormessage_mismatch=null;
    	String errormessage_user=null;
    	String errormessage_password=null;
    	txtUsername.clear();
    	
    	txtUsername.sendKeys(user);
    	txtPassword.clear();
    	txtPassword.sendKeys(password);	
    	flag_validUser=Valid_Users(user,password);
    	btnLoginButton.click();
        
        if(flag_validUser==false)
        {
        	errormessage_mismatch=lblErrormessage.getText();
        }
        if (user== null)
        {
        	errormessage_user=lblErrormessage.getText();
        }
        else if(password==null)
        {
        	errormessage_password=lblErrormessage.getText();
        }
        String[] errormessages= {errormessage_user,errormessage_password,errormessage_mismatch};
		return errormessages;
        
    	
    }
}
