package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import testBase.BaseClass;

public class TC_002_Login extends BaseClass{
	
	@Test(groups= {"sanity","master"})
	public void testLogin()
	{
		logger.info("Starting TC_002_Login ");
		
		try {
		
		driver.get(rb.getString("appURL"));//getting URL from the property file by using resource bundle
		
		logger.info("Home Page Displayed");
		
		driver.manage().window().maximize();
		
		HomePage hp=new HomePage(driver);
		hp.clickMyAccount();
		logger.info("Clicked on My Account");
		hp.clickLogin();
		logger.info("Clicked on Login");
		
		LoginPage lp=new LoginPage(driver);
		lp.setEmail(rb.getString("email"));//should enter the key value from the property file
		logger.info("Provided Email");
		lp.setPassword(rb.getString("password"));
		logger.info("Provided Password");
		lp.clickLogin();
		logger.info("Clicked on Login");
		
		boolean targetPage=lp.isMyAccountPageExists();
		
		if(targetPage)
		{
			logger.info("Login is Success");
			Assert.assertTrue(true);
		}
		else
		{
			logger.info("Login Failed");
			captureScreen(driver,"testLogin");//if the Tc fails need to capture a screen shot so called that method
			Assert.fail();
		}
		}
		catch(Exception e)
		{
			logger.fatal("Login Failed");
			Assert.fail();
		}
		logger.info("Finished TC_002_Login");
		
	}

}
