package com.openmarket.hms.domain;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Data;

@Data
@Entity
public class LabTestType extends BaseHmsDomain  implements Serializable {
  private static final long serialVersionUID = -5793749393283131507L;

@Column()
  String name;
  
  @Column()
  String description;
  
  @Column()
  String procedureDesc;
  
  @Column()
  Integer cost;
  
}
