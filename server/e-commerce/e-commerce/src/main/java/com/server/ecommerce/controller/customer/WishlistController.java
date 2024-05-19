package com.server.ecommerce.controller.customer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.server.ecommerce.dto.WishlistDto;
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

	}
