package com.server.ecommerce.dto;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.server.ecommerce.entity.Product;
import com.server.ecommerce.entity.user;

import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
public class ReviewDto {
     
      private Long id;
	
	private Long rating;
	
	private String description;
	
	private String img;

	private Long userId;
	private Long productId;
    private String username;
}   
