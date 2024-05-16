package com.server.ecommerce.services.customer;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.server.ecommerce.dto.ProductDetailDto;
import com.server.ecommerce.dto.ProductDto;
import com.server.ecommerce.entity.FAQ;
import com.server.ecommerce.entity.Product;
import com.server.ecommerce.entity.Review;
import com.server.ecommerce.repository.FAQRepository;
import com.server.ecommerce.repository.ProductRepository;
import com.server.ecommerce.repository.ReviewRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomerProductServiceImpl implements CustomerProductService {
	
	@Autowired
   private ProductRepository productRepository;
	
	@Autowired
	 private FAQRepository faqRepository;
	
	@Autowired
     private ReviewRepository reviewRepository;
	

	public List<ProductDto> getAllProducts() {
	    List<Product> products = productRepository.findAll();
	    return products.stream().map(Product::getDto).collect(Collectors.toList());
	}

	public List<ProductDto> getAllProductByName(String name) {
	    List<Product> products = productRepository.findAllByNameContaining(name);
	    return products.stream().map(Product::getDto).collect(Collectors.toList());
	}
   
	public ProductDetailDto getProductDetailById(Long productId) {

	    Optional<Product> optionalProduct = productRepository.findById(productId);

	    if(optionalProduct.isPresent()){

	        List<FAQ> faqList = faqRepository.findAllByProductId(productId);

	        List<Review> reviewsList = reviewRepository.findAllByProductId(productId);

	        ProductDetailDto productDetailDto = new ProductDetailDto();

	        productDetailDto.setProductDto(optionalProduct.get().getDto());

	        productDetailDto.setFaqDtoList(faqList.stream().map(FAQ::getFAQDto).collect(Collectors.toList()));

	        productDetailDto.setReviewDtoList(reviewsList.stream().map(Review::getDto).collect(Collectors.toList()));

	        return productDetailDto;
	    }

	    return null;
	}

}
