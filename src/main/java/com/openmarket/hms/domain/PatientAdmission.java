package com.openmarket.hms.domain;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PatientAdmission extends BaseHmsDomain implements Serializable {

	private static final long serialVersionUID = 607673498008271074L;
	
	@ManyToOne()
	@JoinColumn(name="patient_id")
	Patient patient;
	
	@ManyToOne()
	@JoinColumn(name="ward_id")
    Ward ward;
	
	@ManyToOne()
	@JoinColumn(name="admitter_user_id")
	User admittor;
	
	@ManyToOne()
	@JoinColumn(name="session_id")
	PatientSession session;
	
	@Column
	Boolean discharged;
	
	
	@Column()
	String admissionNotes;
	
	@Column()
	String dischargeNotes;
	
	
}
