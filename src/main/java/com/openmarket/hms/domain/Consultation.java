package com.openmarket.hms.domain;

import java.io.Serializable;
import java.util.Date;

import com.openmarket.hms.enums.GenderType;
import com.openmarket.hms.enums.MaritalStatusType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Consultation extends BaseHmsDomain implements Serializable{

	private static final long serialVersionUID = 1727729819632313898L;
	
	@OneToOne()
	@JoinColumn(name="session_id")
	private PatientSession session;

	@ManyToOne()
	@JoinColumn(name="consultant_id")
	private User consultant;
	
	@Lob
	@Column(nullable=true)
	private String findings;
	
	@Lob
	@Column(nullable = true)
	private String summaryReport;

	@Column(nullable=false)
	private long priority;
}
