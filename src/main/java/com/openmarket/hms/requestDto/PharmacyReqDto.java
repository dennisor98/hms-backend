package com.openmarket.hms.requestDto;

import java.util.List;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PharmacyReqDto {
  @NotNull()
  String sessionId;
  
  @NotNull()
  List<String> items;
}
