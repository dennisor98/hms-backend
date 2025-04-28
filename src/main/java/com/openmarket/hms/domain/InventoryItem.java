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
public class InventoryItem extends BaseHmsDomain implements Serializable {

	private static final long serialVersionUID = -8261767560822826168L;
	
	@Column()
	String name;
	
	@Column()
	String description;
	
	@Column()
	String skuNumber;
	
}
