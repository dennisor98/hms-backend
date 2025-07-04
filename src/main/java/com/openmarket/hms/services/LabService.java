package com.openmarket.hms.services;

import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.openmarket.hms.domain.LabTestRequest;
import com.openmarket.hms.domain.PatientSession;
import com.openmarket.hms.repository.LabTestRepository;
import com.openmarket.hms.repository.PatientSessionRepository;

@Service
public class LabService {
	@Autowired
	private PatientSessionRepository sessRepository;
	@Autowired
	private LabTestRepository labRepository;
  public Object getTestRequestsBySession(String sessionId) {
	  Optional<PatientSession> sessOpt = this.sessRepository.findById(sessionId);
	  
	  if(sessOpt.isEmpty()) {
		  return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
				  Map.of(
						  "success",false,
						  "message","Invalid session"
						  )
				  );
	  }
	  
	  PatientSession session = sessOpt.get();
	  Optional<LabTestRequest> labReqOpt = this.labRepository.findBySession(session);
	  if(labReqOpt.isEmpty()) {
		  return Map.of(
				  "success",true,
				  "message","Request success",
				  "test",null
				  );
	  }
	  
	  LabTestRequest labTest = labReqOpt.get();
	  return Map.of(
			  "success",true,
			  "message","Request success",
			  "test",Map.of(
					  "result",labTest.getResult(),
					  "name",""
					  )
			  );
	  	  
  }
  
  public Object getTestRequests(Pageable page) {
	  Page<LabTestRequest> labReqPage = this.labRepository.findAll(page);
	  
	  var requests = labReqPage.stream()
			  .map((req) -> {
				 var map = Map.of(
						 "id",req.getId(),
						 "session_id",req.getSession().getId(),
						 "patientName",req.getPatient().getFirstName() + " " + req.getPatient().getLastName(),
						 "patient_id",req.getPatient().getId()	 
						 );
				
				 return map;
			  });
	  
	  
	  return null;
  }
}
