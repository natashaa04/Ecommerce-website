package com.server.ecommerce.dto;

import com.server.ecommerce.Enum.userRole;

import lombok.Data;

@Data
public class userDto {

	private long id;
	private String email;
	private String name;
	private userRole UserRole;
	
}
