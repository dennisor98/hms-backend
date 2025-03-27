package com.openmarket.hms.services;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.openmarket.hms.domain.Department;
import com.openmarket.hms.domain.User;
import com.openmarket.hms.repository.DepartmentRepository;
import com.openmarket.hms.requestDto.CreateDeptDto;

@Service
public class DepartmentService {
	@Autowired
	DepartmentRepository deptRepository;
	
 
   public Object createDepartment(CreateDeptDto deptDto) {
	   User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	   Optional<Department> deptOpt =  this.deptRepository.findByName(deptDto.getName());
	   if(deptOpt.isPresent()) {
		   Map<String,Object> res = new HashMap<>();
		   res.put("success",false);
		   res.put("message","Department already exists");
		   
		   return ResponseEntity.status(HttpStatus.CONFLICT).body(res);
	   }
	   try {
		   Department deptBuild = Department.builder().name(deptDto.getName()).description(deptDto.getDescription()).user(user).build();
		   this.deptRepository.save(deptBuild);
		   Map<String,Object> res = new HashMap<>();
		   res.put("success",true);
		   res.put("message","Department created!");
		   
		   return ResponseEntity.status(HttpStatus.OK).body(res);
	   }catch(Exception ex) {
		   ex.printStackTrace();
		   Map<String,Object> res = new HashMap<>();
		   res.put("success",false);
		   res.put("message","Server error while processign request");
		   
		   return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(res);
	   }
   }
   
   
   public Object editDepartment(String id,CreateDeptDto deptDto) {
	   Optional<Department> deptOpt = this.deptRepository.findById(id);
	   if(deptOpt.isEmpty()) {
		   Map<String,Object> res = new HashMap<>();
		   res.put("success",false);
		   res.put("message","Invalid deptId");
		   
		   return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(res);
	   }
	   
	   Department dept = deptOpt.get();
	   dept.setName(deptDto.getName());
	   dept.setDescription(deptDto.getDescription());
	   dept.setParentDepartmentId(deptDto.getParentId());
	   
	   try {
		   this.deptRepository.save(dept);
		   Map<String,Object> res = new HashMap<>();
		   res.put("success",true);
		   res.put("message","Department modified");
		   
		   return ResponseEntity.status(HttpStatus.OK).body(res);
	   }catch(Exception ex) {
		   ex.printStackTrace();
		   Map<String,Object> res = new HashMap<>();
		   res.put("success",false);
		   res.put("message","Server error while processing request");
		   return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(res);
	   }
	   
   }
   
   public Object deleteDepartment(String id) {
	   Optional<Department> deptOpt = this.deptRepository.findById(id);
	   if(deptOpt.isEmpty()) {
		   Map<String,Object> res = new HashMap<>();
		   res.put("success",false);
		   res.put("message","Invalid deptId");
		   
		   return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(res);
	   }
	   
	   Department dept = deptOpt.get(); 
	   try {
		   this.deptRepository.delete(dept);
		   Map<String,Object> res = new HashMap<>();
		   res.put("success",true);
		   res.put("message","Department deleted!");
		   
		   return ResponseEntity.status(HttpStatus.OK).body(res);
	   }catch(Exception ex) {
		   ex.printStackTrace();
		   Map<String,Object> res = new HashMap<>();
		   res.put("success",false);
		   res.put("message","Server error while processing request");
		   return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(res);
	   }
   }
}
