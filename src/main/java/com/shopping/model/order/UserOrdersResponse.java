package com.shopping.model.order;

import java.util.List;

import com.shopping.entity.order.Order;
import com.shopping.entity.product.Product;

import lombok.Data;

@Data
public class UserOrdersResponse {
	
	private List<Product> products;

	private List<Order> orders;
}
