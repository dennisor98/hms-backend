package com.openmarket.hms.domain;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;


@Data
@Entity
public class LabTestRequest extends BaseHmsDomain implements Serializable{
	private static final long serialVersionUID = -4168085506481401072L;

	@ManyToOne()
	@JoinColumn(name="requester_id")
	User requestor;
	
	@ManyToOne()
	@JoinColumn(name="patient_id")
	Patient patient;
	
	@ManyToOne()
	@JoinColumn(name="patient_session_id")
	Patient session;
	
	@ManyToOne()
	@JoinColumn(name="operator_id")
	User operator;
	
	@Column()
	Boolean isComplete;
	
	@Column()
	String result;
	
}
