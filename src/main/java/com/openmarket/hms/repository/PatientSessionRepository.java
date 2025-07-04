package com.openmarket.hms.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.openmarket.hms.domain.Patient;
import com.openmarket.hms.domain.PatientSession;

public interface PatientSessionRepository extends JpaRepository<PatientSession,String> {
   Optional<PatientSession> findByPatientAndIsActiveTrue(Patient patient);
   
   List<PatientSession> findByPatient(Patient patient);
}
