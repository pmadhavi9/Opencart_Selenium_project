package testBase;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.ResourceBundle;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.*;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {
	
	public WebDriver driver;
	public Logger logger;//for logging, creating the logger class variable(object)
	public ResourceBundle rb;//to access the property file
	
	 @BeforeClass(groups= {"master","sanity","regression"})
	 @Parameters ({"browser"})
	 public void setup(String br) throws InterruptedException
	    {
		 	//Loading config.properties file
		 	
		 	rb=ResourceBundle.getBundle("config");
		 	
		  	//Logging
		 
		 	logger= LogManager.getLogger(this.getClass());//initiating the logger class
		 	
		 	//Drivers
		 	
		 	if(br.equals("chrome"))
		 	{
		 	
		 		WebDriverManager.chromedriver().setup();
		 		driver=new ChromeDriver();
		 		logger.info("Launched Chrome browser");
			}
		 	else if(br.equals("edge"))
		 	{
		 		WebDriverManager.edgedriver().setup();
		 		driver=new EdgeDriver();
		 		logger.info("Launched Edge browser");
		 		//Thread.sleep(3000);
		 		
		 	}
		 	else if(br.equals("firefox"))
		 	{
		 		WebDriverManager.firefoxdriver().setup();
		 		driver=new FirefoxDriver();
		 		logger.info("Launched Firefox browser");
		 			 		
		 	}
		 	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
	    }
	 
	 @AfterClass(groups= {"master","sanity","regression"})
	 public void tearDown()
	 {
		 driver.quit();
	 }
	 
	 public String randomstring()
	 {
		 String generatedString=RandomStringUtils.randomAlphabetic(5);//here 5 means it generate 5 character string
		 return(generatedString);
	 }
	 public int randomNumber()
	 {
		 String generatedString2=RandomStringUtils.randomNumeric(5);
		 return(Integer.parseInt(generatedString2));//changing from string to integer
	 }
	 public void captureScreen(WebDriver driver, String tname) throws IOException
	 {
		 TakesScreenshot ts= ((TakesScreenshot)driver);//type casting-WebDriver type driver to takeScreenshot type
		 File source=ts.getScreenshotAs(OutputType.FILE);
		 //File target=new File("C:\\Users\\madhavi\\eclipse-workspace(pavan_tutorial)\\OpenCart\\screenshots"+tname+".png");
		 File target=new File(System.getProperty("user.dir")+"\\screenshots\\"+tname+".png");//can add any img extentions
		 FileUtils.copyFile(source, target);
	 }

}
