package com.openmarket.hms.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.openmarket.hms.domain.Role;

public interface RoleRepository extends JpaRepository<Role,String>{
  Optional<Role> findByName(String name);
}
