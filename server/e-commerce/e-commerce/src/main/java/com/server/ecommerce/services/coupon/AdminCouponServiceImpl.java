package com.server.ecommerce.services.coupon;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.server.ecommerce.entity.Coupon;
import com.server.ecommerce.exceptions.ValidationException;
import com.server.ecommerce.repository.CouponRepository;

@Service
public class AdminCouponServiceImpl implements AdminCouponService{

	
	@Autowired
	private CouponRepository couponRepository;
	
	public Coupon createCoupon(Coupon coupon) {
	    if (couponRepository.existsByCode(coupon.getCode())) {
	        throw new ValidationException("Coupon code already exists.");
	    }
	    return couponRepository.save(coupon);
	}

	public List<Coupon> getAllCoupons() {
	    return couponRepository.findAll();
	}

	
}
