package com.user;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

import com.login.Login;
import com.login.UserViewMain;

public class UserMain {

	public JFrame frame;
	protected String userRole;
	private JList userList;
	private JScrollPane  scrollPane;

	/**
	 * Launch the application.
	 
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserMain window = new UserMain();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @throws IOException 
	 */
	public UserMain() throws IOException {
		initialize();
	}

	public UserMain(String userRole2) throws IOException {
		userRole=userRole2;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * @throws IOException 
	 */
	private void initialize() throws IOException {
		frame = new JFrame();
		frame.setBounds(100, 100, 706, 503);
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
		btnLogout.setBounds(591, 11, 89, 23);
		frame.getContentPane().add(btnLogout);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				UserViewMain office;
				try {
					office = new UserViewMain(userRole);
					frame.setVisible(false);
					office.userRole=userRole;
					office.frame.setVisible(true);

				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}				
				
			}
		});
		btnBack.setBounds(42, 11, 89, 23);
		frame.getContentPane().add(btnBack);
		
		JLabel lblCourseManagement = new JLabel("User Management");
		lblCourseManagement.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblCourseManagement.setBounds(284, 38, 165, 29);
		frame.getContentPane().add(lblCourseManagement);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					addUser();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnAdd.setBounds(124, 302, 89, 23);
		frame.getContentPane().add(btnAdd);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					updateUser();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnUpdate.setBounds(313, 302, 89, 23);
		frame.getContentPane().add(btnUpdate);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					delete();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		});
		btnDelete.setBounds(504, 302, 89, 23);
		frame.getContentPane().add(btnDelete);
		
	
		viewUser();

	}

	private void viewUser() throws IOException {
		UserEvent ce=new UserEvent();
		ArrayList<User> avl=new ArrayList<User>();
		avl=ce.getAllUsers();
		String cList[] = new String[100];
		int i=0;
		for(User c:avl){
			if(c.getRole().equals("OFFICE")){
				cList[i]=c.getUserId();
				i++;
			}
			
		}
		userList = new JList(cList);		
		userList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane=new JScrollPane(userList);
		scrollPane.setBounds(261, 125, 155, 137);
		frame.getContentPane().add(scrollPane);
	
		
	}
	
	private void delete() throws Exception {
		if(userList.getSelectedValue() != null)
			try {
				{
					UserEvent fe=new UserEvent();
					fe.deleteUser(userList.getSelectedValue().toString());
					dialog("User deleted sucessfully");
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		else
			dialog("Please select a User");
		UserMain facultyFrame= new UserMain();
		frame.setVisible(false);
		facultyFrame.frame.setVisible(true);
		
	}
	
	private void dialog(String msg) {
		JOptionPane.showMessageDialog(frame, msg);
	}

	private void addUser() throws Exception {
		UserView facultyFrame= new UserView(userRole);
		frame.setVisible(false);
		facultyFrame.frame.setVisible(true);
		facultyFrame.action="ADD";
		
	}
	
	private void updateUser() throws Exception {
		UserView facultyFrame= new UserView(userRole);
		if(userList.getSelectedValue() != null){
				String selected=userList.getSelectedValue().toString().split(" ")[0];
				facultyFrame.viewSelected(selected);
				frame.setVisible(false);
				facultyFrame.frame.setVisible(true);
				facultyFrame.action="UPDATE";
		}else
			dialog("Please select a Course");
	}
	
	
}
