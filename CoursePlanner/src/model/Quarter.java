package model;

import java.util.Calendar;

public class Quarter {

	String quarter;
	public Quarter(){
	}
	public Quarter(String quarter) {
		setQuarter(quarter);
    }
	public String getQuarter() {
		return quarter;
	}
	public void setQuarter(String quarter) {
		this.quarter = quarter;
	}
	
	public String findQuarter() {
		Calendar cal = Calendar.getInstance();
		int week = cal.get(Calendar.WEEK_OF_YEAR);
		//int year = cal.get(Calendar.YEAR);		
		String quarterString = null;
		if(week >= 1 && week <= 12)
			quarterString = "Winter";
		else if(week >= 13 && week <= 24)
			quarterString = "Spring";
		else if(week >= 25 && week <= 37)
			quarterString = "Summer";
		else if(week >= 38)
			quarterString = "Fall";
		
		return quarterString;
	}
}