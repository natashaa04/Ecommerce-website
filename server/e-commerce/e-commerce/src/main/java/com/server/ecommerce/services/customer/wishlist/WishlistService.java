package com.server.ecommerce.services.customer.wishlist;

import com.server.ecommerce.dto.WishlistDto;

public interface WishlistService {
	public WishlistDto addProductToWishlist(WishlistDto wishlistDto);
}
