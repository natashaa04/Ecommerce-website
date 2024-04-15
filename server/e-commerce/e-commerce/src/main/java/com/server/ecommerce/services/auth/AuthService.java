package com.server.ecommerce.services.auth;



import com.server.ecommerce.dto.SignupRequest;
import com.server.ecommerce.dto.userDto;


public interface AuthService {
	 public userDto createUser(SignupRequest signupRequest);
		public Boolean hasUserWithEmail(String email);
		  public void createAdminAccount();
}
