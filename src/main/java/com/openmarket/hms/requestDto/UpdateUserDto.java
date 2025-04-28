package com.openmarket.hms.requestDto;

import java.util.Date;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;


@Data
public class UpdateUserDto {
	@NotNull
	@NotBlank
	String userId;
	
	@NotBlank
    private String firstName;
    
    private String middleName;
    
    @NotBlank
    private String lastName;
    
    @NotBlank
    @Pattern(regexp = "^\\d{9}$", message = "Invalid mobile number format")
    private String mobileNumber;
    
    @NotBlank
    private String idNumber;
    
    private Date dob;
    
    @NotBlank
    @Email(message = "Invalid email format")
    private String email;
    
    @NotNull
    private Boolean isActive;
    
    @NotNull
    @NotBlank
    private String departmentId;
}
