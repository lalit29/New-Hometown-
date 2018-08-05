package com.hometown.qa.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.hometown.qa.util.TestUtil;
import com.hometown.qa.util.WebEventListener;
import com.relevantcodes.extentreports.ExtentTest;

public class TestBase extends TestUtil {
	
	public static WebDriver driver;
	public static Properties prop;
	public  static EventFiringWebDriver e_driver;
	public static WebEventListener eventListener;

	public WebDriver getDriver() {
       return driver;
   }

	
	public TestBase() {
		try {
			prop=new Properties();
			FileInputStream ip= new FileInputStream("C:\\Users\\436793\\eclipse-workspace\\New Hometown\\src\\main\\java\\com\\hometown\\qa\\config\\config.properties");
			prop.load(ip);
			} 
		catch(FileNotFoundException e) {
			e.printStackTrace();
				
			} catch (IOException e) {
				e.printStackTrace();
		}
		
	}
	

	
	public static void initialization() {
		String browserName=System.getProperty("browser");{
		
	     // String browserName = prop.getProperty("browser");{
		if(browserName.equals("chrome")) {
			 System.out.println("Chrome Launched Successfully");
		
			/*
			ChromeOptions chromeOptions = new ChromeOptions();  
		    System.setProperty("webdriver.chrome.driver","C:\\Users\\436793\\eclipse-workspace\\New Hometown\\drivers\\chromedriver.exe");
		    chromeOptions.addArguments("--headless");  
		    chromeOptions.addArguments("--disable-gpu");  
		    chromeOptions.addArguments("window-size=1200x600");
		    driver=new ChromeDriver(chromeOptions);
		    
		    */
			
		 System.setProperty("webdriver.chrome.driver","C:\\Users\\436793\\eclipse-workspace\\New Hometown\\drivers\\chromedriver.exe");
		 driver=new ChromeDriver();
		 
			
		}
		
		if(browserName.equals("firefox")) {
			  System.out.println("Firefox Launched Successfully");
			  
			FirefoxOptions firefoxOptions= new FirefoxOptions();
			System.setProperty("webdriver.gecko.driver", "C:\\Users\\436793\\eclipse-workspace\\New Hometown\\drivers\\geckodriver.exe");
			firefoxOptions.addArguments("--headless");  
			firefoxOptions.addArguments("--disable-gpu");  
			firefoxOptions.addArguments("window-size=1200x600");
		    driver=new FirefoxDriver(firefoxOptions);
			
			
			//System.setProperty("webdriver.gecko.driver", "C:\\Users\\436793\\eclipse-workspace\\New Hometown\\drivers\\geckodriver.exe");
			//driver = new FirefoxDriver();
			
		}
		
		else if(browserName.equals("ie")) {
			
			 System.setProperty("webdriver.ie.driver", "C:\\Users\\436793\\eclipse-workspace\\New Hometown\\drivers\\IEDriverServer.exe");
			driver = new InternetExplorerDriver();
			
		}
		else if(browserName.equals("opera")) {
			 System.out.println("IE Launched Successfully");
			
			 System.setProperty("webdriver.chrome.driver", "C:\\Users\\436793\\eclipse-workspace\\New Hometown\\drivers\\operadriver.exe");
			driver = new OperaDriver();
			
		}
		
		e_driver = new EventFiringWebDriver(driver);
		// Now create object of EventListerHandler to register it with EventFiringWebDriver
		eventListener = new WebEventListener();
		e_driver.register(eventListener);
		driver = e_driver;

		
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		
		driver.get(System.getProperty("environment"));
		//driver.get(prop.getProperty("url"));
		
	}
  }
	

	
	
	
}
