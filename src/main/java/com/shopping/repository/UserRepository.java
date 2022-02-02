package com.shopping.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shopping.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

	Boolean existsByEmail(String email);

	User findByEmail(String email);


}
