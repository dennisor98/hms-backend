package com.openmarket.hms.services;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.openmarket.hms.domain.User;
import com.openmarket.hms.domain.UserPassword;
import com.openmarket.hms.repository.UserPasswordRepository;
import com.openmarket.hms.repository.UserRepository;
import com.openmarket.hms.requestDto.UserLoginDto;

@Service
public class AuthService {
  @Autowired
  UserRepository userRepository;
  @Autowired
  UserPasswordRepository userPasswordRepository;
  @Autowired
  JwtService jwtService;
  
  public Object userLogin(UserLoginDto loginDto) {
	  String mobile = loginDto.getMobileNumber().trim();
	  if(mobile.length() < 9) {
		  Map<String,Object> response = new HashMap<>();
		  
		  response.put("success",false);
		  response.put("message","Invalid mobile number");
		  
		  return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
	  }
	   
	  
	  String sanitizedMobile = "+254"+mobile.substring(mobile.length() -9);
	  
	  Optional<User> userOpt =  this.userRepository.findByMobileNumber(sanitizedMobile);
	  if(userOpt.isEmpty()) {
		  Map<String,Object> response = new HashMap<>();
		  response.put("success",false);
		  response.put("message","Invalid login cridentials");
		  
		  return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
	  }
	  
	  User user = userOpt.get();
	  
	  if(!user.getIsActive()) {
		  Map<String,Object> response = new HashMap<>();
		  response.put("success",false);
		  response.put("message","Access denied");
		  
		  return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
	  }
	  
	  Optional<UserPassword> userPinOpt = this.userPasswordRepository.findByUser(user);
	  if(userPinOpt.isEmpty()) {
		  Map<String,Object> res = new HashMap<>();
		  res.put("success",false);
		  res.put("message","Access denied.Contact your IT support");
		  
		  return ResponseEntity.status(HttpStatus.FORBIDDEN).body(res);
	  }
	  
	  if(! new BCryptPasswordEncoder().matches(loginDto.getPassword(),userPinOpt.get().getPin())) {
		  Map<String,Object> res = new HashMap<>();
		  res.put("success",false);
		  res.put("message","Invalid login cridentials");
		  
		  return ResponseEntity.status(HttpStatus.FORBIDDEN).body(res);
	  }
	  
	  
	  Map<String,Object> userObj =  new HashMap<>();
	  userObj.put("userId",user.getId());
	  userObj.put("firstName",user.getFirstName());
	  userObj.put("middleName",user.getFirstName());
	  userObj.put("lastName",user.getLastName());
	  userObj.put("mobileNumber", user.getMobileNumber());
	  
	  Map<String,Object> payLoad = new HashMap<>();
	  payLoad.put("user",userObj);
	  payLoad.put("access_token",this.jwtService.generateToken(user));
	  payLoad.put("refresh_token",this.jwtService.generateRefreshToken(user));
	  
	  Map<String,Object> response = new HashMap<>();
	  response.put("success", true);
	  response.put("message","Login successful");
	  response.put("payload",payLoad);
	  return ResponseEntity.status(HttpStatus.OK).body(response);
  }
  
   public Object refreshUserToken() {
	   User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	   Map<String,Object> res = new HashMap<>();
	   res.put("success",true);
	   res.put("access_token",this.jwtService.generateToken(user));
	   res.put("refresh_token",this.jwtService.generateRefreshToken(user));
	   
	   return ResponseEntity.status(HttpStatus.OK).body(res);
   }
}
