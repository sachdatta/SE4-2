package com.degree;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

import com.courses.CourseEvent;
import com.degree.DegreeMain;
import com.degree.main.DegreeEventHome;
import com.degree.main.DegreeHome;
import com.login.Login;
import com.login.UserException;
import com.courses.Courses;

public class DegreeView {

	JFrame frame;
	public String action="ADD";
	public String userRole;
	private JComboBox degreeCode ;
	private JComboBox type;
	private JTextField courses;
	private JList courseList;
	private JTextField degreeHours;
	private JTextField degreeDescription;
	private JScrollPane scrollPane1;
	private JScrollPane scrollPane2;
	private String courseListValues[] = new String[1000];
	private String cList[] = new String[1000];

	/**
	 * Launch the application.
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DegreeView window = new DegreeView();
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
	public DegreeView() throws Exception {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * @throws Exception 
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void initialize() throws Exception {
		frame = new JFrame();
		frame.setBounds(100, 100, 728, 498);
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
				DegreeMain degree;
				try {
					degree = new DegreeMain();
					frame.setVisible(false);
					degree.userRole=userRole;
					degree.frame.setVisible(true);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		});
		btnBack.setBounds(10, 16, 89, 23);
		frame.getContentPane().add(btnBack);
		
		JLabel lblDegreeManagement = new JLabel("Degree Management");
		lblDegreeManagement.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblDegreeManagement.setBounds(286, 48, 251, 23);
		frame.getContentPane().add(lblDegreeManagement);
		
		JLabel lblTeachingLoad = new JLabel("Degree Code");
		lblTeachingLoad.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblTeachingLoad.setBounds(84, 87, 100, 14);
		frame.getContentPane().add(lblTeachingLoad);
		
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
					if(action.equals("ADD")){
					addNewDegree();
					dialog("Degree added sucessfully");
					DegreeView courseFrame=new DegreeView();
					frame.setVisible(false);
					courseFrame.frame.setVisible(true);
					}else if(action.equals("UPDATE")){
						updateDegree();
						dialog("Degree updated sucessfully");
					}
				}catch(UserException e1){
					if(!e1.getMessage().toString().equals("Invalid input"))
						dialog(e1.getMessage());
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}


		});
		btnSave.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnSave.setBounds(286, 395, 89, 23);
		frame.getContentPane().add(btnSave);
		
		JLabel lblElective_1 = new JLabel("Courses");
		lblElective_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblElective_1.setBounds(84, 278, 89, 14);
		frame.getContentPane().add(lblElective_1);
		
		courses = new JTextField();
		courses.setBounds(187, 276, 177, 20);
		frame.getContentPane().add(courses);
		courses.setColumns(10);
		/*
		courseList = new JComboBox();
		courseList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String selectedId=(String) courseList.getSelectedItem();
				if(!selectedId.equals(""))
					courses.setText(selectedId);				
		
			}
		});
		courseList.setBounds(389, 276, 98, 20);
		frame.getContentPane().add(courseList);
		courseList.addItem("");*/
		
		JLabel lblHoursRequired = new JLabel("Hours Required");
		lblHoursRequired.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblHoursRequired.setBounds(84, 161, 100, 14);
		frame.getContentPane().add(lblHoursRequired);
		
		degreeHours = new JTextField();
		degreeHours.setBounds(187, 153, 86, 20);
		frame.getContentPane().add(degreeHours);
		degreeHours.setColumns(10);
		
		JLabel lblDescription = new JLabel("Description");
		lblDescription.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblDescription.setBounds(84, 123, 89, 14);
		frame.getContentPane().add(lblDescription);
		
		degreeDescription = new JTextField();
		degreeDescription.setBounds(187, 121, 177, 20);
		frame.getContentPane().add(degreeDescription);
		degreeDescription.setColumns(10);
		
		JLabel lblType = new JLabel("Type");
		lblType.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblType.setBounds(84, 194, 46, 14);
		frame.getContentPane().add(lblType);
		
		type = new JComboBox();
		type.setBounds(187, 192, 88, 20);
		frame.getContentPane().add(type);
		type.addItem("");
		type.addItem("Required");
		type.addItem("Elective");
		
		degreeCode = new JComboBox();
		degreeCode.setBounds(187, 85, 86, 20);
		frame.getContentPane().add(degreeCode);
		degreeCode.addItem("");
		
		
		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if(-1!=(courseList.getSelectedIndex())){
					
					String selectedId=courseList.getSelectedValue().toString();
					if(courses.getText().equals("")||courses.getText().equals(null))
						courses.setText(selectedId);
					else if(courses.getText().toString().contains(selectedId)){					
					}
					else{
						String courses1=courses.getText().toString();
						courses1=courses1+","+selectedId;
						courses.setText(courses1);
					}

				}else
					dialog("Please Select Course");
			}
		});
		btnAdd.setBounds(380, 275, 89, 23);
		frame.getContentPane().add(btnAdd);
		
		
		addAvlIds();
	}

	@SuppressWarnings("unchecked")
	private void addAvlIds() throws Exception {
		CourseEvent ce=new CourseEvent();
		ArrayList<Courses> avl=new ArrayList<Courses>();
		avl=ce.getAllCourse();
		int i=0;
		for(Courses c:avl){
			cList[i]=c.getNumber();
			i++;
		}

		
		courseList = new JList<Object>(cList);		
		courseList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane1 = new JScrollPane(courseList);
		scrollPane1.setBounds(479, 193, 86, 194);
		frame.getContentPane().add(scrollPane1);	
		
		
		
		DegreeEventHome de=new DegreeEventHome();
		ArrayList<DegreeHome> d=new ArrayList<DegreeHome>();
		d=de.getAllDegrees();
		for(DegreeHome dh:d)
			degreeCode.addItem(dh.getDegreeCode());
		
	}

	public void reload() {
		// TODO Auto-generated method stub
		
	}
	
	private void addNewDegree() throws IOException, UserException {
		Degree degree=new Degree();	
		degree.setDegreeCode(degreeCode.getSelectedItem().toString());
		degree.setDegreeDescription(degreeDescription.getText());
		degree.setDegreeHours(degreeHours.getText());
		degree.setType(type.getSelectedItem().toString());
		degree.setDegreeCourses(courses.getText());
		DegreeEvent fe=new DegreeEvent();
		fe.vaidateDegree(degree);
		fe.addDegree(degree);
	}
	
	private void updateDegree() throws UserException, IOException {
		Degree degree=new Degree();	
		degree.setDegreeCode(degreeCode.getSelectedItem().toString());
		degree.setDegreeDescription(degreeDescription.getText());
		degree.setDegreeHours(degreeHours.getText());
		degree.setType(type.getSelectedItem().toString());
		degree.setDegreeCourses(courses.getText());
		DegreeEvent fe=new DegreeEvent();
		fe.vaidateDegree(degree);
		fe.updateDegree(degree);
		
	}
	

	private void dialog(String msg) {
		JOptionPane.showMessageDialog(frame, msg);
	}

	public void viewSelected(String degreeCode1, String degreeDescription1) throws IOException {
		DegreeEvent ce=new DegreeEvent();
		Degree c=ce.getDegree(degreeCode1,degreeDescription1);
		degreeCode.setSelectedItem(c.getDegreeCode());
		degreeDescription.setText(c.getDegreeDescription());
		type.setSelectedItem(c.getType());
		degreeHours.setText(c.getDegreeHours());
		courses.setText(c.getDegreeCourses());
	}
}
