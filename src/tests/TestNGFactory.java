package tests;

import org.testng.Assert;
import org.testng.annotations.*;

import constants.DriverType;

public class TestNGFactory {
	@Factory(dataProvider = "dp")
	public Object[] getTestProductClasses(DriverType type, String searchProduct) {
		Object[] tests = new Object[1];
		tests[0] = new ProductTest(type, searchProduct);
		return tests;
	}
	
	@DataProvider
	public Object[][] dp() {
		return new Object[][] { { DriverType.CHROME, "iPhone 11" } };
	}
}
