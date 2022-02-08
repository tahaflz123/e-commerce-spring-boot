package com.shopping.controller;

import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shopping.entity.product.Category;
import com.shopping.entity.product.Product;
import com.shopping.model.product.ProductCreateRequest;
import com.shopping.service.ProductService;

@RestController
@RequestMapping("/product")
public class ProductController {

	
	@Autowired
	private ProductService productService;
	
	@GetMapping
	public Product product(@PathParam("id") Long id) {
		return this.productService.getProductById(id);
	}
	
	@GetMapping("/")
	public List<Product> getAllProducts(){
		return this.productService.getAllProducts();
	}
	
	@PostMapping("/create")
	public Long createProduct(@RequestBody ProductCreateRequest request) {
		return this.productService.createProduct(request);
	}
	
	@GetMapping("/user")
	public List<Product> getUserProducts(@PathParam("id") Long id){
		return this.productService.getUserProducts(id);
	}
	
	@DeleteMapping("/delete")
	public boolean deleteProductById(@PathParam("id") Long id) {
		return this.productService.deleteProductById(id);
	}
	
	@GetMapping("/search")
	public List<Product> searchProduct(@PathParam("q")String q) {
		return this.productService.searchProduct(q);
	}
	
	
	@GetMapping("/category/{category}")
	public List<Product> getProductsByCategoryAndName(@PathVariable("category") Category category,@PathParam("q") String q){
		return this.productService.getProductsByCategoryAndName(category,q);
	}
	
	
	
	
}
