package com.server.ecommerce.services.customer.cart;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.server.ecommerce.Enum.OrderStatus;
import com.server.ecommerce.dto.AddProductInCartDto;
import com.server.ecommerce.dto.CartItemsDto;
import com.server.ecommerce.dto.OrderDto;
import com.server.ecommerce.dto.PlaceOrderDto;
import com.server.ecommerce.entity.CartItems;
import com.server.ecommerce.entity.Coupon;
import com.server.ecommerce.entity.Order;
import com.server.ecommerce.entity.Product;
import com.server.ecommerce.entity.user;
import com.server.ecommerce.exceptions.ValidationException;
import com.server.ecommerce.repository.CartItemsRepository;
import com.server.ecommerce.repository.CouponRepository;
import com.server.ecommerce.repository.OrderRepository;
import com.server.ecommerce.repository.ProductRepository;
import com.server.ecommerce.repository.UserRepository;


@Service
public class CartServiceImpl implements CartService {

	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private CartItemsRepository cartItemsRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private CouponRepository couponRepository;

	
	public ResponseEntity<?> addProductToCart(AddProductInCartDto addProductInCartDto) {
	    Order activeOrder = orderRepository.findByUserIdAndOrderStatus(addProductInCartDto.getUserId(), OrderStatus.Pending);

	    Optional<CartItems> optionalCartItems = cartItemsRepository.findByProductIdAndOrderIdAndUserId(
	            addProductInCartDto.getProductId(), activeOrder.getId(), addProductInCartDto.getUserId());

	    if (optionalCartItems.isPresent()) {
	        return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	    } else {
	        Optional<Product> optionalProduct = productRepository.findById(addProductInCartDto.getProductId());
	        Optional<user> optionalUser = userRepository.findById(addProductInCartDto.getUserId());

	        if (optionalProduct.isPresent() && optionalUser.isPresent()) {
	            CartItems cart = new CartItems();
	            cart.setProduct(optionalProduct.get());
	            cart.setPrice(optionalProduct.get().getPrice());
	            cart.setQuantity(1L);
	            cart.setUser(optionalUser.get());
	            cart.setOrder(activeOrder);

	            CartItems updatedCart = cartItemsRepository.save(cart);
	            activeOrder.setTotalAmount(activeOrder.getTotalAmount() + cart.getPrice());
	            activeOrder.setAmount(activeOrder.getAmount() + cart.getPrice());
	            activeOrder.getCartItems().add(updatedCart);
	            orderRepository.save(activeOrder);
	            
	            
	        

	            return ResponseEntity.status(HttpStatus.CREATED).body(updatedCart.getId());
	        } else {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User or product not found");
	        }
	    }
	}
	public OrderDto getCartByUserId(Long userId) {
	    Order activeOrder = orderRepository.findByUserIdAndOrderStatus(userId, OrderStatus.Pending);
	    List<CartItemsDto> cartItemsDtoList = activeOrder.getCartItems().stream().map(CartItems::getCartDto).collect(Collectors.toList());
	   OrderDto orderDto = new OrderDto();
	    orderDto.setAmount(activeOrder.getAmount());
	    orderDto.setId(activeOrder.getId());
	    orderDto.setOrderStatus(activeOrder.getOrderStatus());
	    orderDto.setDiscount(activeOrder.getDiscount());
	    orderDto.setTotalAmount(activeOrder.getTotalAmount());
	    orderDto.setCartItemsDto(cartItemsDtoList);
	    if (activeOrder.getCoupon() != null) {
	        orderDto.setCouponName(activeOrder.getCoupon().getName());
	    }
	    return orderDto;   
	}


	public OrderDto placeOrder(PlaceOrderDto placeOrderDto) {
	    Order activeOrder = orderRepository.findByUserIdAndOrderStatus(placeOrderDto.getUserId(), OrderStatus.Pending);
	    Optional<user> optionalUser = userRepository.findById(placeOrderDto.getUserId());
	    
	    if (activeOrder != null && optionalUser.isPresent()) {
	        activeOrder.setOrderDescription(placeOrderDto.getOrderDescription());
	        activeOrder.setAddress(placeOrderDto.getAddress());
	        activeOrder.setDate(new Date());
	        activeOrder.setOrderStatus(OrderStatus.Placed);
	        activeOrder.setTrackingId(UUID.randomUUID());
	        orderRepository.save(activeOrder);
	        
	        Order order = new Order();
	        order.setAmount(0L);
	        order.setTotalAmount(0L);
	        order.setDiscount(0L);
	        order.setUser(optionalUser.get());
	        order.setOrderStatus(OrderStatus.Pending);
	        orderRepository.save(order);
	        
	        return activeOrder.getOrderDto();
	    }
	    
	    return null;
	}
	
	public OrderDto applyCoupon(Long userId, String code) {
	    Order activeOrder = orderRepository.findByUserIdAndOrderStatus(userId, OrderStatus.Pending);
	   if(couponRepository.findByCode(code)!=null){
		    Coupon coupon = couponRepository.findByCode(code);
		    if (couponIsExpired(coupon)) {
		        throw new ValidationException("Coupon has expired.");
		    }

		    double discountAmount = (coupon.getDiscount() / 100.0) * activeOrder.getTotalAmount();
		    double netAmount = activeOrder.getTotalAmount() - discountAmount;

		    activeOrder.setAmount((long) netAmount);
		    activeOrder.setDiscount((long) discountAmount);
		    activeOrder.setCoupon(coupon);

		    orderRepository.save(activeOrder);

		    return activeOrder.getOrderDto();
	}else {
		throw new ValidationException("coupon not found");
	}



	}

	public boolean couponIsExpired(Coupon coupon) {
	    Date currentDate = new Date();
	    Date expirationDate = coupon.getExpirationDate();
	    return expirationDate != null && currentDate.after(expirationDate);
	}


	
	public OrderDto increaseProductQuantity(AddProductInCartDto addProductInCartDto) {
	    Order activeOrder = orderRepository.findByUserIdAndOrderStatus(
	        addProductInCartDto.getUserId(), OrderStatus.Pending);

	    Optional<Product> optionalProduct = productRepository.findById(
	        addProductInCartDto.getProductId());

	    Optional<CartItems> optionalCartItem = cartItemsRepository.findByProductIdAndOrderIdAndUserId(
	        addProductInCartDto.getProductId(), activeOrder.getId(), addProductInCartDto.getUserId());

	    if (optionalProduct.isPresent() && optionalCartItem.isPresent()) {
	        CartItems cartItem = optionalCartItem.get();
	        Product product = optionalProduct.get();

	        activeOrder.setAmount(activeOrder.getAmount() + product.getPrice());
	        activeOrder.setTotalAmount(activeOrder.getTotalAmount() + product.getPrice());

	        cartItem.setQuantity(cartItem.getQuantity() + 1);

	        if (activeOrder.getCoupon() != null) {
	            double discountAmount = (activeOrder.getCoupon().getDiscount() / 100.0) * activeOrder.getTotalAmount();
	            double netAmount = activeOrder.getTotalAmount() - discountAmount;

	            activeOrder.setAmount((long) netAmount);
	            activeOrder.setDiscount((long) discountAmount);
	        }

	        cartItemsRepository.save(cartItem);
	        orderRepository.save(activeOrder);

	        return activeOrder.getOrderDto();
	    }

	    return null;
	}
	
	
	
	
	public OrderDto decreaseProductQuantity(AddProductInCartDto addProductInCartDto) {
	    Order activeOrder = orderRepository.findByUserIdAndOrderStatus(
	        addProductInCartDto.getUserId(), OrderStatus.Pending);

	    Optional<Product> optionalProduct = productRepository.findById(
	        addProductInCartDto.getProductId());

	    Optional<CartItems> optionalCartItem = cartItemsRepository.findByProductIdAndOrderIdAndUserId(
	        addProductInCartDto.getProductId(), activeOrder.getId(), addProductInCartDto.getUserId());

	    if (optionalProduct.isPresent() && optionalCartItem.isPresent()) {
	        CartItems cartItem = optionalCartItem.get();
	        Product product = optionalProduct.get();

	        activeOrder.setAmount(activeOrder.getAmount() - product.getPrice());
	        activeOrder.setTotalAmount(activeOrder.getTotalAmount() - product.getPrice());

	        cartItem.setQuantity(cartItem.getQuantity() - 1);

	        if (activeOrder.getCoupon() != null) {
	            double discountAmount = (activeOrder.getCoupon().getDiscount() / 100.0) * activeOrder.getTotalAmount();
	            double netAmount = activeOrder.getTotalAmount() - discountAmount;

	            activeOrder.setAmount((long) netAmount);
	            activeOrder.setDiscount((long) discountAmount);
	        }

	        cartItemsRepository.save(cartItem);
	        orderRepository.save(activeOrder);

	        return activeOrder.getOrderDto();
	    }

	    return null;
	}


	
	
}

