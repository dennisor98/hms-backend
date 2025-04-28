package com.openmarket.hms.domain;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PharmacyPrescription extends BaseHmsDomain implements Serializable{

	private static final long serialVersionUID = -5031908963041224454L;
	
	@OneToOne()
	@JoinColumn(name="session_id")
	PatientSession session;
	
	@ManyToOne()
	@JoinColumn(name="presc_user_id")
    User prescriber;
	
	@Column()
	List<InventoryItem> items;
	
    @Column()
    Boolean processed;
    
    @Column()
    Boolean cancelled;
    
    @Column()
    Boolean voided;
}
