package com.openmarket.hms.services;

import java.util.ArrayList;
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

import com.openmarket.hms.domain.InventoryItem;
import com.openmarket.hms.domain.PatientSession;
import com.openmarket.hms.domain.PharmacyPrescription;
import com.openmarket.hms.domain.User;
import com.openmarket.hms.repository.InventoryItemRepository;
import com.openmarket.hms.repository.PatientSessionRepository;
import com.openmarket.hms.repository.PharmacyPrescriptionRepository;
import com.openmarket.hms.requestDto.PharmacyReqDto;

@Service
public class PharmacyService {
	@Autowired
	PatientSessionRepository patSessionRepository;
	@Autowired
	InventoryItemRepository inventoryRepository;
	@Autowired
	PharmacyPrescriptionRepository pharmacyprescRepository;
	
  public Object createPharmacyRequest(PharmacyReqDto req) {
	  Optional<PatientSession> patSesOpt = this.patSessionRepository.findById(req.getSessionId());
	  User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	  if(patSesOpt.isEmpty()) {
		  Map<String,Object> res = new HashMap<>();
		  res.put("success",false);
		  res.put("message","Invalid patient session");
		  
		  return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(res);
	  }
	  
	  PatientSession session = patSesOpt.get();
	  if(!session.getIsActive()) {
		  Map<String,Object> res = new HashMap<>();
		  res.put("success",false);
		  res.put("message","Patient not in session");
		  
		  return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(res);
	  }
	  
	  List<InventoryItem> items = new ArrayList<>();
	  
	  List<String> itemIds = req.getItems(); 
	  if(itemIds.size() > 0) {
		  itemIds.stream()
		  .map((id)->{
			  Optional<InventoryItem> itemOpt = this.inventoryRepository.findById(id);
			  if(itemOpt.isPresent()) {
				  InventoryItem item = itemOpt.get();
				 return items.add(item);
			  }
			  return null;
		  }).collect(Collectors.toList());
		 
	  }
	  
	  if(!items.isEmpty()) {
		  try {
			  PharmacyPrescription	presc =  PharmacyPrescription.builder().cancelled(false).items(items).processed(false).session(session).prescriber(user).build();
			  Map<String,Object> res = new HashMap<>();
			  res.put("success",true);
			  res.put("message","Prescription added");
			  
			  this.pharmacyprescRepository.save(presc);
			  return ResponseEntity.status(HttpStatus.OK).body(res);
		  }catch(Exception ex) {
			  Map<String,Object> res = new HashMap<>();
			  res.put("success",false);
			  res.put("message","Oops!Something went wrong!");
			  ex.printStackTrace();
			  
			  return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(res);
		  }
	  }
	  return null;
  }
  
  public Object getPharmacyPrescriptions(Pageable pageable) {
	  try {
		  Page<PharmacyPrescription> prescList = this.pharmacyprescRepository.findAll(pageable);
		  var prescs =  prescList.stream()
				  .map((p)->{
					 Map<String,Object> map = new HashMap<>(); 
					 var session = p.getSession();
					 var patient = session !=null ? session.getPatient(): null;
					 
					 Map<String,Object> pmap =  new HashMap<>();
					 pmap.put("id",patient.getId());
					 pmap.put("name",patient.getFirstName() + " "+ patient.getLastName());
					 pmap.put("phone",patient.getMobileNumber());
					 
					 map.put("id",p.getId());
					 map.put("sessionId",p.getSession() !=null ? p.getSession().getId() : null);
					 map.put("items",p.getItems());
					 map.put("patient",pmap);
					 return map;
				  }).collect(Collectors.toList());
		  Map<String,Object> res = new HashMap<>();
		  res.put("success",true);
		  res.put("message","Request complete");
		  res.put("prescs",prescs);
		  
		  return ResponseEntity.status(HttpStatus.OK).body(res);
	  }catch(Exception ex) {
		  ex.printStackTrace();
		  Map<String,Object> res = new HashMap<>();
		  res.put("success",false);
		  res.put("message","Oops!Something went wrong");
		  
		  return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(res);
	  }
  }
  public Object getPatientPrescriptionBySession(String sessionId) {
	  try {
		  Optional<PatientSession> patSessOpt = this.patSessionRepository.findById(sessionId);
		  if(patSessOpt.isEmpty()) {
			  Map<String,Object> res = new HashMap<>();
			  res.put("success", false);
			  res.put("message","Invalid session");
			  
			  return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(res);
		  }
		  
		  PatientSession session = patSessOpt.get();
		  Optional<PharmacyPrescription> prescOpt =  this.pharmacyprescRepository.findBySession(session);
		  if(prescOpt.isPresent()) {
			  Map<String,Object> res = new HashMap<>();
			  res.put("success", true);
			  res.put("message","Request succesful");
			  res.put("presc", prescOpt.get());
			  return ResponseEntity.status(HttpStatus.OK).body(res);
		  }
		  Map<String,Object> res = new HashMap<>();
		  res.put("success", true);
		  res.put("message","Request succesful");
		  res.put("presc",null);
		  
		 return ResponseEntity.status(HttpStatus.OK).body(res);
	  
	  }catch(Exception ex) {
		  ex.printStackTrace();
		  Map<String,Object> res = new HashMap<>();
		  res.put("success", false);
		  res.put("message","Oops! Server error!");
		  
		  return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(res);
	  }
  }
  
  public Object editPrescription(String prescriptionId,List<String> items) {
	  Optional<PharmacyPrescription> prescOpt = this.pharmacyprescRepository.findById(prescriptionId);
	  if(prescOpt.isEmpty()) {
		  return ResponseEntity.status(HttpStatus.BAD_REQUEST)
				  .body(Map.of("success",false,"message","Invalid prescription"));
	  }
	  PharmacyPrescription presc =  prescOpt.get();
	  if(presc.getProcessed()) {
		  return ResponseEntity.status(HttpStatus.BAD_REQUEST)
				  .body(Map.of("success",false,"message","Prescription already processed"));  
	  }
	  
	  
	  
	  
	  
	  
	  return null;
  }
}
