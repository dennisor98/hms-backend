package com.openmarket.hms.requestDto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UserLoginDto {
  @NotNull(message="mobileNumber is missing")
  String mobileNumber;
  
  @NotNull(message="password is missing")
  String password;
  
}
