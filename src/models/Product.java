package models;

public class Product {
	private String name;
	private double price;
	private String link;
	private String storeName;
	
	public Product(String storeName, String productName, double price, String imgLink) {
		this.storeName = storeName;
		this.name = productName;
		this.price = price;
		this.link = imgLink;
	}
	
	public String getName() {
		return name;
	}
	
	public Double getPrice() {
		return price;
	}
	
	public String getLink() {
		return link;
	}
	
	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setPrice(double price) {
		this.price = price;
	}
	
	public void setLink(String link) {
		this.link = link;
	}
	
	public String getInfo() {
		return "Store: " + this.getStoreName() + ", Name:" + this.getName() + 
				", Price: " + this.getPrice() + ", Link: " + this.getLink(); 
	}
}
