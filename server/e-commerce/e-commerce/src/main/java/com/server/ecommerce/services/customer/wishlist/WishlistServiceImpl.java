package com.server.ecommerce.services.customer.wishlist;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.server.ecommerce.dto.WishlistDto;
import com.server.ecommerce.entity.Product;
import com.server.ecommerce.entity.Wishlist;
import com.server.ecommerce.entity.user;
import com.server.ecommerce.repository.ProductRepository;
import com.server.ecommerce.repository.UserRepository;
import com.server.ecommerce.repository.WishlistRepository;

import lombok.RequiredArgsConstructor;



@Service
@RequiredArgsConstructor
public class WishlistServiceImpl implements WishlistService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private WishlistRepository wishlistRepository;
	
	public WishlistDto addProductToWishlist(WishlistDto wishlistDto) {

	    Optional<Product> optionalProduct = productRepository.findById(wishlistDto.getProductId());
	    Optional<user> optionalUser = userRepository.findById(wishlistDto.getUserId());

	    if (optionalProduct.isPresent() && optionalUser.isPresent()) {
	        
              Wishlist wishlist = new Wishlist();
	     	 wishlist.setProduct(optionalProduct.get());
	        wishlist.setUser(optionalUser.get());

	        return wishlistRepository.save(wishlist).getWishlistDto();
	    }

	    return null;
	}
	
	public List<WishlistDto> getWishlistByUserId(Long userId){
	List<Wishlist>wishlist= wishlistRepository.findByUserId(userId);
			return (List<WishlistDto>) wishlist.stream().map(Wishlist::getWishlistDto).collect(Collectors.toList());
		
				}


}
