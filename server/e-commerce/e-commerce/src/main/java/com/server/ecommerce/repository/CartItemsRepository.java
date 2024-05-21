package com.server.ecommerce.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.server.ecommerce.entity.CartItems;

@Repository
public interface CartItemsRepository extends JpaRepository<CartItems, Long>{

Optional<CartItems> findByProductIdAndOrderIdAndUserId(Long productId, Long orderId, Long userId);

}
