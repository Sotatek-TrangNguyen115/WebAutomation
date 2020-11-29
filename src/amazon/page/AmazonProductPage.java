package amazon.page;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import core.selenium.ObjectExtension;
import models.Product;

public class AmazonProductPage {
	WebDriver driver;
	public AmazonProductPage(WebDriver driver) {
		this.driver = driver;
	}
	
	private By searchTextBox = By.id("twotabsearchtextbox");
	private By searchButton = By.id("nav-search-submit-text");
	private By searchResults = By.xpath("//span[starts-with(@cel_widget_id,'MAIN-SEARCH_RESULTS-')]");
	
	public void search(String searchValue) {
		this.setSearchTextBox(searchValue);
		this.clickSearch();
	}
	
	private static boolean isMatchProduct(String searchResult, String filterName) {
		return searchResult.contains(filterName);
	}
	
	public List<Product> getProducts(String searchValue) {
		List<Product> result = new ArrayList<Product>();
		search(searchValue);
		List<WebElement> elements = driver.findElements(searchResults);
		for(WebElement element : elements){
			String productName = getProductName(element);
			if(!isMatchProduct(productName, searchValue)) {
				System.out.println("Product:" + productName + " is not match the search key: " + searchValue);
			}
			result.add(new Product("amazon", productName, getPrice(element), getProductLink(element)));
		}
		return result;
	}
	
	private String getProductName(WebElement root) {
		WebElement productNameEle = root.findElement(By.xpath(".//span[@class='a-size-medium a-color-base a-text-normal']"));
		return productNameEle.getText();
	}
	
	private String getProductLink(WebElement root) {
		WebElement linkEle = root.findElement(By.xpath(".//a[@class='a-link-normal a-text-normal']"));
		return linkEle.getAttribute("href");
	}
	
	private double getPrice(WebElement root) {
		String priceText = "";
		List<WebElement> eles = root.findElements(By.cssSelector("a > span.a-price:first-child> span:first-child"));
		if(!eles.isEmpty()) {
			priceText = eles.get(0).getAttribute("innerHTML").replace("$", "");
		}
		return ObjectExtension.asDouble(priceText);
	}

	private void setSearchTextBox(String searchValue) {
		driver.findElement(searchTextBox).sendKeys(searchValue);;
	}
	
	private void clickSearch() {
		driver.findElement(searchButton).click();
	}
}
