package com.server.ecommerce.controller.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.server.ecommerce.dto.OrderedProductsResponseDto;
import com.server.ecommerce.services.customer.review.ReviewService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/customer")
public class ReviewController {
     
	@Autowired
	private final ReviewService reviewService;
	
	
	@GetMapping("/ordered-products/{orderId}")
	public ResponseEntity<OrderedProductsResponseDto> getOrderedproductsDetailsByOrderId(@PathVariable Long orderId){
		return ResponseEntity.ok(reviewService.getOrderdProductsDetailsByOrderId(orderId));
	}
}
