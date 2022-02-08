package com.shopping.model.order;

import com.shopping.entity.order.Order;
import com.shopping.entity.product.Product;
import com.shopping.entity.user.User;

import lombok.Data;

@Data
public class OrderResponse {

	
	private Product product;
	
	private User user;
	
	private Order order;
	
}
