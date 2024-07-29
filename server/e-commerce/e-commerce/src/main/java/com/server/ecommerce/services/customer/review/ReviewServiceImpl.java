package com.server.ecommerce.services.customer.review;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.server.ecommerce.dto.OrderedProductsResponseDto;
import com.server.ecommerce.dto.ProductDto;
import com.server.ecommerce.dto.ReviewDto;
import com.server.ecommerce.entity.CartItems;
import com.server.ecommerce.entity.Order;
import com.server.ecommerce.entity.Product;
import com.server.ecommerce.entity.Review;
import com.server.ecommerce.entity.user;
import com.server.ecommerce.repository.OrderRepository;
import com.server.ecommerce.repository.ProductRepository;
import com.server.ecommerce.repository.ReviewRepository;
import com.server.ecommerce.repository.UserRepository;

import io.jsonwebtoken.io.IOException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class ReviewServiceImpl  implements ReviewService{

	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private ReviewRepository reviewRepository;
	
	public OrderedProductsResponseDto getOrderdProductsDetailsByOrderId(Long orderId) {
		
		Optional<Order>optionalOrder= orderRepository.findById(orderId);
		
		if(optionalOrder.isPresent()) {
//			log.info("optional oder:{}",optionalOrder.get());
			OrderedProductsResponseDto orderedProductsResponseDto = new OrderedProductsResponseDto();
			orderedProductsResponseDto.setOrderAmount(optionalOrder.get().getAmount());

			List<ProductDto> productDtoList = new ArrayList<>();

			for (CartItems cartItems : optionalOrder.get().getCartItems()) {
			    log.info("productdto");
			    ProductDto productDto = new ProductDto();
			    productDto.setId(cartItems.getProduct().getId());
			    productDto.setName(cartItems.getProduct().getName());
			    productDto.setPrice(cartItems.getPrice());
			    productDto.setQuantity(cartItems.getQuantity());
			    productDto.setImg(cartItems.getProduct().getImg());

			    productDtoList.add(productDto);
			    log.info("productdto");
			}

			orderedProductsResponseDto.setProductDtoList(productDtoList);

			return orderedProductsResponseDto;

		}
		return null;
	}
	
	public ReviewDto giveReview(ReviewDto reviewDto) throws IOException {
	    Optional<Product> optionalProduct = productRepository.findById(reviewDto.getProductId());
	    Optional<user> optionalUser = userRepository.findById(reviewDto.getUserId());

	    if (optionalProduct.isPresent() && optionalUser.isPresent()) {
	        Review review = new Review();
	        review.setRating(reviewDto.getRating());
	        review.setDescription(reviewDto.getDescription());
	        review.setUser(optionalUser.get());
	        review.setProduct(optionalProduct.get());

	        if (reviewDto.getImg() != null) {
	            review.setImg(reviewDto.getImg());
	        }

	        return reviewRepository.save(review).getDto();
	    }

	    return null;
	}

	
	
	
	}
