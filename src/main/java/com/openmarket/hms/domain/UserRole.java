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
public class UserRole extends BaseHmsDomain implements Serializable{
  private static final long serialVersionUID = 905694600992207892L;

@ManyToOne()
  @JoinColumn(name="role_id")
  Role role;
  
  @ManyToOne()
  @JoinColumn(name="user_id")
  User user;
  
  @ManyToOne()
  @JoinColumn(name="creator_user_id")
  User creator;
  
}
