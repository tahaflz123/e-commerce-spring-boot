package com.shopping.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shopping.entity.user.User;
import com.shopping.model.auth.AuthRequest;
import com.shopping.model.auth.RegistrationRequest;
import com.shopping.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;
	
	@PostMapping("/register")
	public Long register(@RequestBody RegistrationRequest request) {
		return this.userService.register(request);
	}
	
	@PostMapping("/login")
	public String login(@RequestBody AuthRequest request) {
		return this.userService.login(request);
	}
	
	@GetMapping("/profile")
	public User userProfile() {
		return this.userService.profile();
	}
	
	@PutMapping("/wallet/upload/{amount}")
	public Double uploadWallet(@PathVariable("amount") Double amount) {
		return this.userService.uploadWallet(amount);
	}
	
	
}
