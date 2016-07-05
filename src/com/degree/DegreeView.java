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
	private JTextField coursesRequired;
	private JList courseList;
	private JList courseListEle1;
	private JList courseListEle2;
	private JTextField degreeHoursRequired;
	private JTextField degreeDescriptionRequired;
	private JScrollPane scrollPane1;
	//private JScrollPane scrollPane2;
	private JScrollPane scrollPane3;
	private JScrollPane scrollPane4;
	private String cList[] = new String[1000];
	private JTextField degreeDescriptionElective1;
	private JTextField degreeHoursElective1;
	private JTextField coursesElective1;
	private JTextField degreeDescriptionElective2;
	private JTextField degreeHoursElective2;
	private JTextField coursesElective2;
	private JTextField degreeCode;

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
	public DegreeView(String role) throws Exception {
		userRole=role;
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
		
		
		JLabel lblLoggesAs = new JLabel("Logged as "+userRole+" User");
		lblLoggesAs.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblLoggesAs.setBounds(141, 14, 129, 14);
		frame.getContentPane().add(lblLoggesAs);
		
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
					degree = new DegreeMain(userRole);
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
		btnSave.setBounds(286, 436, 89, 23);
		frame.getContentPane().add(btnSave);
		
		JLabel lblElective_1 = new JLabel("Courses");
		lblElective_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblElective_1.setBounds(86, 210, 117, 14);
		frame.getContentPane().add(lblElective_1);
		
		coursesRequired = new JTextField();
		coursesRequired.setBounds(187, 208, 251, 20);
		frame.getContentPane().add(coursesRequired);
		coursesRequired.setColumns(10);
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
		lblHoursRequired.setBounds(84, 181, 100, 14);
		frame.getContentPane().add(lblHoursRequired);
		
		degreeHoursRequired = new JTextField();
		degreeHoursRequired.setBounds(187, 179, 86, 20);
		frame.getContentPane().add(degreeHoursRequired);
		degreeHoursRequired.setColumns(10);
		
		JLabel lblDescription = new JLabel("Description");
		lblDescription.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblDescription.setBounds(84, 150, 89, 14);
		frame.getContentPane().add(lblDescription);
		
		degreeDescriptionRequired = new JTextField();
		degreeDescriptionRequired.setBounds(187, 144, 177, 20);
		frame.getContentPane().add(degreeDescriptionRequired);
		degreeDescriptionRequired.setColumns(10);
		
		
		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if(-1!=(courseList.getSelectedIndex())){
					
					String selectedId=courseList.getSelectedValue().toString();
					if(coursesRequired.getText().equals("")||coursesRequired.getText().equals(null))
						coursesRequired.setText(selectedId);
					else if(coursesRequired.getText().toString().contains(selectedId)){					
					}
					else{
						String courses1=coursesRequired.getText().toString();
						courses1=courses1+","+selectedId;
						coursesRequired.setText(courses1);
					}

				}else
					dialog("Please Select Course");
			}
		});
		btnAdd.setBounds(448, 207, 89, 23);
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
		scrollPane1.setBounds(547, 110, 86, 111);
		frame.getContentPane().add(scrollPane1);
		
		courseListEle1 = new JList<Object>(cList);		
		courseListEle1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane3 = new JScrollPane(courseListEle1);
		scrollPane3.setBounds(547, 226, 86, 111);
		frame.getContentPane().add(scrollPane3);
		

		courseListEle2 = new JList<Object>(cList);		
		courseListEle2.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane4 = new JScrollPane(courseListEle2);
		scrollPane4.setBounds(547, 344, 86, 111);
		frame.getContentPane().add(scrollPane4);
		
		
		JLabel lblRequiredCourse = new JLabel("Required Course");
		lblRequiredCourse.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblRequiredCourse.setBounds(32, 118, 152, 14);
		frame.getContentPane().add(lblRequiredCourse);
		
		JLabel lblElective = new JLabel("Elective 1");
		lblElective.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblElective.setBounds(32, 235, 152, 14);
		frame.getContentPane().add(lblElective);
		
		JLabel label = new JLabel("Description");
		label.setFont(new Font("Tahoma", Font.PLAIN, 12));
		label.setBounds(84, 260, 89, 14);
		frame.getContentPane().add(label);
		
		degreeDescriptionElective1 = new JTextField();
		degreeDescriptionElective1.setColumns(10);
		degreeDescriptionElective1.setBounds(187, 258, 177, 20);
		frame.getContentPane().add(degreeDescriptionElective1);
		
		JLabel label_1 = new JLabel("Hours Required");
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		label_1.setBounds(84, 291, 100, 14);
		frame.getContentPane().add(label_1);
		
		degreeHoursElective1 = new JTextField();
		degreeHoursElective1.setColumns(10);
		degreeHoursElective1.setBounds(187, 289, 86, 20);
		frame.getContentPane().add(degreeHoursElective1);
		
		JLabel label_2 = new JLabel("Courses");
		label_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		label_2.setBounds(84, 325, 117, 14);
		frame.getContentPane().add(label_2);
		
		coursesElective1 = new JTextField();
		coursesElective1.setColumns(10);
		coursesElective1.setBounds(187, 316, 251, 20);
		frame.getContentPane().add(coursesElective1);
		
		JLabel lblElective_2 = new JLabel("Elective 2");
		lblElective_2.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblElective_2.setBounds(32, 345, 152, 14);
		frame.getContentPane().add(lblElective_2);
		
		JLabel label_3 = new JLabel("Description");
		label_3.setFont(new Font("Tahoma", Font.PLAIN, 12));
		label_3.setBounds(84, 361, 89, 14);
		frame.getContentPane().add(label_3);
		
		degreeDescriptionElective2 = new JTextField();
		degreeDescriptionElective2.setColumns(10);
		degreeDescriptionElective2.setBounds(187, 359, 177, 20);
		frame.getContentPane().add(degreeDescriptionElective2);
		
		JLabel label_4 = new JLabel("Hours Required");
		label_4.setFont(new Font("Tahoma", Font.PLAIN, 12));
		label_4.setBounds(84, 389, 100, 14);
		frame.getContentPane().add(label_4);
		
		degreeHoursElective2 = new JTextField();
		degreeHoursElective2.setColumns(10);
		degreeHoursElective2.setBounds(187, 390, 86, 20);
		frame.getContentPane().add(degreeHoursElective2);
		
		JLabel label_5 = new JLabel("Courses");
		label_5.setFont(new Font("Tahoma", Font.PLAIN, 12));
		label_5.setBounds(84, 416, 117, 14);
		frame.getContentPane().add(label_5);
		
		coursesElective2 = new JTextField();
		coursesElective2.setColumns(10);
		coursesElective2.setBounds(187, 414, 251, 20);
		frame.getContentPane().add(coursesElective2);
		
		JButton addEle1 = new JButton("Add");
		addEle1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
					if(-1!=(courseListEle1.getSelectedIndex())){
					
					String selectedId=courseListEle1.getSelectedValue().toString();
					if(coursesElective1.getText().equals("")||coursesElective1.getText().equals(null))
						coursesElective1.setText(selectedId);
					else if(coursesElective1.getText().toString().contains(selectedId)){					
					}
					else{
						String courses1=coursesElective1.getText().toString();
						courses1=courses1+","+selectedId;
						coursesElective1.setText(courses1);
					}

				}else
					dialog("Please Select Course");

			}
		});
		addEle1.setBounds(448, 315, 89, 23);
		frame.getContentPane().add(addEle1);
		
		JButton addEle2 = new JButton("Add");
		addEle2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
					if(-1!=(courseListEle2.getSelectedIndex())){
					
					String selectedId=courseListEle2.getSelectedValue().toString();
					if(coursesElective2.getText().equals("")||coursesElective2.getText().equals(null))
						coursesElective2.setText(selectedId);
					else if(coursesElective2.getText().toString().contains(selectedId)){					
					}
					else{
						String courses1=coursesElective2.getText().toString();
						courses1=courses1+","+selectedId;
						coursesElective2.setText(courses1);
					}

				}else
					dialog("Please Select Course");


				
			}
		});
		addEle2.setBounds(448, 413, 89, 23);
		frame.getContentPane().add(addEle2);
		
		degreeCode = new JTextField();
		degreeCode.setBounds(187, 85, 86, 20);
		frame.getContentPane().add(degreeCode);
		degreeCode.setColumns(10);
		
		
	}

	public void reload() {
		// TODO Auto-generated method stub
		
	}
	
	private void addNewDegree() throws IOException, UserException {
		ArrayList<Degree> degreeList= new ArrayList<Degree>();
		
		Degree degree=new Degree();	
		degree.setDegreeCode(degreeCode.getText().toString());
		degree.setDegreeDescription(degreeDescriptionRequired.getText());
		degree.setDegreeHours(degreeHoursRequired.getText());
		degree.setType("Required");
		degree.setDegreeCourses(coursesRequired.getText());
		
		Degree degreeEle1=new Degree();	
		degreeEle1.setDegreeCode(degreeCode.getText().toString());
		degreeEle1.setDegreeDescription(degreeDescriptionElective1.getText());
		degreeEle1.setDegreeHours(degreeHoursElective1.getText());
		degreeEle1.setType("Elective");
		degreeEle1.setDegreeCourses(coursesElective1.getText());
		
		
		Degree degreeEle2=new Degree();	
		degreeEle2.setDegreeCode(degreeCode.getText().toString());
		degreeEle2.setDegreeDescription(degreeDescriptionElective2.getText());
		degreeEle2.setDegreeHours(degreeHoursElective2.getText());
		degreeEle2.setType("Elective");
		degreeEle2.setDegreeCourses(coursesElective2.getText());
		
		
		DegreeEvent fe=new DegreeEvent();
		fe.vaidateDegree(degree);
		fe.vaidateDegree(degreeEle1);
		fe.vaidateDegree(degreeEle2);
		degreeList.add(degree);
		degreeList.add(degreeEle1);
		degreeList.add(degreeEle2);		
		fe.addDegree(degreeList);
	}
	
	private void updateDegree() throws UserException, IOException {
		ArrayList<Degree> degreeList= new ArrayList<Degree>();
		
		Degree degree=new Degree();	
		degree.setDegreeCode(degreeCode.getText().toString());
		degree.setDegreeDescription(degreeDescriptionRequired.getText());
		degree.setDegreeHours(degreeHoursRequired.getText());
		degree.setType("Required");
		degree.setDegreeCourses(coursesRequired.getText());
		
		Degree degreeEle1=new Degree();	
		degreeEle1.setDegreeCode(degreeCode.getText().toString());
		degreeEle1.setDegreeDescription(degreeDescriptionElective1.getText());
		degreeEle1.setDegreeHours(degreeHoursElective1.getText());
		degreeEle1.setType("Elective");
		degreeEle1.setDegreeCourses(coursesElective1.getText());
		
		
		Degree degreeEle2=new Degree();	
		degreeEle2.setDegreeCode(degreeCode.getText().toString());
		degreeEle2.setDegreeDescription(degreeDescriptionElective2.getText());
		degreeEle2.setDegreeHours(degreeHoursElective2.getText());
		degreeEle2.setType("Elective");
		degreeEle2.setDegreeCourses(coursesElective2.getText());
		
		
		DegreeEvent fe=new DegreeEvent();
		fe.vaidateDegree(degree);
		fe.vaidateDegree(degreeEle1);
		fe.vaidateDegree(degreeEle2);
		degreeList.add(degree);
		degreeList.add(degreeEle1);
		degreeList.add(degreeEle2);		
	
		fe.updateDegree(degreeList);
		
	}
	

	private void dialog(String msg) {
		JOptionPane.showMessageDialog(frame, msg);
	}

	public void viewSelected(String degreeCode1) throws IOException {
		DegreeEvent ce=new DegreeEvent();
		degreeCode.setEditable(false);
		ArrayList<Degree> d=ce.getDegree(degreeCode1);
		for(Degree c:d){
			if(c.getType().equals("Required")){
				degreeCode.setText(c.getDegreeCode());
				degreeDescriptionRequired.setText(c.getDegreeDescription());
				degreeHoursRequired.setText(c.getDegreeHours());
				coursesRequired.setText(c.getDegreeCourses());				
			}
			if(c.getType().equals("Elective")&&( degreeDescriptionElective1.getText().equals("")||degreeDescriptionElective1.getText().equals(null))){
				degreeDescriptionElective1.setText(c.getDegreeDescription());
				degreeHoursElective1.setText(c.getDegreeHours());
				coursesElective1.setText(c.getDegreeCourses());
			}
			if(c.getType().equals("Elective")&&!( degreeDescriptionElective1.getText().equals("")||degreeDescriptionElective1.getText().equals(null))){
				degreeDescriptionElective2.setText(c.getDegreeDescription());
				degreeHoursElective2.setText(c.getDegreeHours());
				coursesElective2.setText(c.getDegreeCourses());	
			}
			
			
		}
	}
}
