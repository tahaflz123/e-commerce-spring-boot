package com.shopping.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shopping.entity.order.Order;
import com.shopping.entity.product.Product;
import com.shopping.entity.user.User;
import com.shopping.exception.OrderException;
import com.shopping.exception.ProductException;
import com.shopping.repository.OrderRepository;

@Service
public class OrderService {

	private OrderRepository orderRepository;
	private UserService userService;
	private ProductService productService;
	
	@Autowired
	public OrderService(OrderRepository orderRepository, UserService userService, ProductService productService) {
		this.orderRepository = orderRepository;
		this.userService = userService;
		this.productService = productService;
	}
	
	public List<Order> getUserOrders() {
		User user = this.userService.getLoggedInUser();
		List<Order> orders = this.orderRepository.findAllByUser(user);
		return orders;
	}

	public Long orderProduct(Long id,Integer amount) {
		if(amount == null || amount == 0) {
			amount = 1;
		}
		if(amount < 0) {
			throw new ProductException("amount must be greater than 0");
		}
		
		
		Product product = this.productService.getProductById(id);
		if(product == null)
			throw new ProductException("Product is not found with following id = " + id);
        
		if(product.getStock() == 0)
        	throw new ProductException("out of stock");
		
		User user = this.userService.getLoggedInUser();
		
		Boolean ordered = this.orderRepository.existsByProductAndUser(product,user);
		if(ordered)
			throw new OrderException("Already you are ordered this product!");
		
		
		if(user.getWallet() < (product.getPrice() * amount))
			throw new OrderException("Insufficient balance!");
		
	
		Order order = new Order();
		order.setOrderDate(new Date());
		order.setUser(user);
		order.setProduct(product);
		order.setAmount(amount);
		order.setTotalCost(product.getPrice() * (amount.doubleValue()));
		
		
		List<Order> userOrders = user.getOrders();
		userOrders.add(order);
		user.setOrders(userOrders);
		user.setWallet(user.getWallet() - (product.getPrice() * amount));
		
		this.userService.saveUser(user);
		
		product.setStock(product.getStock() - amount);
		
		return this.orderRepository.save(order).getId();
	}
	
	public Order getOrder(Long id) {
		Order order = this.orderRepository.findById(id).get();
		if(order == null) {
			throw new OrderException("Order didn't find");
		}
		
		return order;
	}
	
	public Boolean existsOrderById(Long id) {
		return this.orderRepository.existsById(id);
	}
	
	public Boolean deleteOrder(Long id) {
		User user = this.userService.getLoggedInUser();
		Order order = this.orderRepository.findById(id).get();
		
		user.setWallet(user.getWallet() + order.getTotalCost());
		Product product = order.getProduct();
		product.setStock(product.getStock() + order.getAmount());

		this.orderRepository.deleteById(id);
		this.userService.saveUser(user);
		this.productService.saveProduct(product);
		
		return true;
	}
	
	
}
