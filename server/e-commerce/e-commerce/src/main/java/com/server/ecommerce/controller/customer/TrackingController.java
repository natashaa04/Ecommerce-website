package com.server.ecommerce.controller.customer;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.server.ecommerce.dto.OrderDto;
import com.server.ecommerce.services.customer.cart.CartService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class TrackingController {
     
	@Autowired
	private CartService cartService;
	
	@GetMapping("/order/{trackingId}")
	public ResponseEntity<OrderDto> searchOrderBYTrackingId(@PathVariable UUID trackingId ){
		OrderDto orderDto =cartService.searchByTrackingId(trackingId);
		return ResponseEntity.ok(orderDto);
	}
}
