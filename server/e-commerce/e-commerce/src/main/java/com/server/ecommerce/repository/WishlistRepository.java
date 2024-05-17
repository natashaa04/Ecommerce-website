package com.server.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.server.ecommerce.entity.Wishlist;

public interface WishlistRepository extends JpaRepository<Wishlist,Long> {

}
