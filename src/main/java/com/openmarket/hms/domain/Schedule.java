package com.openmarket.hms.domain;

import java.io.Serializable;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor


public class Schedule extends BaseHmsDomain implements Serializable{
   private static final long serialVersionUID = 1L;
   @ManyToOne
   @JoinColumn(name = "staff_id", nullable = false)
   private User staff;
   
   @ManyToOne
   @JoinColumn(name = "shift_id", nullable = false)
   private Shift shift;
   
   @Column(nullable = false)
   private LocalDate shiftDate;
   
   
   @Enumerated(EnumType.STRING)
   private Status status = Status.SCHEDULED;

   public enum Status {
       SCHEDULED, COMPLETED, MISSED
   }
}
