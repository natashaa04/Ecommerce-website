package com.server.ecommerce.services.customer.review;

import com.server.ecommerce.dto.OrderedProductsResponseDto;

public interface ReviewService {

	public OrderedProductsResponseDto getOrderdProductsDetailsByOrderId(Long orderId);
}
