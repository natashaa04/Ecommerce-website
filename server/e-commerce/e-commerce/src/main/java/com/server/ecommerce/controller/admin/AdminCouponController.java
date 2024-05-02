package com.server.ecommerce.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.server.ecommerce.entity.Coupon;
import com.server.ecommerce.exceptions.ValidationException;

import com.server.ecommerce.services.coupon.AdminCouponService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@RequestMapping("/api/admin/coupons")
public class AdminCouponController {

	@Autowired
  private AdminCouponService adminCouponService;
	
	@PostMapping("/create")
	public ResponseEntity<?> createCoupon(@RequestBody Coupon coupon) {
	    try {
	        Coupon createdCoupon = adminCouponService.createCoupon(coupon);
	        return ResponseEntity.ok(createdCoupon);
	    } catch (ValidationException ex) {
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
	    }
	}

	@GetMapping("/all")
	public ResponseEntity<List<Coupon>> getAllCoupons() {
	    return ResponseEntity.ok(adminCouponService.getAllCoupons());
	}

}
