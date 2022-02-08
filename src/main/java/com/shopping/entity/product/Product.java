package com.shopping.entity.product;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;
	
	private Long sellerUserId;
	
	private String name;
	
	private Integer stock;
	
	@Enumerated(EnumType.STRING)
	private Category category;
	
	private Double price;
	
	@Lob
	private String about;
	
	private Date createdDate;
	
	
	
	
	
}
