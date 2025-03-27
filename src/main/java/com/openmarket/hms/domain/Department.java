package com.openmarket.hms.domain;

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
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Department extends BaseHmsDomain {
    @Column()
    String name;
    
    @Column()
    String description;
    
    @Column(nullable=true)
    String parentDepartmentId;
    
    @OneToOne()
    @JoinColumn(name="creator_user_id",nullable=false)
    User user;
}
