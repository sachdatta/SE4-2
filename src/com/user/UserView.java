package com.user;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JTextField;
import javax.swing.JButton;

import com.login.Login;
import com.login.UserException;

public class UserView {

	public String userRole;
	JFrame frame;
	private JTextField role;
	private JTextField userId;
	private JTextField password;
	public String action="ADD";

	/**
	 * Launch the application.
	 
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserView window = new UserView();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public UserView() {
		initialize();
	}

	public UserView(String userRole2) {
		userRole=userRole2;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 724, 499);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		
		JButton btnLogout = new JButton("Logout");
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Login login=new Login();
				frame.setVisible(false);
				login.frame.setVisible(true);
			}
		});
		btnLogout.setBounds(614, 16, 89, 23);
		frame.getContentPane().add(btnLogout);	
		
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				UserMain room;
				try {
					room = new UserMain(userRole);
					frame.setVisible(false);
					room.userRole=userRole;
					room.frame.setVisible(true);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		});
		btnBack.setBounds(10, 16, 89, 23);
		frame.getContentPane().add(btnBack);

		
		
		
		JLabel lblUserManagement = new JLabel("User Management");
		lblUserManagement.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblUserManagement.setBounds(305, 88, 184, 14);
		frame.getContentPane().add(lblUserManagement);
		
		JLabel lblRole = new JLabel("Role");
		lblRole.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblRole.setBounds(201, 143, 46, 14);
		frame.getContentPane().add(lblRole);
		
		role = new JTextField();
		role.setBounds(280, 141, 86, 20);
		frame.getContentPane().add(role);
		role.setColumns(10);
		role.setText("OFFICE");
		role.setEditable(false);
		
		JLabel lblUserId = new JLabel("User Id");
		lblUserId.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblUserId.setBounds(201, 191, 46, 14);
		frame.getContentPane().add(lblUserId);
		
		userId = new JTextField();
		userId.setBounds(280, 189, 161, 20);
		frame.getContentPane().add(userId);
		userId.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblPassword.setBounds(201, 244, 68, 14);
		frame.getContentPane().add(lblPassword);
		
		password = new JPasswordField();
		password.setBounds(280, 242, 161, 20);
		frame.getContentPane().add(password);
		password.setColumns(10);
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
					if(action.equals("ADD")){
					addNewUser();
					dialog("User added sucessfully");
					UserView courseFrame=new UserView(userRole);
					frame.setVisible(false);
					courseFrame.frame.setVisible(true);
					}else if(action.equals("UPDATE")){
						updateUser();
						dialog("User updated sucessfully");
					}
				}catch(UserException e1){
					if(!e1.getMessage().toString().equals("Invalid input"))
						dialog(e1.getMessage());
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}


		});

		
		btnSave.setBounds(259, 314, 89, 23);
		frame.getContentPane().add(btnSave);
	}
	
	private void dialog(String msg) {
		JOptionPane.showMessageDialog(frame, msg);
	}
	
	public void addNewUser() throws IOException, UserException{
		User u=new User();
		u.setUserId(userId.getText());
		u.setPassword(password.getText());
		u.setRole(role.getText());
		UserEvent ue=new UserEvent();
		ue.vaidateUser(u);
		ue.addUser(u);
	}
	
	public void updateUser() throws UserException, IOException{
		User u=new User();
		u.setUserId(userId.getText());
		u.setPassword(password.getText());
		u.setRole(role.getText());
		UserEvent ue=new UserEvent();
		ue.vaidateUser(u);
		ue.updateUser(u);
	}

	public void viewSelected(String selected) throws IOException {
		UserEvent ue=new UserEvent()	;
		User u=ue.getUser(selected);
		userId.setText(u.getUserId());
		password.setText(u.getPassword());
		
	}

}
