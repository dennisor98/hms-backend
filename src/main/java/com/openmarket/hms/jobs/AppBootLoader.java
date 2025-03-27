package com.openmarket.hms.jobs;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.openmarket.hms.constants.GlobalPermissionsContants;
import com.openmarket.hms.domain.Permission;
import com.openmarket.hms.domain.Role;
import com.openmarket.hms.domain.User;
import com.openmarket.hms.domain.UserRole;
import com.openmarket.hms.repository.RolePermissionRepository;
import com.openmarket.hms.repository.RoleRepository;
import com.openmarket.hms.repository.UserRoleRepository;
import com.openmarket.hms.services.PermissionService;
import com.openmarket.hms.services.RoleService;
import com.openmarket.hms.services.UserService;


@Component
public class AppBootLoader implements ApplicationListener<ApplicationReadyEvent> {
	@Autowired
	RoleService roleService;
	@Autowired
	UserService userService;
	@Autowired
	RoleRepository roleRepository;
	@Autowired
	RolePermissionRepository rolePermissionRepository;
	@Autowired
	UserRoleRepository userRoleRepository;
	@Autowired
	PermissionService permissionService;
//	@Autowired
//	MessagingService msgService;	
	@Override
	@Transactional
	public void onApplicationEvent(ApplicationReadyEvent event) {
		this.userService.createSuperUser("+254700000000");
      //create super admin role
		this.roleService.createSuperRole();
		
		var permissions = GlobalPermissionsContants.scan();

		permissions.forEach((permissionKey, permissionDetails) -> {
		    // Create the Permission object using builder pattern and include description and category
		    var permission = Permission.builder()
		                               .name(permissionDetails.getPermission())  // Using permission as name
		                               .description(permissionDetails.getDescription())  // Description
		                               .category(permissionDetails.getCategory())  // Category
		                               .build();

		    // Insert or update the permission
		    this.permissionService.insertPermissionIfNotExistsOrUpdateDescription(permission);
		});

		
		Optional<Role> superRoleOpt =  this.roleRepository.findByName("SUPER_ADMIN");
		if(superRoleOpt.isPresent()) {
			
			var role = superRoleOpt.get();
		Optional<User> userOpt =  this.userService.findUserByPhone("+254700000000");
		if(userOpt.isPresent()) {
			var user = userOpt.get();
			var userRole =  UserRole.builder().role(role).user(userOpt.get()).creator(user).build();
			Optional<UserRole> userRoleOpt =  this.userRoleRepository.findByUserAndRole(user,role);
			if(userRoleOpt.isEmpty()) {
				try {
					this.userRoleRepository.save(userRole);
				}catch(Exception ex) {
					ex.printStackTrace();
				}
			}
		}
			List<Permission> perms = this.permissionService.getAllPermissions();
			perms.forEach(p->{
				this.roleService.assignPermissionsNotAssignedToSuperRole(role, p);
			});
		}


	}
}
