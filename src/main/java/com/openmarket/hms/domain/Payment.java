package com.openmarket.hms.domain;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Data
@Entity
public class Payment extends BaseHmsDomain implements Serializable {
  @Column()
  String payerName;
  
  @Column()
  String payerNuumber;
  
  @Column()
  String checkoutReqId;
  
  @Column()
  Boolean completed;
  
  @Column()
  Boolean isSuccessful;
  
  @Column()
  String receiptNo;
  
  @Column()
  String source;
  
  @Column()
  String initiatedTime;
  
  @Column()
  String completedTime;
  
  @OneToOne()
  @JoinColumn(name="session_id",nullable=true)
  PatientSession session;
  
  
}
