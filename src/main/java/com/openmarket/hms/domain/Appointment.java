package com.openmarket.hms.domain;

import java.io.Serializable;

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
public class Appointment extends BaseHmsDomain implements Serializable{
	private static final long serialVersionUID = -7345558772513679483L;
	
   @ManyToOne()
   @JoinColumn(name="doctor_user_id")
   User doctor;
   
   @ManyToOne()
   @JoinColumn(name="service_id")
   MedService service;
}
