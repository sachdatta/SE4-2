package com.schedule;

public class ScheduleSection {
	private String sectionId;
	private String courseId;
	private String courseName;
	private String teacherName;
	private int sCount;
	private String tDay;
	private String students;
	private String yearSem;
	
	public String getStudents() {
		return students;
	}
	public void setStudents(String students) {
		this.students = students;
	}
	public String getSectionId() {
		return sectionId;
	}
	public void setSectionId(String sectionId) {
		this.sectionId = sectionId;
	}
	public String getCourseId() {
		return courseId;
	}
	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}
	public String getTeacherName() {
		return teacherName;
	}
	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}
	public int getsCount() {
		return sCount;
	}
	public void setsCount(int i) {
		this.sCount = i;
	}
	public String gettDay() {
		return tDay;
	}
	public void settDay(String teacherDetails) {
		this.tDay = teacherDetails;
	}
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public String getYearSem() {
		return yearSem;
	}
	public void setYearSem(String yearSem) {
		this.yearSem = yearSem;
	}
	
	
}
