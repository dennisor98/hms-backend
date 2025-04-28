package com.openmarket.hms.domain;

import java.io.Serializable;

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
public class Role extends BaseHmsDomain implements Serializable{
	private static final long serialVersionUID = -3520584602128565523L;

	@Column(nullable=false)
	String name;
	
	@Column(nullable=false)
	String description;
	
	@Column(nullable=false)
    Boolean active;
	
	@ManyToOne()
	@JoinColumn(name="user_creator_id")
	User user;

}
