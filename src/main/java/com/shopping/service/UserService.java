package com.shopping.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.shopping.config.JwtService;
import com.shopping.entity.user.User;
import com.shopping.entity.user.UserRole;
import com.shopping.exception.AuthenticationException;
import com.shopping.model.auth.AuthRequest;
import com.shopping.model.auth.RegistrationRequest;
import com.shopping.repository.UserRepository;

@Service
public class UserService {

	private BCryptPasswordEncoder passwordEncoder;
	private UserRepository userRepository;
	private JwtService jwtService;

	@Autowired
	public UserService(UserRepository userRepository, JwtService jwtService,BCryptPasswordEncoder passwordEncoder) {
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
		this.jwtService = jwtService;
	}

	public Long register(RegistrationRequest request) {
		Boolean existsEmail = this.userRepository.existsByEmail(request.getEmail());
		if(existsEmail) {
			throw new AuthenticationException("Email is valid");
		}
		User user = new User();
		user.setName(request.getName());
		user.setSurname(request.getSurname());
		user.setUsername(request.getEmail());
		user.setPassword(this.passwordEncoder.encode(request.getPassword()));
		user.setUserRole(UserRole.USER);
		return this.userRepository.save(user).getId();
	}

	public String login(AuthRequest request) {
		User user = this.userRepository.findByEmail(request.getEmail());
		if(user == null) {
			throw new AuthenticationException("Given email and password not matches.");
		}
		if(!this.passwordEncoder.matches(request.getPassword(), user.getPassword())) {
			throw new AuthenticationException("Given email and password not matches.");
		}
		String token = this.jwtService.createToken(user);
		return token;
	}
	
	
	public User profile() {
		return this.getLoggedInUser();
	}
	
	public User getLoggedInUser() {
		User user = this.userRepository.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
		return user;
	}
	
	
	
	
	
	
}
