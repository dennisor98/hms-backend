package com.openmarket.hms.domain;

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
public class ItemCategory extends BaseHmsDomain{
    @Column()
    private String name;
    
    @Column(nullable=true)
    private String tag;
    
    @Column()
    private String description;
    
    
}
