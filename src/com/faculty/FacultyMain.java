package com.faculty;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

import com.login.Login;
import com.login.UserViewMain;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

public class FacultyMain {

	public JFrame frame;
	public String userRole;
	private JButton btnLogout;
	private JList facultyList;
	private JScrollPane  scrollPane;

	/**
	 * Create the application.
	 * @throws Exception 
	 */
	public FacultyMain() throws Exception {
		initialize();
	}

	public FacultyMain(String userRole2) throws Exception {
		userRole=userRole2;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * @throws Exception 
	 */
	private void initialize() throws Exception {
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
					office.frame.setVisible(true);

				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}				
				
			}
		});
		btnBack.setBounds(42, 11, 89, 23);
		frame.getContentPane().add(btnBack);
		
		JLabel lblCourseManagement = new JLabel("Faculty Management");
		lblCourseManagement.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblCourseManagement.setBounds(284, 38, 165, 29);
		frame.getContentPane().add(lblCourseManagement);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					addFaculty();
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
					updateFaculty();
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
		
	
		viewFaculty();

	}

	private void viewFaculty() throws Exception {
		FacultyEvent ce=new FacultyEvent();
		ArrayList<Faculty> avl=new ArrayList<Faculty>();
		avl=ce.getAllFaculty();
		String cList[] = new String[100];
		int i=0;
		for(Faculty c:avl){
			cList[i]=c.getLastName()+"  "+c.getFirstName();
			i++;
		}
		facultyList = new JList(cList);		
		facultyList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane=new JScrollPane(facultyList);
		scrollPane.setBounds(155, 131, 396, 124);
		frame.getContentPane().add(scrollPane);
	
		
	}
	
	private void delete() throws Exception {
		if(facultyList.getSelectedValue() != null)
			try {
				{
					FacultyEvent fe=new FacultyEvent();
					fe.deleteFaculty(facultyList.getSelectedValue().toString().split(" ")[0]);
					dialog("Faculty deleted sucessfully");
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		else
			dialog("Please select a Course");
		FacultyMain facultyFrame= new FacultyMain();
		frame.setVisible(false);
		facultyFrame.frame.setVisible(true);
		
	}
	
	private void dialog(String msg) {
		JOptionPane.showMessageDialog(frame, msg);
	}

	private void addFaculty() throws Exception {
		FacultyView facultyFrame= new FacultyView(userRole);
		facultyFrame.reload();
		frame.setVisible(false);
		facultyFrame.frame.setVisible(true);
		facultyFrame.action="ADD";
		
	}
	
	private void updateFaculty() throws Exception {
		FacultyView facultyFrame= new FacultyView(userRole);
		if(facultyList.getSelectedValue() != null){
				//String selected=facultyList.getSelectedValue().toString().substring(0, 2);
				String selected=facultyList.getSelectedValue().toString().split(" ")[0];
				facultyFrame.viewSelected(selected);
				frame.setVisible(false);
				facultyFrame.frame.setVisible(true);
				facultyFrame.action="UPDATE";
		}else
			dialog("Please select a Course");
	}
	
	
}
