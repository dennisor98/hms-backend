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
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InventoryItem extends BaseHmsDomain implements Serializable {

	private static final long serialVersionUID = -8261767560822826168L;
	
	@Column()
	private String name;
	
	@Column()
	private String description;
	
	@Column()
	private String skuNumber;
	
	@Column()
	private Double buyingPrice;
	
	@Column()
	private Double sellingPrice;
	
	@Column()
	private String imageName;
	
	@OneToOne()
	@JoinColumn(name="category_id")
	private ItemCategory category;
	
}
