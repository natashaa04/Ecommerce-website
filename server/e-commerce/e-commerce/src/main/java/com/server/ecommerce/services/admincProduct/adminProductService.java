package com.server.ecommerce.services.admincProduct;

import java.util.List;

import com.server.ecommerce.dto.ProductDto;
import com.server.ecommerce.entity.Product;

import io.jsonwebtoken.io.IOException;

public interface adminProductService {
	 public ProductDto addProduct(ProductDto productDto) throws IOException;
		public List<ProductDto> getAllProducts();
		public List<ProductDto> getAllProductByName(String name);
		public boolean deleteProduct(Long id);
		public ProductDto getProductById(Long productId);
		public ProductDto updateProduct(Long productId, ProductDto productDto);
}

   