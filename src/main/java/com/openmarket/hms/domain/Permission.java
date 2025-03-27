package com.openmarket.hms.domain;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Permission extends BaseHmsDomain implements Serializable{
   private static final long serialVersionUID = -7819108916469175413L;


@Column()
   String name;
   
   @Column()
   String description;
   
   @Column()
   String category;
      
}
