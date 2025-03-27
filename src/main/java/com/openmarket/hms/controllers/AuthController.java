package com.openmarket.hms.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.openmarket.hms.annotations.CustomController;
import com.openmarket.hms.requestDto.UserLoginDto;
import com.openmarket.hms.services.AuthService;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;


@CustomController
@RequestMapping("auth")
@Tag(name="Auth")
public class AuthController {
	@Autowired
	private AuthService authService;
	
   @PostMapping("/user/login")
   public Object userLogin(
		   @Valid @RequestBody UserLoginDto loginDto
		   ) {
	   return this.authService.userLogin(loginDto);
   }
}
