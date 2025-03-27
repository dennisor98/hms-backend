package com.openmarket.hms.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.openmarket.hms.annotations.CustomController;
import com.openmarket.hms.requestDto.CreateDeptDto;
import com.openmarket.hms.services.DepartmentService;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@CustomController
@RequestMapping("department")
@Tag(name="Department")
public class DepartmentController {
	@Autowired
	DepartmentService departmentService;
	
   @PostMapping("")
   public Object createDepartment(@Valid @RequestBody CreateDeptDto createDept) {
	   return this.departmentService.createDepartment(createDept);
   }
}
