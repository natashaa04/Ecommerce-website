package com.server.ecommerce.controller.customer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.server.ecommerce.dto.AddProductInCartDto;
import com.server.ecommerce.dto.OrderDto;
import com.server.ecommerce.dto.PlaceOrderDto;
import com.server.ecommerce.exceptions.ValidationException;
import com.server.ecommerce.services.customer.cart.CartService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/customer")
@RequiredArgsConstructor
@Slf4j
public class CartController {

	@Autowired
	private final CartService cartService;
	
	@PostMapping("/cart")
	public ResponseEntity<?> addProductToCart(@RequestBody AddProductInCartDto addProductInCartDto){
		return cartService.addProductToCart(addProductInCartDto);
	}
	
	@GetMapping("/coupon/{userId}/{code}")
	public ResponseEntity<?> applyCoupon(@PathVariable Long userId, @PathVariable String code) {
	    try {
	        OrderDto orderDto = cartService.applyCoupon(userId, code);
	        return ResponseEntity.ok(orderDto);
	    } catch (ValidationException ex) {
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
	    }
	}
	@GetMapping("/cart/{userId}")
	public ResponseEntity<?> getCartByUserId(@PathVariable Long userId) {
		try {
			
	    OrderDto orderDto = cartService.getCartByUserId(userId);
	    return ResponseEntity.status(HttpStatus.OK).body(orderDto);
		}catch (Exception e) {
			log.info("get cart controller error");
//			e.printStackTrace();)
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
	}

	
	@PostMapping("/addition")
	public ResponseEntity<OrderDto> increaseProductQuantity(@RequestBody AddProductInCartDto addProductInCartDto) {
	    return ResponseEntity.status(HttpStatus.CREATED).body(cartService.increaseProductQuantity(addProductInCartDto));
	}

	@PostMapping("/deduction")
	public ResponseEntity<OrderDto> decreaseProductQuantity(@RequestBody AddProductInCartDto addProductInCartDto) {
	    return ResponseEntity.status(HttpStatus.CREATED).body(cartService.decreaseProductQuantity(addProductInCartDto));
	}

	@PostMapping("/placeOrder")
	public ResponseEntity<OrderDto> placeOrder(@RequestBody PlaceOrderDto placeOrderDto) {
	    return ResponseEntity.status(HttpStatus.CREATED).body(cartService.placeOrder(placeOrderDto));
	}
	
	 @GetMapping("/myOrders/{userId}")
		public ResponseEntity<List<OrderDto>> getMyPlacedOrders(@PathVariable Long userId){
	
			List<OrderDto> orderDtos= cartService.getMyPlacedOrders(userId);
			if(orderDtos.isEmpty()) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
			}
			return ResponseEntity.status(HttpStatus.OK).body(orderDtos);
			
	}
	 

}
