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
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserPassword extends BaseHmsDomain implements Serializable{
    private static final long serialVersionUID = -5353841172898075007L;

	@OneToOne()
    @JoinColumn(name="user_id")
    User user;
    
    @Column(nullable=false)
    String pin;
    
    @Column(nullable=false)
    Boolean active;
}
