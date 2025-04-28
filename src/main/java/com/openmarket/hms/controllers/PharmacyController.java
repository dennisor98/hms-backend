package com.openmarket.hms.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.openmarket.hms.annotations.CustomController;
import com.openmarket.hms.requestDto.PharmacyReqDto;
import com.openmarket.hms.services.PharmacyService;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@CustomController
@RequestMapping("pharm")
@Tag(name="Pharmacy")
public class PharmacyController {
	@Autowired
	private PharmacyService pharmService;
   @PostMapping("/prescription")
   public Object createPrescription(@Valid @RequestBody PharmacyReqDto req) {
	 return this.pharmService.createPharmacyRequest(req);   
   }
   
   @GetMapping("prescriptions")
   public Object getPrescriptions(
		   @RequestParam(name="pageNumber") Integer pageNumber,
		   @RequestParam(name="pageSize") Integer pageSize
		   ) {
	   if(pageSize > 200) {
		   pageSize = 200;
	   }
	   
	   PageRequest page  =  PageRequest.of(pageNumber,pageSize,Sort.Direction.ASC,"createdAt");
	  return this.pharmService.getPharmacyPrescriptions(page);
   }
   
   
   @GetMapping("session/prescription")
   public Object getPrescriptionBySession(
		   @RequestParam(name="sessionId") String sessionId
		   ) {
	   return this.pharmService.getPatientPrescriptionBySession(sessionId);
   }
   
}
