package com.server.ecommerce.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.server.ecommerce.dto.ProductDto;
import com.server.ecommerce.services.admincProduct.adminProductService;

import io.jsonwebtoken.io.IOException;
import lombok.RequiredArgsConstructor;

@RequestMapping("/api/admin")
@RestController
@RequiredArgsConstructor
public class AdminProductController {

	@Autowired
	private adminProductService adminProductService;
	
	@PostMapping("/product")
	public ResponseEntity<ProductDto> addProduct(@ModelAttribute ProductDto productDto) throws IOException {
	    ProductDto addedProduct = adminProductService.addProduct(productDto);
	    return ResponseEntity.status(HttpStatus.CREATED).body(addedProduct);
	}

	@GetMapping("/products")
	public ResponseEntity<List<ProductDto>> getAllProducts() {
	    List<ProductDto> productDtos = adminProductService.getAllProducts();
	    return ResponseEntity.ok(productDtos);
	}

}
