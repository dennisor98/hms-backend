package com.openmarket.hms.domain;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity
public class PatientSession extends BaseHmsDomain{
  @Column()
  String sessionId;
  
 @Column()
 Boolean isActive;
 
 @ManyToOne()
 @JoinColumn(name="patient_id",nullable=false)
 Patient patient;
 
 
}
