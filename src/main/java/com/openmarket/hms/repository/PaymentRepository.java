package com.openmarket.hms.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.openmarket.hms.domain.PatientSession;
import com.openmarket.hms.domain.Payment;

public interface PaymentRepository extends JpaRepository <Payment,String>{
  Optional<Payment> findBySession(PatientSession session);
}
