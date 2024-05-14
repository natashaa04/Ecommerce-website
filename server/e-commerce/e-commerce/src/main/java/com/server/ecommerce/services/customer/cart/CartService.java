package com.server.ecommerce.services.customer.cart;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.server.ecommerce.dto.AddProductInCartDto;
import com.server.ecommerce.dto.OrderDto;
import com.server.ecommerce.dto.PlaceOrderDto;
import com.server.ecommerce.entity.Coupon;

public interface CartService  {
	public ResponseEntity<?> addProductToCart(AddProductInCartDto addProductInCartDto);
	public OrderDto getCartByUserId(Long userId);
	public OrderDto placeOrder(PlaceOrderDto placeOrderDto) ;
	public OrderDto applyCoupon(Long userId, String code) ;
	public boolean couponIsExpired(Coupon coupon) ;
	public OrderDto increaseProductQuantity(AddProductInCartDto addProductInCartDto);
	public OrderDto decreaseProductQuantity(AddProductInCartDto addProductInCartDto);
	 public List<OrderDto> getMyPlacedOrders(Long userId);
}
