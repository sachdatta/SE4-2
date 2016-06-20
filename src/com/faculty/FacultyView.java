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
	private JTextField facultyLastName;
	private JTextField facultyTitle;
	private JTextField grandSchool;
	private JTextField facultyDegree;
	private JCheckBox chckbxMonday;
	private JCheckBox chckbxTuesday;
	private JCheckBox chckbxWednesday;
	private JCheckBox chckbxThursday;
	private JCheckBox chckbxFriday;
	private JTextField facultyFirstName;
	private JLabel lblMaxLoadFall;
	private JTextField fallLoad;
	private JTextField springLoad;
	private JLabel lblFall;
	private JLabel lblSpring;
	private JLabel lblSummer;
	private JTextField summerLoad;

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
		lblName.setBounds(84, 130, 46, 14);
		frame.getContentPane().add(lblName);
		
		facultyLastName = new JTextField();
		facultyLastName.setBounds(187, 128, 122, 20);
		frame.getContentPane().add(facultyLastName);
		facultyLastName.setColumns(10);
		
		JLabel lblTitle = new JLabel("Title");
		lblTitle.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblTitle.setBounds(84, 94, 46, 14);
		frame.getContentPane().add(lblTitle);
		
		facultyTitle = new JTextField();
		facultyTitle.setBounds(187, 92, 86, 20);
		frame.getContentPane().add(facultyTitle);
		facultyTitle.setColumns(10);
		
		JLabel lblTeachingLoad = new JLabel("Grand School");
		lblTeachingLoad.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblTeachingLoad.setBounds(84, 168, 100, 14);
		frame.getContentPane().add(lblTeachingLoad);
		
		grandSchool = new JTextField();
		grandSchool.setBounds(187, 166, 86, 20);
		frame.getContentPane().add(grandSchool);
		grandSchool.setColumns(10);
		
		JLabel lblCourses = new JLabel("Degree");
		lblCourses.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblCourses.setBounds(84, 203, 46, 14);
		frame.getContentPane().add(lblCourses);
		
		facultyDegree = new JTextField();
		facultyDegree.setBounds(187, 197, 86, 20);
		frame.getContentPane().add(facultyDegree);
		facultyDegree.setColumns(10);
		
		JLabel lblAvailabeDays = new JLabel("Availabe Days");
		lblAvailabeDays.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblAvailabeDays.setBounds(54, 308, 100, 14);
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
		
		facultyFirstName = new JTextField();
		facultyFirstName.setBounds(331, 128, 108, 20);
		frame.getContentPane().add(facultyFirstName);
		facultyFirstName.setColumns(10);
		
		lblMaxLoadFall = new JLabel("Max Load Fall");
		lblMaxLoadFall.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblMaxLoadFall.setBounds(84, 237, 108, 14);
		frame.getContentPane().add(lblMaxLoadFall);
		
		fallLoad = new JTextField();
		fallLoad.setBounds(143, 262, 59, 20);
		frame.getContentPane().add(fallLoad);
		fallLoad.setColumns(10);
		
		springLoad = new JTextField();
		springLoad.setBounds(331, 262, 59, 20);
		frame.getContentPane().add(springLoad);
		springLoad.setColumns(10);
		
		lblFall = new JLabel("Fall");
		lblFall.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblFall.setBounds(84, 264, 46, 14);
		frame.getContentPane().add(lblFall);
		
		lblSpring = new JLabel("Spring");
		lblSpring.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblSpring.setBounds(263, 264, 46, 14);
		frame.getContentPane().add(lblSpring);
		
		lblSummer = new JLabel("Summer");
		lblSummer.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblSummer.setBounds(443, 265, 74, 14);
		frame.getContentPane().add(lblSummer);
		
		summerLoad = new JTextField();
		summerLoad.setBounds(517, 262, 59, 20);
		frame.getContentPane().add(summerLoad);
		summerLoad.setColumns(10);
	}


	public void reload() {
		// TODO Auto-generated method stub
		
	}
	
	private void addNewFaculty() throws IOException, UserException {
		Faculty faculty=new Faculty();
		faculty.setTitle(facultyTitle.getText());
		faculty.setLastName(facultyLastName.getText());
		faculty.setFirstName(facultyFirstName.getText());
		faculty.setGrandSchool(grandSchool.getText());
		faculty.setDegree(facultyDegree.getText());
		if(fallLoad.getText().equals("")||fallLoad.getText().equals(null))
			faculty.setFallLoad("0");
		else
			faculty.setFallLoad(fallLoad.getText());
		
		if(springLoad.getText().equals("")||springLoad.getText().equals(null))
			faculty.setSpringLoad("0");
		else
			faculty.setSpringLoad(springLoad.getText());
		
		if(summerLoad.getText().equals("")||summerLoad.getText().equals(null))
			faculty.setSummerLoad("0");
		else
			faculty.setSummerLoad(summerLoad.getText());
		
		String avlDays="";
		if(chckbxMonday.isSelected())
			avlDays="M";
		if(chckbxTuesday.isSelected())
			avlDays=avlDays+"T";
		if(chckbxWednesday.isSelected())
			avlDays=avlDays+"W";
		if(chckbxThursday.isSelected())
			avlDays=avlDays+"R";
		if(chckbxFriday.isSelected())
			avlDays=avlDays+"F";
		faculty.setAvlDays(avlDays);
		FacultyEvent fe=new FacultyEvent();
		fe.vaidateFaculty(faculty);
		fe.addFaculty(faculty);
	}
	
	private void updateFaculty() throws UserException, IOException {
		Faculty faculty=new Faculty();
		faculty.setTitle(facultyTitle.getText());
		faculty.setLastName(facultyLastName.getText());
		faculty.setFirstName(facultyFirstName.getText());
		faculty.setGrandSchool(grandSchool.getText());
		faculty.setDegree(facultyDegree.getText());
		if(fallLoad.getText().equals("")||fallLoad.getText().equals(null))
			faculty.setFallLoad("0");
		else
			faculty.setFallLoad(fallLoad.getText());
		
		if(springLoad.getText().equals("")||springLoad.getText().equals(null))
			faculty.setSpringLoad("0");
		else
			faculty.setSpringLoad(springLoad.getText());
		
		if(summerLoad.getText().equals("")||summerLoad.getText().equals(null))
			faculty.setSummerLoad("0");
		else
			faculty.setSummerLoad(summerLoad.getText());
		String avlDays="";
		if(chckbxMonday.isSelected())
			avlDays="M";
		if(chckbxTuesday.isSelected())
			avlDays=avlDays+"T";
		if(chckbxWednesday.isSelected())
			avlDays=avlDays+"W";
		if(chckbxThursday.isSelected())
			avlDays=avlDays+"R";
		if(chckbxFriday.isSelected())
			avlDays=avlDays+"F";
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
		facultyFirstName.setText(c.getFirstName());
		facultyFirstName.setEditable(false);
		facultyLastName.setText(c.getLastName());
		facultyLastName.setEditable(false);
		facultyTitle.setText(c.getTitle());
		grandSchool.setText(c.getGrandSchool());
		facultyDegree.setText(c.getDegree());
		fallLoad.setText(c.getFallLoad());
		springLoad.setText(c.getSpringLoad());
		summerLoad.setText(c.getSummerLoad());
		chckbxMonday.setSelected(false);
		chckbxTuesday.setSelected(false);
		chckbxWednesday.setSelected(false);
		chckbxThursday.setSelected(false);
		chckbxThursday.setSelected(false);
		
		String avlDays=c.getAvlDays();
		if((avlDays.contains("M")))
			chckbxMonday.setSelected(true);
		if((avlDays.contains("T")))
			chckbxTuesday.setSelected(true);
		if((avlDays.contains("W")))
			chckbxWednesday.setSelected(true);
		if((avlDays.contains("R")))
			chckbxThursday.setSelected(true);
		if((avlDays.contains("F")))
			chckbxFriday.setSelected(true);		
	}
}
