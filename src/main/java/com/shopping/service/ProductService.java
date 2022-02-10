package com.shopping.service;

import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shopping.entity.product.Category;
import com.shopping.entity.product.Product;
import com.shopping.entity.user.User;
import com.shopping.exception.ProductException;
import com.shopping.model.product.ProductCreateRequest;
import com.shopping.repository.ProductRepository;

@Service
public class ProductService {

	Logger logger = Logger.getLogger(ProductService.class);
	
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
		product.setStock(request.getStock());
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

	public List<Product> getProductsByCategoryAndName(Category category,String q) {
		if(q != null) {
			this.logger.info("Param 'q' not null!");
			return this.productRepository.findAllByCategoryAndName(category.name(), q);
		}
		this.logger.info("Param 'q' is null!");
		return this.productRepository.findAllByCategoryName(category.name());
	}

	public List<Product> getAllProducts() {
		return this.productRepository.findAll();
	}
	
	public Boolean existsById(Long id) {
		Boolean exists = this.productRepository.existsById(id);
		return exists;
	}
	
	public Product updateStock(Long productId,Integer stock) {
		Product product = this.productRepository.findById(productId).get();
		User user = this.userService.getLoggedInUser();
		if(user.getId() != product.getSellerUserId())
			throw new ProductException("You can't access");
		
		if(stock < 0) {
			throw new ProductException("stock must be greater than 0");
		}
		product.setStock(product.getStock() + stock);
		return this.productRepository.save(product);
	}
	
	public Product saveProduct(Product product) {
		return this.productRepository.save(product);
	}
	
}
