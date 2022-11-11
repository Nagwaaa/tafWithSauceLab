package tests;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import data.readPropertiesFile;
import utilities.Helper;

public class testBase2 {
	
	public static final String username=readPropertiesFile.sauseData.getProperty("userName");
	public static final String key=readPropertiesFile.sauseData.getProperty("accessKey");
	public static final String url="https://"+username+":"+key+readPropertiesFile.sauseData.getProperty("sauceUrl");
	public static String BaseUrl="https://demo.nopcommerce.com/";
	
	protected ThreadLocal<RemoteWebDriver> driver=null;
	
	@BeforeClass
	@Parameters({"browser"})
	public void setup(@Optional("chrome") String bname) throws MalformedURLException
	{
		System.out.println("saucelab url"+url);
		driver=new ThreadLocal<>();
		DesiredCapabilities caps=new DesiredCapabilities();
		caps.setCapability("browserName", bname);
		//driver.set(new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"),caps));
		driver.set(new RemoteWebDriver(new URL(url),caps));
		getDriver().navigate().to(BaseUrl);		
		
	}
	
	public WebDriver getDriver()
	{
		 return driver.get();
	}
	
	@AfterClass
	public void closeDreiver()
	{
		getDriver().quit();
		driver.remove();
		
		
	}
	
	@AfterMethod
	public void TakeScreenOnFailure(ITestResult res) throws IOException
	{
		
		if(res.getStatus()==ITestResult.FAILURE)
		{
			System.out.println("Failed..");
			System.out.println("Taking screenshot...!");
			Helper.CaptureScreenshot(getDriver(), res.getName());
			
		}
	}

}
