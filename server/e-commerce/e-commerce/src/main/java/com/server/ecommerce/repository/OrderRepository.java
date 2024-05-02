package com.server.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.server.ecommerce.Enum.OrderStatus;
import com.server.ecommerce.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {

	Order findByUserIdAndOrderStatus(Long userId,OrderStatus orderStatus);
}
