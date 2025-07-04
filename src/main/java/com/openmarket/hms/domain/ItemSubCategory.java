package com.openmarket.hms.domain;

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
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ItemSubCategory extends BaseHmsDomain {
   @Column(nullable=false)
   private String name;
   
   @ManyToOne()
   @JoinColumn(name="parent_id",nullable=false)
   private ItemCategory parentCategory;
   
   @Column()
   private String description; 
}
