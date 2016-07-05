package com.schedule;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import com.csvreader.CsvReader;
import com.csvreader.CsvWriter;
import com.login.Login;
import com.login.UserViewMain;

import javax.swing.JLabel;
import javax.swing.table.DefaultTableModel;

import java.awt.Font;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;



public class ScheduleMain {

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
	public ScheduleMain() throws Exception {
		initialize();
	}

	public ScheduleMain(String role) throws Exception {
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
		
		sectionValues=new String[count][8];
		sectionValues=getData();
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
				sectionValues,			
			new String[] {
				"Course Id", "Course Name", "Section Id","Student Count", "Teacher", "Students","Day","Year&Sem"
			}
		));
	//table.setBounds(34, 76, 608, 348);
		
		scrollPane = new JScrollPane( table );
		scrollPane.setBounds(34, 76, 608, 348);
		frame.getContentPane().add(scrollPane);
		
		JLabel lblSchedule = new JLabel("Schedule");
		lblSchedule.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblSchedule.setBounds(309, 35, 122, 14);
		frame.getContentPane().add(lblSchedule);
		
		JButton btnGenerate = new JButton(" Generate");
		btnGenerate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				sectionValues=new String[count][8];
				try {
					generate();
					sectionValues=getData();					
					table.setModel(new DefaultTableModel(
							sectionValues,			
						new String[] {
							"Course Id", "Course Name", "Section Id","Student Count", "Teacher","Students", "Day","Year&Sem"
						}));
					table.updateUI();
					scrollPane.updateUI();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnGenerate.setBounds(449, 42, 89, 23);
		frame.getContentPane().add(btnGenerate);
		
				
	}
	
	public void generate() throws Exception{
		GenerateSchedule g=new GenerateSchedule();	
		g.readData();
	}

	private String[][] getData() throws Exception {	
		
		CsvReader section = new CsvReader(FILE_PATH);
		section.readHeaders();
		String[][] data=new String[100][8];
		//ArrayList<ScheduleSection> sectionList=new ArrayList<ScheduleSection>();
		int i=0;
		count=0;
		while(section.readRecord()){
			for(int j=0;j<8;j++){
				if(Integer.parseInt(section.get(3))>=17&&!section.get(4).equals("Not available"))
				data[i][j]=section.get(j);
			}
			if(Integer.parseInt(section.get(3))>=17&&!section.get(4).equals("Not available")){
				i++;
				count++;
			}
			
		}
		section.close();
		if(i==0&&!first){			
			JOptionPane.showMessageDialog(frame, "No student&course records found");			
		}
		first=false;
		return data;
	}
	
}
