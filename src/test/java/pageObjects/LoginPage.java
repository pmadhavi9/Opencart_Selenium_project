package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	
    WebDriver driver;
	
	public LoginPage(WebDriver driver)//Constructor
	{
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}
	
	//Locators
	
	@FindBy(id="input-email")
	WebElement txtEmail;
	
	@FindBy(id="input-password")
	WebElement txtPassword;
	
	@FindBy(xpath="//input[@value='Login']")
	WebElement btnLogin;
	
	@FindBy(xpath="//h2[text()='My Account']")
	WebElement msgHeading;
	
	//Action methods
	
	public void setEmail(String email)
	{
		txtEmail.sendKeys(email);
	}
	public void setPassword(String pwd)
	{
		txtPassword.sendKeys(pwd);
	}
	public void clickLogin()
	{
		btnLogin.click();
	}
	public boolean isMyAccountPageExists()
	{
		try {
		return(msgHeading.isDisplayed());
	       }
		catch(Exception e)
		{
			return(false);
		}
	}
}

