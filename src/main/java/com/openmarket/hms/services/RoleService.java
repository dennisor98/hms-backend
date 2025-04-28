package com.openmarket.hms.services;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.openmarket.hms.domain.Permission;
import com.openmarket.hms.domain.Role;
import com.openmarket.hms.domain.RolePermission;
import com.openmarket.hms.domain.User;
import com.openmarket.hms.repository.RolePermissionRepository;
import com.openmarket.hms.repository.RoleRepository;
import com.openmarket.hms.requestDto.RoleDto;



@Service
public class RoleService {
	@Autowired
	RoleRepository roleRepository;
	@Autowired
	RolePermissionRepository rolePermissionRepository;
  public Object createRole(RoleDto roleDto) {
	  User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	  Optional<Role> roleOpt =  this.roleRepository.findByName(roleDto.getName());
	  if(roleOpt.isPresent()) {
		  Map<String,Object> res = new HashMap<>();
		  res.put("success",false);
		  res.put("message","Role already exists");
		  
		  return ResponseEntity.status(HttpStatus.CONFLICT).body(res);
	  }
	  Role role = Role.builder().name(roleDto.getName()).description(roleDto.getName()).user(user).active(roleDto.getActive()).build();
	  try {
		  Map<String,Object> res = new HashMap<>();
		  res.put("success",true);
		  res.put("message","Role created!");
		  
		  return ResponseEntity.status(HttpStatus.OK).body(res);
	  }catch(Exception ex) {
		  ex.printStackTrace();
		  
		  Map<String,Object> res = new HashMap<>();
		  res.put("success",false);
		  res.put("message","Server error processing request");
		  
		  return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(res);
	  }
  }
  
  public Object editRole(String roleId,RoleDto roleDto) {
	  Optional<Role> roleOpt = this.roleRepository.findById(roleId);
	  if(roleOpt.isEmpty()) {
		  Map<String,Object> res = new HashMap<>();
		  res.put("success",false);
		  res.put("message","Invalid roleId");
		  return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(res);
	  }

	  Role role = roleOpt.get();
	  role.setName(roleDto.getName());
	  role.setDescription(roleDto.getDescription());
	  role.setActive(roleDto.getActive());

	  try {
		  this.roleRepository.save(role);
		  Map<String,Object> res = new HashMap<>();
		  res.put("success",true);
		  res.put("message","Role modified!");

		  return ResponseEntity.status(HttpStatus.OK).body(res);
	  }catch(Exception ex) {
		  ex.printStackTrace();
		  Map<String,Object> res = new HashMap<>();
		  res.put("success",false);
		  res.put("message","Server error in processing request");

		  return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(res);

	  }
  }
  
  public void createSuperRole() {
	  Optional<Role> roleOpt = this.roleRepository.findByName("SUPER_ADMIN");
	  if(roleOpt.isEmpty()) {
		  var role = Role.builder().name("SUPER_ADMIN").description("Has all permissions in the system and can perform any action").active(true).build();
		  this.roleRepository.save(role);
	  }
  }

  public void assignPermissionsNotAssignedToSuperRole(Role role,Permission permission) {
	  Optional<RolePermission> rolepermopt =  this.rolePermissionRepository.findByRoleAndPermission(role, permission);
	  if(rolepermopt.isEmpty()) {
		  var roleperm = RolePermission.builder().role(role).permission(permission).user(null).build();
		  try {
			  this.rolePermissionRepository.save(roleperm);
		  }catch(Exception ex) {
			  ex.printStackTrace();
		  }
	  }
  }
  

}
