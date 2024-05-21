package com.server.ecommerce.services.customer.wishlist;

import java.util.List;

import com.server.ecommerce.dto.AddProductInCartDto;
import com.server.ecommerce.dto.WishlistDto;

public interface WishlistService {
	public WishlistDto addProductToWishlist(WishlistDto wishlistDto);
	public List<WishlistDto> getWishlistByUserId(Long userId);
	public Boolean removeFromWishlist(AddProductInCartDto addProductInCartDto);
	public Boolean isProductInWislist(Long userId,Long productId);
}
