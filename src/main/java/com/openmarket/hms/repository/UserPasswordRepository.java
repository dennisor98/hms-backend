package com.openmarket.hms.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.openmarket.hms.domain.User;
import com.openmarket.hms.domain.UserPassword;

public interface UserPasswordRepository extends JpaRepository<UserPassword,String> {
  Optional<UserPassword> findByUser(User user);
}
