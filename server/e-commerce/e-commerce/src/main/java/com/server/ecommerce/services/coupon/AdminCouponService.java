package com.server.ecommerce.services.coupon;

import java.util.List;

import com.server.ecommerce.entity.Coupon;

public interface AdminCouponService {
	public Coupon createCoupon(Coupon coupon);
	public List<Coupon> getAllCoupons();
}
