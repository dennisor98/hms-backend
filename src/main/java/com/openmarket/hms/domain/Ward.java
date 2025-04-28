package com.openmarket.hms.domain;

import java.io.Serializable;

import com.openmarket.hms.enums.GenderType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Ward extends  BaseHmsDomain implements Serializable{

	private static final long serialVersionUID = 1177985131800307228L;
	
	@Column()
	String  name;
	
	@Column()
	Integer capacity;
	
	@Column()
	GenderType gender;
}
