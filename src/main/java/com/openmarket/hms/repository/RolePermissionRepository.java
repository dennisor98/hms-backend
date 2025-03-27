package com.openmarket.hms.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.openmarket.hms.domain.Permission;
import com.openmarket.hms.domain.Role;
import com.openmarket.hms.domain.RolePermission;

public interface RolePermissionRepository  extends JpaRepository<RolePermission,String>{

	Optional<RolePermission> findByRoleAndPermission(Role role, Permission permission);

}
