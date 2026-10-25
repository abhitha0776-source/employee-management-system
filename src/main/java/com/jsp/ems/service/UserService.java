package com.jsp.ems.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.jsp.ems.dto.RegisterRequest;
import com.jsp.ems.entity.User;
import com.jsp.ems.respository.UserRepository;
import com.jsp.ems.util.OtpGenerator;

@Service
public class UserService {
	
	private UserRepository userRepository;
	private EmailService emailService;
	private PasswordEncoder passwordEncoder;
	
	public UserService(UserRepository userRepository, EmailService emailService, PasswordEncoder passwordEncoder) {
		this.userRepository = userRepository;
		this.emailService = emailService;
		this.passwordEncoder = passwordEncoder;
	}

	public String register(RegisterRequest registerRequest) {
		Optional<User> ou= userRepository.findByEmail(registerRequest.getEmail());
		if(ou.isPresent()) {
			return "email_id already exists";
		}else {
			User user = new User();
			user.setName(registerRequest.getName());
			user.setEmail(registerRequest.getEmail());
			user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
			user.setRole("USER_ROLE");
			user.setVerified(false);
			String otp = OtpGenerator.generatOtp();
			user.setOtp(otp);
			user.setOtpexpirytime(LocalDateTime.now().plusMinutes(5));
			userRepository.save(user);
			
			emailService.sendOtp(registerRequest.getEmail(), otp);
			return "please check your email for otp";
		}
	}
	

}
