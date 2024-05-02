package com.server.ecommerce.entity;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.server.ecommerce.dto.CartItemsDto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Data
@Entity
public class CartItems {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column
	private Long price;

	@Column
	private Long quantity;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "product_id", nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Product product;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "user_id", nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private user user;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "order_id")
	private Order order;

	public CartItemsDto getCartDto() {
	    CartItemsDto cartItemsDto = new CartItemsDto();

	    cartItemsDto.setId(id);
	    cartItemsDto.setPrice(price);
	    cartItemsDto.setProductId(product.getId());
	    cartItemsDto.setQuantity(quantity);
	    cartItemsDto.setUserId(user.getId());
	    cartItemsDto.setProductName(product.getName());
	    cartItemsDto.setReturnedImg(product.getImg());

	    return cartItemsDto;
	}

}
