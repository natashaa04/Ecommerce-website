package com.server.ecommerce.entity;


import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class Review {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private Long rating;
	
	private String description;
	
	private String img;
	
	@ManyToOne(fetch=FetchType.LAZY,optional = false)
	@JoinColumn(name="user_id", nullable = false )
	@OnDelete(action=OnDeleteAction.CASCADE)
	private user user;
	
	@ManyToOne(fetch=FetchType.LAZY,optional = false)
	@JoinColumn(name="product_id", nullable = false )
	@OnDelete(action=OnDeleteAction.CASCADE)
	private Product product;
}
