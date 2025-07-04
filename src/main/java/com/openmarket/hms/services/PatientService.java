package com.openmarket.hms.services;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.openmarket.hms.beans.AdvancedUniqueKeyGenerator;
import com.openmarket.hms.domain.Patient;
import com.openmarket.hms.domain.PatientSession;
import com.openmarket.hms.domain.Payment;
import com.openmarket.hms.domain.User;
import com.openmarket.hms.enums.GenderType;
import com.openmarket.hms.enums.MaritalStatusType;
import com.openmarket.hms.repository.PatientRepository;
import com.openmarket.hms.repository.PatientSessionRepository;
import com.openmarket.hms.repository.PaymentRepository;
import com.openmarket.hms.requestDto.PatientDto;
import com.openmarket.hms.requestDto.PatientSessionDto;

@Service
public class PatientService {
	@Autowired
	private PatientRepository patientRepository;
	@Autowired
	private PatientSessionRepository patSessRepository;
	@Autowired
	private PaymentRepository payRepository;
	
   public Object createPatient(PatientDto patientDto) {
	   GenderType gender = null;
	   
	   switch(patientDto.getGender()) {
	     case "1": {
	    	 gender = GenderType.MALE;
	     }
	     break;
	     case "2":{
	    	 gender =  GenderType.FEMALE;
	     }
	     break;
	     default:{
	    	 gender = GenderType.OTHER;
	     }
	   }
	   
	   MaritalStatusType maritalStatus =  null;
	   switch(patientDto.getMaritalStatus()) {
	     case "1": {
	    	 maritalStatus = MaritalStatusType.SINGLE;
	     }
	     break;
	     case "2":{
	    	maritalStatus =  MaritalStatusType.MARIED;
	     }
	     break;
	     case "3":{
		    	maritalStatus =  MaritalStatusType.LIVING_TOGETHER;
		     }
		     break;
	     case "4":{
		    	maritalStatus =  MaritalStatusType.DIVORCED;
		     }
		     break;
	     default:{
	    	 gender = GenderType.OTHER;
	     }
	   }
	   User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	   Optional<Patient> patientOpt = this.patientRepository.findByMobileNumber(patientDto.getMobileNumber());
	   if(patientOpt.isPresent()) {
		   Map<String,Object> res = new HashMap<>();
		   res.put("success",false);
		   res.put("message","Patient already registered");
		   
		   return ResponseEntity.status(HttpStatus.CONFLICT).body(res);
	   }
	   Patient patientBuild =  Patient.builder()
			   .firstName(patientDto.getFirstName())
			   .middleName(patientDto.getMiddleName())
			   .lastName(patientDto.getLastName())
			   .email(patientDto.getEmail())
			   .creator(user)
			   .gender(gender)
			   .maritalStatus(maritalStatus)
			   .mobileNumber(patientDto.getMobileNumber())
			   .residence(patientDto.getResidence())
			   .isActive(patientDto.getIsActive())
			   .build();
	   
	   try {
		   this.patientRepository.save(patientBuild);
		   Map<String,Object> res = new HashMap<>();
		   res.put("success",true);
		   res.put("message","patient created!");
		   
		   return ResponseEntity.status(HttpStatus.OK).body(res);
	   }catch(Exception ex) {
		   ex.printStackTrace();
		   Map<String,Object> res = new HashMap<>();
		   res.put("success",false);
		   res.put("message","Error processing the request");
		   
		   return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(res);
	   }
   }
   
   public Object getPatients(Pageable pageable) {
	   try {
		   Page<Patient> patientsPage = this.patientRepository.findAll(pageable);
		   var patients = patientsPage.stream()
				   .map((p)->{
					   Map<String,Object> map = new HashMap<>();
					   map.put("id",p.getId());
					   map.put("firstName", p.getFirstName());
					   map.put("lastName", p.getLastName());
					   map.put("mobileNumber", p.getMobileNumber());
					   map.put("email", p.getEmail());
					   map.put("gender",p.getGender().toString());
					   map.put("maritalStatus",p.getMaritalStatus().toString());
					   map.put("age","");
					   return map; 
				   }).collect(Collectors.toList()); 
		   Map<String,Object> res = new HashMap<>();
			  res.put("success", true);
			  res.put("message","Request success");
			  res.put("result",patients);
			  
			  return ResponseEntity.status(HttpStatus.OK).body(res);
	   }catch(Exception ex) {
		   Map<String,Object> res = new HashMap<>();
			  res.put("success", false);
			  res.put("message","Oops!Server error");
			  res.put("result",new ArrayList<>());
		   return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR);
	   }
	   
   }
   
   public Object searchPatient(String searchTerm) {
	   try {
		   List<Patient> ptList =  this.patientRepository.searchPatient(searchTerm);
		  var patients = ptList.stream()
                 .map((p)->{
                	  Map<String,Object> map = new HashMap<>();
                	  map.put("id",p.getId());
                	  map.put("firstName", p.getFirstName());
                	  map.put("lastName", p.getLastName());
                	  map.put("mobileNumber", p.getMobileNumber());
                	  map.put("email", p.getEmail());
                	  map.put("gender",p.getGender().toString());
                	  map.put("maritalStatus",p.getMaritalStatus().toString());
                	  map.put("age","");
                	return map; 
                 }).collect(Collectors.toList());
		   
		  Map<String,Object> res = new HashMap<>();
		  res.put("success", true);
		  res.put("message","Request success");
		  res.put("result",patients);
		  
		  return ResponseEntity.status(HttpStatus.OK).body(res);
	   }catch(Exception ex) {
		   ex.printStackTrace();
		   Map<String,Object> res = new HashMap<>();
			  res.put("success", false);
			  res.put("message","Oops!Server error");
			  res.put("result",new ArrayList<>());
		   return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR);
	   }
	  
   }
   
   public Object editPatient(String patientId,PatientDto patientDto) {
	   Optional<Patient> patientOpt =  this.patientRepository.findById(patientId);
	   if(patientOpt.isEmpty()) {
		   Map<String,Object> res = new HashMap<>();
		   res.put("success",false);
		   res.put("message","Invalid patientId");

		   return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(res);
	   }

	   Patient patient = patientOpt.get();
	   patient.setFirstName(patientDto.getFirstName());
	   patient.setMiddleName(patientDto.getMiddleName());
	   patient.setLastName(patientDto.getLastName());
	   patient.setEmail(patientDto.getEmail());
	   patient.setIsActive(patientDto.getIsActive());
	   patient.setResidence(patientDto.getResidence());

	   try {
		   this.patientRepository.save(patient);
		   Map<String,Object> res = new HashMap<>();
		   res.put("success",true);
		   res.put("message","Patient edited!");

		   return ResponseEntity.status(HttpStatus.OK).body(res);
	   }catch(Exception ex) {
		   ex.printStackTrace();
		   Map<String,Object> res = new HashMap<>();
		   res.put("success",false);
		   res.put("message","Error processing the request");

		   return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(res);
	   }
   }
   
   public Object deletePatient(String id) {
	   Optional<Patient> patOpt = this.patientRepository.findById(id);
	   if(patOpt.isEmpty()) {
		   Map<String,Object> res = new HashMap<>();
		   res.put("success",false);
		   res.put("message","Invalid patient");

		   return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(res);
	   }
	   
	   try {
		   this.patientRepository.delete(patOpt.get());
		   Map<String,Object> res = new HashMap<>();
		   res.put("success",true);
		   res.put("message","Record deleted");

		   return ResponseEntity.status(HttpStatus.OK).body(res);
	   }catch(Exception ex) {
		   Map<String,Object> res = new HashMap<>();
		   res.put("success",false);
		   res.put("message","Oops!Server error");

		   return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(res);
	   }
	   
   }
   
   
   public Object addPatientToSession(String patientId) {
	   User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	   Optional<Patient> patOpt = this.patientRepository.findById(patientId);
	   if(patOpt.isEmpty()) {
		   Map<String,Object> res = new HashMap<>();
		   res.put("success",false);
		   res.put("message","Invalid patient identity");

		   return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(res);
	   }
	   Patient patient =  patOpt.get();
	   
	   Optional<PatientSession> patSessOpt =  this.patSessRepository.findByPatientAndIsActiveTrue(patient);
	   if(patSessOpt.isPresent()) {
		   Map<String,Object> res = new HashMap<>();
		   res.put("success",false);
		   res.put("message","Patient already in session");

		   return ResponseEntity.status(HttpStatus.CONFLICT).body(res);
	   }
	   
	   PatientSession patSessionBuild =  PatientSession.builder()
			   .isActive(true).patient(patient).sessionId(AdvancedUniqueKeyGenerator.generateUniqueKey().toUpperCase()).initiatedBy(user)
			   .build();
	   try {
		   this.patSessRepository.save(patSessionBuild);
		   Map<String,Object> res = new HashMap<>();
		   res.put("success",true);
		   res.put("message","Patient added to session");

		   return ResponseEntity.status(HttpStatus.OK).body(res);
		   
	   }catch(Exception ex) {
		   Map<String,Object> res = new HashMap<>();
		   res.put("success",false);
		   res.put("message","Oops!Server error");

		   return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(res);   
	   }
	   
	  
   }
   
   public Object activatePatientSession(PatientSessionDto sesdto) {
	   Optional<PatientSession> patSesOpt =  this.patSessRepository.findById(sesdto.getSessionId());
	   if(patSesOpt.isEmpty()) {
		   Map<String,Object> res = new HashMap<>();
		   res.put("success",false);
		   res.put("message", "Invalid sessionId");
		   
		   return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(res);
	   }
	   
	   Optional<Patient> patOpt =  this.patientRepository.findById(sesdto.getPatientId());
	   
	   if(patOpt.isEmpty()) {
		   Map<String,Object> res = new HashMap<>();
		   res.put("success",false);
		   res.put("message", "Invalid patientId");

		   return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(res);
	   }
	   
	   PatientSession session = patSesOpt.get();
	   Patient patient = patOpt.get();
	   
	   Date updatedAt = session.getUpdatedAt();
	   Instant updatedAtInstant = updatedAt.toInstant();
	   Instant nowMinus24Hours = Instant.now().minus(24, ChronoUnit.HOURS);
	   
	   if (updatedAtInstant.isBefore(nowMinus24Hours)) {
		   Map<String,Object> res = new HashMap<>();
		   res.put("success",false);
		   res.put("message", "Invalid operation. Session is beyond 24 hours");

		   return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(res);
	   }
	   session.setIsActive(true);
	   
	   try {
		   this.patSessRepository.save(session);
		   Map<String,Object> res = new HashMap<>();
		   res.put("success",true);
		   res.put("message", "Session activated");

		   return ResponseEntity.status(HttpStatus.OK).body(res);
	   }catch(Exception ex) {
		   ex.printStackTrace();
		   Map<String,Object> res = new HashMap<>();
		   res.put("success",false);
		   res.put("message", "Oops! Server error!");

		   return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(res);
	   }
	   	   
   }
   
   
   public Object endPatientSession(String sessionId) {
	   Optional<PatientSession> patSesOpt = this.patSessRepository.findById(sessionId);
	   if(patSesOpt.isEmpty()) {
		   Map<String,Object> res = new HashMap<>();
		   res.put("success",false);
		   res.put("message", "Invalid session");

		   return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(res);
	   }
	   
	   PatientSession session = patSesOpt.get();
	   
	   Optional<Payment> payOpt =  this.payRepository.findBySession(session);
	   if(payOpt.isEmpty()) {
		   Map<String,Object> res = new HashMap<>();
		   res.put("success",false);
		   res.put("message", "Session is pending payment");

		   return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(res);
	   }
	   try {
		   session.setIsActive(false);
		   Map<String,Object> res = new HashMap<>();
		   res.put("success",true);
		   res.put("message", "Patient session ended");
		   this.patSessRepository.save(session);
		   return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(res);
	   }catch(Exception ex) {
		   ex.printStackTrace();
		   Map<String,Object> res = new HashMap<>();
		   res.put("success",false);
		   res.put("message", "Oops! Server error!");

		   return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(res);
	   }
	   	   
   }
   
   
   public Object getPatientsInSession() {
	   return null;
   }
   
   
    
}
