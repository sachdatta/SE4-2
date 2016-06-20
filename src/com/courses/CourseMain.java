package com.courses;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import com.login.Login;
import com.login.UserViewMain;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.ListSelectionModel;

public class CourseMain {

	public JFrame frame;
	public String userRole;
	private JList courseList;
	private JScrollPane  scrollPane;

	/**
	 * Launch the application.
	 
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CourseMain window = new CourseMain();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @throws Exception 
	 */
	public CourseMain() throws Exception {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * @throws Exception 
	 */
	private void initialize() throws Exception {
		frame = new JFrame();
		frame.setBounds(100, 100, 717, 486);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblCourseManagement = new JLabel("Course Management");
		lblCourseManagement.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblCourseManagement.setBounds(284, 38, 165, 29);
		frame.getContentPane().add(lblCourseManagement);
		
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
				UserViewMain office=new UserViewMain();
				
				frame.setVisible(false);
				office.userRole=userRole;
				office.frame.setVisible(true);

			}
		});
		btnBack.setBounds(42, 11, 89, 23);
		frame.getContentPane().add(btnBack);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					addCourse();
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
					updateCourse();
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
		
	
		viewCourses();
	}

	@SuppressWarnings("unchecked")
	private void viewCourses() throws Exception {
		CourseEvent ce=new CourseEvent();
		ArrayList<Courses> avl=new ArrayList<Courses>();
		avl=ce.getAllCourse();
		String cList[] = new String[100];
		int i=0;
		for(Courses c:avl){
			cList[i]=c.getNumber()+"   "+c.getName()+"   "+c.getDescription();
			i++;
		}
		courseList = new JList(cList);		
		courseList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		//courseList.setBounds(155, 131, 396, 124);		
		scrollPane=new JScrollPane(courseList);
		scrollPane.setBounds(155, 131, 396, 124);
		//frame.getContentPane().add(courseList);
		frame.getContentPane().add(scrollPane);
	}
	
	private void addCourse() throws Exception{
		CourseView courseFrame= new CourseView();
		courseFrame.reload();
		frame.setVisible(false);
		courseFrame.frame.setVisible(true);
		courseFrame.action="ADD";
	}
	
	private void updateCourse() throws Exception{
		CourseView courseFrame= new CourseView();
		if(courseList.getSelectedValue() != null){
				String selected=courseList.getSelectedValue().toString().substring(0, 9);
				courseFrame.viewSelected(selected);
				frame.setVisible(false);
				courseFrame.frame.setVisible(true);
				courseFrame.action="UPDATE";
		}else
			dialog("Please select a Course");
	}
	public void dialog(String msg){
		JOptionPane.showMessageDialog(frame, msg);
	}
	 
	public void delete() throws Exception{
		if(courseList.getSelectedValue() != null){
			CourseEvent ce=new CourseEvent();
			ce.deleteCourse(courseList.getSelectedValue().toString().substring(0, 9));
			dialog("Course deleted sucessfully");
		}else
			dialog("Please select a Course");
		CourseMain courseFrame= new CourseMain();
		frame.setVisible(false);
		courseFrame.frame.setVisible(true);
	}
	
}
