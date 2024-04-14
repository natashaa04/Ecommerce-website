package com.server.ecommerce.services.jwt;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.server.ecommerce.entity.user;
import com.server.ecommerce.repository.UserRepository;

@Service
public class UserDetaileServiceImpl implements UserDetailsService{

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
	 Optional<user>optionalUser= userRepository.findFirstByEmail(username);
	 if(optionalUser.isEmpty()) throw new UsernameNotFoundException("username not found",null);
	return new org.springframework.security.core.userdetails.User(optionalUser.get().getEmail(), optionalUser.get().getPassword(),new ArrayList<GrantedAuthority>());
  }
}
