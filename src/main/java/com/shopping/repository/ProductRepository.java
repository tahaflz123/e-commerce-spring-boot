package com.shopping.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.shopping.entity.product.Category;
import com.shopping.entity.product.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{

	List<Product> findAllBySellerUserId(Long id);

	@Query("SELECT p FROM Product p WHERE p.name LIKE %:q%")
	List<Product> search(String q);

	@Query("SELECT u FROM Product u WHERE u.category=UPPER(:categoryName)")
	List<Product> findAllByCategoryName(String categoryName);

	@Query("SELECT u FROM Product u WHERE u.category=UPPER(:category) AND u.name LIKE %:name%")
	List<Product> findAllByCategoryAndName(String category, String name);


}
