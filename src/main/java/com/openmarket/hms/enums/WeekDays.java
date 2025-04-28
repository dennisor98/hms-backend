package com.openmarket.hms.enums;

public enum WeekDays {
	SUN("SUNDAY"),TUE("TUESDAY"),WED("WEDNESDAY"),THUR("THURSDAY"),FRI("FRIDAY"),SAT("SATURDAY");
	String weekDay;
	private WeekDays(String weekDay){
		this.weekDay = weekDay;
	}
	
	public String getDay() {
		return this.weekDay;
	}

}
