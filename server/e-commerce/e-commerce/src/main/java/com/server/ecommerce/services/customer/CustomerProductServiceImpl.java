package com.server.ecommerce.services.customer;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.server.ecommerce.dto.ProductDto;
import com.server.ecommerce.entity.Product;
import com.server.ecommerce.repository.ProductRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomerProductServiceImpl implements CustomerProductService {
	
	@Autowired
   private ProductRepository productRepository;
	

	public List<ProductDto> getAllProducts() {
	    List<Product> products = productRepository.findAll();
	    return products.stream().map(Product::getDto).collect(Collectors.toList());
	}

	public List<ProductDto> getAllProductByName(String name) {
	    List<Product> products = productRepository.findAllByNameContaining(name);
	    return products.stream().map(Product::getDto).collect(Collectors.toList());
	}
   
}
