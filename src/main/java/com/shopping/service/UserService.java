package com.shopping.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.shopping.config.JwtService;
import com.shopping.entity.user.User;
import com.shopping.entity.user.UserRole;
import com.shopping.exception.AuthenticationException;
import com.shopping.exception.OrderException;
import com.shopping.model.auth.AuthRequest;
import com.shopping.model.auth.RegistrationRequest;
import com.shopping.repository.UserRepository;

@Service
public class UserService{

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
		user.setEmail(request.getEmail());
		user.setWallet(0.00);
		user.setCreatedDate(new Date());
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

	public User getUserById(Long id) {
		return this.userRepository.findById(id).get();
	}
	
	public User saveUser(User user) {
		return this.userRepository.save(user);
	}

	public Double uploadWallet(Double amount) {
		User user = this.getLoggedInUser();
		if(amount < 0) {
			throw new OrderException("amount must be greater than 0");
		}
		amount += user.getWallet();
		user.setWallet(amount);
		return this.userRepository.save(user).getWallet();
	}

	
	
	
	
}
