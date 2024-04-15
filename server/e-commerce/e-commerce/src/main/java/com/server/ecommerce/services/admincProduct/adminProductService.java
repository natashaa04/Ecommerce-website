package com.server.ecommerce.services.admincProduct;

import java.util.List;

import com.server.ecommerce.dto.ProductDto;

import io.jsonwebtoken.io.IOException;

public interface adminProductService {
	 public ProductDto addProduct(ProductDto productDto) throws IOException;
		public List<ProductDto> getAllProducts();
}
