package com.openmarket.hms.requestDto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreateDeptDto {
  @NotNull()
  String name;
  
  @NotNull()
  String description;
  
  String parentId;
}
