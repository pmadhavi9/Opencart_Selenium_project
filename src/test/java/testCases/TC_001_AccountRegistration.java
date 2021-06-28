package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.BaseClass;

public class TC_001_AccountRegistration extends BaseClass{
	

	@Test(groups= {"regression","master"})
	public void test_account_Registration()
	{
		logger.info("Starting TC_001_AccountRegistration ");
		logger.info("-------------------------------------------------");
		
		try
		{
		
		driver.get(rb.getString("appURL"));//getting URL from property file
		
		logger.info("Home Page Displayed");
		
		driver.manage().window().maximize();
		
		HomePage hp=new HomePage(driver);
		hp.clickMyAccount();
		logger.info("Clicked on My Account");
		
		hp.clickRegister();
		logger.info("Clicked on Register");
		
		AccountRegistrationPage regPage= new AccountRegistrationPage(driver);
		
		regPage.setFirstName("John");
		logger.info("Provided first name");
		
		regPage.setLastName("Kennydy");
		logger.info("Provided last name");
		
		regPage.setEmail(randomstring()+"@gmail.com");
		logger.info("Provided Email");
		
		regPage.setTelephone("32823920");
		logger.info("Provided Telephone number");
		
		regPage.setPassword("test123");
		logger.info("Provided Password");
		
		regPage.setConfirmPassword("test123");
		logger.info("Provided Confirmed Password");
		
		regPage.setPrivacyPolicy();
		logger.info("Set Privacy Policy");
		
		regPage.clickContinue();
		logger.info("Clicked on Continue");
		
		String confmsg=regPage.getConfirmationMsg();
		
		if(confmsg.equals("Your Account Has Been Created!"))
		{
			logger.info("Account Registration Success");
			Assert.assertTrue(true);
			
		}
		else
		{ 
			logger.info("Account Registration Failed");
			//As soon as invoke this method screen shot will be in screenshots folder
			captureScreen(driver,"test_account_Registration");
			Assert.assertTrue(false);
		}
				
		}
		
		catch(Exception e)
		{
			logger.info("Account Registration Failed");
			Assert.fail();
		}
		logger.info("Finished TC_001_AccountRegistration");
	}

	
}
