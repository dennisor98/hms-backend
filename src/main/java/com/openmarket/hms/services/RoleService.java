package com.openmarket.hms.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openmarket.hms.domain.Permission;
import com.openmarket.hms.domain.Role;
import com.openmarket.hms.domain.RolePermission;
import com.openmarket.hms.repository.RolePermissionRepository;
import com.openmarket.hms.repository.RoleRepository;



@Service
public class RoleService {
	@Autowired
	RoleRepository roleRepository;
	@Autowired
	RolePermissionRepository rolePermissionRepository;
  public Object createRole() {
	  return null;
  }
  
  public void createSuperRole() {
	  Optional<Role> roleOpt = this.roleRepository.findByName("SUPER_ADMIN");
	  if(roleOpt.isEmpty()) {
		  var role = Role.builder().name("SUPER_ADMIN").description("Has all permissions in the system and can perform any action").build();
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
