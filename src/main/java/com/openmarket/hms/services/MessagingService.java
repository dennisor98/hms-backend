package com.openmarket.hms.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import com.openmarket.hms.domain.Consultation;
import com.openmarket.hms.domain.LabTestRequest;
import com.openmarket.hms.domain.Triage;


@Service
public class MessagingService {
	@Autowired
    private SimpMessagingTemplate messagingTemplate;
	
	public void emitTriageQueue(Triage triage) {
	    Map<String, Object> res = new HashMap<>();

	        Map<String, Object> sessionMap = Map.of(
	            "session_id", triage.getSession().getSessionId(),
	            "id", triage.getSession().getId()
	        );

	        var patient = triage.getPatient();
	        Map<String, Object> patientMap = Map.of(
	            "id", patient.getId(),
	            "phone", patient.getMobileNumber(),
	            "firstName", patient.getFirstName(),
	            "middleName", patient.getMiddleName(),
	            "lastName", patient.getLastName(),
	            "gender", patient.getGender(),
	            "residence", patient.getResidence()
	        );

	        Map<String, Object> triageMap = new HashMap<>();
	        triageMap.put("session", sessionMap);
	        triageMap.put("patient", patientMap);

	    res.put("payload", triageMap);

	    // Send to topic
	    messagingTemplate.convertAndSend("/topic/triage/queue", res);
	}
	
	public void emitConsulatationQueue(Consultation consultation) {
		Map<String, Object> res = new HashMap<>();

		Map<String, Object> sessionMap = Map.of(
				"session_id", consultation.getSession().getSessionId(),
				"id", consultation.getSession().getId()
				);

		var patient = consultation.getSession().getPatient();
		Map<String, Object> patientMap = Map.of(
				"id", patient.getId(),
				"phone", patient.getMobileNumber(),
				"firstName", patient.getFirstName(),
				"middleName", patient.getMiddleName(),
				"lastName", patient.getLastName(),
				"gender", patient.getGender(),
				"residence", patient.getResidence()
				);

		Map<String, Object> triageMap = new HashMap<>();
		triageMap.put("session", sessionMap);
		triageMap.put("patient", patientMap);

		res.put("payload", triageMap);

		// Send to topic
		messagingTemplate.convertAndSend("/topic/consultation/queue", res);
	}
	
	public void emitLabRequestQueue(LabTestRequest req) {
		messagingTemplate.convertAndSend("/topic/consultation/queue", req);
	}

  
}
