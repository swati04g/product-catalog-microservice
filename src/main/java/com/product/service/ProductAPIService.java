package main.java.com.product.service;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import com.fasterxml.jackson.databind.ObjectMapper;
import main.java.com.product.data.enums.StatusCode;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import main.java.com.product.data.persistent.Product;
import main.java.com.product.dto.ProductAPIResponse;
import main.java.com.product.handler.ProductCatalogHandler;

@RestController
public class ProductAPIService {
	
	@Autowired
	private ProductCatalogHandler productCatalogHandler;
	
	private ObjectMapper OBJECT_MAPPER = new ObjectMapper();

	@Consumes(MediaType.APPLICATION_JSON)
	@Produces({ MediaType.APPLICATION_JSON })
	@PostMapping("/product/add")
    public Response addProduct(@RequestBody Product product) {
		try{
		ProductAPIResponse response =productCatalogHandler.addProduct(product);
		String responseString = OBJECT_MAPPER.writeValueAsString(response);
    	int contentLength = responseString.getBytes("UTF-8").length;
    	return Response.status(Status.OK).entity(responseString).type(MediaType.APPLICATION_JSON)
				.header(HttpHeaders.CONTENT_LENGTH, contentLength).build();
		}catch (Throwable t) {
			t.printStackTrace();
			ProductAPIResponse errorResponse = new ProductAPIResponse(StatusCode.ERROR,
					"There was an error processing the add product request.");
			return Response.status(500).entity(errorResponse).type(MediaType.APPLICATION_JSON).build();
		}
       
    }
    

	@GetMapping({"/product/search/{productType}","/product/search/"})
	@Produces({ MediaType.APPLICATION_JSON})
    public Response searchProduct(@PathVariable Optional<String> productType) {
    	try{
    		ProductAPIResponse response;
    		if(productType.isPresent()){
    			response = productCatalogHandler.searchProduct(productType.get());
    		}else{
    			response = productCatalogHandler.listAllProducts();
    		}
		String responseString = OBJECT_MAPPER.writeValueAsString(response);
    	int contentLength = responseString.getBytes("UTF-8").length;
    	return Response.status(Status.OK).entity(responseString).type(MediaType.APPLICATION_JSON)
				.header(HttpHeaders.CONTENT_LENGTH, contentLength).build();
    	}catch (Throwable t) {
			t.printStackTrace();
			ProductAPIResponse errorResponse = new ProductAPIResponse(StatusCode.ERROR,
					"There was an error processing the search product by product type request.");
			return Response.status(500).entity(errorResponse).type(MediaType.APPLICATION_JSON).build();
		}
       
    }
    

	@Produces({ MediaType.APPLICATION_JSON})
    @GetMapping("/product/remove/{productname}")
    public Response removeProduct(@PathVariable String productname) {
    	try{
    	ProductAPIResponse response = productCatalogHandler.removeProduct(productname);
    	String responseString = OBJECT_MAPPER.writeValueAsString(response);
    	int contentLength = responseString.getBytes("UTF-8").length;
    	return Response.status(Status.OK).entity(responseString).type(MediaType.APPLICATION_JSON)
				.header(HttpHeaders.CONTENT_LENGTH, contentLength).build();
    }catch (Throwable t) {
		t.printStackTrace();
		ProductAPIResponse errorResponse = new ProductAPIResponse(StatusCode.ERROR,
				"There was an error processing the remove product request.");
		return Response.status(500).entity(errorResponse).type(MediaType.APPLICATION_JSON).build();
	}
       
    }
    
}