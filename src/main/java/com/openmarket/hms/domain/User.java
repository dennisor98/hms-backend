package com.openmarket.hms.domain;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User extends BaseHmsDomain implements UserDetails,Serializable {
   private static final long serialVersionUID = 671076748891562002L;

@Column(nullable=false)	
   String firstName;

   @Column(nullable=true)
   String middleName;
   
   @Column(nullable=false)
   String lastName;
   
   @Column(nullable=false)
   String mobileNumber;
   
   @Column(nullable=false)
   String idNumber;
   
   @Column()
   Date dob;
   
   @Column(nullable=false)
   String email;
   
   @Column(nullable=false)
   Boolean isActive;
   
   @ManyToOne()
   @JoinColumn(name="department_id",nullable=true)
   Department department;
   
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return false;
	}

}
