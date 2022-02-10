package com.shopping.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shopping.entity.order.Order;
import com.shopping.entity.product.Product;
import com.shopping.entity.user.User;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long>{


	Boolean existsByProductAndUser(Product product, User user);

	List<Order> findAllByUser(User user);

}
