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
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class StockMovement extends BaseHmsDomain implements Serializable{

	private static final long serialVersionUID = -8526133168356821929L;
	
	@ManyToOne()
	@JoinColumn(name="item_id")
	InventoryItem item;
	
	@ManyToOne()
	@JoinColumn(name="session_id",nullable=true)
	PatientSession session;
	
	@ManyToOne()
	@JoinColumn(name="user_id",nullable=false)
	User user;
	
	@Column()
	Integer quantity;
	
	@Column()
	Double price;
	
	@Column()
	Integer previousStock;
	
	@Column()
	Integer newStock;
	
	@Column()
	String description;

}
