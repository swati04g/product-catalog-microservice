package main.java.com.product.data.persistent;

public class Product {

    private long id;

	private String name;
    private String productType;
    
    
	public Product(String name, String productType) {
		super();
		this.name = name;
		this.productType = productType;
	}
	
	public Product() {
		super();
	}

	public String getProductType() {
		return productType;
	}

	public long getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	
	public void setId(long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", productType=" + productType + "]";
	}

	
	
    
}