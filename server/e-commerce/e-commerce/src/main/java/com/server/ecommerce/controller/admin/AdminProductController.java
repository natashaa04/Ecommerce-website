package com.server.ecommerce.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.server.ecommerce.dto.FAQDto;
import com.server.ecommerce.dto.ProductDto;
import com.server.ecommerce.services.FAQ.FAQService;
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
	
	@Autowired
	private final FAQService faqService;
	@PostMapping("/product")
	public ResponseEntity<ProductDto> addProduct(@RequestBody ProductDto productDto) throws IOException {
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
	log.info("deleting product");
    boolean deleted = adminProductService.deleteProduct(productId);
    if (deleted) {
        return ResponseEntity.ok().build();
    }
    return ResponseEntity.notFound().build();
}

@PostMapping("/faq/{productId}")
public ResponseEntity<FAQDto> postFAQ(@PathVariable Long productId,@RequestBody FAQDto faqDto){
	FAQDto ResultedfaqDto= faqService.postFAQ(productId, faqDto);
	if(ResultedfaqDto!=null) {
		return ResponseEntity.ok(ResultedfaqDto);
	}
	return ResponseEntity.notFound().build();
	
}

@GetMapping("/product/{productId}")
public ResponseEntity<ProductDto> getProductById(@PathVariable Long productId){
	
	ProductDto productDto = adminProductService.getProductById(productId);
	log.info("product in controller:{}",productDto);
	

	return ResponseEntity.status(HttpStatus.OK).body(productDto);

}

@PutMapping("/product/{productId}")
public ResponseEntity<ProductDto> updateProduct(@PathVariable Long productId, @RequestBody ProductDto productDto) throws IOException {
    try {
        ProductDto updatedProduct = adminProductService.updateProduct(productId, productDto);
      
            return ResponseEntity.ok(updatedProduct);
      
    } catch (IOException e) {
        // Handle IOException appropriately
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
}

}
