package com.server.ecommerce.controller.customer;

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

@RestController
@RequestMapping("/api/customer")
@RequiredArgsConstructor
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
	    OrderDto orderDto = cartService.getCartByUserId(userId);
	    return ResponseEntity.status(HttpStatus.OK).body(orderDto);
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

}
