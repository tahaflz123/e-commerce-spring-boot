package com.shopping.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.shopping.entity.order.Order;
import com.shopping.entity.product.Product;
import com.shopping.entity.user.User;
import com.shopping.exception.AuthenticationException;
import com.shopping.exception.OrderException;
import com.shopping.exception.ProductException;
import com.shopping.model.order.OrderResponse;
import com.shopping.model.order.UserOrdersResponse;
import com.shopping.repository.OrderRepository;

@Service
public class OrderService {

	private OrderRepository orderRepository;
	private UserService userService;
	private ProductService productService;
	
	public OrderService(OrderRepository orderRepository, UserService userService, ProductService productService) {
		this.orderRepository = orderRepository;
		this.userService = userService;
		this.productService = productService;
	}
	
	public UserOrdersResponse getUserOrders() {
		User user = this.userService.getLoggedInUser();
		
		List<Order> order = this.orderRepository.findAllByOrdererUserId(user.getId());
		List<Product> products = new ArrayList<Product>();
		ArrayList<Long> productIds = new ArrayList<Long>();
		
		for(Order o : order) {
			productIds.add(o.getProductId());
		}
		for(Long id : productIds) {
			products.add(this.productService.getProductById(id));
		}
		
		UserOrdersResponse userOrdersResponse = new UserOrdersResponse();
		userOrdersResponse.setOrders(order);
		userOrdersResponse.setProducts(products);
		
		
		
		return userOrdersResponse;
	}

	public Long orderProduct(Long id) {
		Boolean exists = this.productService.existsById(id);
		if(!exists) {
			throw new ProductException("Product is not found with following id = " + id);
		}
		User user = this.userService.getLoggedInUser();
		
		Boolean ordered = this.orderRepository.existsByProductIdAndOrdererUserId(id,user.getId());
		if(ordered) {
			throw new OrderException("Already you are ordered this product!");
		}
		
		Order order = new Order();
		order.setOrderDate(new Date());
		order.setOrdererUserId(user.getId());
		order.setProductId(id);;
		
		return this.orderRepository.save(order).getId();
	}
	
	public OrderResponse getOrder(Long id) {
		Order order = this.orderRepository.findById(id).get();
		if(order == null) {
			throw new OrderException("Order didn't find");
		}
		
		User user = this.userService.getUserById(order.getOrdererUserId());
		if(user.getId() != this.userService.getLoggedInUser().getId()) {
			throw new AuthenticationException("You can't access!");
		}
		
		Product product = this.productService.getProductById(order.getProductId());
		
		OrderResponse orderResponse = new OrderResponse();
		orderResponse.setOrder(order);
		orderResponse.setProduct(product);
		orderResponse.setUser(user);
		return orderResponse;
	}
	
	public Boolean existsOrderById(Long id) {
		return this.orderRepository.existsById(id);
	}
	
	
	
}
