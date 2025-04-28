package com.openmarket.hms.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.openmarket.hms.domain.Patient;

public interface PatientRepository extends JpaRepository<Patient,String> {
  Optional<Patient> findByMobileNumber(String mobileNumber);
  @Query("SELECT p FROM Patient p WHERE p.firstName LIKE %:term% OR p.middleName LIKE %:term% OR p.mobileNumber LIKE %:term% OR p.lastName LIKE %:term%")
  List<Patient> searchPatient(@Param("term") String term);
}
