package com.room;

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

import com.room.Room;
import com.courses.Courses;
import com.login.UserException;

public class RoomEvent {
	
	private static final String FILE_PATH =  "C:/Input/RoomDetails.xlsx";;

	public ArrayList<Room> getAllRooms() throws IOException {
		FileInputStream file = new FileInputStream(new File(FILE_PATH));
		XSSFWorkbook workbook = new XSSFWorkbook(file);
		XSSFSheet sheet = workbook.getSheetAt(0);
		Iterator<Row> rowIterator = sheet.rowIterator();
		ArrayList<Room> roomList=new ArrayList<Room>();
		while(rowIterator.hasNext()) {
	        Row row = rowIterator.next();
	        Iterator<Cell> cellIterator = row.cellIterator();
	        Room r=new Room();
	        int count=0;
	        while(cellIterator.hasNext()) {	        	
	        	Cell cell = cellIterator.next();
	        	switch(count){
	        	case 0:
	        		r.setRoomNumber(cell.getStringCellValue());
	        		count++;
	        		break;
	        	case 1:	        		
	        		r.setBuilding(cell.getStringCellValue());
	        		count++;
	        		break;
	        	case 2:
	        		r.setCapacity(cell.getStringCellValue());
	        		count++;
	        		break;
	        	}  	
	        }
	        roomList.add(r);
		} file.close();
		return roomList;		
	}

	public void addRoom(Room room) throws IOException, UserException {
		try{
			if(validate(room))
				saveRoom(room);
			else
				throw new UserException("Room Number already found");
			}catch(Exception e){
				throw new UserException(e.getMessage());
			}
	;
	}

	private boolean validate(Room room) throws IOException {
		ArrayList<Room> roomList=new ArrayList<Room>();
		boolean status=true;
		roomList=getAllRooms();
		for(Room c:roomList){
			if(c.getRoomNumber().equals(room.getRoomNumber()))
				status=false;
		}
		return status;
	}

	private void saveRoom(Room room) throws IOException {
		ArrayList<Room> roomList=new ArrayList<Room>();
		roomList=getAllRooms();
		roomList.add(room);
		updateSheet(roomList);
		
	}

	private void updateSheet(ArrayList<Room> roomList) throws IOException {
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet("course");
		 int rowCount = 0;
         
	        for (Room f : roomList) {
	            Row row = sheet.createRow(++rowCount);	             
	            int columnCount = 0;
	            Cell cell1 = row.createCell(columnCount++);
	            cell1.setCellValue(f.getRoomNumber());
	            Cell cell2 = row.createCell(columnCount++);
	            cell2.setCellValue(f.getBuilding());
	            Cell cell3 = row.createCell(columnCount++);
	            cell3.setCellValue(f.getCapacity());
	        }
	         //Write the workbook in file system
	            FileOutputStream out = new FileOutputStream(new File(FILE_PATH));
	            workbook.write(out);
	            out.close();
		
	}

	public void vaidateRoom(Room room) throws UserException {
		
		if(room.getRoomNumber().equals("")||room.getRoomNumber().equals(null))
			throw new UserException("Please enter Room number");	
		if(room.getBuilding().equals("")||room.getBuilding().equals(null))
			throw new UserException("Please enter Room Building");	
		if(room.getCapacity().equals("")||room.getCapacity().equals(null))
			throw new UserException("Please enter Room capacity");	
		String roomNumber=room.getRoomNumber();
		
		if(!roomNumber.substring(0,1).matches("[a-zA-Z]+"))
			throw new UserException("Room number leangth should be 4, first character should be alphabetic and next 3 characters should be number ");
		if(!(roomNumber.length()==4))
			throw new UserException("Room number leangth should be 4, first character should be alphabetic and next 3 characters should be number ");
		if(roomNumber.substring(1,4).matches("[a-zA-Z]+"))
			throw new UserException("Room number leangth should be 4, first character should be alphabetic and next 3 characters should be number ");
		
		
		
		
		if(Pattern.matches("[a-zA-Z]+", room.getCapacity()) == true)
			throw new UserException("Room capacity should be numaric ");
		
	}

	public void deleteRoom(String roomId) throws Exception {
		ArrayList<Room> roomList=new ArrayList<Room>();
		try {
			roomList=getAllRooms();
			int index=0;
			for(Room c:roomList){
				index++;
				if(c.getRoomNumber().equals(roomId)){
					break;
				}
			}
			roomList.remove(index-1);
			updateSheet(roomList);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	
		
	}

	public void updateRoom(Room room) throws IOException {
		ArrayList<Room> roomList=new ArrayList<Room>();
		roomList=getAllRooms();
			for(Room c:roomList){
				if(c.getRoomNumber().equals(room.getRoomNumber())){
					c.setRoomNumber(room.getRoomNumber());
					c.setCapacity(room.getCapacity());	
					c.setBuilding(room.getBuilding());
				}
			}
			updateSheet(roomList);
	}

	public Room getRoom(String selected) throws IOException {
		ArrayList<Room> roomList=getAllRooms();
		for(Room c:roomList){
			if(c.getRoomNumber().equals(selected))
				return c;
		}		
		return null;
	}

}
