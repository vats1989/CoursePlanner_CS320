package model;

public class QuarterPlan {

	Quarter quarter;
	CourseData course;
	
	public QuarterPlan(Quarter quarter,CourseData course) 
	{
		this.quarter = quarter;
		this.course = course;
	}
	public Quarter getQuarter() {
		return quarter;
	}
	public void setQuarter(Quarter quarter) {
		this.quarter = quarter;
	}
	public CourseData getCourse() {
		return course;
	}
	public void setCourse(CourseData course) {
		this.course = course;
	}
}