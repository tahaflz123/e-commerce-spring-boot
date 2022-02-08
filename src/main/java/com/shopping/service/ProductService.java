package com.shopping.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shopping.entity.product.Product;
import com.shopping.entity.user.User;
import com.shopping.model.product.ProductCreateRequest;
import com.shopping.repository.ProductRepository;

@Service
public class ProductService {

	private ProductRepository productRepository;
	private UserService userService;
	
	@Autowired
	public ProductService(ProductRepository productRepository, UserService userService) {
		this.productRepository = productRepository;
		this.userService = userService;
	}
	
	
	
	public Long createProduct(ProductCreateRequest request) {
		User user = this.userService.getLoggedInUser();
		
		Product product = new Product();
		product.setSellerUserId(user.getId());
		product.setAbout(request.getAbout());
		product.setCategory(request.getCategory());
		product.setCreatedDate(new Date());
		product.setName(request.getName());
		product.setPrice(request.getPrice());
		
		return this.productRepository.save(product).getId();
	}



	public Product getProductById(Long id) {
		return this.productRepository.findById(id).get();
	}



	public List<Product> getUserProducts(Long id) {
		return this.productRepository.findAllBySellerUserId(id);
	}



	public boolean deleteProductById(Long id) {
		Product product = this.productRepository.findById(id).get();
		User user = this.userService.getLoggedInUser();
		if(product.getSellerUserId() == user.getId()) {
			this.productRepository.delete(product);
			return true;
		}
		return false;
	}



	public List<Product> searchProduct(String q) {
		List<Product> products = this.productRepository.search(q);
		return products;
	}
	
}
