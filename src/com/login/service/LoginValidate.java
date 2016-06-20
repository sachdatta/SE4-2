package com.login.service;


import java.io.IOException;
import java.util.ArrayList;
import com.user.User;
import com.user.UserEvent;

public class LoginValidate {
	
	public String validate(String username,String password) throws IOException{
		String role="";
		try {			
			UserEvent ue=new UserEvent();
			ArrayList<User> usr=new ArrayList<User>();
			usr=ue.getAllUsers();
			for(User u:usr){
				if(u.getUserId().equals(username)){
					if(u.getPassword().equals(password)){
						role=u.getRole();
					}
				}
			}
			
			
		} catch (IOException ex){
			ex.printStackTrace();
		}
		return role;
	}
}
