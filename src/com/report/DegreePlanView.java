package com.report;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.degree.Degree;
import com.degree.DegreeEvent;
import com.degree.main.DegreeEventHome;
import com.degree.main.DegreeHome;
import com.login.Login;
import com.login.UserViewMain;

public class DegreePlanView {

	public JFrame frame;
	public String userRole;
	private String[][] degreeValue;
	private JScrollPane scrollPane ;
	private JTable table;
	private int count=0;
	private ArrayList<DegreePlan> degreeList;

	/**
	 * Launch the application.
	 
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DegreePlanView window = new DegreePlanView();
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
	public DegreePlanView() throws IOException {
		initialize();
	}
	public DegreePlanView(String role) throws IOException {
		userRole=role;
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
		
		JLabel lblCourseManagement = new JLabel("Degree Plan");
		lblCourseManagement.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblCourseManagement.setBounds(284, 38, 165, 29);
		frame.getContentPane().add(lblCourseManagement);
		
		
		degreeValue=new String[10][5];
		degreeValue=getData();
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
				degreeValue,			
			new String[] {
				"Degree","Forecast","Required","Elective 1","Elective 2"
			}
		));
		
		scrollPane = new JScrollPane( table );
		scrollPane.setBounds(34, 76, 608, 348);
		frame.getContentPane().add(scrollPane);


	}

	private String[][] getData() throws IOException {
		generate();
		String[][] degreePlan=new String[10][5];
		int i=0;
		for(DegreePlan d:degreeList){
			degreePlan[i][0]=d.getDegreeName();
			degreePlan[i][1]=d.getForcast();
			degreePlan[i][2]=d.getRequired();
			degreePlan[i][3]=d.getElective1();
			degreePlan[i][4]=d.getElective2();
			i++;
			count++;
		}
		return degreePlan;
	}

	private void generate() throws IOException {
		degreeList=new ArrayList<DegreePlan>();
		DegreeEventHome dme=new DegreeEventHome();
		ArrayList<DegreeHome>dh=dme.getAllDegrees();
		for(DegreeHome d:dh){
			DegreePlan dp=new DegreePlan();
			dp.setDegreeName(d.getDegreeCode());
			dp.setForcast(d.getForecast());
			degreeList.add(dp);
		}
		
		DegreeEvent de=new DegreeEvent();
		ArrayList<Degree>deg=de.getAllDegree();
		for(DegreePlan dp:degreeList){
			for(Degree d:deg){
				if(dp.getDegreeName().equals(d.getDegreeCode())){
					if(d.getType().equals("Required"))
						dp.setRequired(d.getDegreeCourses());
					else if(d.getType().equals("Elective")&&null==dp.getElective1())
						dp.setElective1(d.getDegreeCourses());
					else if(d.getType().equals("Elective")&&null!=dp.getElective1())
						dp.setElective2(d.getDegreeCourses());
				}
			}
		}
		
	}

}
