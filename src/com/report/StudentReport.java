package com.report;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
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
import com.importfile.StudentCourse;
import com.login.Login;
import com.login.UserViewMain;
import com.schedule.ScheduleSection;

public class StudentReport {
	private static final String FILE_PATH_STUDENT = "C:/Input/StudentDetails.csv";
	private static final String FILE_PATH_STUDENT_COURSE = "C:/Input/StudentCourseDetails.csv";
	private static final String FILE_PATH_SCHEDULE = "C:/Input/Schedule.csv";
	private static final String FILE_PATH = "C:/Input/StudentReport.csv";
	public ArrayList<Students> studentList;
	public ArrayList<StudentCourse> studentCourseList;
	public ArrayList<ScheduleSection> sectionList;
	

	public JFrame frame;
	private String userRole;
	
	public String[][] studentValues;
	private JScrollPane scrollPane ;
	private JTable table;
	private int count=0;


	/**
	 * Launch the application.
	 
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StudentReport window = new StudentReport();
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
	public StudentReport() throws IOException {
		initialize();
	}
	

	public StudentReport(String userRole2) throws IOException {
		userRole=userRole2;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() throws IOException {
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
					office.frame.setVisible(true);

				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		});
		btnBack.setBounds(42, 11, 89, 23);
		frame.getContentPane().add(btnBack);
		
		JLabel lblCourseManagement = new JLabel("Sutdent Report");
		lblCourseManagement.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblCourseManagement.setBounds(284, 38, 165, 29);
		frame.getContentPane().add(lblCourseManagement);
		
		studentValues=new String[count][9];
		studentValues=getData();
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
				studentValues,			
			new String[] {
				"StudentId", "Degree","Courses","FA2016", "FA2017","SP2017","SP2018","FA2019","SP2019"
			}
		));
		
		scrollPane = new JScrollPane( table );
		scrollPane.setBounds(34, 76, 608, 348);
		frame.getContentPane().add(scrollPane);

		

	}

	private String[][] getData() throws IOException {
		//generate();
		CsvReader section = new CsvReader(FILE_PATH);
		section.readHeaders();
		String[][] data=new String[500][9];
		//ArrayList<ScheduleSection> sectionList=new ArrayList<ScheduleSection>();
		int i=0;
		count=0;
		while(section.readRecord()){
			for(int j=0;j<9;j++)
				data[i][j]=section.get(j);
			i++;
			count++;
		}
		section.close();
		return data;
	}
	
	public void generate() throws IOException{
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
				sectionList.add(s);
			}
		}
		sections.close();
		
		
		CsvReader studentRecord = new CsvReader(FILE_PATH_STUDENT);
		studentList=new ArrayList<Students>();
		int cs=0;
		while(studentRecord.readRecord()){
			cs++;
			if(cs>1){
				Students s=new Students();
				s.setStudentId(studentRecord.get(0));
				s.setDegree(studentRecord.get(1));
				studentList.add(s);
			}
			
			}
		studentRecord.close();
		
		
		CsvReader studentCourse = new CsvReader(FILE_PATH_STUDENT_COURSE);
		studentCourseList=new ArrayList<StudentCourse>();
		while(studentCourse.readRecord()){
			StudentCourse r=new StudentCourse();
			 r.setStudentId(studentCourse.get(0));
			 r.setCourseId(studentCourse.get(1));
			 r.setCourseName(studentCourse.get(2));
			 r.setYearSem(studentCourse.get(3));
			 r.setGrade(studentCourse.get(4));
			 studentCourseList.add(r);
		}
		studentCourse.close();
		
		
		for(Students s:studentList){
			for(StudentCourse sc:studentCourseList){
				if(sc.getStudentId().equals(s.getStudentId())){
					if(null==s.getCoursesAssigned())
						s.setCoursesAssigned(sc.getCourseId());
					else
						s.setCoursesAssigned(s.getCoursesAssigned()+","+sc.getCourseId());						
				}
			}
		
		}
		
		for(Students s:studentList){
			for(ScheduleSection ss:sectionList){
				String[] stList=ss.getStudents().split(",");
				boolean status=false;
				for(int i=0;i<stList.length;i++){
					if(s.getStudentId().equals(stList[i])){
						status=true;
					}
				}
				if(status){
					if(ss.getSectionId().split("-")[1].equals("2016FA")){
						if(null==s.getFA2016())
							s.setFA2016(ss.getSectionId());
						else
							s.setFA2016(s.getFA2016()+","+ss.getSectionId());
					}
					
					if(ss.getSectionId().split("-")[1].equals("2017FA")){
						if(null==s.getFA2017())
							s.setFA2017(ss.getSectionId());
						else
							s.setFA2017(s.getFA2017()+","+ss.getSectionId());
					}
					
					if(ss.getSectionId().split("-")[1].equals("2017SP")){
						if(null==s.getSP2017())
							s.setSP2017(ss.getSectionId());
						else
							s.setSP2017(s.getSP2017()+","+ss.getSectionId());
					}
					
					if(ss.getSectionId().split("-")[1].equals("2018SP")){
						if(null==s.getSP2018())
							s.setSP2018(ss.getSectionId());
						else
							s.setSP2018(s.getSP2018()+","+ss.getSectionId());
					}
					
					if(ss.getSectionId().split("-")[1].equals("2019FA")){
						if(null==s.getFA2019())
							s.setFA2019(ss.getSectionId());
						else
							s.setFA2019(s.getFA2019()+","+ss.getSectionId());
					}
					
					if(ss.getSectionId().split("-")[1].equals("2019SP")){
						if(null==s.getSP2017())
							s.setSP2019(ss.getSectionId());
						else
							s.setSP2019(s.getSP2017()+","+ss.getSectionId());
					}
				
		
				}
			}
		}
		File files = new File(FILE_PATH);
		files.delete();
        CsvWriter csvOutputT = new CsvWriter(new FileWriter(FILE_PATH, true), ',');  
        csvOutputT.write("StudentId");
        csvOutputT.write("Degree");
        csvOutputT.write("Courses");
        csvOutputT.write("2016FA");
        csvOutputT.write("2017FA");
        csvOutputT.write("2017SP");
        csvOutputT.write("2018SP");
        csvOutputT.write("2019FA");
        csvOutputT.write("2019SP");
        csvOutputT.endRecord();
        for(Students s:studentList){
        	csvOutputT.write(s.getStudentId());
        	csvOutputT.write(s.getDegree());
        	csvOutputT.write(s.getCoursesAssigned());
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
