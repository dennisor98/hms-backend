package com.openmarket.hms.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.openmarket.hms.domain.Role;
import com.openmarket.hms.domain.User;
import com.openmarket.hms.domain.UserRole;

public interface UserRoleRepository extends JpaRepository<UserRole,String>{

	Optional<UserRole> findByUserAndRole(User user, Role role);

}
