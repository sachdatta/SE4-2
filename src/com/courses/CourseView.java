package com.courses;

import com.courses.Courses;
import com.faculty.Faculty;
import com.faculty.FacultyEvent;
import com.login.UserException;
import com.login.Login;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;

import java.awt.Font;

import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JCheckBox;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

public class CourseView {

	public JFrame frame;
	private JTextField courseNumber;
	private JTextField courseName;
	private JTextField courseDescription;
	private JTextField courseHours;
	private JTextField courseCapacity;
	private JCheckBox chckbxSem1;
	private JCheckBox chckbxSem2;
	private JCheckBox chckbxSem3;
	private JTextField coursePrerequisites;
	public String userRole;
	public String action="ADD";
	private JList<?> courseList;
	private JList<?> teacherList;
	private JTextField teacher;
	private JScrollPane scrollPane1;
	private JScrollPane scrollPane2;
	private JButton btnAdd;
	private JButton btnAddTr;

	public CourseView() throws Exception {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * @throws Exception 
	 */
	private void initialize() throws Exception {
		frame = new JFrame();
		frame.setBounds(100, 100, 729, 491);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblCourses = new JLabel("Courses");
		lblCourses.setHorizontalAlignment(SwingConstants.CENTER);
		lblCourses.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		lblCourses.setBounds(202, 11, 268, 29);
		frame.getContentPane().add(lblCourses);
		
		JLabel lblNumber = new JLabel("Number*");
		lblNumber.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNumber.setBounds(73, 116, 58, 14);
		frame.getContentPane().add(lblNumber);
		
		courseNumber = new JTextField();
		courseNumber.setBounds(168, 114, 86, 20);
		frame.getContentPane().add(courseNumber);
		courseNumber.setColumns(10);
		
		JLabel lblName = new JLabel("Name*");
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblName.setBounds(72, 147, 46, 14);
		frame.getContentPane().add(lblName);
		
		courseName = new JTextField();
		courseName.setBounds(168, 145, 262, 20);
		frame.getContentPane().add(courseName);
		courseName.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Description");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel.setBounds(72, 178, 86, 14);
		frame.getContentPane().add(lblNewLabel);
		
		courseDescription = new JTextField();
		courseDescription.setBounds(168, 176, 262, 20);
		frame.getContentPane().add(courseDescription);
		courseDescription.setColumns(10);
		
		courseHours = new JTextField();
		courseHours.setBounds(168, 207, 86, 20);
		frame.getContentPane().add(courseHours);
		courseHours.setColumns(10);
		
		JLabel lblHours = new JLabel("Hours");
		lblHours.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblHours.setBounds(72, 209, 46, 14);
		frame.getContentPane().add(lblHours);
		
		JLabel lblCapacity = new JLabel("Capacity");
		lblCapacity.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblCapacity.setBounds(72, 240, 46, 14);
		frame.getContentPane().add(lblCapacity);
		
		courseCapacity = new JTextField();
		courseCapacity.setBounds(168, 238, 86, 20);
		frame.getContentPane().add(courseCapacity);
		courseCapacity.setColumns(10);
		
		JLabel lblPrerequestic = new JLabel("Prerequisites");
		lblPrerequestic.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblPrerequestic.setBounds(73, 269, 86, 40);
		frame.getContentPane().add(lblPrerequestic);
		
		coursePrerequisites = new JTextField();
		coursePrerequisites.setBounds(168, 280, 262, 20);
		frame.getContentPane().add(coursePrerequisites);
		coursePrerequisites.setColumns(10);
		
		
		JLabel lblSemesters = new JLabel("Semesters");
		lblSemesters.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblSemesters.setBounds(61, 344, 86, 14);
		frame.getContentPane().add(lblSemesters);
		
		chckbxSem1 = new JCheckBox("Fall");
		chckbxSem1.setBounds(168, 341, 98, 23);
		frame.getContentPane().add(chckbxSem1);
		
		chckbxSem2 = new JCheckBox("Spring");
		chckbxSem2.setBounds(268, 341, 98, 23);
		frame.getContentPane().add(chckbxSem2);
		
		chckbxSem3 = new JCheckBox("Summer");
		chckbxSem3.setBounds(398, 341, 86, 23);
		frame.getContentPane().add(chckbxSem3);
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try{
					if(action.equals("ADD")){
					addNewCourse();
					dialog("Course added sucessfully");
					CourseView courseFrame=new CourseView();
					frame.setVisible(false);
					courseFrame.frame.setVisible(true);
					}else if(action.equals("UPDATE")){
						updateCourse();
						dialog("Course updated sucessfully");
					}
				}catch(UserException e1){
					if(!e1.getMessage().toString().equals("Invalid input"))
						dialog(e1.getMessage());
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				
			}

		});
		btnSave.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnSave.setBounds(266, 418, 109, 23);
		frame.getContentPane().add(btnSave);
		
		JButton btnBack = new JButton("back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CourseMain office;
				try {
					office = new CourseMain();
					frame.setVisible(false);
					office.userRole=userRole;
					office.frame.setVisible(true);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		});
		btnBack.setBounds(10, 16, 89, 23);
		frame.getContentPane().add(btnBack);
		
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
		
		JLabel lblTeacher = new JLabel("Teacher");
		lblTeacher.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblTeacher.setBounds(73, 381, 74, 14);
		frame.getContentPane().add(lblTeacher);
		
		teacher = new JTextField();
		teacher.setBounds(168, 379, 262, 20);
		frame.getContentPane().add(teacher);
		teacher.setColumns(10);
		
		addAvlIds();
		
		
	}
	@SuppressWarnings("unchecked")
	private void addAvlIds() throws Exception {
		
		CourseEvent ce=new CourseEvent();
		ArrayList<Courses> avl=new ArrayList<Courses>();
		avl=ce.getAllCourse();
		String cList[] = new String[1000];
		int i=0;
		for(Courses c:avl){
			cList[i]=c.getNumber();
			i++;
		}
		courseList = new JList<Object>(cList);		
		courseList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane1 = new JScrollPane(courseList);
		scrollPane1.setBounds(546, 208, 86, 92);
		frame.getContentPane().add(scrollPane1);
		
		btnAdd = new JButton("Add ");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String selectedId=courseList.getSelectedValue().toString();
				if(coursePrerequisites.getText().equals("")||coursePrerequisites.getText().equals(null)||coursePrerequisites.getText().equals("none"))
					coursePrerequisites.setText(selectedId);
				else if(coursePrerequisites.getText().toString().contains(selectedId)){					
				}
				else{
					String coursePrerequisitesId=coursePrerequisites.getText().toString();
					coursePrerequisitesId=coursePrerequisitesId+","+selectedId;
					coursePrerequisites.setText(coursePrerequisitesId);
				}
			}
		});
		btnAdd.setBounds(440, 279, 89, 23);
		frame.getContentPane().add(btnAdd);
	
		
		
		FacultyEvent fe=new FacultyEvent();
		ArrayList<Faculty> avlF=new ArrayList<Faculty>();
		avlF=fe.getAllFaculty();	
		String tList[] = new String[100];
		int j=0;
		for(Faculty c:avlF){
			tList[j]=c.getLastName();
			j++;
		}
		teacherList = new JList<Object>(tList);		
		teacherList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane2 = new JScrollPane(teacherList);
		scrollPane2.setBounds(546, 332, 86, 92);
		frame.getContentPane().add(scrollPane2);		
		
		btnAddTr = new JButton("Add");
		btnAddTr.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String selectedId=teacherList.getSelectedValue().toString();
				if(teacher.getText().equals("")||teacher.getText().equals(null)||teacher.getText().equals("none"))
					teacher.setText(selectedId);
				else if(teacher.getText().toString().contains(selectedId)){
					
				}
				else{
					String teacherId=teacher.getText().toString();
					teacherId=teacherId+","+selectedId;
					teacher.setText(teacherId);
			}
			}
		});
		btnAddTr.setBounds(440, 378, 89, 23);
		frame.getContentPane().add(btnAddTr);
	}
	

	public void viewSelected(String selected) throws Exception {
		reload();
		CourseEvent ce=new CourseEvent();
		Courses c=ce.getCourse(selected);
		courseNumber.setText(c.getNumber());
		courseNumber.setEditable(false);
		courseName.setText(c.getName());
		courseDescription.setText(c.getDescription());
		courseHours.setText(c.getHours());;
		courseCapacity.setText(c.getCapacity());
		coursePrerequisites.setText(c.getPrerequested());
		String semester=c.getSemester();	
		if((semester.charAt(0))=='1')
			chckbxSem1.setSelected(true);
		if((semester.charAt(1))=='1')
			chckbxSem2.setSelected(true);
		if((semester.charAt(2))=='1')
			chckbxSem3.setSelected(true);
		teacher.setText(c.getTeacher());
		
	}
	
	public void reload(){
		courseNumber.setText("");
		courseNumber.setEditable(true);
		courseName.setText("");
		courseDescription.setText("");
		courseHours.setText("");
		courseCapacity.setText("");
		coursePrerequisites.setText("");
		chckbxSem1.setSelected(false);
		chckbxSem2.setSelected(false);
		chckbxSem3.setSelected(false);
		//avlCourseIdList.setSelectedIndex(0);
	}
	private void addNewCourse() throws Exception{
		Courses course=new Courses();
		course.setNumber(courseNumber.getText().toUpperCase());
		course.setName(courseName.getText());
		course.setDescription(courseDescription.getText());
		course.setHours(courseHours.getText());
		course.setCapacity(courseCapacity.getText());
		course.setPrerequested(coursePrerequisites.getText().toUpperCase());
		String semester;
		semester=(chckbxSem1.isSelected())?"1":"0";
		semester=(chckbxSem2.isSelected())?semester.concat("1"):semester.concat("0");
		semester=(chckbxSem3.isSelected())?semester.concat("1"):semester.concat("0");
		course.setTeacher(teacher.getText());
		course.setSemester(semester);
		CourseEvent ce=new CourseEvent();
		ce.validateCourse(course);
		ce.addCourse(course);
	}

	private void updateCourse() throws Exception {
		Courses course=new Courses();
		course.setNumber(courseNumber.getText());
		course.setName(courseName.getText());
		course.setDescription(courseDescription.getText());
		course.setHours(courseHours.getText());
		course.setCapacity(courseCapacity.getText());
		course.setPrerequested(coursePrerequisites.getText());
		String semester;
		semester=(chckbxSem1.isSelected())?"1":"0";
		semester=(chckbxSem2.isSelected())?semester.concat("1"):semester.concat("0");
		semester=(chckbxSem3.isSelected())?semester.concat("1"):semester.concat("0");
		course.setTeacher(teacher.getText());
		course.setSemester(semester);
		CourseEvent ce=new CourseEvent();
		ce.validateCourse(course);
		ce.updateCourse(course);
			
	}
	public void dialog(String msg){
		JOptionPane.showMessageDialog(frame, msg);
	}
}
