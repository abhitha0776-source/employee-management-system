package com.jsp.ems.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.ems.dto.RegisterRequest;
import com.jsp.ems.dto.VerifyOtpRequest;
import com.jsp.ems.service.OtpService;
import com.jsp.ems.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

	private UserService userService;
	private OtpService otpService;
	
	
	public UserController(UserService userService, OtpService otpService) {
		super();
		this.userService = userService;
		this.otpService = otpService;
	}

	@PostMapping("/register")
	public String register(@RequestBody RegisterRequest registerRequest) {
		return userService.register(registerRequest);
	}
	
	@PostMapping("/verify-otp")
	public String VerifyOtp(@RequestBody VerifyOtpRequest verifyOtpRequest) {
		return otpService.VerifyOtp(verifyOtpRequest);
	}
}
