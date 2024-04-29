package com.server.ecommerce.controller.admin;

import java.lang.ProcessHandle.Info;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.server.ecommerce.dto.ProductDto;
import com.server.ecommerce.services.admincProduct.adminProductService;

import io.jsonwebtoken.io.IOException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequestMapping("/api/admin")
@RestController
@RequiredArgsConstructor
@Slf4j
public class AdminProductController {

	@Autowired
	private adminProductService adminProductService;
	
	@PostMapping("/product")public ResponseEntity<ProductDto> addProduct(@RequestBody ProductDto productDto) throws IOException {
        log.info("In product controller: {}", productDto);
         ProductDto addedProduct = adminProductService.addProduct(productDto);
	    return ResponseEntity.status(HttpStatus.CREATED).body(addedProduct);
	}

	@GetMapping("/products")
	public ResponseEntity<List<ProductDto>> getAllProducts() {
	    List<ProductDto> productDtos = adminProductService.getAllProducts();
	    return ResponseEntity.ok(productDtos);
	}
@GetMapping("/search/{name}")
	public  ResponseEntity<List<ProductDto>> getAllProductByName(@PathVariable String name){
		  List<ProductDto>productDtos=adminProductService.getAllProductByName(name);
		  return ResponseEntity.ok(productDtos);
	}
@DeleteMapping("/product/{productId}")
public ResponseEntity<Void> deleteProduct(@PathVariable Long productId) {
    boolean deleted = adminProductService.deleteProduct(productId);
    if (deleted) {
        return ResponseEntity.noContent().build();
    }
    return ResponseEntity.notFound().build();
}

}
