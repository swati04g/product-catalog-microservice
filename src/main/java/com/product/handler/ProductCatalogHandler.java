package main.java.com.product.handler;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import main.java.com.product.dao.ProductCatalogDao;
import main.java.com.product.data.enums.StatusCode;
import main.java.com.product.data.persistent.Product;
import main.java.com.product.dto.ProductAPIResponse;

@Component
public class ProductCatalogHandler {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	ProductCatalogDao productCatalogDao;

	public ProductAPIResponse addProduct(Product product) throws Exception {
		logger.info("add product -> {}", product.getName());
		int result = productCatalogDao.insert(product);
		logger.info("Add new product -> {}", result);
		return new ProductAPIResponse(StatusCode.SUCCESS,
				"Product added succusfully to catalog.");
	}

	public ProductAPIResponse searchProduct(String productType) {
		List<Product> productList = productCatalogDao.findByProductType(productType);
		if(productList.size()>0){
			logger.info("found product by type. List size is -> {}", productList.size());
			return new ProductAPIResponse(StatusCode.SUCCESS,"Product of type " +productType + " are found.",productList);
		}else
			return new ProductAPIResponse(StatusCode.SUCCESS,"No Product found of type " + productType);
	}
	
	public ProductAPIResponse listAllProducts() {
		List<Product> productList = productCatalogDao.findAll();
		
		if(productList.size()>0){
			logger.info("list all products. List size is -> {}", productList.size());
			return new ProductAPIResponse(StatusCode.SUCCESS,"List of products is as follows ",productList);
		}else
			return new ProductAPIResponse(StatusCode.SUCCESS,"No Products found in the catalog.");
	}

	public ProductAPIResponse removeProduct(String productName) {
		int result = productCatalogDao.delete(productName);
		logger.debug("remove product -> {}", result);
		if(result ==1){
			logger.info("remove product success.");
		return new ProductAPIResponse(StatusCode.SUCCESS,
				"Product removed succusfully from catalog.");
		}else
			return new ProductAPIResponse(StatusCode.FAILED,
					"No product found in catalog to remove.");
			
	}

   
}