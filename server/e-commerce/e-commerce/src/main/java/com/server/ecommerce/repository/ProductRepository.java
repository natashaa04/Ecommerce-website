package com.server.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.server.ecommerce.entity.Product;


@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{

}
