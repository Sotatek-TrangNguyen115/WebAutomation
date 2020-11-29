package tests;

import java.io.InputStream;
import java.net.URL;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import constants.DriverType;
import core.selenium.DriverManager;
import core.selenium.DriverManagerFactory;

public abstract class BaseTest {
	protected WebDriver webDriver;
	private DriverManager driverManager;
	private DriverType driverType;
	
	public BaseTest(DriverType type) {
		driverType = type;
	}
	
	private String getDriverPath() {
		String driverPath = "";
		switch(driverType) {
			case CHROME:
				driverPath = "drivers/chromedriver.exe";
			break;
			default:
				driverPath = "drivers/chromedriver.exe";
				break;
		}
		return driverPath;
	}
	
	@BeforeClass
	public void InitializeTest() {
		URL url = BaseTest.class.getClassLoader().getResource(getDriverPath());
		System.setProperty("webdriver.chrome.driver", url.getPath());
		driverManager = DriverManagerFactory.getDriverManager(driverType);
		webDriver = driverManager.getWebDriver();
	}
	
	@AfterClass
	public void FinalizeTest() {
		driverManager.quitWebDriver();
	}
}
