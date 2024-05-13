package com.server.ecommerce.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.server.ecommerce.dto.OrderDto;
import com.server.ecommerce.services.adminOrder.AdminOrderService;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
@Slf4j
public class AdminOrderController {

	@Autowired
	private AdminOrderService adminOrderService;
	
	@GetMapping("/placeOrders")
	public ResponseEntity<List<OrderDto>>getAllPlacedOrders(){
		return ResponseEntity.ok(adminOrderService.getAllPlacedOrders());
	}
	
	@GetMapping("/order/{orderId}/{status}") 
	public ResponseEntity<?> changeOrderStatus(@PathVariable Long orderId, @PathVariable String status) {
	    OrderDto orderDto = adminOrderService.changeOrderStatus(orderId, status); 
	    if (orderDto == null) {
	        return new ResponseEntity<>("Something went wrong!", HttpStatus.BAD_REQUEST); 
	    }
	    return ResponseEntity.status(HttpStatus.OK).body(orderDto); 
	}

} 
