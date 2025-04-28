package com.openmarket.hms.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.openmarket.hms.domain.PatientSession;
import com.openmarket.hms.domain.PharmacyPrescription;

public interface PharmacyPrescriptionRepository extends JpaRepository<PharmacyPrescription,String>{
	Optional<PharmacyPrescription> findBySession(PatientSession sessiom);

}
