package com.server.ecommerce.services.adminOrder;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.server.ecommerce.Enum.OrderStatus;
import com.server.ecommerce.dto.AnalyticsResponse;
import com.server.ecommerce.dto.OrderDto;
import com.server.ecommerce.entity.Order;
import com.server.ecommerce.repository.OrderRepository;

import io.jsonwebtoken.lang.Objects;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AdminOrderServiceImpl implements AdminOrderService{

	@Autowired
	private OrderRepository orderRepository;
	
	public List<OrderDto>getAllPlacedOrders(){
		List<Order>orderList=orderRepository.findAllByOrderStatusIn(List.of(OrderStatus.Placed , OrderStatus.Shipped ,OrderStatus.Delivered));
		return orderList.stream().map(Order::getOrderDto).collect(Collectors.toList());
		}
	
	public OrderDto changeOrderStatus(Long orderId, String status) {
		try {
			
			Optional<Order> optionalOrder = orderRepository.findById(orderId); 
		
	    if (optionalOrder.isPresent()) {
	    	
	        Order order = optionalOrder.get(); 
	        if (Objects.nullSafeEquals(status, "Shipped")) { 
	            order.setOrderStatus(OrderStatus.Shipped); 
	        } else if (Objects.nullSafeEquals(status, "Delivered")) { 
	            order.setOrderStatus(OrderStatus.Delivered); 
	        }
	        return orderRepository.save(order).getOrderDto(); 
	    }
	    
		
	} 
	catch (Exception e) {
		log.info("change order status error");
		e.printStackTrace();
		 return null; 
	}
		return null;

	   
	}
	
	public Long getTotalEarningsForMonth(int month, int year) {
	    Calendar calendar = Calendar.getInstance();

	    calendar.set(Calendar.YEAR, year);
	    calendar.set(Calendar.MONTH, month - 1);  // Month is zero-based
	    calendar.set(Calendar.DAY_OF_MONTH, 1);
	    calendar.set(Calendar.HOUR_OF_DAY, 0);
	    calendar.set(Calendar.MINUTE, 0);
	    calendar.set(Calendar.SECOND, 0);

	    Date startOfMonth = calendar.getTime();

	    // Move the calendar to the end of the specified month
	    calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
	    calendar.set(Calendar.HOUR_OF_DAY, 23);
	    calendar.set(Calendar.MINUTE, 59);
	    calendar.set(Calendar.SECOND, 59);

	    Date endOfMonth = calendar.getTime();

	    List<Order> orders = orderRepository.findByDateBetweenAndOrderStatus(startOfMonth, endOfMonth, OrderStatus.Delivered);

	    Long sum = 0L;
	    for (Order order : orders) {
	        sum += order.getAmount();
	    }

	    return sum;
	}

	public Long getTotalOrdersForMonth(int month, int year) {

	    Calendar calendar = Calendar.getInstance();

	    calendar.set(Calendar.YEAR, year);
	    calendar.set(Calendar.MONTH, month - 1);  // Month is zero-based
	    calendar.set(Calendar.DAY_OF_MONTH, 1);
	    calendar.set(Calendar.HOUR_OF_DAY, 0);
	    calendar.set(Calendar.MINUTE, 0);
	    calendar.set(Calendar.SECOND, 0);

	    Date startOfMonth = calendar.getTime();

	    // Move the calendar to the end of the specified month
	    calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
	    calendar.set(Calendar.HOUR_OF_DAY, 23);
	    calendar.set(Calendar.MINUTE, 59);
	    calendar.set(Calendar.SECOND, 59);

	    Date endOfMonth = calendar.getTime();

	    List<Order> orders = orderRepository.findByDateBetweenAndOrderStatus(startOfMonth, endOfMonth, OrderStatus.Delivered);

	    return (long) orders.size();
	}

	public AnalyticsResponse calculateAnalytics() {

	    LocalDate currentDate = LocalDate.now();
	    LocalDate previousMonthDate = currentDate.minusMonths(1);

	    Long currentMonthOrders = getTotalOrdersForMonth(currentDate.getMonthValue(), currentDate.getYear());
	    Long previousMonthOrders = getTotalOrdersForMonth(previousMonthDate.getMonthValue(), previousMonthDate.getYear());

	    Long currentMonthEarnings = getTotalEarningsForMonth(currentDate.getMonthValue(), currentDate.getYear());
	    Long previousMonthEarnings = getTotalEarningsForMonth(previousMonthDate.getMonthValue(), previousMonthDate.getYear());

	    Long placed = orderRepository.countByOrderStatus(OrderStatus.Placed);
	    Long shipped = orderRepository.countByOrderStatus(OrderStatus.Shipped);
	    Long delivered = orderRepository.countByOrderStatus(OrderStatus.Delivered);
AnalyticsResponse analyticsResponse= new AnalyticsResponse(placed, shipped, delivered, currentMonthOrders, previousMonthOrders, currentMonthEarnings, previousMonthEarnings);
return analyticsResponse;
	}


	
	
}







