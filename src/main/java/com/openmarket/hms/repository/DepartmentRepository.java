package com.openmarket.hms.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.openmarket.hms.domain.Department;

public interface DepartmentRepository extends JpaRepository<Department,String> {
   Optional<Department> findByName(String name);
}
