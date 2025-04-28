package com.openmarket.hms.enums;

public enum MaritalStatusType {
	SINGLE("1"),MARIED("2"),LIVING_TOGETHER("3"),DIVORCED("4");
	String status;
	private MaritalStatusType(String status) {
		this.status = status;
	}

	public String getGender() {
		return this.status;
	}
}
