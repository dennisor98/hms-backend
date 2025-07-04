package com.openmarket.hms.beans.queue;

import java.util.Comparator;
import java.util.PriorityQueue;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.openmarket.hms.domain.Consultation;
import com.openmarket.hms.domain.LabTestRequest;
import com.openmarket.hms.domain.PatientAdmissionRequest;
import com.openmarket.hms.domain.Triage;



@Configuration
public class QueuesConfig {
   
  @Bean
   PriorityQueue<Consultation> consultationQueue() {
      return new PriorityQueue<>(Comparator.comparingLong(Consultation::getPriority));
  }
  
  @Bean
  PriorityQueue<Triage> triageQueue(){
	  return new PriorityQueue<>(Comparator.comparingLong(Triage::getPriority));
  }
    
  @Bean
  PriorityQueue<LabTestRequest> labQueue(){
	  return new PriorityQueue<>(Comparator.comparingLong(LabTestRequest::getPriority));
  }
  
  @Bean
  PriorityQueue<PatientAdmissionRequest> admissionQueue(){
	  return new PriorityQueue<>(Comparator.comparingLong(PatientAdmissionRequest::getPriority));
  }
}
