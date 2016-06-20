package com.degree;

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
import com.degree.Degree;
import com.login.UserException;
import com.user.User;

public class DegreeEvent {
	
	private static final String FILE_PATH =  "C:/Input/DegreeCourseDetails.csv";;

	public ArrayList<Degree> getAllDegree() throws IOException {
		CsvReader degree = new CsvReader(FILE_PATH);
		degree.readHeaders();
		ArrayList<Degree> degreeList=new ArrayList<Degree>();
		while(degree.readRecord()){
			Degree r=new Degree();
			r.setDegreeCode(degree.get("DegreeCode"));
			r.setDegreeDescription(degree.get("Description"));
			r.setDegreeHours(degree.get("Hours"));
			r.setType(degree.get("Type"));
			r.setDegreeCourses(degree.get("Courses"));
			degreeList.add(r);
		}
		degree.close();
		return degreeList;			
	}

	public void addDegree(Degree degree) throws IOException, UserException {
		vaidate(degree);
		saveDegree(degree);
	}

	private void vaidate(Degree degree) throws IOException, UserException {
		ArrayList<Degree> degreeList=new ArrayList<Degree>();
		degreeList=getAllDegree();
		for(Degree d:degreeList){
			if(d.getDegreeCode().equals(degree.getDegreeCode())&&d.getDegreeDescription().equals(degree.getDegreeDescription()))
				throw new UserException("Degree Code & Description combination already found");
					
		}
	}

	private void saveDegree(Degree degree) throws IOException {
		ArrayList<Degree> degreeList=new ArrayList<Degree>();
		degreeList=getAllDegree();
		degreeList.add(degree);
		updateSheet(degreeList);
		
	}

	private void updateSheet(ArrayList<Degree> degreeList) throws IOException {
		try {
			File file = new File(FILE_PATH);
			file.delete();
	        CsvWriter csvOutput = new CsvWriter(new FileWriter(FILE_PATH, true), ',');
	        csvOutput.write("DegreeCode");
	        csvOutput.write("Description");
	        csvOutput.write("Hours");
	        csvOutput.write("Type");
	        csvOutput.write("Courses");
	        csvOutput.endRecord();
			for(Degree d:degreeList){
			csvOutput.write(d.getDegreeCode());
			csvOutput.write(d.getDegreeDescription());
			csvOutput.write(d.getDegreeHours());
			csvOutput.write(d.getType());
			csvOutput.write(d.getDegreeCourses());
			csvOutput.endRecord();	
			}
			csvOutput.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	public void vaidateDegree(Degree degree) throws UserException {
		
		if(degree.getDegreeCode().equals("")||degree.getDegreeCode().equals(null))
			throw new UserException("Please select Degree code");	
		if(degree.getDegreeDescription().equals("")||degree.getDegreeDescription().equals(null))
			throw new UserException("Please enter Degree Description");	
		if(degree.getDegreeHours().equals("")||degree.getDegreeHours().equals(null))
			throw new UserException("Please enter hours");	
		if(degree.getType().equals("")||degree.getType().equals(null))
			throw new UserException("Please select type");		
		if(degree.getDegreeCourses().equals("")||degree.getDegreeCourses().equals(null))
			throw new UserException("Please select Courses");
		
		if(Pattern.matches("[a-zA-Z]+", degree.getDegreeHours()) == true)
			throw new UserException("Hours should be numaric ");
		
		
		String fac=degree.getDegreeCourses();
		ArrayList<String> courseList= new ArrayList<String>();
		int leangth=fac.length();
		int i=0;
		int j=9;
		while(j<=leangth){
			courseList.add(fac.substring(i,j));
			i=j+1;
			j=j+10;
		}
		
		for(int k=0;k<courseList.size();k++){
			String courseList1=courseList.get(k);
			if(courseList1.length()!=9)
				throw new UserException("Required Course length should be 9, First 4 characters should be alphabetic,5 character should be space,6th character should be between 1 to 5, and last 3 digits should be number");
			if(!courseList1.substring(0,4).matches("[a-zA-Z]+"))
				throw new UserException("Required Course length should be 9, First 4 characters should be alphabetic,5 character should be space,6th character should be between 1 to 5, and last 3 digits should be number");
			if(courseList1.charAt(5)>5&&courseList1.charAt(5)==0)
				throw new UserException("Required Course length should be 9, First 4 characters should be alphabetic,5 character should be space,6th character should be between 1 to 5, and last 3 digits should be number");
			if(Pattern.matches("[a-zA-Z]+",courseList1.substring(6,9)) == true)
				throw new UserException("Required Course length should be 9, First 4 characters should be alphabetic,5 character should be space,6th character should be between 1 to 5, and last 3 digits should be number");
		}
		
				
	}

	public void deleteDegree(String degreeCode, String degreeDescription) throws Exception {
		ArrayList<Degree> degreeList=new ArrayList<Degree>();
		try {
			degreeList=getAllDegree();
			int index=0;
			for(Degree c:degreeList){
				index++;
				if(c.getDegreeCode().equals(degreeCode)&&c.getDegreeDescription().equals(degreeDescription)){
					break;
				}
			}
			degreeList.remove(index-1);
			updateSheet(degreeList);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	
		
	}

	public void updateDegree(Degree degree) throws IOException {
		ArrayList<Degree> degreeList=new ArrayList<Degree>();
		degreeList=getAllDegree();
			for(Degree c:degreeList){
				if(c.getDegreeCode().equals(degree.getDegreeCode())&& c.getDegreeDescription().equals(degree.getDegreeDescription())){
					c.setDegreeDescription(degree.getDegreeDescription());
					c.setDegreeHours(degree.getDegreeHours());
					c.setType(degree.getType());
					c.setDegreeCourses(degree.getDegreeCourses());
				}
			}
			updateSheet(degreeList);
	}

	public Degree getDegree(String degreeCode, String degreeDescription) throws IOException {
		ArrayList<Degree> degreeList=getAllDegree();
		for(Degree c:degreeList){
			if(c.getDegreeCode().equals(degreeCode)&&c.getDegreeDescription().equals(degreeDescription))
				return c;
		}		
		return null;
	}

}

