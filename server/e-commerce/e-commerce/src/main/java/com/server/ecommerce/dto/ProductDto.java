package com.server.ecommerce.dto;



import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class ProductDto {

	private Long id;
	private String name;
	private Long price;
	private String description;
	private Long categoryId;
	private String img;


}
