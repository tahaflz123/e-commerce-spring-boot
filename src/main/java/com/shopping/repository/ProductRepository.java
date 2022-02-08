package com.shopping.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.shopping.entity.product.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{

	List<Product> findAllBySellerUserId(Long id);

	@Query("SELECT pro FROM Product pro WHERE pro.name LIKE %:q%")
	List<Product> search(String q);


}
