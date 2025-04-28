package com.openmarket.hms.domain;

import java.io.Serializable;

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
public class MedService extends BaseHmsDomain implements Serializable{

	private static final long serialVersionUID = 2838019303524731505L;
	
	@Column()
	String name;
	
	@Column()
	String description;
	
	@Column()
	Double  cost;

}
