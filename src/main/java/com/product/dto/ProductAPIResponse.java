package main.java.com.product.dto;

import java.io.Serializable;
import java.util.List;

import main.java.com.product.data.enums.StatusCode;
import main.java.com.product.data.persistent.Product;

public class ProductAPIResponse implements Serializable {

	private StatusCode status;
	private String message;
	private List<Product> productList;
	

	public ProductAPIResponse(StatusCode status, String message) {
		super();
		this.status = status;
		this.message = message;
	}


	public StatusCode getStatus() {
		return status;
	}

	public void setStatus(StatusCode status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public ProductAPIResponse(StatusCode status, String message, List<Product> productList) {
		super();
		this.status = status;
		this.message = message;
		this.productList = productList;
	}


	public List<Product> getProductList() {
		return productList;
	}

	public void setProductList(List<Product> productList) {
		this.productList = productList;
	}
	
	

}
