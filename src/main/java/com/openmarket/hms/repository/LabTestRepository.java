package com.openmarket.hms.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.openmarket.hms.domain.LabTestRequest;
import com.openmarket.hms.domain.PatientSession;

public interface LabTestRepository extends JpaRepository<LabTestRequest,String>{
  Optional<LabTestRequest> findBySession(PatientSession session);
  
}
