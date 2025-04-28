package com.openmarket.hms.domain;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Stock extends  BaseHmsDomain implements Serializable{

	private static final long serialVersionUID = 5563099323102626235L;
	
	@OneToOne
	@JoinColumn(name="item_id")
	InventoryItem item;
	
	@Column()
	Integer availableQuantity;
	
	@Column()
	Integer reservedQuantity;
	
	
	
	
	

}
