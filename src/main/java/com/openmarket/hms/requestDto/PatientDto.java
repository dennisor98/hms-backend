package com.openmarket.hms.requestDto;

import java.util.Date;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PatientDto {
	@NotNull
	String firstName;
	String middleName;
	@NotNull
	String lastName;
	@NotNull
	String mobileNumber;
	String email;
	@NotNull
	String residence;
	@NotNull
	Boolean isActive;
	@NotNull
	String gender;
	@NotNull
	Date dob;
	@NotNull
	String maritalStatus;
}
