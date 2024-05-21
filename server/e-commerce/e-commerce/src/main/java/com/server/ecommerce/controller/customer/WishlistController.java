package com.server.ecommerce.controller.customer;

import java.util.List;

import org.hibernate.annotations.Any;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.service.annotation.DeleteExchange;

import com.server.ecommerce.dto.AddProductInCartDto;
import com.server.ecommerce.dto.WishlistDto;
import com.server.ecommerce.repository.WishlistRepository;
import com.server.ecommerce.services.customer.wishlist.WishlistService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/customer")
public class WishlistController {
 
	@Autowired
	private WishlistService wishlistService;
	
	@PostMapping("/wishlist")
	public ResponseEntity<?> addProductToWishlist(@RequestBody WishlistDto wishlistDto) {
	    WishlistDto postedWishlistDto = wishlistService.addProductToWishlist(wishlistDto);
//	    if (postedWishlistDto == null) {
//	        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Something went wrong");
//	    }
	    return ResponseEntity.status(HttpStatus.CREATED).body(postedWishlistDto);
	}
	@GetMapping("/wishlist/{userId}")
	public ResponseEntity<List<WishlistDto>> getWishlistByUseriId(@PathVariable Long userId){
		return ResponseEntity.ok(wishlistService.getWishlistByUserId(userId));
	}
	@GetMapping("wishlist/{userId}/isInWishlist/{productId}")
	public ResponseEntity<Boolean> isProductInWislist(@PathVariable Long userId,@PathVariable Long productId){
		boolean result=wishlistService.isProductInWislist(userId,productId);
		return ResponseEntity.ok(result);
	}
	
	@PostMapping("wishlist/remove")
	public  ResponseEntity<?> removeFromWislist(@RequestBody AddProductInCartDto addProductInCartDto){
		Boolean result= wishlistService.removeFromWishlist(addProductInCartDto);
		if(result) {
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(true);
		}
		return ResponseEntity.status(HttpStatus.CONFLICT).body(false);
	}
}
				
		
	
    


	