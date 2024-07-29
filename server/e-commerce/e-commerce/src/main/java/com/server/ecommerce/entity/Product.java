package com.server.ecommerce.entity;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.server.ecommerce.dto.ProductDto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name="product")
public class Product {
      
	  @Id
      @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	  
	private String name;
	private Long price;
	
	@Lob
	private String description;
	
	@Lob
	@Column(columnDefinition = "longblob")
	private String img;
	
	@ManyToOne(fetch = FetchType.LAZY,optional=false)
	@JoinColumn(name = "catagory_id",nullable=false)
	@OnDelete(action=OnDeleteAction.CASCADE)
	@JsonIgnore
	private Category category;
	
	public ProductDto getDto(	) {
	    ProductDto productDto = new ProductDto();
	    productDto.setId(id);
	    productDto.setName(name);
	    productDto.setPrice(price);
	    productDto.setDescription(description);
	    productDto.setImg(img);
	    productDto.setCategoryId(category.getId());
	    productDto.setCategoryName(category.getName());
	    return productDto;
	}

}
