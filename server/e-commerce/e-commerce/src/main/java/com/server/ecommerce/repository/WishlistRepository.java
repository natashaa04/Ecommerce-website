package com.server.ecommerce.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.server.ecommerce.dto.WishlistDto;
import com.server.ecommerce.entity.Wishlist;

public interface WishlistRepository extends JpaRepository<Wishlist,Long> {
 
	List<Wishlist> findByUserId(Long userId);
}
