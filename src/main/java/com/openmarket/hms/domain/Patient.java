package com.openmarket.hms.domain;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Data;

@Data
@Entity
public class Patient extends BaseHmsDomain implements Serializable{
   private static final long serialVersionUID = -4855739893453316162L;

@Column(nullable=false)
   String firstName;
   
   @Column(nullable=true)
   String middleName;
   
   @Column(nullable=false)
   String lastName;
   
   @Column(nullable=false,unique=true)
   String mobileNumber;
   
   @Column()
   String email;
   
   @Column()
   String residence;
   
   @Column()
   Boolean isActive;
   
}
