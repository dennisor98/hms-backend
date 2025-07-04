package com.openmarket.hms.domain;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
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
public class Triage  extends BaseHmsDomain implements Serializable{
private static final long serialVersionUID = -3794172061482160917L;

@OneToOne()
@JoinColumn(name="session_id")
PatientSession session;

@ManyToOne()
@JoinColumn(name="patient_id")
Patient patient;

@Column(nullable=true)
Double weight;

@Column(nullable=true)
Double height;

@Column(nullable=true)
Double temp;

@Column(nullable=true)
Double bmi;

@Column
Double bp;

@Column(nullable=false,columnDefinition = "BOOLEAN DEFAULT FALSE")
Boolean isComplete;

long priority;





}
