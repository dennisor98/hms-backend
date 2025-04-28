package com.openmarket.hms.domain;

import java.io.Serializable;
import java.util.Date;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

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
   
   @Column()
   GenderType gender;
   
   @Column
   Date dob;
   
   @Column()
   MaritalStatusType maritalStatus;

   @ManyToOne
   @JoinColumn(name = "creator_user_id", nullable = true)
   @OnDelete(action = OnDeleteAction.SET_NULL) 
   private User creator;

   
}
