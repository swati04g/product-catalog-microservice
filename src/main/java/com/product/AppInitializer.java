package main.java.com.product;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import main.java.com.product.dao.ProductCatalogDao;
import main.java.com.product.data.persistent.Product;

import org.springframework.boot.CommandLineRunner;

@SpringBootApplication
public class AppInitializer implements CommandLineRunner {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	ProductCatalogDao repository;

    public static void main(String[] args) {
        SpringApplication.run(AppInitializer.class, args);
    }
    
    public void run(String... args) throws Exception {
    	
        logger.info("Add new product -> {}", repository.insert(new Product("Pizza", "Food")));
        
        logger.info("List all products -> {}", repository.findAll());
		
		logger.info("Search product by type -> {}", repository.findByProductType("Food"));
		
		//repository.delete("Pizza");
	}
}
