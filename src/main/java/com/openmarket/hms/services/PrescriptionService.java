package com.openmarket.hms.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.openmarket.hms.domain.PatientSession;
import com.openmarket.hms.domain.PharmacyPrescription;
import com.openmarket.hms.repository.PatientSessionRepository;
import com.openmarket.hms.repository.PharmacyPrescriptionRepository;

@Service
public class PrescriptionService {
	@Autowired
	private PharmacyPrescriptionRepository prescRepository;
	@Autowired
	private PatientSessionRepository sessionRepository;

	public Object getPrescriptionBySessionId(String sessionId) {
		Optional<PatientSession> sessOpt = this.sessionRepository.findById(sessionId);
		if(sessOpt.isEmpty()) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
					Map.of(
							"success",false,
							"message","Session not found"
							)
					);
		}
		PatientSession session = sessOpt.get();
		Optional<PharmacyPrescription> prescOpt = this.prescRepository.findBySession(session);
		if(prescOpt.isEmpty()) {
			return ResponseEntity.status(HttpStatus.OK).body(
					Map.of(
							"success",true,
							"message","Request success",
							"prescriptions",new ArrayList<>()
							)
					);

		}

		PharmacyPrescription presc = prescOpt.get();

		var items = presc.getItems();
		var itemsData =  items.stream()
				.map((i)->{
					var cost = 0;
					cost +=i.getSellingPrice();
					return Map.of(
							"id",i.getId(),
							"name",i.getName(),
							"skuNumber",i.getSkuNumber(),
							"description",i.getDescription(),
							"cost",cost
							);
				});
		Map<String,Object> res = new HashMap<>();
		res.put("success",true);
		res.put("message","Request success");
		res.put("prescription",itemsData);

		return ResponseEntity.status(HttpStatus.OK).body(res);
	}
}
