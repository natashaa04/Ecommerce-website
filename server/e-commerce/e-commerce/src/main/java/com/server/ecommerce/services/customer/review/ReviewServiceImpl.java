package com.server.ecommerce.services.customer.review;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.server.ecommerce.dto.OrderedProductsResponseDto;
import com.server.ecommerce.dto.ProductDto;
import com.server.ecommerce.entity.CartItems;
import com.server.ecommerce.entity.Order;
import com.server.ecommerce.repository.OrderRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl  implements ReviewService{

	@Autowired
	private OrderRepository orderRepository;
	
	public OrderedProductsResponseDto getOrderdProductsDetailsByOrderId(Long orderId) {
		
		Optional<Order>optionalOrder= orderRepository.findById(orderId);
		
		if(optionalOrder.isPresent()) {
			OrderedProductsResponseDto orderedProductsResponseDto = new OrderedProductsResponseDto();
			orderedProductsResponseDto.setOrderAmount(optionalOrder.get().getAmount());

			List<ProductDto> productDtoList = new ArrayList<>();

			for (CartItems cartItems : optionalOrder.get().getCartItems()) {
			    ProductDto productDto = new ProductDto();
			    productDto.setId(cartItems.getProduct().getId());
			    productDto.setName(cartItems.getProduct().getName());
			    productDto.setPrice(cartItems.getPrice());
			    productDto.setQuantity(cartItems.getQuantity());
			    productDto.setImg(cartItems.getProduct().getImg());

			    productDtoList.add(productDto);
			}

			orderedProductsResponseDto.setProductDtoList(productDtoList);

			return orderedProductsResponseDto;

		}
		return null;
	}
	}
