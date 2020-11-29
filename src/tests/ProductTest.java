package tests;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.*;
import constants.DriverType;
import core.selenium.ChromeDriverManager;
import core.selenium.DriverManager;
import core.selenium.DriverManagerFactory;
import epay.page.*;
import amazon.page.*;
import models.*;

public class ProductTest extends BaseTest {
	private AmazonProductPage amazonProductPage;
	private EpayProductPage epayProducPage;
	private String product;
	private List<Product> products;
	public ProductTest(DriverType type, String searchProduct) {
		super(type);
		product = searchProduct;
		products = new ArrayList<Product>();
	}
	
	@Test(priority=1)
	public void getProductsFromAmazon() {
		webDriver.get("https://www.amazon.com/");
		amazonProductPage = new AmazonProductPage(webDriver);
		products.addAll(amazonProductPage.getProducts(product));
	}
	
	@Test(priority=2)
	public void getProductsFromEpay() {
		webDriver.get("https://www.ebay.com/");
		epayProducPage = new EpayProductPage(webDriver);
		products.addAll(epayProducPage.getProducts(product));
	}
	
	@Test(priority=3)
	public void printAllProducts() {
		Collections.sort(products, new Comparator<Product>() {
			@Override
            public int compare(Product o1, Product o2) {
				return Double.compare(o1.getPrice(), o2.getPrice());
            }
        });
		for(Product product : products) {
			System.out.println(product.getInfo());
		}
	}
}
