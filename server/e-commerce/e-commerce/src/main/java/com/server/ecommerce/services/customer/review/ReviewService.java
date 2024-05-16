package com.server.ecommerce.services.customer.review;

import com.server.ecommerce.dto.OrderedProductsResponseDto;
import com.server.ecommerce.dto.ReviewDto;

import io.jsonwebtoken.io.IOException;

public interface ReviewService {

	public OrderedProductsResponseDto getOrderdProductsDetailsByOrderId(Long orderId);
	public ReviewDto giveReview(ReviewDto reviewDto) throws IOException ;
}
