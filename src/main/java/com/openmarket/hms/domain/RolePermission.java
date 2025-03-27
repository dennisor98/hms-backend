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
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RolePermission extends BaseHmsDomain implements Serializable{
	private static final long serialVersionUID = -7485848387695804412L;

	@ManyToOne()
	@JoinColumn(name="role_id")
	Role role;
	
	@ManyToOne()
	@JoinColumn(name="permission_id")
	Permission permission;
	
	@ManyToOne()
	@JoinColumn(name="creator_user_id",nullable=true)
	User user;

}
