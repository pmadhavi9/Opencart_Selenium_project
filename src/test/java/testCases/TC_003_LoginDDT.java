package testCases;

import java.io.IOException;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.*;


import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;
import utilities.XLUtility;

public class TC_003_LoginDDT extends BaseClass {
	
	@Test(dataProvider="LoginData")
	public void test_LoginDDT(String email,String pwd,String exp)
	{
		logger.info("Starting TC_003_LoginDDT");
		
		try
		{
			driver.get(rb.getString("appURL"));
			logger.info("Home Page Displayed");
			
			driver.manage().window().maximize();
			
			HomePage hp=new HomePage(driver);
			hp.clickMyAccount();
			logger.info("Clicked on MyAccount");
			hp.clickLogin();
			logger.info("Clicked on Login");
			
			LoginPage lp=new LoginPage(driver);
			lp.setEmail(email);
			logger.info("Provided Email");
			lp.setPassword(pwd);
			logger.info("Provided Password");
			lp.clickLogin();
			logger.info("Clicked on Login");
			
			
		    boolean targetPage=lp.isMyAccountPageExists();
		    
		    if(exp.equals("Valid"))
		    {
		    	if(targetPage==true)
		    	{
		    		logger.info("Login Success");
		    		MyAccountPage myaccpage=new MyAccountPage(driver);
		    		myaccpage.clickLogout();
		    		Assert.assertTrue(true);
		     	 }
		    	else
		    	{
		    		logger.info("Login Failed");
		    		Assert.assertTrue(false);
		      	}
		    }
		    if(exp.equals("Invalid"))
		    {
		    	if(targetPage==true)
		    	{
		    		//logger.info("Login Failed");
		    		MyAccountPage myaccpage=new MyAccountPage(driver);
		    		myaccpage.clickLogout();
		    		Assert.assertTrue(false);
		    	}
		    	else   //test pass
		    	{
		    		logger.info("Login Failed");
		    		Assert.assertTrue(true);
		    	}
		    }
			
		}
		catch(Exception e)
		{
			logger.fatal("Login Failed");
			Assert.fail();
		}
		logger.info("Finished TC_003_LoginDDT");
	}
	
	//DataProvider method to read data from the excel vi Utilities class
	
	@DataProvider(name="LoginData")
	public String[][] getData() throws IOException
	{
		String path=".\\testData\\Opencart_loginData.xlsx";
		//String path="C:\\Users\\madhavi\\eclipse-workspace(pavan_tutorial)\\OpenCart\\testData\\OpenCart_loginData.xlsx";
		XLUtility xlutil = new XLUtility(path);//to read data from excel
		int totalRows=xlutil.getRowCount("sheet1");
		int totalCols=xlutil.getCellCount("sheet1",1);
		
		String logindata[][]=new String[totalRows][totalCols];
		
		for(int r=1;r<=totalRows;r++)//r=1 becos we don't need to read the header of the excel sheet
		{
			for(int c=0;c<totalCols;c++)
			{
				logindata[r-1][c]=xlutil.getCellData("sheet1", r, c);//why r-1 becos it has to store in the very first row of an 2 dim array
			}
		}
		return logindata;
	}	


}