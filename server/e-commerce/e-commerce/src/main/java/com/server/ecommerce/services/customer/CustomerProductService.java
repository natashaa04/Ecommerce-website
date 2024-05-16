package com.server.ecommerce.services.customer;

import java.util.List;

import com.server.ecommerce.dto.ProductDetailDto;
import com.server.ecommerce.dto.ProductDto;

public interface CustomerProductService {
	
	
	public List<ProductDto> getAllProducts() ;
	public List<ProductDto> getAllProductByName(String name);
	public ProductDetailDto getProductDetailById(Long productId);
}
