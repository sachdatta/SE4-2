package com.report;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.csvreader.CsvReader;
import com.csvreader.CsvWriter;
import com.faculty.Faculty;
import com.faculty.FacultyEvent;
import com.login.Login;
import com.login.UserViewMain;
import com.schedule.ScheduleSection;

public class TeacherReport {

	public JFrame frame;
	public String userRole;
	
	private JTable table;
	private static final String FILE_PATH_SCHEDULE = "C:/Input/Schedule.csv";	
	private static final String FILE_PATH = "C:/Input/TeacherReport.csv";
	public ArrayList<Teachers> teacherList;
	public ArrayList<Faculty> facultyList;
	public ArrayList<ScheduleSection> sectionList;
	public String[][] teacherValues;
	public String[][] update;
	private JScrollPane scrollPane ;
	private int count=0;

	/**
	 * Launch the application.
	 
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TeacherReport window = new TeacherReport();
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
	public TeacherReport() {
		initialize();
	}
	
	public TeacherReport(String role) {
		userRole=role;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 706, 503);
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
		
		JLabel lblCourseManagement = new JLabel("Teacher Report");
		lblCourseManagement.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblCourseManagement.setBounds(284, 38, 165, 29);
		frame.getContentPane().add(lblCourseManagement);
		
		
		
		teacherValues=new String[count][8];
		try {
			teacherValues=getData();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
				teacherValues,			
			new String[] {
				"TeacherName", "Assigned Courses","FA2016", "FA2017","SP2017","SP2018","FA2019","SP2019"
			}
		));
	//table.setBounds(34, 76, 608, 348);
		
		scrollPane = new JScrollPane( table );
		scrollPane.setBounds(34, 76, 608, 348);
		frame.getContentPane().add(scrollPane);

	}

	private String[][] getData() throws IOException {
		CsvReader section = new CsvReader(FILE_PATH);
		section.readHeaders();
		String[][] data=new String[100][8];
		int i=0;
		count=0;
		while(section.readRecord()){
			for(int j=0;j<8;j++){
				data[i][j]=section.get(j);
			}
			i++;
		}
			
		section.close();
		return data;
	}
	
	
	public void generate() throws Exception{
		
		CsvReader sections = new CsvReader(FILE_PATH_SCHEDULE);
		sectionList=new ArrayList<ScheduleSection>();
		int c=0;
		while(sections.readRecord()){
			c++;
			if (c>1){
				ScheduleSection s=new ScheduleSection();
				s.setCourseId(sections.get(0));
				s.setCourseName(sections.get(1));
				s.setSectionId(sections.get(2));
				s.setsCount(Integer.parseInt(sections.get(3)));
				s.setTeacherName(sections.get(4));
				s.setStudents(sections.get(5));
				s.settDay(sections.get(6));
				s.setYearSem(sections.get(7));
				sectionList.add(s);
			}
		}
		sections.close();
		
		FacultyEvent fe=new FacultyEvent();
		facultyList=fe.getAllFaculty();
		teacherList=new ArrayList<Teachers>();
		for(Faculty f:facultyList){
			Teachers t=new Teachers();
			t.setTeachername(f.getLastName());
			t.setAssignedCourses(f.getCourses());	
			teacherList.add(t);
		}
		
		for(Teachers t:teacherList){
			for(ScheduleSection s:sectionList){
				if(s.getTeacherName().equals(t.getTeachername())){
					
					if(s.getSectionId().split("-")[1].equals("2016FA")){
						if(null==t.getFA2016())
							t.setFA2016(s.getSectionId());
						else
							t.setFA2016(t.getFA2016()+","+s.getSectionId());
					}
					
					if(s.getSectionId().split("-")[1].equals("2017FA")){
						if(null==t.getFA2017())
							t.setFA2017(s.getSectionId());
						else
							t.setFA2017(t.getFA2017()+","+s.getSectionId());
					}
					
					if(s.getSectionId().split("-")[1].equals("2017SP")){
						if(null==t.getSP2017())
							t.setSP2017(s.getSectionId());
						else
							t.setSP2017(t.getSP2017()+","+s.getSectionId());
					}
					
					if(s.getSectionId().split("-")[1].equals("2018SP")){
						if(null==t.getSP2018())
							t.setSP2018(s.getSectionId());
						else
							t.setSP2018(t.getSP2018()+","+s.getSectionId());
					}
					
					if(s.getSectionId().split("-")[1].equals("2019FA")){
						if(null==t.getFA2019())
							t.setFA2019(s.getSectionId());
						else
							t.setFA2019(t.getFA2019()+","+s.getSectionId());
					}
					
					if(s.getSectionId().split("-")[1].equals("2019SP")){
						if(null==t.getSP2017())
							t.setSP2019(s.getSectionId());
						else
							t.setSP2019(t.getSP2017()+","+s.getSectionId());
					}
				
				}
			}
		}
		
		
		File files = new File(FILE_PATH);
		files.delete();
        CsvWriter csvOutputT = new CsvWriter(new FileWriter(FILE_PATH, true), ',');  
        csvOutputT.write("TeacherName");
        csvOutputT.write("Assigned");
        csvOutputT.write("Courses");
        csvOutputT.write("2016FA");
        csvOutputT.write("2017FA");
        csvOutputT.write("2017SP");
        csvOutputT.write("2018SP");
        csvOutputT.write("2019FA");
        csvOutputT.write("2019SP");
        csvOutputT.endRecord();
        for(Teachers s:teacherList){
        	csvOutputT.write(s.getTeachername());
        	csvOutputT.write(s.getAssignedCourses());
        	csvOutputT.write(s.getFA2016());
        	csvOutputT.write(s.getFA2017());
        	csvOutputT.write(s.getSP2017());
        	csvOutputT.write(s.getSP2018());
        	csvOutputT.write(s.getFA2019());
        	csvOutputT.write(s.getSP2019());
        	csvOutputT.endRecord();
		}
        csvOutputT.close();
		
	}
	

}
