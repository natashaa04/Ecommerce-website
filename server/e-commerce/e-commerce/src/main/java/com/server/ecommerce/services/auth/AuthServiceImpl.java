package com.server.ecommerce.services.auth;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.server.ecommerce.Enum.OrderStatus;
import com.server.ecommerce.Enum.userRole;
import com.server.ecommerce.dto.SignupRequest;
import com.server.ecommerce.dto.userDto;
import com.server.ecommerce.entity.Order;
import com.server.ecommerce.entity.user;
import com.server.ecommerce.repository.OrderRepository;
import com.server.ecommerce.repository.UserRepository;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class AuthServiceImpl implements AuthService {
 
	@Autowired
	private UserRepository userRepository;
	

	@Autowired
	private OrderRepository orderRepository;
	
	 
	 public userDto createUser(SignupRequest signupRequest) {
		 log.info(" creating user");
		 try {
		    user User = new user();
		    User.setEmail(signupRequest.getEmail());
		    User.setName(signupRequest.getName());
		    User.setPassword(new BCryptPasswordEncoder().encode(signupRequest.getPassword()));
		    User.setRole(userRole.CUSTOMER);
		    
		    user createdUser = userRepository.save(User);
		    
		    userDto userDto = new userDto();
		    userDto.setId((long) createdUser.getId());
		    
		    Order order = new Order();
		    order.setAmount(0L);
		    order.setTotalAmount(0L);
		    order.setDiscount(0L);
		    order.setUser(createdUser);
		    order.setOrderStatus(OrderStatus.Pending);
		    orderRepository.save(order);
		    return userDto;
		 }
		 catch (Exception e) {
		  log.info("err wile creating user");
		  e.printStackTrace();
		  return null;
		}

		}

		public Boolean hasUserWithEmail(String email) {
		    return userRepository.findFirstByEmail(email).isPresent();
		}
		
	
	
		    @PostConstruct
		    public void createAdminAccount() {
		    	log.info("hello0");
		        user adminAccount = userRepository.findByRole(userRole.ADMIN);
		        if (adminAccount == null) {
		        	log.info("hello1");
		            user User = new user();
		            User.setEmail("admin@test.com");
		            User.setName("admin");
		            User.setRole(userRole.ADMIN);
		            User.setPassword(new BCryptPasswordEncoder().encode("admin"));
		            userRepository.save(User);
		        	log.info("hello2");

		        }else {log.info(adminAccount.toString() );
		    }
		}
}


