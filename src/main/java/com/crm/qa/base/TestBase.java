package com.crm.qa.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import com.crm.qa.util.TestUtil;
import com.crm.qa.util.WebEventListener;


public class TestBase {
	public static WebDriver driver;
	public static Properties prop;
	public static EventFiringWebDriver e_driver;
	public static WebEventListener eventListener;
	public static Path path;
	public static Logger log = Logger.getLogger(TestBase.class);
	
	// Constructor to load properties file
	public TestBase(){
		prop = new Properties();
		try {
			FileInputStream ip =new FileInputStream("C:\\Global ERP classes\\Project\\FreeCRMTestAutomation\\src\\"
					+ "main\\java\\com\\crm\\qa\\config\\config.properties");
			prop.load(ip);
		} catch (FileNotFoundException e) {
			e.printStackTrace();  
		} catch (IOException e) {  
			e.printStackTrace();
		} 
		
		
	} 
	public static void initialization(){
		String browserName=prop.getProperty("browser");
		if (browserName.equalsIgnoreCase("chrome") ) {
			System.setProperty("webdriver.chrome.driver", "C:\\Tools\\chromedriver.exe");
			driver = new ChromeDriver();
		} else if (browserName.equalsIgnoreCase("firefox")) {
			System.setProperty("webdriver.gecko.driver", "C:\\Tools\\geckodriver.exe");
			
		} else {
			System.setProperty("webdriver.ie.driver", "C://Tools//iedriver.exe");

		}
	
		e_driver =new EventFiringWebDriver(driver);
		eventListener=new WebEventListener();
		e_driver.register(eventListener);
		driver=e_driver;
		driver.manage().window().maximize(); 
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(TestUtil.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(TestUtil.IMPLICIT_WAIT,TimeUnit.SECONDS);
		driver.get(prop.getProperty("url"));
	
		
	}
	public static Path captureScreenshot(WebDriver driver, String screenShotName) throws IOException{
		String dateName=new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		File source=((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		
		String destination=System.getProperty("user.dir")+"/Screenshots/"+ screenShotName+dateName+".png";
		File finalDestination = new File(destination);
		FileUtils.copyFile(source, finalDestination);
		return finalDestination.toPath();
	}

} 
