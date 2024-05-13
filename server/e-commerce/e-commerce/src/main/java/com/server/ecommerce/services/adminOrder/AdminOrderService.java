package com.server.ecommerce.services.adminOrder;

import java.util.List;

import com.server.ecommerce.dto.OrderDto;

public interface AdminOrderService {
	public List<OrderDto>getAllPlacedOrders();
	public OrderDto changeOrderStatus(Long orderId, String status) ;
}
