package com.server.ecommerce.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class AnalyticsResponse {
  

	

	private Long placed;
	
	private Long shipped;
	
	private Long delivered;
	
	private Long currentMonthOrders;
	
	private Long previousMonthOrders;
	
	private Long currentMonthEarnings;

	private Long previousMonthEarnings;

	public AnalyticsResponse(Long placed2, Long shipped2, Long delivered2, Long currentMonthOrders2,
			Long previousMonthOrders2, Long currentMonthEarnings2, Long previousMonthEarnings2) {
	this.placed=placed2;
	this.shipped=shipped2;
	this.delivered=delivered2;
	this.currentMonthEarnings=currentMonthEarnings2;
	this.previousMonthEarnings=previousMonthEarnings2;
	this.currentMonthOrders=currentMonthOrders2;
	this.previousMonthOrders= previousMonthOrders2;
	}
	
}