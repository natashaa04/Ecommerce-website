package com.server.ecommerce.controller.customer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.server.ecommerce.dto.ProductDetailDto;
import com.server.ecommerce.dto.ProductDto;
import com.server.ecommerce.services.customer.CustomerProductService;


@RestController
public class CustomerProductController {

	@Autowired
	private CustomerProductService customerProductService;
	
	@GetMapping("/products")
	public ResponseEntity<List<ProductDto>>getAllProducts() {
	    List<ProductDto> productDtos = customerProductService.getAllProducts();
	    return ResponseEntity.ok(productDtos);
	}

	@GetMapping("/search/{name}")
	public ResponseEntity<List<ProductDto>> getAllProductByName(@PathVariable String name) {
	    List<ProductDto> productDtos = customerProductService.getAllProductByName(name);
	    return ResponseEntity.ok(productDtos);
	}
	
	@GetMapping("/product/{productId}")
	public ResponseEntity<ProductDetailDto> getProductDetailById(@PathVariable Long productId) {

	    ProductDetailDto productDetailDto = customerProductService.getProductDetailById(productId);

//	    if (productDetailDto == null) {
//	        return ResponseEntity.notFound().build();
//	    }

	    return ResponseEntity.ok(productDetailDto);
	}


}
