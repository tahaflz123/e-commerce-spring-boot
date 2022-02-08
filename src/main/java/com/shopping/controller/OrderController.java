package com.shopping.controller;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shopping.model.order.OrderResponse;
import com.shopping.model.order.UserOrdersResponse;
import com.shopping.service.OrderService;

@RestController
@RequestMapping("/order")
public class OrderController {

	@Autowired
	private OrderService orderService;
	
	@GetMapping("/user")
	private UserOrdersResponse getUserOrders(){
		return this.orderService.getUserOrders();
	}
	
	@GetMapping("/product")
	private Long orderProduct(@PathParam("id") Long id) {
		return this.orderService.orderProduct(id);
	}
	
	@GetMapping("/show")
	private OrderResponse orderResponse(@PathParam("id")Long id) {
		return this.orderService.getOrder(id);
	}
	
	
}
