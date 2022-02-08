package com.shopping.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shopping.entity.order.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long>{


	Boolean existsByProductIdAndOrdererUserId(Long productId, Long userId);

	List<Order> findAllByOrdererUserId(Long userId);

}
