package com.courses;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.regex.Pattern;

import com.courses.Courses;
import com.csvreader.CsvReader;
import com.csvreader.CsvWriter;
import com.login.UserException;
import com.user.User;

public class CourseEvent {
	
	private static final String FILE_PATH = "C:/Input/CourseDetails.csv";
	 

	
	public void saveCourse(Courses course) throws Exception {
		
		ArrayList<Courses> courseList=new ArrayList<Courses>();
		courseList=getAllCourse();
		courseList.add(course);
		updateSheet(courseList);
		
	}
	
	public ArrayList getAllCourse() throws Exception{
		
		CsvReader course = new CsvReader(FILE_PATH);
		course.readHeaders();
		ArrayList<Courses> courseList=new ArrayList<Courses>();
		while(course.readRecord()){
			 Courses c=new Courses();
			 c.setNumber(course.get("CourseCode"));
			 c.setName(course.get("CourseName"));
			 c.setDescription(course.get("CourseDescription"));
			 c.setHours(course.get("CourseHours"));
			 c.setCapacity(course.get("CourseCap"));
			 c.setFallS(course.get("OfferedFall"));
			 c.setSpringS(course.get("OfferedSpring"));
			 c.setSummerS(course.get("OfferedSummer"));
			 String sem;
			 if(course.get("OfferedFall").equals("Y"))
					sem="1";
				else
					sem="0";
			 if(course.get("OfferedSpring").equals("Y"))
					sem=sem+"1";
				else
					sem=sem+"0";
			 if(course.get("OfferedSummer").equals("Y"))
					sem=sem+"1";
				else
					sem=sem+"0";
			 c.setSemester(sem);
			 c.setPrerequested(course.get("CoursePrereqs"));
			 c.setTeacher(course.get("Teachers"));
			 
			 courseList.add(c);
		}
		course.close();
		return courseList;		
	}
	
	public void updateSheet(ArrayList<Courses> courseList) throws UserException{
		try{
			
			File file = new File(FILE_PATH);
			file.delete();
	        CsvWriter csvOutput = new CsvWriter(new FileWriter(FILE_PATH, true), ',');  
	        csvOutput.write("CourseCode");
	        csvOutput.write("CourseName");
	        csvOutput.write("CourseDescription");
	        csvOutput.write("CourseHours");
	        csvOutput.write("CourseCap");
	        csvOutput.write("OfferedFall");
	        csvOutput.write("OfferedSpring");
	        csvOutput.write("OfferedSummer");
	        csvOutput.write("CoursePrereqs");
	        csvOutput.write("Teachers");
	        csvOutput.endRecord();
			for(Courses c:courseList){
			csvOutput.write(c.getNumber());
			csvOutput.write(c.getName());
			csvOutput.write(c.getDescription());
			csvOutput.write(c.getHours());
			csvOutput.write(c.getCapacity());
			if(c.getSemester().charAt(1)=='0')
				csvOutput.write("Y");
			else
				csvOutput.write("N");
			if(c.getSemester().charAt(1)=='1')
				csvOutput.write("Y");
			else
				csvOutput.write("N");
			if(c.getSemester().charAt(1)=='2')
				csvOutput.write("Y");
			else
				csvOutput.write("N");
			csvOutput.write(c.getPrerequested());
			csvOutput.write(c.getTeacher());
			csvOutput.endRecord();	
			}
			csvOutput.close();		
					        		
			}catch(Exception e){
				throw new UserException("Failed to save");		
			}
	}

	public Courses getCourse(String selected) throws Exception {
		ArrayList<Courses> courseList=getAllCourse();
		for(Courses c:courseList){
			if(c.getNumber().equals(selected))
				return c;
		}		
		return null;
	}

	public void updateCourse(Courses course) throws Exception {		
		ArrayList<Courses> courseList=new ArrayList<Courses>();
		courseList=getAllCourse();
		boolean status=false;
		try{
			for(Courses c:courseList){
				if(c.getNumber().equals(course.getNumber())){
					status=true;
					c.setName(course.getName());
					c.setDescription(course.getDescription());
					c.setHours(course.getHours());
					c.setPrerequested(course.getPrerequested());
					c.setCapacity(course.getCapacity());
					c.setSemester(course.getSemester());
					c.setTeacher(course.getTeacher());
				}
			}
			if(status)
				updateSheet(courseList);
			else
				throw new UserException("Course ID not found");
		
			
		}catch(Exception e){
			throw new UserException(e.getMessage());
		}
	}

	public void addCourse(Courses course) throws Exception {
		try{
		if(validate(course))
			 saveCourse(course);
		else
			throw new UserException("CourseId already found");
		}catch(Exception e){
			throw new UserException(e.getMessage());
		}
	}
	
	public boolean validate(Courses course) throws Exception{
		ArrayList<Courses> courseList=new ArrayList<Courses>();
		boolean status=true;
		courseList=getAllCourse();
		for(Courses c:courseList){
			if(c.getNumber().equals(course.getNumber()))
				status=false;
		}
		return status;
	}

	public void deleteCourse(String courseNumber) throws Exception {
		ArrayList<Courses> courseList=new ArrayList<Courses>();
		try {
			courseList=getAllCourse();
			int index=0;
			for(Courses c:courseList){
				index++;
				if(c.getNumber().equals(courseNumber)){
					break;
				}
			}
			courseList.remove(index-1);
			updateSheet(courseList);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
		
		
	}
	public void validateCourse(Courses course) throws UserException{
		try{
		String courseNumber=course.getNumber();
		String coursePrerequisites=course.getPrerequested();
		ArrayList<String> coursePrerequisitesList= new ArrayList();
		int leangth=coursePrerequisites.length();
		int i=0;
		int j=8;
		while(j<=leangth){
			coursePrerequisitesList.add(coursePrerequisites.substring(i,j));
			i=j+1;
			j=j+9;
		}
		if(courseNumber.equals("")||courseNumber.equals(null))
			throw new UserException("Please enter course number");
		if(course.getName().equals("")||course.getName().equals(null))
			throw new UserException("Please enter course name");
		if(course.getDescription().equals("")||course.getDescription().equals(null))
			throw new UserException("Please enter course description");
		if(course.getHours().equals("")||course.getHours().equals(null))
			throw new UserException("Please enter course hours");			
		if(course.getCapacity().equals("")||course.getCapacity().equals(null))
			throw new UserException("Please enter course capacity");
		if(course.getPrerequested().equals("")||course.getPrerequested().equals(null))
			throw new UserException("Please enter course prerequisites");			
		if(course.getSemester().equals("000")||course.getSemester().equals("")||course.getSemester().equals(null))		
			throw new UserException("Please select atleast a semester");					
		if(courseNumber.length()!=9)
			throw new UserException("Coursenumber length should be 9, First 4 characters should be alphabetic,5 character should be space,6th character should be between 1 to 5, and last 3 digits should be number");			
		if(!courseNumber.substring(0,3).matches("[a-zA-Z]+"))
			throw new UserException("Coursenumber length should be 9, First 4 characters should be alphabetic,5 character should be space,6th character should be between 1 to 5, and last 3 digits should be number");	
		if(!((courseNumber.substring(5,6).equals("1"))| (courseNumber.substring(5,6).equals("2"))||(courseNumber.substring(5,6).equals("3"))||(courseNumber.substring(5,6).equals("4"))||(courseNumber.substring(5,6).equals("5"))))
			throw new UserException("Coursenumber length should be 9, First 4 characters should be alphabetic,5 character should be space,6th character should be between 1 to 5, and last 3 digits should be number");				
		if(Pattern.matches("[a-zA-Z]+",courseNumber.substring(6,9)) == true)
			throw new UserException("Coursenumber length should be 9, First 4 characters should be alphabetic,5 character should be space,6th character should be between 1 to 5, and last 3 digits should be number");	
		/*
		for(int k=0;k<coursePrerequisitesList.size();k++){
			String coursePrerequisites1=coursePrerequisitesList.get(k);
			if(coursePrerequisites1.length()!=8)
				throw new UserException("Prerequisites length should be 8, First 4 characters should be alphabetic,5th character should be between 1 to 5, and last 3 digits should be number");
			if(!coursePrerequisites1.substring(0,3).matches("[a-zA-Z]+"))
				throw new UserException("Prerequisites length should be 8, First 4 characters should be alphabetic,5th character should be between 1 to 5, and last 3 digits should be number");
			if(coursePrerequisites1.charAt(4)>5&&coursePrerequisites1.charAt(4)==0)
				throw new UserException("Prerequisites length should be 8, First 4 characters should be alphabetic,5th character should be between 1 to 5, and last 3 digits should be number");
			if(Pattern.matches("[a-zA-Z]+",coursePrerequisites1.substring(5,8)) == true)
				throw new UserException("Prerequisites length should be 8, First 4 characters should be alphabetic,5th character should be between 1 to 5, and last 3 digits should be number");
		}*/
		if(Pattern.matches("[a-zA-Z]+", course.getHours()) == true)
			throw new UserException("Hours should be numaric ");
		if(Pattern.matches("[a-zA-Z]+", course.getCapacity()) == true)
			throw new UserException("Capacity should be numaric ");
		}catch(Exception e){
			throw new UserException(e.getMessage());
		}
		
		
	}

}
