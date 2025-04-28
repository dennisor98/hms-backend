package com.openmarket.hms.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.openmarket.hms.annotations.CustomController;
import com.openmarket.hms.repository.DepartmentRepository;
import com.openmarket.hms.repository.UserRepository;
import com.openmarket.hms.requestDto.CreateUserDto;
import com.openmarket.hms.requestDto.UpdateUserDto;
import com.openmarket.hms.services.UserService;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@CustomController
@RequestMapping("admin")
@Tag(name="BackOffice")
public class AdminController {
   @Autowired
   UserRepository userRepo;
   @Autowired
   DepartmentRepository deptRepo;
   @Autowired
   UserService userService;
	@PostMapping()
	public Object addStaff(@Valid @RequestBody CreateUserDto userDto) {
		return this.userService.createUser(userDto);
	}
	
	@PutMapping()
	public Object editStaff(@Valid @RequestBody UpdateUserDto userDto) {
		return null;
	}
	
}
