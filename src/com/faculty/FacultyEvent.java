package com.faculty;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.regex.Pattern;

import com.csvreader.CsvReader;
import com.csvreader.CsvWriter;
import com.login.UserException;
import com.user.User;

public class FacultyEvent {

	private static final String FILE_PATH =  "C:/Input/FacultyDetails.csv";;

	public ArrayList<Faculty> getAllFaculty() throws IOException {
		CsvReader faculty = new CsvReader(FILE_PATH);
		faculty.readHeaders();
		ArrayList<Faculty> facultyList=new ArrayList<Faculty>();
		while(faculty.readRecord()){
			Faculty r=new Faculty();											
			r.setLastName(faculty.get("LastName"));
			r.setFirstName(faculty.get("FirstName"));
			r.setGrandSchool(faculty.get("Grad School"));
			r.setDegree(faculty.get("Degree"));
			r.setTitle(faculty.get("Title"));
			r.setAvlDays(faculty.get("DaysToTeach"));
			r.setFallLoad(faculty.get("MaxLoadFall"));
			r.setSpringLoad(faculty.get("MaxLoadSpring"));
			r.setSummerLoad(faculty.get("MaxLoadSummer"));

			 facultyList.add(r);
		}
		faculty.close();
		return facultyList;		
	}

	public void addFaculty(Faculty faculty) throws IOException {
		saveFaculty(faculty);
	}

	private void saveFaculty(Faculty faculty) throws IOException {
		ArrayList<Faculty> facultyList=new ArrayList<Faculty>();
		facultyList=getAllFaculty();
		facultyList.add(faculty);
		updateSheet(facultyList);
		
	}

	private void updateSheet(ArrayList<Faculty> facultyList) throws IOException {
		try {
			File file = new File(FILE_PATH);
			file.delete();
	        CsvWriter csvOutput = new CsvWriter(new FileWriter(FILE_PATH, true), ',');        								

	        csvOutput.write("LastName");
	        csvOutput.write("FirstName");
	        csvOutput.write("Grad School");
	        csvOutput.write("Degree");
	        csvOutput.write("Title");
	        csvOutput.write("DaysToTeach");
	        csvOutput.write("MaxLoadFall");
	        csvOutput.write("MaxLoadSpring");
	        csvOutput.write("MaxLoadSummer");
	        csvOutput.endRecord();
			for(Faculty f:facultyList){
			csvOutput.write(f.getLastName());
			csvOutput.write(f.getFirstName());
			csvOutput.write(f.getGrandSchool());
			csvOutput.write(f.getDegree());
			csvOutput.write(f.getTitle());
			csvOutput.write(f.getAvlDays());
			csvOutput.write(f.getFallLoad());
			csvOutput.write(f.getSpringLoad());
			csvOutput.write(f.getSummerLoad());
			csvOutput.endRecord();	
			}
			csvOutput.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	public void vaidateFaculty(Faculty faculty) throws UserException {
		if(faculty.getFirstName().equals("")||faculty.getFirstName().equals(null))
			throw new UserException("Please enter Faculty First name");	
		if(faculty.getLastName().equals("")||faculty.getLastName().equals(null))
			throw new UserException("Please enter Faculty Last name");			
		if(faculty.getTitle().equals("")||faculty.getTitle().equals(null))
			throw new UserException("Please enter Faculty Title");	
		if(faculty.getDegree().equals("")||faculty.getDegree().equals(null))
			throw new UserException("Please enter Faculty degree");	
		if(faculty.getGrandSchool().equals("")||faculty.getGrandSchool().equals(null))
			throw new UserException("Please enter grand school ");
		if(faculty.getAvlDays().equals("")||faculty.getAvlDays().equals(null))		
			throw new UserException("Please select atleast one avilable day");
		
				
		if(Pattern.matches("[a-zA-Z]+", faculty.getSpringLoad()) == true)
			throw new UserException("Spring load should be numaric ");
		if(Pattern.matches("[a-zA-Z]+", faculty.getFallLoad()) == true)
			throw new UserException("Fall load should be numaric ");
		if(Pattern.matches("[a-zA-Z]+", faculty.getSummerLoad()) == true)
			throw new UserException("Summer load should be numaric ");
		
	}

	public void deleteFaculty(String facultyLastName) throws Exception {
		ArrayList<Faculty> facultyList=new ArrayList<Faculty>();
		try {
			facultyList=getAllFaculty();
			int index=0;
			for(Faculty c:facultyList){
				index++;
				if(c.getLastName().equals(facultyLastName)){
					break;
				}
			}
			facultyList.remove(index-1);
			updateSheet(facultyList);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	
		
	}

	public void updateFaculty(Faculty faculty) throws IOException {
		ArrayList<Faculty> facultyList=new ArrayList<Faculty>();
		facultyList=getAllFaculty();
			for(Faculty c:facultyList){
				if(c.getLastName().equals(faculty.getLastName())){
					c.setTitle(faculty.getTitle());
					c.setGrandSchool(faculty.getGrandSchool());
					c.setDegree(faculty.getDegree());
					c.setFallLoad(faculty.getFallLoad());
					c.setSpringLoad(faculty.getSpringLoad());
					c.setSummerLoad(faculty.getSummerLoad());
					c.setAvlDays(faculty.getAvlDays());
				}
			}
			updateSheet(facultyList);
	}

	public Faculty getFaculty(String selected) throws IOException {
		ArrayList<Faculty> facultyList=getAllFaculty();
		for(Faculty c:facultyList){
			if(c.getLastName().equals(selected))
				return c;
		}		
		return null;
	}

}
