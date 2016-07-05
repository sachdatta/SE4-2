package com.schedule;

import java.util.ArrayList;

public class SchedulePojo {
	private String course;
	private String courseName;
	private int count;
	private String teacher;
	private String students;
	private String yearSem;
	private ArrayList<String> allStudents;
	private String fallS;
	private String springS;
	private String summerS;
	private String hours;

	/*private int teacherAllocated;
	private int teacherAvailbaleDaysCount;
	private String teacherAvailbaleDays;*/
	
	
	
	public String getFallS() {
		return fallS;
	}
	public void setFallS(String fallS) {
		this.fallS = fallS;
	}
	public String getSpringS() {
		return springS;
	}
	public void setSpringS(String springS) {
		this.springS = springS;
	}
	public String getSummerS() {
		return summerS;
	}
	public void setSummerS(String summerS) {
		this.summerS = summerS;
	}
	public String getHours() {
		return hours;
	}
	public void setHours(String hours) {
		this.hours = hours;
	}
	public String getYearSem() {
		return yearSem;
	}
	public void setYearSem(String yearSem) {
		this.yearSem = yearSem;
	}
	public String getStudents() {
		return students;
	}
	public void setStudents(String students) {
		this.students = students;
	}
	/*public int getTeacherAllocated() {
		return teacherAllocated;
	}
	public void setTeacherAllocated(int length) {
		this.teacherAllocated = length;
	}*/
	public String getCourse() {
		return course;
	}
	public void setCourse(String course) {
		this.course = course;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int studentCount) {
		this.count = studentCount;
	}
	public String getTeacher() {
		return teacher;
	}
	public void setTeacher(String teacher) {
		this.teacher = teacher;
	}
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public ArrayList<String> getAllStudents() {
		return allStudents;
	}
	public void setAllStudents(ArrayList<String> allStudents) {
		this.allStudents = allStudents;
	}
	
	
	
	

}
