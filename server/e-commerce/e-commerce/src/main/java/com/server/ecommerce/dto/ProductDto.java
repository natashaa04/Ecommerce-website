package com.server.ecommerce.dto;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class ProductDto {

	private Long id;
	private String name;
	private Long price;
	private String description;
	private byte[] byteImg;
	private Long categoryId;
	private MultipartFile img;


}
