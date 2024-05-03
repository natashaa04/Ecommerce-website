package com.server.ecommerce.services.coupon;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.server.ecommerce.entity.Coupon;
import com.server.ecommerce.exceptions.ValidationException;
import com.server.ecommerce.repository.CouponRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AdminCouponServiceImpl implements AdminCouponService{

	
	@Autowired
	private CouponRepository couponRepository;
	
	public Coupon createCoupon(Coupon coupon) {
		
		try {
			 if (couponRepository.existsByCode(coupon.getCode())) {
			        throw new ValidationException("Coupon code already exists.");
			    }
			    return couponRepository.save(coupon);
			
		} catch (Exception e) {
			log.info("error in creatin token");
			e.printStackTrace();
			return null;
		}
	}
	   

	public List<Coupon> getAllCoupons() {
	    return couponRepository.findAll();
	}

	
}
