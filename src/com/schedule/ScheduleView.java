package com.schedule;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

import com.courses.CourseEvent;
import com.courses.Courses;
import com.csvreader.CsvReader;
import com.login.Login;

import javax.swing.JTextField;
import javax.swing.JComboBox;

import org.jdatepicker.impl.DateComponentFormatter;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

public class ScheduleView {

	JFrame frame;
	public String userRole;
	public String action;
	private JTextField term;
	private JTextField status;
	private JTextField courseId;
	private JTextField courseName;
	private JTextField location;
	private JTextField description;
	private JTextField availabe;
	private JTextField capacity;
	private JTextField waitlist;
	private JTextField credits;
	private JTextField cues;
	private JTextField academeiLevel;
	private JList<Object> semesterList;
	private JList<Object> courseList;
	private JScrollPane scrollPane2;
	private String[] avlCourseList=new String[100];
	private int fCount=0;
	
	private static final String FILE_PATH_SEMESTER = "C:/Input/SemesterDetails.csv";
	private JTextField faculty;

	/**
	 * Launch the application.
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ScheduleView window = new ScheduleView();
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
	public ScheduleView() throws IOException {
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
				ScheduleMain sch=new ScheduleMain();
				
				frame.setVisible(false);				
				sch.userRole=userRole;
				sch.frame.setVisible(true);

			}
		});
		btnBack.setBounds(42, 11, 89, 23);
		frame.getContentPane().add(btnBack);
		

		JLabel lblScheduleManagement = new JLabel("Schedule Management");
		lblScheduleManagement.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblScheduleManagement.setBounds(256, 54, 219, 23);
		frame.getContentPane().add(lblScheduleManagement);
		
		JLabel lblTerm = new JLabel("Term");
		lblTerm.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblTerm.setBounds(63, 102, 46, 14);
		frame.getContentPane().add(lblTerm);
		
		
		CsvReader semester = new CsvReader(FILE_PATH_SEMESTER);
		String[] sem=new String[100];
		int i=0;
		while(semester.readRecord()){
			sem[i]=semester.get(0);
			i++;
		}
		sem[0]="";
		semester.close();
		semesterList = new JList<Object>(sem);		
		semesterList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		JScrollPane scrollPane1 = new JScrollPane(semesterList);
		scrollPane1.setBounds(389, 90, 86, 57);
		frame.getContentPane().add(scrollPane1);		
	
		
		term = new JTextField();
		term.setBounds(178, 100, 86, 20);
		frame.getContentPane().add(term);
		term.setColumns(10);
		term.setEditable(false);
		
		JLabel lblStatus = new JLabel("Status");
		lblStatus.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblStatus.setBounds(63, 133, 46, 14);
		frame.getContentPane().add(lblStatus);
		
		status = new JTextField();
		status.setBounds(178, 131, 86, 20);
		frame.getContentPane().add(status);
		status.setColumns(10);
		
		JLabel lblCourseName = new JLabel("Course Id");
		lblCourseName.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblCourseName.setBounds(63, 164, 77, 14);
		frame.getContentPane().add(lblCourseName);
		
		int j=0;
		int l=avlCourseList.length;
		while(j<l){
			avlCourseList[j]=" ";
			j++;
		}
		
		/*courseList = new JList<Object>(avlCourseList);		
		courseList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane2 = new JScrollPane(courseList);
		scrollPane2.setBounds(389, 158, 86, 89);
		frame.getContentPane().add(scrollPane2);	*/	
	
		
		courseId = new JTextField();
		courseId.setBounds(178, 162, 89, 20);
		frame.getContentPane().add(courseId);
		courseId.setColumns(10);
		courseId.setEditable(false);
		
		JLabel lblCourseName_1 = new JLabel("Course Name");
		lblCourseName_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblCourseName_1.setBounds(63, 198, 95, 14);
		frame.getContentPane().add(lblCourseName_1);
		
		courseName = new JTextField();
		courseName.setBounds(178, 196, 151, 20);
		frame.getContentPane().add(courseName);
		courseName.setColumns(10);
		courseName.setEditable(false);
		
		JLabel lblLocation = new JLabel("Location");
		lblLocation.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblLocation.setBounds(63, 229, 77, 14);
		frame.getContentPane().add(lblLocation);
		
		location = new JTextField();
		location.setBounds(178, 227, 86, 20);
		frame.getContentPane().add(location);
		location.setColumns(10);
		
		JLabel lblMeetingInformation = new JLabel("Meeting Information");
		lblMeetingInformation.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblMeetingInformation.setBounds(63, 269, 115, 14);
		frame.getContentPane().add(lblMeetingInformation);
		
		JLabel lblFrom = new JLabel("From");
		lblFrom.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblFrom.setBounds(63, 302, 46, 14);
		frame.getContentPane().add(lblFrom);
		
		JLabel lblTo = new JLabel("To");
		lblTo.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblTo.setBounds(231, 302, 46, 14);
		frame.getContentPane().add(lblTo);
		
		JLabel lblDescription = new JLabel("Description");
		lblDescription.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblDescription.setBounds(387, 302, 77, 14);
		frame.getContentPane().add(lblDescription);
		
		description = new JTextField();
		description.setBounds(486, 300, 194, 20);
		frame.getContentPane().add(description);
		description.setColumns(10);
		
		JLabel lblFaculty = new JLabel("Faculty");
		lblFaculty.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblFaculty.setBounds(63, 346, 58, 14);
		frame.getContentPane().add(lblFaculty);
		
		JLabel lblAvailable = new JLabel("Capacity");
		lblAvailable.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblAvailable.setBounds(417, 346, 58, 14);
		frame.getContentPane().add(lblAvailable);
		
		JLabel label = new JLabel("Available");
		label.setFont(new Font("Tahoma", Font.PLAIN, 12));
		label.setBounds(315, 347, 58, 14);
		frame.getContentPane().add(label);
		
		JLabel lblWaitlist = new JLabel("WaitList");
		lblWaitlist.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblWaitlist.setBounds(523, 347, 58, 14);
		frame.getContentPane().add(lblWaitlist);
		
		availabe = new JTextField();
		availabe.setBounds(370, 344, 33, 20);
		frame.getContentPane().add(availabe);
		availabe.setColumns(10);
		
		capacity = new JTextField();
		capacity.setColumns(10);
		capacity.setBounds(469, 344, 33, 20);
		frame.getContentPane().add(capacity);
		capacity.setEditable(false);
		
		waitlist = new JTextField();
		waitlist.setColumns(10);
		waitlist.setBounds(580, 344, 33, 20);
		frame.getContentPane().add(waitlist);
		
		JLabel lblCredeits = new JLabel("Credeits");
		lblCredeits.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblCredeits.setBounds(80, 383, 68, 14);
		frame.getContentPane().add(lblCredeits);
		
		credits = new JTextField();
		credits.setBounds(155, 381, 38, 20);
		frame.getContentPane().add(credits);
		credits.setColumns(10);
		
		cues = new JTextField();
		cues.setColumns(10);
		cues.setBounds(279, 381, 38, 20);
		frame.getContentPane().add(cues);
		
		academeiLevel = new JTextField();
		academeiLevel.setColumns(10);
		academeiLevel.setBounds(464, 381, 120, 20);
		frame.getContentPane().add(academeiLevel);
		
		JLabel lblCue = new JLabel("CUE");
		lblCue.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblCue.setBounds(242, 384, 46, 14);
		frame.getContentPane().add(lblCue);
		
		JLabel lblAcadameicLevel = new JLabel("Acadameic Level");
		lblAcadameicLevel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblAcadameicLevel.setBounds(354, 384, 95, 14);
		frame.getContentPane().add(lblAcadameicLevel);
		
		Properties p = new Properties();
		p.put("text.today", "Today");
		p.put("text.month", "Month");
		p.put("text.year", "Year");
		
		UtilDateModel model = new UtilDateModel();
		JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
		JDatePickerImpl mFromDate = new JDatePickerImpl(datePanel, new DateComponentFormatter());
		mFromDate.setBounds(101, 300, 120, 28);
		frame.getContentPane().add(mFromDate);
		
		
		UtilDateModel model1 = new UtilDateModel();
		JDatePanelImpl datePanel1 = new JDatePanelImpl(model1, p);
		JDatePickerImpl mToDate = new JDatePickerImpl(datePanel1, new DateComponentFormatter());
		mToDate.setBounds(257, 296, 120, 28);
		frame.getContentPane().add(mToDate);
		
		JButton btnSave = new JButton("Save");
		btnSave.setBounds(315, 430, 89, 23);
		frame.getContentPane().add(btnSave);
		
		JButton btnSelect = new JButton("Select");
		btnSelect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					setFields(semesterList.getSelectedValue().toString());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		});
		btnSelect.setBounds(287, 99, 86, 23);
		frame.getContentPane().add(btnSelect);
		
		JButton btnSelect_1 = new JButton("Select");
		btnSelect_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
					try {
						setCourse();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					
				}
			}

		});
		btnSelect_1.setBounds(282, 161, 91, 23);
		frame.getContentPane().add(btnSelect_1);
		
		faculty = new JTextField();
		faculty.setBounds(111, 344, 86, 20);
		frame.getContentPane().add(faculty);
		faculty.setColumns(10);
	}
	
	@SuppressWarnings("unchecked")
	private void setFields(String selected) throws Exception {
		String sem=selected.substring(4, 6);
		term.setText(selected);
		CourseEvent ce=new CourseEvent();
		ArrayList<Courses> cList=new ArrayList<Courses>();
		cList=ce.getAllCourse();
		int i=0;
		for(Courses c:cList){
			if(sem.equals("FA")&&c.getFallS().equals("Y")){
				avlCourseList[i]=c.getNumber();
				i++;
			}
			if(sem.equals("SP")&&c.getSpringS().equals("Y")){
				avlCourseList[i]=c.getNumber();
				i++;
			}
			if(sem.equals("SU")&&c.getSummerS().equals("Y")){
				avlCourseList[i]=c.getNumber();
				i++;
			}
		}
		courseList = new JList<Object>(avlCourseList);		
		courseList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane2 = new JScrollPane(courseList);
		scrollPane2.setBounds(389, 158, 86, 89);
		frame.getContentPane().add(scrollPane2);
		courseList.updateUI();
		scrollPane2.updateUI();
	}

	@SuppressWarnings("unchecked")
	private void setCourse() throws Exception {		
		String selected=courseList.getSelectedValue().toString();
		
		if(!selected.equals("")){
		CourseEvent ce=new CourseEvent();
		Courses c=new Courses();
		c=ce.getCourse(selected);
		courseId.setText(c.getNumber());
		courseName.setText(c.getName());
		capacity.setText(c.getCapacity());
		String fList[]=c.getTeacher().split(",");
		JComboBox facultyList = new JComboBox();
		facultyList.setBounds(216, 344, 89, 20);
		frame.getContentPane().add(facultyList);

		if(fList.length==1){
			faculty.setText(c.getTeacher());
			if(fCount>0){
				for(int i=0;i<fCount;i++)
					facultyList.remove(i++);
				fCount=0;
			}
			facultyList.setEnabled(false);
			facultyList.setEditable(false);
			facultyList.setVisible(false);
			facultyList.revalidate();
			facultyList.repaint();
			
		}else{
			if(fCount>0){
				for(int i=0;i<fCount;i++)
					facultyList.remove(i++);
				fCount=0;
			}
			facultyList.addItem("");
			int l=fList.length;
			int i=0;
			while(l>i){
				fCount++;
				facultyList.addItem(fList[i]);
				i++;
			}
		}
		facultyList.updateUI();
		}
	
	}
}
