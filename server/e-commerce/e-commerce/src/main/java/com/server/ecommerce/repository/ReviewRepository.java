package com.server.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.server.ecommerce.entity.Review;

import jakarta.persistence.JoinColumn;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
     
}
