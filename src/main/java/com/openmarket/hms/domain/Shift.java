package com.openmarket.hms.domain;

import java.io.Serializable;
import java.time.LocalTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Shift extends BaseHmsDomain implements Serializable {

	private static final long serialVersionUID = -381229699171765805L;
	
	@Column()
	String name;
	
	 @Column(nullable = false)
	  private LocalTime startTime;
	 
	 @Column(nullable = false)
	 private LocalTime endTime;

}
