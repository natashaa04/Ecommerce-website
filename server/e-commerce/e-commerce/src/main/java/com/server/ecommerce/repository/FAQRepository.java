package com.server.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.server.ecommerce.entity.FAQ;

@Repository
public interface FAQRepository extends JpaRepository<FAQ,Long>{

	
}
