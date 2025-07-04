package com.openmarket.hms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.openmarket.hms.domain.Triage;

public interface TriageRepository extends JpaRepository<Triage,String>{
   
}
