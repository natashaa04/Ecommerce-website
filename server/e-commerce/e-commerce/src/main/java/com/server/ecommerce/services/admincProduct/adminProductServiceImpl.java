package com.server.ecommerce.services.admincProduct;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.server.ecommerce.dto.ProductDto;
import com.server.ecommerce.entity.Product;
import com.server.ecommerce.repository.CategoryRepository;
import com.server.ecommerce.repository.ProductRepository;

import io.jsonwebtoken.io.IOException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class adminProductServiceImpl implements adminProductService {

	@Autowired
	 private ProductRepository productRepository;
	
	 @Autowired
	 private CategoryRepository categoryRepository;
	 
	 public ProductDto addProduct(ProductDto productDto) throws IOException{
		    Product product = new Product();
		    product.setName(productDto.getName());
		    product.setDescription(productDto.getDescription());
		    product.setPrice(productDto.getPrice());
		    try {
				product.setImg(productDto.getImg());
			} catch (Exception e) {			// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    ;
		 
		    log.info("id is :{}",productDto);
		    com.server.ecommerce.entity.Category category = categoryRepository.findById(productDto.getCategoryId())
		            .orElseThrow(() -> new RuntimeException("Catagory not found"));
		    product.setCategory(category);
		    return productRepository.save(product).getDto();
		}

		public List<ProductDto> getAllProducts() {
		    List<Product> products = productRepository.findAll();
		    return products.stream().map(Product::getDto).collect(Collectors.toList());
		}
         
		public List<ProductDto> getAllProductByName(String name){
			List<Product>products= productRepository.findAllByNameContaining(name);
			return products.stream().map(Product::getDto).collect(Collectors.toList());	
		}
		public boolean deleteProduct(Long id) {
			try {
		    Optional<Product> optionalProduct = productRepository.findById(id);
		    
		        productRepository.deleteById(id);
		        return true;
		    
		    
			}
			catch (Exception e) {
				log.info("delete exception");
				e.printStackTrace();
				return false;
			}
	
		}

}
