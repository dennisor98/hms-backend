package com.openmarket.hms.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
	
   @PostMapping()
   public Object createDepartment(@Valid @RequestBody CreateDeptDto createDept) {
	   return this.departmentService.createDepartment(createDept);
   }
   
   @GetMapping()
   public Object getDepartments() {
	   
	   return this.departmentService.getDepartments();
   }
   
   
   @PutMapping()
   public Object updateDepartment(
		   @Valid @RequestBody CreateDeptDto createDept,
		   @RequestParam(name="departmentId",required=true) String id
		   
		   ) {
	   return this.departmentService.editDepartment(id, createDept);
   }
   
   @DeleteMapping()
   public Object deleteDepartment(
		   @RequestParam(name="departmentId") String departmentId
		   ) {
	   return this.departmentService.deleteDepartment(departmentId);
   }
}
