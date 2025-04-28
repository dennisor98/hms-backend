package com.openmarket.hms.enums;

public enum GenderType {
	MALE("1"),FEMALE("2"),OTHER("3");
	String gender;
	private GenderType(String gender) {
		this.gender = gender;
	}

	public String getGender() {
		return this.gender;
	}

}
