package com.shopping.model.product;

import com.shopping.entity.product.Category;

import lombok.Data;

@Data
public class ProductCreateRequest {

	
	private String name;
	
	private Category category;
	
	private Double price;
	
	private String about;
	
	
}
