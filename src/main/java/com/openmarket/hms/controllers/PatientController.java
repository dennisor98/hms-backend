package com.openmarket.hms.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.openmarket.hms.annotations.CustomController;
import com.openmarket.hms.requestDto.PatientDto;
import com.openmarket.hms.requestDto.PatientSessionDto;
import com.openmarket.hms.services.PatientService;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@CustomController
@RequestMapping("patient")
@Tag(name="Patient")
public class PatientController {
	@Autowired
	private PatientService patientService;
	@PostMapping()
	public Object addPatient(@Valid @RequestBody PatientDto ptdto) {
		return this.patientService.createPatient(ptdto);
	}
	
	@PutMapping()
	public Object editPatient(
			@RequestParam(name="patient_id") String patientId,
			@Valid @RequestBody PatientDto ptdto
			) {
		return this.patientService.editPatient(patientId, ptdto);
	}
	
	@GetMapping()
	public Object getPatients(
			@RequestParam(name="pageNumber") Integer pageNumber,
			@RequestParam(name="pageSize") Integer pageSize
			) {
		if(pageSize > 200) {
			   pageSize = 200;
		   }
		   
		   PageRequest page  =  PageRequest.of(pageNumber,pageSize,Sort.Direction.ASC,"createdAt");
		return this.patientService.getPatients(page);
	}
	
	@GetMapping("/search")
	public Object searchPatient(
			@RequestParam(name="searchString") String searchTerm
			) {
		return this.patientService.searchPatient(searchTerm);
	}
	
	@PostMapping("/session/activate")
	public Object createPatientSession(
			@RequestParam("patientId") String patientId
			) {
		return this.patientService.addPatientToSession(patientId);
		
	}
	
	@PostMapping("/session/add")
	public Object activatePatientSession(@Valid @RequestBody  PatientSessionDto req) {
		return this.patientService.activatePatientSession(req);
		
	}
	
	@PutMapping("/session/end")
	public Object endPatientSession(
			@RequestParam("sessionId") String sessionId
			) {
		return this.patientService.endPatientSession(sessionId);
		
	}

}
