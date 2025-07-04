package com.openmarket.hms.domain;

import java.io.Serializable;
import java.util.Date;

import com.openmarket.hms.enums.GenderType;
import com.openmarket.hms.enums.MaritalStatusType;

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
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PatientAdmissionRequest extends BaseHmsDomain implements Serializable{

	private static final long serialVersionUID = -5282245473970298540L;
	
	@ManyToOne()
	@JoinColumn()
	Patient patient;
	
	@Column()
	Boolean accepted;
	
	@Column()
	Boolean rejected;
	
	long priority;
	
	

}
