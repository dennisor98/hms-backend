package com.openmarket.hms.requestDto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class RoleDto {
  @NotNull
  String name;
  
  @NotNull
  String description;
  
  @NotNull
  Boolean active;
}
