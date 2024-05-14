package com.server.ecommerce.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.server.ecommerce.Enum.OrderStatus;
import com.server.ecommerce.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {

	Order findByUserIdAndOrderStatus(Long userId,OrderStatus orderStatus);
	List<Order>findAllByOrderStatusIn(List<OrderStatus>orderStatus);
	List<Order>findByUserIdAndOrderStatusIn(Long userId, List<OrderStatus> orderStatus);
}
