package com.openmarket.hms.requestDto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class TriageDto {
	@NotNull()
	String triageId;
	@NotNull()
	Double height;
	@NotNull()
	Double temp;
	@NotNull()
	Double weight;
	@NotNull()
	Double bp;
	@NotNull()
	Boolean isComplete;
}
