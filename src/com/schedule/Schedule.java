package com.schedule;

import java.util.Date;

public class Schedule {
	private String scheduleId;
	private String status;
	private String term;
	private String courseId;
	private String courseName;
	private String location;
	private Date mFromDate;
	private Date mToDate;
	private String faculty;
	private String available;
	private String capacity;
	private String waitlist;
	private String credits;
	private String ceus;
	private String academeiLevel;
	
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getScheduleId() {
		return scheduleId;
	}
	public void setScheduleId(String scheduleId) {
		this.scheduleId = scheduleId;
	}
	public String getTerm() {
		return term;
	}
	public void setTerm(String term) {
		this.term = term;
	}
	public String getCourseId() {
		return courseId;
	}
	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public Date getmFromDate() {
		return mFromDate;
	}
	public void setmFromDate(Date mFromDate) {
		this.mFromDate = mFromDate;
	}
	public Date getmToDate() {
		return mToDate;
	}
	public void setmToDate(Date mToDate) {
		this.mToDate = mToDate;
	}
	public String getFaculty() {
		return faculty;
	}
	public void setFaculty(String faculty) {
		this.faculty = faculty;
	}
	public String getAvailable() {
		return available;
	}
	public void setAvailable(String available) {
		this.available = available;
	}
	public String getCapacity() {
		return capacity;
	}
	public void setCapacity(String capacity) {
		this.capacity = capacity;
	}
	public String getWaitlist() {
		return waitlist;
	}
	public void setWaitlist(String waitlist) {
		this.waitlist = waitlist;
	}
	public String getCredits() {
		return credits;
	}
	public void setCredits(String credits) {
		this.credits = credits;
	}
	public String getCeus() {
		return ceus;
	}
	public void setCeus(String ceus) {
		this.ceus = ceus;
	}
	public String getAcademeiLevel() {
		return academeiLevel;
	}
	public void setAcademeiLevel(String academeiLevel) {
		this.academeiLevel = academeiLevel;
	}
	

}
