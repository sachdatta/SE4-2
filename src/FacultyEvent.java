package com.faculty;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.regex.Pattern;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.login.UserException;

public class FacultyEvent {

	private static final String FILE_PATH =  "C:/Input/FacultyDetails.xlsx";;

	public ArrayList<Faculty> getAllFaculty() throws IOException {
		FileInputStream file = new FileInputStream(new File(FILE_PATH));
		XSSFWorkbook workbook = new XSSFWorkbook(file);
		XSSFSheet sheet = workbook.getSheetAt(0);
		Iterator<Row> rowIterator = sheet.rowIterator();
		ArrayList<Faculty> facultyList=new ArrayList<Faculty>();
		while(rowIterator.hasNext()) {
	        Row row = rowIterator.next();
	        Iterator<Cell> cellIterator = row.cellIterator();
	        Faculty c=new Faculty();
	        int count=0;
	        while(cellIterator.hasNext()) {	        	
	        	Cell cell = cellIterator.next();
	        	switch(count){
	        	case 0:
	        		c.setFacultyId(cell.getStringCellValue());
	        		count++;
	        		break;
	        	case 1:
	        		c.setTitle(cell.getStringCellValue());
	        		count++;
	        		break;
	        	case 2:
	        		c.setName(cell.getStringCellValue());
	        		count++;
	        		break;
	        		
	        	case 3:
	        		c.setFacultyLoad(cell.getStringCellValue());
	        		count++;
	        		break;
	        		
	        	case 4:
	        		c.setCourse(cell.getStringCellValue());
	        		count++;
	        		break;
	        	case 5:
	        		c.setAvlDays(cell.getStringCellValue());
	        		count++;
	        		break;
	        	
	        	}  	
	        }
	        facultyList.add(c);
		} file.close();
		return facultyList;		
	}

	public void addFaculty(Faculty faculty) throws IOException {
		saveFaculty(faculty);
	}

	private void saveFaculty(Faculty faculty) throws IOException {
		ArrayList<Faculty> facultyList=new ArrayList<Faculty>();
		facultyList=getAllFaculty();
		if(facultyList.size()==0)
			faculty.setFacultyId("F1");
		else
			faculty.setFacultyId("F"+facultyList.size());
		facultyList.add(faculty);
		updateSheet(facultyList);
		
	}

	private void updateSheet(ArrayList<Faculty> facultyList) throws IOException {
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet("course");
		 int rowCount = 0;
         
	        for (Faculty f : facultyList) {
	            Row row = sheet.createRow(++rowCount);	             
	            int columnCount = 0;
	            Cell cell1 = row.createCell(columnCount++);
	            cell1.setCellValue(f.getFacultyId());
	            Cell cell2 = row.createCell(columnCount++);
	            cell2.setCellValue(f.getTitle());
	            Cell cell3 = row.createCell(columnCount++);
	            cell3.setCellValue(f.getName());
	            Cell cell4 = row.createCell(columnCount++);
	            cell4.setCellValue(f.getFacultyLoad());
	            Cell cell5 = row.createCell(columnCount++);
	            cell5.setCellValue(f.getCourse());
	            Cell cell6 = row.createCell(columnCount++);
	            cell6.setCellValue(f.getAvlDays());
	        }
	         //Write the workbook in file system
	            FileOutputStream out = new FileOutputStream(new File(FILE_PATH));
	            workbook.write(out);
	            out.close();
		
	}

	public void vaidateFaculty(Faculty faculty) throws UserException {
		
		if(faculty.getName().equals("")||faculty.getName().equals(null))
			throw new UserException("Please enter Faculty name");	
		if(faculty.getTitle().equals("")||faculty.getTitle().equals(null))
			throw new UserException("Please enter Faculty Title");	
		if(faculty.getFacultyLoad().equals("")||faculty.getFacultyLoad().equals(null))
			throw new UserException("Please enter Faculty load");	
		if(faculty.getCourse().equals("")||faculty.getCourse().equals(null))
			throw new UserException("Please select atleast one course");
		if(faculty.getAvlDays().equals("00000")||faculty.getAvlDays().equals("")||faculty.getAvlDays().equals(null))		
			throw new UserException("Please select atleast one avilable day");
		
		String fac=faculty.getCourse();
		ArrayList<String> courseList= new ArrayList<String>();
		int leangth=fac.length();
		int i=0;
		int j=8;
		while(j<=leangth){
			courseList.add(fac.substring(i,j));
			i=j+1;
			j=j+9;
		}
		
		for(int k=0;k<courseList.size();k++){
			String courseList1=courseList.get(k);
			if(courseList1.length()!=8)
				throw new UserException("Course length should be 8, First 4 characters should be alphabetic,5th character should be between 1 to 5, and last 3 digits should be number");
			if(!courseList1.substring(0,3).matches("[a-zA-Z]+"))
				throw new UserException("Course length should be 8, First 4 characters should be alphabetic,5th character should be between 1 to 5, and last 3 digits should be number");
			if(courseList1.charAt(4)>5&&courseList1.charAt(4)==0)
				throw new UserException("Course length should be 8, First 4 characters should be alphabetic,5th character should be between 1 to 5, and last 3 digits should be number");
			if(Pattern.matches("[a-zA-Z]+",courseList1.substring(5,8)) == true)
				throw new UserException("Course length should be 8, First 4 characters should be alphabetic,5th character should be between 1 to 5, and last 3 digits should be number");
		}
		
		if(Pattern.matches("[a-zA-Z]+", faculty.getFacultyLoad()) == true)
			throw new UserException("Faculty load should be numaric ");
		
	}

	public void deleteFaculty(String facultyId) throws Exception {
		ArrayList<Faculty> facultyList=new ArrayList<Faculty>();
		try {
			facultyList=getAllFaculty();
			int index=0;
			for(Faculty c:facultyList){
				index++;
				if(c.getFacultyId().equals(facultyId)){
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
				if(c.getFacultyId().equals(faculty.getFacultyId())){
					c.setTitle(faculty.getTitle());
					c.setName(faculty.getName());
					c.setFacultyLoad(faculty.getFacultyLoad());
					c.setCourse(faculty.getCourse());
					c.setAvlDays(faculty.getAvlDays());								
				}
			}
			updateSheet(facultyList);
	}

	public Faculty getFaculty(String selected) throws IOException {
		ArrayList<Faculty> facultyList=getAllFaculty();
		for(Faculty c:facultyList){
			if(c.getFacultyId().equals(selected))
				return c;
		}		
		return null;
	}

}
