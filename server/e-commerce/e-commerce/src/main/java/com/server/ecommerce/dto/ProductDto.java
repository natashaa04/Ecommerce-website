package com.server.ecommerce.dto;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class ProductDto {

	private Long id;
	private String name;
	private Long price;
	private String description;
//	private byte[] byteImg;
	private Long categoryId;
	private String img;


}
