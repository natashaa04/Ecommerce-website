package com.server.ecommerce.controller;


import org.springframework.http.HttpHeaders;

import java.util.Optional;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.server.ecommerce.dto.AuthenticationRequest;
import com.server.ecommerce.dto.SignupRequest;
import com.server.ecommerce.dto.userDto;
import com.server.ecommerce.entity.user;
import com.server.ecommerce.repository.UserRepository;
import com.server.ecommerce.services.auth.AuthService;
import com.server.ecommerce.services.auth.AuthServiceImpl;
import com.server.ecommerce.services.jwt.UserDetaileServiceImpl;
import com.server.ecommerce.utils.jwtUtils;

import io.jsonwebtoken.io.IOException;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@Slf4j
public class AuthController {

	@Autowired
	private  AuthenticationManager authenticationManager;
	
	@Autowired
	private  UserDetaileServiceImpl userDetaileService;
	
	@Autowired
	private AuthServiceImpl authService;
	
	@Autowired
	private  UserRepository userRepository;
	
	@Autowired
	private final jwtUtils jwtUtil;
	
	
	public static final String TOKEN_PREFIX = "Bearer ";

	public static final String HEADER_STRING = "Authorization";

	@PostMapping("/authenticate")
	public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest,
	                                      HttpServletResponse response) throws IOException, JSONException, AuthenticationException, java.io.IOException {
		log.info("in auth controller");
	    try {
	        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
	                authenticationRequest.getUsername(),
	                authenticationRequest.getPassword()
	        ));
	    } catch (BadCredentialsException e) {
	    	 return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Incorrect username or password.");
	    }

	    UserDetails userDetails = null;
		try {
			
			userDetails = userDetaileService.loadUserByUsername(authenticationRequest.getUsername());
		} catch (UsernameNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	    Optional<user> optionalUser = userRepository.findFirstByEmail(userDetails.getUsername());
	    
		    final String jwt = jwtUtil.generateToken(userDetails.getUsername());
		    log.info(jwt);
		    
		    
    if (optionalUser.isPresent()) {
    
	        JSONObject responseBody = new JSONObject()
	                .put("userId", optionalUser.get().getId())
	                .put("role", optionalUser.get().getRole());

	        HttpHeaders headers = new HttpHeaders();
	        headers.add("Access-Control-Expose-Headers", "Authorization");
	        headers.add("Access-Control-Allow-Headers", "Authorization, X-PINGOTHER, Origin, X-Requested-With, Content-Type, Accept, X-Custom-header");
	        headers.add(HEADER_STRING, TOKEN_PREFIX + jwt);

	        return ResponseEntity.ok().headers(headers).body(responseBody.toString());
	    } else {
	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User not found.");
	    }
	}
	
	
	@PostMapping("/sign-up")
	public ResponseEntity<?> signUpUser(@RequestBody SignupRequest signupRequest) {
		log.info("in sign-up controller");
		try {
	    if (authService.hasUserWithEmail(signupRequest.getEmail())) {
	        return new ResponseEntity<>("User already exists", HttpStatus.NOT_ACCEPTABLE);
	    }

	    userDto userDto = authService.createUser(signupRequest);
	    return new ResponseEntity<>(userDto, HttpStatus.OK);
		}catch (Exception e) {
			log.info("error in sign-up controller");
			e.printStackTrace();
		}
        return ResponseEntity.status(HttpStatus.CONFLICT).body("bad request");
	}


}

