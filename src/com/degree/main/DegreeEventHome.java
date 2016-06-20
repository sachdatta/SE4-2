package com.degree.main;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.regex.Pattern;

import com.degree.main.DegreeHome;
import com.courses.Courses;
import com.csvreader.CsvReader;
import com.csvreader.CsvWriter;
import com.login.UserException;
import com.user.User;

public class DegreeEventHome {
	
	private static final String FILE_PATH =  "C:/Input/DegreeDetails.csv";

	public ArrayList<DegreeHome> getAllDegrees() throws IOException {
		CsvReader degree = new CsvReader(FILE_PATH);
		degree.readHeaders();
		ArrayList<DegreeHome> degreeList=new ArrayList<DegreeHome>();
		while(degree.readRecord()){
			DegreeHome r=new DegreeHome();
			 r.setDegreeCode(degree.get("DegreeCode"));
			 r.setDegreeName(degree.get("DegreeName"));
			 r.setGradSchool(degree.get("Grad School"));
			 r.setForecast(degree.get("Forecast"));
			 degreeList.add(r);
		}
		degree.close();
		return degreeList;			
	}

	public void addDegree(DegreeHome degree) throws IOException, UserException {
		try{
			if(validate(degree))
				saveDegree(degree);
			else
				throw new UserException("Degree Number already found");
			}catch(Exception e){
				throw new UserException(e.getMessage());
			}
	;
	}

	private boolean validate(DegreeHome degree) throws IOException {
		ArrayList<DegreeHome> degreeList=new ArrayList<DegreeHome>();
		boolean status=true;
		degreeList=getAllDegrees();
		for(DegreeHome c:degreeList){
			if(c.getDegreeCode().equals(degree.getDegreeCode()))
				status=false;
		}
		return status;
	}

	private void saveDegree(DegreeHome degree) throws IOException {
		ArrayList<DegreeHome> degreeList=new ArrayList<DegreeHome>();
		degreeList=getAllDegrees();
		degreeList.add(degree);
		updateSheet(degreeList);
		
	}

	private void updateSheet(ArrayList<DegreeHome> degreeList) throws IOException {
		try{
		File file = new File(FILE_PATH);
		file.delete();
        CsvWriter csvOutput = new CsvWriter(new FileWriter(FILE_PATH, true), ',');
        csvOutput.write("DegreeCode");
        csvOutput.write("Grad School");
        csvOutput.write("DegreeName");
        csvOutput.write("Forecast");
        csvOutput.endRecord();
		for(DegreeHome d:degreeList){
		csvOutput.write(d.getDegreeCode());
		csvOutput.write(d.getGradSchool());
		csvOutput.write(d.getDegreeName());
		csvOutput.write(d.getForecast());
		csvOutput.endRecord();	
		}
		csvOutput.close();
	} catch (IOException e) {
		e.printStackTrace();
	}	
	}

	public void vaidateDegree(DegreeHome degree) throws UserException {
		
		if(degree.getDegreeCode().equals("")||degree.getDegreeCode().equals(null))
			throw new UserException("Please enter Degree Code");	
		if(degree.getDegreeName().equals("")||degree.getDegreeName().equals(null))
			throw new UserException("Please enter Degree Name");	
		if(degree.getGradSchool().equals("")||degree.getGradSchool().equals(null))
			throw new UserException("Please enter Degree grad school");	
		
		if(degree.getForecast().equals("")||degree.getForecast().equals(null))
			throw new UserException("Please enter Degree forecast");	
		
		
		if(Pattern.matches("[a-zA-Z]+", degree.getForecast()) == true)
			throw new UserException("Degree capacity should be numaric ");
		
	}

	public void deleteDegree(String degreeId) throws Exception {
		ArrayList<DegreeHome> degreeList=new ArrayList<DegreeHome>();
		try {
			degreeList=getAllDegrees();
			int index=0;
			for(DegreeHome c:degreeList){
				index++;
				if(c.getDegreeCode().equals(degreeId)){
					break;
				}
			}
			degreeList.remove(index-1);
			updateSheet(degreeList);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	
		
	}

	public void updateDegree(DegreeHome degree) throws IOException {
		ArrayList<DegreeHome> degreeList=new ArrayList<DegreeHome>();
		degreeList=getAllDegrees();
			for(DegreeHome c:degreeList){
				if(c.getDegreeCode().equals(degree.getDegreeCode())){
					c.setDegreeName(degree.getDegreeName());
					c.setGradSchool(degree.getGradSchool());	
					c.setForecast(degree.getForecast());
				}
			}
			updateSheet(degreeList);
	}

	public DegreeHome getDegree(String selected) throws IOException {
		ArrayList<DegreeHome> degreeList=getAllDegrees();
		for(DegreeHome c:degreeList){
			if(c.getDegreeCode().equals(selected))
				return c;
		}		
		return null;
	}

}
