package com.openmarket.hms.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.openmarket.hms.domain.User;

public interface UserRepository extends JpaRepository<User,String>{
  Optional<User> findByMobileNumber(String mobileNumber);
}
