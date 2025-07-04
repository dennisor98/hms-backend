package com.openmarket.hms.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PatientSession extends BaseHmsDomain{
	@Column()
	String sessionId;

	@Column()
	Boolean isActive;

	@ManyToOne()
	@JoinColumn(name="patient_id",nullable=false)
	Patient patient;

	@ManyToOne()
	@JoinColumn(name="initiator_id",nullable=false)
	User initiatedBy;

	@ManyToOne()
	@JoinColumn(name="ender_id",nullable=true)
	User endedBy;


	@Column()
	Double consultationFee;

	@Column()
	Double totalFee;


}
