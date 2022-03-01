package model;
import service.ProductDetails;
public class ProductModel {

	 public int pid;
     public String productName;
     public double price;
     public int stock;
	
     public ProductModel() {
		super();
		
	}

	public ProductModel(int pid, String productName, double price, int stock) {
		super();
		this.pid = pid;
		this.productName = productName;
		this.price = price;
		this.stock = stock;
	}

	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	@Override
	public String toString() {
		return "ProductModel [pid=" + pid + ", productName=" + productName + ", price=" + price + ", stock=" + stock
				+ "]";
	}

	 
}
