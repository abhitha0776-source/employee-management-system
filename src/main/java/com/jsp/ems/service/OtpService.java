package com.jsp.ems.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.jsp.ems.dto.VerifyOtpRequest;
import com.jsp.ems.entity.User;
import com.jsp.ems.exception.InvalidOtpException;
import com.jsp.ems.exception.OtpExpriedException;
import com.jsp.ems.exception.OtpVerifiedException;
import com.jsp.ems.exception.UserNotFoundException;
import com.jsp.ems.respository.UserRepository;

@Service
public class OtpService {
	
	private UserRepository userRepository;

	public OtpService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	public String VerifyOtp(VerifyOtpRequest verifyOtpRequest) {
		Optional<User> optionaluser = userRepository.findByEmail(verifyOtpRequest.getEmail());
		if(optionaluser.isPresent()) {
			User user = optionaluser.get();
			if(!user.getOtp().equals(verifyOtpRequest.getOtp())) {
				throw new InvalidOtpException("invalid otp");
			}
			if(LocalDateTime.now().isAfter(user.getOtpexpirytime())) {
				throw new OtpExpriedException("otp expried");
			} else {
				user.setVerified(true);
				user.setOtp(null);
				user.setOtpexpirytime(null);
				userRepository.save(user);
				throw new OtpVerifiedException("otp verified successfully") ;
			}
			
		} else {
			throw new UserNotFoundException("no user found");
		}
	}

	

}
