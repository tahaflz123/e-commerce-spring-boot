package com.shopping.controller;

import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shopping.entity.order.Order;
import com.shopping.service.OrderService;

@RestController
@RequestMapping("/order")
public class OrderController {

	@Autowired
	private OrderService orderService;
	
	@GetMapping("/user")
	private List<Order> getUserOrders(){
		return this.orderService.getUserOrders();
	}
	
	@GetMapping("/product")
	private Long orderProduct(@PathParam("id") Long id,@PathParam("amount") Integer amount) {
		return this.orderService.orderProduct(id,amount);
	}
	
	@GetMapping("/show")
	private Order orderResponse(@PathParam("id")Long id) {
		return this.orderService.getOrder(id);
	}
	
	@DeleteMapping("/product/delete")
	private Boolean deleteOrder(@PathParam("id") Long id) {
		return this.orderService.deleteOrder(id);
	}
	
}
