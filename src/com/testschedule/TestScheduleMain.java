package com.testschedule;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.csvreader.CsvReader;
import com.csvreader.CsvWriter;
import com.login.Login;
import com.login.UserViewMain;
import com.report.StudentReport;
import com.report.TeacherReport;
import com.schedule.AddSection;
import com.schedule.GenerateSchedule;

import javax.swing.JTextPane;

public class TestScheduleMain {

	public JFrame frame;
	public String userRole;
	public boolean first=true;
	private JTable table;
	private static final String FILE_PATH = "C:/Input/Schedule.csv";
	public String[][] sectionValues;
	public String[][] update;
	private JScrollPane scrollPane ;
	private int count=0;
	public boolean String;
	public int secGra=0;
	public ArrayList<String> graduate;
	

	/**
	 * Launch the application.
	 
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ScheduleMain window = new ScheduleMain();
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
	
	public TestScheduleMain() throws Exception {
		initialize();
	}
	
	
	public TestScheduleMain(String role) throws Exception {
		userRole=role;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * @throws Exception 
	 */
	private void initialize() throws Exception {
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
		
		sectionValues=new String[count][8];
		sectionValues=getData();
		
		table = new JTable();
		table.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent evt) {
				int row = table.getSelectedRow();
				int column = table.getSelectedColumn();				
				if(row!=-1){
					sectionValues[row][column]= (String) table.getModel().getValueAt(row, column);
					updateValue(row,column,(String) table.getModel().getValueAt(row, column));				
					}
				}

		});
		table.setModel(new DefaultTableModel(
				sectionValues,			
			new String[] {
				"Course Id", "Course Name", "Section Id","Student Count", "Teacher","Students", "Day","Year&Sem"
			}
		));
	//table.setBounds(34, 76, 608, 348);
		
		scrollPane = new JScrollPane( table );
		scrollPane.setBounds(33, 109, 599, 321);
		frame.getContentPane().add(scrollPane);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					updateSehedule();
					JOptionPane.showMessageDialog(frame, "Schedule Updated");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnUpdate.setBounds(282, 441, 89, 23);
		frame.getContentPane().add(btnUpdate);
		
		JLabel lblSchedule = new JLabel(" Test Schedule");
		lblSchedule.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblSchedule.setBounds(307, 13, 122, 14);
		frame.getContentPane().add(lblSchedule);
		
		JButton btnAddNew = new JButton("Add new");
		btnAddNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					AddSection as=new AddSection(userRole);
					frame.setVisible(false);
					as.frame.setVisible(true);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		});
		btnAddNew.setBounds(441, 441, 89, 23);
		frame.getContentPane().add(btnAddNew);
		
		if(secGra>0){
			JLabel lblThereAre = new JLabel("*There are "+secGra+" section students going to graduate this semester, but no teacher assigned to them");
			lblThereAre.setFont(new Font("Tahoma", Font.PLAIN, 11));
			lblThereAre.setBounds(52, 45, 608, 14);
			frame.getContentPane().add(lblThereAre);
			
			String list="";
			for(String s:graduate){
				if(list.equals(""))
					list=s;
				else
					list=list+","+s;
			}

			JTextPane textPane = new JTextPane();
			textPane.setFont(new Font("Tahoma", Font.PLAIN, 9));
			textPane.setBounds(62, 61, 559, 37);
			frame.getContentPane().add(textPane);
			textPane.setText(list);
			textPane.setEditable(false);
			
		}
		
		
				
	}
	private String[][] getData() throws Exception {	
		
		CsvReader section = new CsvReader(FILE_PATH);
		section.readHeaders();
		String[][] data=new String[300][8];
		//ArrayList<ScheduleSection> sectionList=new ArrayList<ScheduleSection>();
		int i=0;
		count=0;
		while(section.readRecord()){
			for(int j=0;j<8;j++)
				data[i][j]=section.get(j);
			i++;
			count++;
		}
		section.close();
		if(i==0&&!first){			
			JOptionPane.showMessageDialog(frame, "No student&course records found");			
		}
		graduate= new ArrayList<String>();
		boolean status=false;
		for(int k=0; k<count;k++){
			String test=data[k][6];
			
			if(data[k][7].equals("2016FA")&&test.equalsIgnoreCase("Not available")){
				secGra++;	
				status=true;
			}
			if(status){
				graduate.add(data[k][2]);
				status=false;
			}
		}
		
		
		first=false;
		return data;
	}
	
	public void updateSehedule() throws Exception{
		File file = new File(FILE_PATH);
		file.delete();
        CsvWriter csvOutput = new CsvWriter(new FileWriter(FILE_PATH, true), ',');  
        csvOutput.write("CourseCode");
        csvOutput.write("CourseName");
        csvOutput.write("SectionId");
        csvOutput.write("StudentCount");
        csvOutput.write("TeacherName");
        csvOutput.write("Students");
        csvOutput.write("TeacherAvailableDay");
        csvOutput.write("Year&sem");
        csvOutput.endRecord();
        update=new String[300][8];
        int i=0;
        while(i<count){
        	for(int j=0;j<8;j++){
        		update[i][1]=sectionValues[i][j];
        		csvOutput.write((String) table.getModel().getValueAt(i,j));
        	}
        	csvOutput.endRecord();
        	i++;
        	
        }
        csvOutput.close();
        StudentReport sr=new StudentReport();
        sr.generate();
        
        TeacherReport tr=new TeacherReport();
        tr.generate();
        
	}
	
	private void updateValue(int row, int column, String valueAt) {
		sectionValues[row][column]=valueAt;		
	}
	
	public void firstUse(){
		
	}
}
