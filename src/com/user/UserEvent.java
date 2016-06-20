package com.user;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import com.csvreader.CsvReader;
import com.csvreader.CsvWriter;
import com.login.UserException;

public class UserEvent {
	
	private static final String FILE_PATH =  "C:/Input/UserDetails.csv";;

	public ArrayList<User> getAllUsers() throws IOException {		
		CsvReader users = new CsvReader(FILE_PATH);
		users.readHeaders();
		ArrayList<User> userList=new ArrayList<User>();
		while(users.readRecord()){
			 User r=new User();
			 r.setUserId(users.get("UserId"));
			 r.setPassword(users.get("Password"));
			 r.setRole(users.get("Role"));
			 userList.add(r);
		}
		users.close();
		return userList;		
	}

	public void addUser(User user) throws IOException, UserException {
		try{
			if(validate(user))
				saveUser(user);
			else
				throw new UserException("User Name already found");
			}catch(Exception e){
				throw new UserException(e.getMessage());
			}
	;
	}

	private boolean validate(User user) throws IOException {
		ArrayList<User> userList=new ArrayList<User>();
		boolean status=true;
		userList=getAllUsers();
		for(User c:userList){
			if(c.getUserId().equals(user.getUserId()))
				status=false;
		}
		return status;
	}

	private void saveUser(User user) throws IOException {
		try {
		CsvWriter csvOutput = new CsvWriter(new FileWriter(FILE_PATH, true), ',');
		
		csvOutput.write(user.getUserId());
		csvOutput.write(user.getPassword());
		csvOutput.write(user.getRole());
		csvOutput.endRecord();			
		csvOutput.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void updateSheet(ArrayList<User> userList) throws IOException {			
		try {
			File file = new File(FILE_PATH);
			file.delete();
	        CsvWriter csvOutput = new CsvWriter(new FileWriter(FILE_PATH, true), ',');
	        csvOutput.write("UserId");
	        csvOutput.write("Password");
	        csvOutput.write("Role");
	        csvOutput.endRecord();
			for(User user:userList){
			csvOutput.write(user.getUserId());
			csvOutput.write(user.getPassword());
			csvOutput.write(user.getRole());
			csvOutput.endRecord();	
			}
			csvOutput.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	public void vaidateUser(User user) throws UserException {
		
		if(user.getUserId().equals("")||user.getUserId().equals(null))
			throw new UserException("Please enter Userid");	
		if(user.getPassword().equals("")||user.getPassword().equals(null))
			throw new UserException("Please enter password");
				
	}

	public void deleteUser(String userId) throws Exception {
		ArrayList<User> userList=new ArrayList<User>();
		try {
			userList=getAllUsers();
			int index=0;
			for(User c:userList){
				index++;
				if(c.getUserId().equals(userId)){
					break;
				}
			}
			userList.remove(index-1);
			updateSheet(userList);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	
		
	}

	public void updateUser(User user) throws IOException {
		ArrayList<User> userList=new ArrayList<User>();
		userList=getAllUsers();
			for(User c:userList){
				if(c.getUserId().equals(user.getUserId())){
					c.setPassword(user.getPassword());
				}				
			}
			updateSheet(userList);
			
	}

	public User getUser(String selected) throws IOException {
		ArrayList<User> userList=getAllUsers();
		for(User c:userList){
			if(c.getUserId().equals(selected))
				return c;
		}		
		return null;
	}

}
