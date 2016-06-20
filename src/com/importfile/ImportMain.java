package com.importfile;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;

import com.courses.CourseEvent;
import com.courses.Courses;
import com.csvreader.CsvReader;
import com.csvreader.CsvWriter;
import com.degree.Degree;
import com.degree.DegreeEvent;
import com.login.Login;
import com.login.UserException;
import com.login.UserViewMain;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JTextField;

public class ImportMain {

	public JFrame frame;
	public String selectedOption="";
	public String userRole="COURSE";
	private JTextField filePath;
	@SuppressWarnings("rawtypes")
	private JList resultList;
	private JScrollPane  scrollPane;
	
	private static final String FILE_PATH_STUDENT = "C:/Input/StudentDetails.csv";
	private static final String FILE_PATH_STUDENT_COURSE = "C:/Input/StudentCourseDetails.csv";
	private static final String FILE_PATH_SEMESTER = "C:/Input/SemesterDetails.csv";

	/**
	 * Launch the application.
	 
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ImportMain window = new ImportMain();
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
	public ImportMain() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 722, 491);
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
				UserViewMain userMain;
				try {
					userMain = new UserViewMain();
					frame.setVisible(false);					
					userMain.userRole=userRole;
					userMain.frame.setVisible(true);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		});
		btnBack.setBounds(10, 16, 89, 23);
		frame.getContentPane().add(btnBack);
		
		JLabel lblChooseFile = new JLabel("Choose File");
		lblChooseFile.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblChooseFile.setBounds(127, 116, 102, 14);
		frame.getContentPane().add(lblChooseFile);
		
		filePath = new JTextField();
		filePath.setBounds(243, 114, 309, 20);
		frame.getContentPane().add(filePath);
		filePath.setColumns(10);
		
		JButton btnBrowse = new JButton("Browse");
		btnBrowse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				selectFile();
			}
		});
		btnBrowse.setBounds(565, 113, 89, 23);
		frame.getContentPane().add(btnBrowse);
		
		JButton btnUpload = new JButton("Upload");
		btnUpload.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
				if(selectedOption.equals("COURSE")){
					UploadCourse();
					dialog("File uploaded Sucessfully in  "+FILE_PATH_STUDENT_COURSE);
				}
					
				if(selectedOption.equals("STUDENT")){
						UploadStudent();
						dialog("File uploaded Sucessfully in "+FILE_PATH_STUDENT);
				}
				
				} catch (IOException | UserException e) {
					dialog("File upload failed, Please choose correct csv file");
					e.printStackTrace();
				}
					
			}
		});
		btnUpload.setBounds(350, 165, 89, 23);
		frame.getContentPane().add(btnUpload);
		

		
		
				
	}

	private void selectFile() {
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
		int result = fileChooser.showOpenDialog(null);
		if (result == JFileChooser.APPROVE_OPTION) {
		    File selectedFile = fileChooser.getSelectedFile();
		    filePath.setText(selectedFile.getAbsolutePath());
		}
		
	}
	

	public void showTitle(){
	if(selectedOption.equals("STUDENT")){
		JLabel lblImortStudent = new JLabel("Import Student");
		lblImortStudent.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblImortStudent.setBounds(275, 73, 162, 20);
		frame.getContentPane().add(lblImortStudent);
		}
		if(selectedOption=="COURSE"){
		JLabel lblImortCourse = new JLabel("Import Student Course");
		lblImortCourse.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblImortCourse.setBounds(275, 73, 182, 20);
		frame.getContentPane().add(lblImortCourse);
		}
	}
	
	public void UploadStudent() throws IOException, UserException{
		 try{
			CsvReader student = new CsvReader(filePath.getText());
			ArrayList<Student> studentList=new ArrayList<Student>();
			while(student.readRecord()){
				Student r=new Student();
				 r.setStudentId(student.get(0));
				 r.setDegreeID(student.get(1));
				 r.setYearSem(student.get(2));
				 studentList.add(r);
			}
			student.close();
			writeStudent(studentList);
			}catch(Exception e){
				throw new UserException("Failed to save");
			}
	}
	
	private void writeStudent(ArrayList<Student> studentList) {		
		try{
			String[] result=new String[5000];
			DegreeEvent de=new DegreeEvent();
			ArrayList<String> deg=new ArrayList<String>();
			for(Degree d:de.getAllDegree())
				deg.add(d.getDegreeCode());
			
			CsvReader semester = new CsvReader(FILE_PATH_SEMESTER);
			ArrayList<String> sem=new ArrayList<String>();
			while(semester.readRecord())
				sem.add(semester.get(0));
			semester.close();
			
			CsvReader stu = new CsvReader(FILE_PATH_STUDENT);
			ArrayList<String> stuL=new ArrayList<String>();
			while(stu.readRecord())
				stuL.add(stu.get(0));
			stu.close();
			
			ArrayList<String> indexRemove=new ArrayList<String>();
			int i=0;
			for(Student s:studentList){
				boolean status=false;				
					result[i]=s.getStudentId()+" "+s.getDegreeID()+" "+s.getYearSem();
					if(stuL.contains(s.getStudentId())){
						result[i]=result[i]+"   Failed "+"   Student ID already found";
						status=true;
						i++;
					}
					else if(!deg.contains(s.getDegreeID())){
						result[i]=result[i]+"   Failed "+"   Degree code not found";
						status=true;
						i++;
					}
					else if(!sem.contains(s.getYearSem())){
						result[i]=result[i]+" Failed "+"   Invalid semester";
						status=true;
						i++;
					}
					else{
						result[i]=result[i]+"   Sucess";	
						i++;				
					}
					if(status){
						indexRemove.add(s.getStudentId());
					}
			}	
			
        CsvWriter csvOutput = new CsvWriter(new FileWriter(FILE_PATH_STUDENT, true), ',');
		for(Student s:studentList){
			if(!indexRemove.contains(s.getStudentId())){
				csvOutput.write(s.getStudentId());
				csvOutput.write(s.getDegreeID());
				csvOutput.write(s.getYearSem());
				csvOutput.endRecord();				
			}
		}
		viewStatus(result);
		csvOutput.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}

	public void UploadCourse() throws UserException, IOException{
		 try{
			CsvReader studentCourse = new CsvReader(filePath.getText());
			ArrayList<StudentCourse> studentCourseList=new ArrayList<StudentCourse>();
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
			writeStudentCourse(studentCourseList);
					
			}catch(Exception e){
				throw new UserException("Failed to save");
			}
	}
	
	
	
	@SuppressWarnings("unchecked")
	private void writeStudentCourse(ArrayList<StudentCourse> studentCourseList) throws Exception {
		String[] result=new String[5000];
		
		CsvReader stc = new CsvReader(FILE_PATH_STUDENT_COURSE);
		ArrayList<StudentCourse> stuC=new ArrayList<StudentCourse>();
		while(stc.readRecord()){
			StudentCourse s=new StudentCourse();
			s.setStudentId(stc.get(0));
			s.setCourseId(stc.get(1));
			stuC.add(s);
		}
		stc.close();
		
		CsvReader stu = new CsvReader(FILE_PATH_STUDENT);
		ArrayList<String> stuL=new ArrayList<String>();
		while(stu.readRecord())
			stuL.add(stu.get(0));
		stu.close();
	
		CourseEvent ce=new CourseEvent();
		ArrayList<Courses> cur1=new ArrayList<Courses>();
		cur1=ce.getAllCourse();
		ArrayList<String> cur=new ArrayList<String>();
		for(Courses d:cur1)
			cur.add(d.getNumber());
		
		CsvReader semester = new CsvReader(FILE_PATH_SEMESTER);
		ArrayList<String> sem=new ArrayList<String>();
		while(semester.readRecord())
			sem.add(semester.get(0));
		semester.close();
		
			
		ArrayList<StudentCourse> indexRemove=new ArrayList<StudentCourse>();
		int i=0;
		for(StudentCourse s:studentCourseList){
			boolean status=false;
			boolean statusCombination=true;
				result[i]=s.getStudentId()+" "+s.getCourseId()+" "+s.getCourseName();
				for(StudentCourse s1:stuC){
					if((s1.getStudentId().equals(s.getStudentId())&&s1.getCourseId().equals(s.getCourseId()))){
						result[i]=result[i]+"   Failed "+"   Student ID and Course Id combination already found";
						status=true;
						statusCombination=false;						
						i++;
						break;
					}
				}
				if(statusCombination){
					if(!stuL.contains(s.getStudentId())){
						result[i]=result[i]+"   Failed "+"   Student ID not found";
						status=true;
						i++;
					}
					else if(!cur.contains(s.getCourseId())){
						result[i]=result[i]+"   Failed "+"   Course code not found";
						status=true;
						i++;
					}
					else if(!sem.contains(s.getYearSem())){
						result[i]=result[i]+" Failed "+"   Invalid semester";
						status=true;
						i++;
					}
					else{
						result[i]=result[i]+"   Sucess";	
						i++;				
					}					
				}
				
		
				if(status){
					StudentCourse se=new StudentCourse();
					se.setStudentId(s.getStudentId());
					se.setCourseId(s.getCourseId());
					indexRemove.add(se);
				}
		}
        CsvWriter csvOutput = new CsvWriter(new FileWriter(FILE_PATH_STUDENT_COURSE, true), ',');
		for(StudentCourse s:studentCourseList){
			boolean write=true;
			for(StudentCourse s1:indexRemove){
				write=true;
				if((s1.getStudentId().equals(s.getStudentId()) && s1.getCourseId().equals(s.getCourseId()))){
					write=false;
					break;
				}
					
			}
			
			if(write){
				csvOutput.write(s.getStudentId());
				csvOutput.write(s.getCourseId());
				csvOutput.write(s.getCourseName());
				csvOutput.write(s.getYearSem());
				csvOutput.write(s.getGrade());
				csvOutput.endRecord();					


			}
			
		}
		viewStatus(result);
		csvOutput.close();
	
		
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void viewStatus(String[] result){

		resultList = new JList(result);		
		//resultList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane=new JScrollPane(resultList);
		scrollPane.setBounds(56, 201, 621, 225);
		frame.getContentPane().add(scrollPane);
		scrollPane.updateUI();
	}
	
	private void dialog(String msg) {
		JOptionPane.showMessageDialog(frame, msg);
	}
}
