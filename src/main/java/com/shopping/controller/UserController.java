package com.shopping.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
	
}
