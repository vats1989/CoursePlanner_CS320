package model;

import java.util.*;

public class CourseData {
	
	private int id;
	private String courseCode;
	private String courseDesc;
	private String preReq;
	private ArrayList<String> preReqCodes;
	
	public CourseData() {
	}
	
	public CourseData (int id, String code, String desc, String preReqs, ArrayList<String> preReq) {
	
	this.id = id;
	this.courseCode = code;
	this.courseDesc = desc;
	this.preReqCodes = preReq;
	this.preReq = preReqs;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCourseCode() {
		return courseCode;
	}
	public void setCourseCode(String courseCode) {
		this.courseCode = courseCode;
	}
	public String getCourseDesc() {
		return courseDesc;
	}
	public void setCourseDesc(String courseDesc) {
		this.courseDesc = courseDesc;
	}
	public ArrayList<String> getPreReqCodes() {
		return preReqCodes;
	}
	public void setPreReqCodes(ArrayList<String> preReqCodes) {
		this.preReqCodes = preReqCodes;
	}
	public String getPreReq() {
		return preReq;
	}
	public void setPreReq(String preReq) {
		this.preReq = preReq;
	}
}