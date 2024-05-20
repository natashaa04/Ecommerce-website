package com.server.ecommerce.repository;



import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.server.ecommerce.Enum.OrderStatus;
import com.server.ecommerce.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {

	Order findByUserIdAndOrderStatus(Long userId,OrderStatus orderStatus);
	List<Order>findAllByOrderStatusIn(List<OrderStatus>orderStatus);
	List<Order>findByUserIdAndOrderStatusIn(Long userId, List<OrderStatus> orderStatus);
	Optional<Order> findByTrackingId(UUID trackingId);
	List<Order> findByDateBetweenAndOrderStatus(Date startMonth,Date endOfMonth,OrderStatus status);
	Long countByOrderStatus(OrderStatus status);
}
