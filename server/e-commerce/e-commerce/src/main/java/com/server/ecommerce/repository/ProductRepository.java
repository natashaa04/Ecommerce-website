package com.server.ecommerce.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.server.ecommerce.entity.Product;


@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{
List<Product>findAllByNameContaining(String title);
   void deleteById(Long id);
}
