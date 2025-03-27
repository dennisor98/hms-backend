package com.openmarket.hms.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.openmarket.hms.domain.Permission;

public interface PermissionRepository extends JpaRepository<Permission,String> {
	Optional<Permission> findByName(String name);

}
