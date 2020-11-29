package epay.page;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import core.selenium.ObjectExtension;
import models.Product;

public class EpayProductPage {
	WebDriver driver;
	public EpayProductPage(WebDriver driver) {
		this.driver = driver;
	}
	
	private By searchTextBox = By.id("gh-ac");
	private By searchButton = By.id("gh-btn");
	private By searchResults = By.xpath("//li[starts-with(@data-view,'mi:1686|iid:')]");
	
	public void search(String searchValue) {
		this.setSearchTextBox(searchValue);
		this.clickSearch();
	}
	
	public List<Product> getProducts(String searchValue) {
		List<Product> result = new ArrayList<Product>();
		search(searchValue);
		List<WebElement> elements = driver.findElements(searchResults);
		for(WebElement element : elements){
			result.add(new Product("epay", getProductName(element), getPrice(element), getProductLink(element)));
		}
		return result;
	}
	
	private String getProductName(WebElement root) {
		WebElement productNameEle = root.findElement(By.cssSelector("h3.s-item__title"));
		return productNameEle.getText();
	}
	
	private String getProductLink(WebElement root) {
		WebElement linkEle = root.findElement(By.xpath(".//a[@class='s-item__link']"));
		return linkEle.getAttribute("href");
	}
	
	private double getPrice(WebElement root) {
		String priceText = "";
		List<WebElement> eles = root.findElements(By.cssSelector("div.s-item__detail.s-item__detail--primary > span.s-item__price"));
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
