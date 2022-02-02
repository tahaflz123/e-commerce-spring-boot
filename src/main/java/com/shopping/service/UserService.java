package com.shopping.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shopping.entity.User;
import com.shopping.exception.AuthenticationException;
import com.shopping.model.auth.RegistrationRequest;
import com.shopping.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	public Long register(RegistrationRequest request) {
		Boolean existsEmail = this.userRepository.existsByEmail(request.getEmail());
		if(existsEmail) {
			throw new AuthenticationException("Email is valid");
		}
		User user = new User();
		user.setName(request.getName());
		user.setSurname(request.getSurname());
		user.setUsername(request.getEmail());
		user.setPassword(request.getPassword());
		return this.userRepository.save(user).getId();
	}
	
	
	
	
	
}
