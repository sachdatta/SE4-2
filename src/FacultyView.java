package com.faculty;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

import com.courses.CourseEvent;
import com.courses.Courses;
import com.login.Login;
import com.login.UserException;

import javax.swing.JLabel;

import java.awt.Font;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JCheckBox;

public class FacultyView {

	JFrame frame;
	public String action="ADD";
	public String userRole;
	public String facultyId;
	private JTextField facultyName;
	private JTextField facultyTitle;
	private JTextField facultyLoad;
	private JTextField facultyCourses;
	private JComboBox avlCourseIdList ;
	private JCheckBox chckbxMonday;
	private JCheckBox chckbxTuesday;
	private JCheckBox chckbxWednesday;
	private JCheckBox chckbxThursday;
	private JCheckBox chckbxFriday;

	/**
	 * Launch the application.
	 
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FacultyView window = new FacultyView();
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
	public FacultyView() throws Exception {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * @throws Exception 
	 */
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
				FacultyMain faculty;
				try {
					faculty = new FacultyMain();
					frame.setVisible(false);
					faculty.userRole=userRole;
					faculty.frame.setVisible(true);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		});
		btnBack.setBounds(10, 16, 89, 23);
		frame.getContentPane().add(btnBack);
		
		JLabel lblFacultyManagement = new JLabel("Faculty Management");
		lblFacultyManagement.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblFacultyManagement.setBounds(286, 48, 177, 23);
		frame.getContentPane().add(lblFacultyManagement);
		
		JLabel lblName = new JLabel("Name");
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblName.setBounds(84, 106, 46, 14);
		frame.getContentPane().add(lblName);
		
		facultyName = new JTextField();
		facultyName.setBounds(187, 104, 177, 20);
		frame.getContentPane().add(facultyName);
		facultyName.setColumns(10);
		
		JLabel lblTitle = new JLabel("Title");
		lblTitle.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblTitle.setBounds(405, 107, 46, 14);
		frame.getContentPane().add(lblTitle);
		
		facultyTitle = new JTextField();
		facultyTitle.setBounds(456, 105, 86, 20);
		frame.getContentPane().add(facultyTitle);
		facultyTitle.setColumns(10);
		
		JLabel lblTeachingLoad = new JLabel("Teaching Load");
		lblTeachingLoad.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblTeachingLoad.setBounds(84, 168, 100, 14);
		frame.getContentPane().add(lblTeachingLoad);
		
		facultyLoad = new JTextField();
		facultyLoad.setBounds(187, 166, 86, 20);
		frame.getContentPane().add(facultyLoad);
		facultyLoad.setColumns(10);
		
		JLabel lblCourses = new JLabel("Courses");
		lblCourses.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblCourses.setBounds(84, 230, 46, 14);
		frame.getContentPane().add(lblCourses);
		
		facultyCourses = new JTextField();
		facultyCourses.setBounds(187, 228, 177, 20);
		frame.getContentPane().add(facultyCourses);
		facultyCourses.setColumns(10);
		
		avlCourseIdList = new JComboBox();
		avlCourseIdList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String selectedId=(String) avlCourseIdList.getSelectedItem();
				if(facultyCourses.getText().equals("")||facultyCourses.getText().equals(null))
					facultyCourses.setText(selectedId);				
				else{
					String coursePrerequisitesId=facultyCourses.getText().toString();
					coursePrerequisitesId=coursePrerequisitesId+";"+selectedId;
					facultyCourses.setText(coursePrerequisitesId);
				}
			}
		});
		avlCourseIdList.setBounds(456, 228, 98, 20);
		frame.getContentPane().add(avlCourseIdList);
		avlCourseIdList.addItem("");
		
		JLabel lblAvailabeDays = new JLabel("Availabe Days");
		lblAvailabeDays.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblAvailabeDays.setBounds(84, 295, 100, 14);
		frame.getContentPane().add(lblAvailabeDays);
		
		chckbxMonday = new JCheckBox("Monday");
		chckbxMonday.setFont(new Font("Tahoma", Font.PLAIN, 12));
		chckbxMonday.setBounds(143, 329, 97, 23);
		frame.getContentPane().add(chckbxMonday);
		
		chckbxTuesday = new JCheckBox("Tuesday");
		chckbxTuesday.setFont(new Font("Tahoma", Font.PLAIN, 12));
		chckbxTuesday.setBounds(268, 330, 97, 23);
		frame.getContentPane().add(chckbxTuesday);
		
		chckbxWednesday = new JCheckBox("Wednesday");
		chckbxWednesday.setFont(new Font("Tahoma", Font.PLAIN, 12));
		chckbxWednesday.setBounds(420, 330, 97, 23);
		frame.getContentPane().add(chckbxWednesday);
		
		chckbxThursday = new JCheckBox("Thursday");
		chckbxThursday.setFont(new Font("Tahoma", Font.PLAIN, 12));
		chckbxThursday.setBounds(197, 364, 97, 23);
		frame.getContentPane().add(chckbxThursday);
		
		chckbxFriday = new JCheckBox("Friday");
		chckbxFriday.setFont(new Font("Tahoma", Font.PLAIN, 12));
		chckbxFriday.setBounds(355, 365, 97, 23);
		frame.getContentPane().add(chckbxFriday);
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
					if(action.equals("ADD")){
					addNewFaculty();
					dialog("Faculty added sucessfully");
					FacultyView courseFrame=new FacultyView();
					frame.setVisible(false);
					courseFrame.frame.setVisible(true);
					}else if(action.equals("UPDATE")){
						updateFaculty();
						dialog("Faculty updated sucessfully");
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
		btnSave.setBounds(289, 413, 89, 23);
		frame.getContentPane().add(btnSave);
		addAvlIds();
	}

	private void addAvlIds() throws Exception {
		CourseEvent ce=new CourseEvent();
		ArrayList<Courses> avl=new ArrayList<Courses>();
		avl=ce.getAllCourse();	
		for(Courses c:avl)
			avlCourseIdList.addItem(c.getNumber());
		
	}

	public void reload() {
		// TODO Auto-generated method stub
		
	}
	
	private void addNewFaculty() throws IOException, UserException {
		Faculty faculty=new Faculty();
		faculty.setTitle(facultyTitle.getText());
		faculty.setName(facultyName.getText());
		faculty.setFacultyLoad(facultyLoad.getText());
		faculty.setCourse(facultyCourses.getText());
		String avlDays;
		avlDays=(chckbxMonday.isSelected())?"1":"0";
		avlDays=(chckbxTuesday.isSelected())?avlDays.concat("1"):avlDays.concat("0");
		avlDays=(chckbxWednesday.isSelected())?avlDays.concat("1"):avlDays.concat("0");
		avlDays=(chckbxThursday.isSelected())?avlDays.concat("1"):avlDays.concat("0");
		avlDays=(chckbxFriday.isSelected())?avlDays.concat("1"):avlDays.concat("0");
		faculty.setAvlDays(avlDays);
		FacultyEvent fe=new FacultyEvent();
		fe.vaidateFaculty(faculty);
		fe.addFaculty(faculty);
	}
	
	private void updateFaculty() throws UserException, IOException {
		Faculty faculty=new Faculty();
		faculty.setFacultyId(facultyId);
		faculty.setTitle(facultyTitle.getText());
		faculty.setName(facultyName.getText());
		faculty.setFacultyLoad(facultyLoad.getText());
		faculty.setCourse(facultyCourses.getText());
		String avlDays;
		avlDays=(chckbxMonday.isSelected())?"1":"0";
		avlDays=(chckbxTuesday.isSelected())?avlDays.concat("1"):avlDays.concat("0");
		avlDays=(chckbxWednesday.isSelected())?avlDays.concat("1"):avlDays.concat("0");
		avlDays=(chckbxThursday.isSelected())?avlDays.concat("1"):avlDays.concat("0");
		avlDays=(chckbxFriday.isSelected())?avlDays.concat("1"):avlDays.concat("0");
		faculty.setAvlDays(avlDays);
		FacultyEvent fe=new FacultyEvent();
		fe.vaidateFaculty(faculty);
		fe.updateFaculty(faculty);
		
	}
	

	private void dialog(String msg) {
		JOptionPane.showMessageDialog(frame, msg);
	}

	public void viewSelected(String selected) throws IOException {
		FacultyEvent ce=new FacultyEvent();
		Faculty c=ce.getFaculty(selected);
		facultyId=c.getFacultyId();
		facultyName.setText(c.getName());
		facultyTitle.setText(c.getTitle());
		facultyLoad.setText(c.getFacultyLoad());
		facultyCourses.setText(c.getCourse());
		chckbxMonday.setSelected(false);
		chckbxTuesday.setSelected(false);
		chckbxWednesday.setSelected(false);
		chckbxThursday.setSelected(false);
		chckbxThursday.setSelected(false);
		
		String avlDays=c.getAvlDays();
		if((avlDays.charAt(0))=='1')
			chckbxMonday.setSelected(true);
		if((avlDays.charAt(1))=='1')
			chckbxTuesday.setSelected(true);
		if((avlDays.charAt(2))=='1')
			chckbxWednesday.setSelected(true);
		if((avlDays.charAt(3))=='1')
			chckbxThursday.setSelected(true);
		if((avlDays.charAt(4))=='1')
			chckbxFriday.setSelected(true);		
	}
}
