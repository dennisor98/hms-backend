package com.openmarket.hms.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openmarket.hms.domain.Permission;
import com.openmarket.hms.repository.PermissionRepository;


@Service
public class PermissionService {
	@Autowired
	PermissionRepository permissionRepository;
	 public void insertPermissionIfNotExistsOrUpdateDescription(Permission permission) {
		   Optional<Permission> permOpt = this.permissionRepository.findByName(permission.getName());
		   
		   if(permOpt.isEmpty()) {
			   this.permissionRepository.save(permission);
		   }else {
			   var perm = permOpt.get();
			   perm.setDescription(permission.getDescription());
			   this.permissionRepository.save(perm);
		   }
	   }
	   
	   public List<Permission> getAllPermissions() {
		   return this.permissionRepository.findAll();
	   }
}
