package com.server.ecommerce.services.customer.cart;

import org.springframework.http.ResponseEntity;

import com.server.ecommerce.dto.AddProductInCartDto;
import com.server.ecommerce.dto.OrderDto;
import com.server.ecommerce.dto.PlaceOrderDto;
import com.server.ecommerce.entity.Coupon;

public interface CartService  {
	public ResponseEntity<?> addProductToCart(AddProductInCartDto addProductInCartDto);
	public OrderDto getCartByUserId(Long userId);
	public OrderDto placeOrder(PlaceOrderDto placeOrderDto) ;
	public boolean couponIsExpired(Coupon coupon) ;
	public OrderDto increaseProductQuantity(AddProductInCartDto addProductInCartDto);
	public OrderDto decreaseProductQuantity(AddProductInCartDto addProductInCartDto);
}
