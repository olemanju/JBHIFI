package com.wip.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;


public class BaseTestObject 
{
	
	protected WebDriver driver;
	
	public static String propertyFilePath=System.getProperty("user.dir")+"\\src\\test\\resources\\testdata\\data.properties";
	public static String chromeDriverPath=System.getProperty("user.dir")+"\\src\\test\\resources\\drivers\\81\\chromedriver.exe";
	public static String IEDriverPath=System.getProperty("user.dir")+"\\src\\test\\resources\\drivers\\IE\\IEDriverServer.exe";
	public static String geckoDriverPath=System.getProperty("user.dir")+"\\src\\test\\resources\\drivers\\geckodriver\\geckodriver.exe";
	public static String lo4jpath= System.getProperty("user.dir")+"\\src\\test\\resources\\testdata\\log4j.properties";
	FileInputStream fileInput =null;
	
	VedioRecording test=new VedioRecording();
	

	public Properties ObjProperty=getPropertyContents();
	public String browser = ObjProperty.getProperty("browser");
	public String url = ObjProperty.getProperty("url");
	

	private static final Properties prop = new Properties();

	private static void loadPropertiesFile() 
	{
		InputStream input = null;

		try
		{
			input = new FileInputStream(propertyFilePath);
			// load a properties file
			prop.load(input);
		} 
		catch (IOException ex) 
		{
			ex.printStackTrace();
		} 
		finally 
		{
			if (input != null) 
			{
				try
				{
					input.close();
				} 
				catch (IOException e) 
				{
					e.printStackTrace();
				}
			}
		}
	}

	public static Properties getPropertyContents() {
		loadPropertiesFile();
		return prop;
	}
	
	/**
	 
     * This function will execute before each Test tag in testng.xml
 
     * @param browser
 
     * @throws Exception
 
     */
	@Parameters({"browserType"})
	@BeforeTest(alwaysRun = true)
	  public void setup(String browserType) throws Exception
	{
	
	
        if(browser.equalsIgnoreCase("FF"))
        {
        	System.out.println(browserType);
        	System.setProperty("webdriver.gecko.driver",geckoDriverPath);
            driver = new FirefoxDriver();
        }
        else if(browser.trim().equalsIgnoreCase(browserType.trim()))
        {
        	System.out.println(browser);
        	System.setProperty("webdriver.chrome.driver",chromeDriverPath);
            driver = new ChromeDriver();
        }
        else if(browser.equalsIgnoreCase("IE")){
            System.setProperty("webdriver.ie.driver",IEDriverPath);
            driver = new InternetExplorerDriver();
        }
        else
        {
        	throw new Exception("Browser is not correct");
        }
       
        
        driver.get(url);
        test.startRecording();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS); 
        driver.manage().window().maximize();
        
}
	
	@AfterTest(alwaysRun = true)
	public void tearDown() throws Exception{
		test.stopRecording();
		driver.quit();
       
	}
	
	public void closePopUp() throws InterruptedException{
		String parent = driver.getWindowHandle();
		Set<String>pops=driver.getWindowHandles();
        {
        Iterator<String>it = pops.iterator();
        while (it.hasNext()) {
            String popupHandle=it.next().toString();
            if(!popupHandle.contains(parent))
            {
            driver.switchTo().window(popupHandle);
            System.out.println("Popu Up Title: "+ driver.switchTo().window(popupHandle).getTitle());
            driver.close();
            Thread.sleep(5000);
            }
        }
	}
	}
}



