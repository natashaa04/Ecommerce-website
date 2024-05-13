package com.server.ecommerce.services.adminOrder;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.server.ecommerce.Enum.OrderStatus;
import com.server.ecommerce.dto.OrderDto;
import com.server.ecommerce.entity.Order;
import com.server.ecommerce.repository.OrderRepository;

import io.jsonwebtoken.lang.Objects;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AdminOrderServiceImpl implements AdminOrderService{

	@Autowired
	private OrderRepository orderRepository;
	
	public List<OrderDto>getAllPlacedOrders(){
		List<Order>orderList=orderRepository.findAllByOrderStatusIn(List.of(OrderStatus.Placed , OrderStatus.Shipped ,OrderStatus.Delivered));
		return orderList.stream().map(Order::getOrderDto).collect(Collectors.toList());
		}
	
	public OrderDto changeOrderStatus(Long orderId, String status) {
		try {
			
			Optional<Order> optionalOrder = orderRepository.findById(orderId); 
		
	    if (optionalOrder.isPresent()) {
	    	
	        Order order = optionalOrder.get(); 
	        if (Objects.nullSafeEquals(status, "Shipped")) { 
	            order.setOrderStatus(OrderStatus.Shipped); 
	        } else if (Objects.nullSafeEquals(status, "Delivered")) { 
	            order.setOrderStatus(OrderStatus.Delivered); 
	        }
	        return orderRepository.save(order).getOrderDto(); 
	    }
	    
		
	} 
	catch (Exception e) {
		log.info("change order status error");
		e.printStackTrace();
		 return null; 
	}
		return null;

	   
	}

	
	
}







