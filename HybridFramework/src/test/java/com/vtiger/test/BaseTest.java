package com.vtiger.test;

import java.io.FileInputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.codoid.products.fillo.Connection;
import com.codoid.products.fillo.Fillo;
import com.codoid.products.fillo.Recordset;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
	
	public static WebDriver driver; // parallel execution mate base test na variables static banavva, kem k bau badha classes extend kare che base class ne and static na hoy to koi ek j subclass na test ma aa variables pochse baki ma error aavse
	public static Properties prop;
	public static Map<String, Map<String, String>> td;
	
	public static ExtentHtmlReporter htmlReporter;
	public static ExtentReports extent;
	public static ExtentTest logger;
	
	
	public Properties readProperties()
	{
		Properties prop = null;
		try
		{
		prop = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+ "/src/test/resources/Config/Setting.properties");
		prop.load(fis);
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		return prop;
	}
	
	
	public void launchApp()
	{
		if(prop.getProperty("Browser").equals("chrome"))
		{
			WebDriverManager.chromedriver().setup();   //instead of System.setProperty("webdriver.chrome.driver", exe path);
			driver = new ChromeDriver();	
		}
		else if(prop.getProperty("Browser").equals("firefox"))
		{
			WebDriverManager.firefoxdriver().setup();   //instead of System.setProperty("webdriver.chrome.driver", exe path);
			driver = new FirefoxDriver();	
		}
		else if(prop.getProperty("Browser").equals("edge"))
		{
			WebDriverManager.edgedriver().setup();   //instead of System.setProperty("webdriver.chrome.driver", exe path);
			driver = new EdgeDriver();	
		}
		driver.get("http://localhost:100");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Integer.parseInt(prop.getProperty("GlobalTimeOut"))));
	}
	
	@AfterSuite
	public void closeApp()
	{
		driver.quit();
	}
	
	@BeforeSuite
	public void initiation() throws Exception
	{
		prop = readProperties();
		td = getdata(System.getProperty("user.dir") + "/src/test/resources/TestData/Data.xlsx.xlsx", "Sheet1");
		System.out.println(td);
		//System.exit(0);
		launchApp();
		createExtentReport();
	}
	
	public Map<String, Map<String, String>> getdata(String file, String sheet) throws Exception
	{
		Fillo fillo = new Fillo();
		
			Connection connection = fillo.getConnection(file);
			String str = "Select * from " + sheet;
			Recordset recordset = connection.executeQuery(str);
			List<String> collist = recordset.getFieldNames();
			
			Map<String, Map<String, String>> data = new LinkedHashMap<String, Map<String,String>>();
			while(recordset.next())
			{
				Map<String, String> rowdata = new LinkedHashMap<String, String>();
				
				for(int i=0; i<collist.size(); i++)
				{
					String fieldname = collist.get(i);
					String colvalue = recordset.getField(fieldname);
					rowdata.put(fieldname, colvalue);
				}
				data.put(recordset.getField(collist.get(0)), rowdata);
				System.out.println();
			}
			
			recordset.close();
			connection.close();
			
			return data;
		} 
	
	public void createExtentReport()
	{
		Date d = new Date();
		DateFormat ft = new SimpleDateFormat("ddMMyyyyhhmmss");
		String filename = ft.format(d);
		
		htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") + "\\src\\test\\java\\com\\vtiger\\reports\\ExtentReport" + filename + ".html");
		
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);    //file attach thai
		extent.setSystemInfo("Host Name", "Automation test hub");
		extent.setSystemInfo("Environment", "Test");
		extent.setSystemInfo("User Name", "Rajesh U");
		
		htmlReporter.config().setDocumentTitle("Title of report");
		htmlReporter.config().setReportName("Name of report");
		htmlReporter.config().setTheme(Theme.DARK);
		
	}
		
		
	

}
