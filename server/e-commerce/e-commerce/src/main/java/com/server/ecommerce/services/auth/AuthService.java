package com.server.ecommerce.services.auth;

import org.springframework.stereotype.Service;

import com.server.ecommerce.dto.SignupRequest;
import com.server.ecommerce.dto.userDto;

@Service
public interface AuthService {
	 public userDto createUser(SignupRequest signupRequest);
		public Boolean hasUserWithEmail(String email);
		  public void createAdminAccount();
}
