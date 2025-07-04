package com.openmarket.hms.requestDto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ConsultationDto {
  @NotNull()
  String sessionId;
  
  String doctorId;
  
}
