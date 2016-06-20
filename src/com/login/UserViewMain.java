package com.login;

import com.faculty.FacultyMain;
import com.importfile.ImportMain;
import com.room.RoomMain;
import com.schedule.ScheduleMain;
import com.user.UserMain;

import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JButton;

import com.courses.CourseMain;
import com.degree.DegreeMain;
import com.degree.main.DegreeMainHome;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class UserViewMain {

	public JFrame frame;
	public static String  userRole;
	public String selectedOption;
	public static int count=0;

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserView window = new UserView();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/

	/**
	 * Create the application.
	 */
	public UserViewMain() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 725, 495);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblWelcome = new JLabel("Oklahoma Christian Universitys");
		lblWelcome.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblWelcome.setBounds(211, 60, 318, 27);
		frame.getContentPane().add(lblWelcome);
		
		JButton btnCourseManagement = new JButton("Course Management");
		btnCourseManagement.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CourseMain courseFrame;
					try {
						courseFrame = new CourseMain();
						frame.setVisible(false);
						courseFrame.frame.setVisible(true);
						courseFrame.userRole=userRole;
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			}
		});
		btnCourseManagement.setBounds(22, 195, 207, 23);
		frame.getContentPane().add(btnCourseManagement);
		
		JButton btnNewButton = new JButton("Faculty Management");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				FacultyMain facultyFrame;
				try {
					facultyFrame = new FacultyMain();
					frame.setVisible(false);
					facultyFrame.frame.setVisible(true);
					facultyFrame.userRole=userRole;
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnNewButton.setBounds(22, 240, 207, 23);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnRoomManagement = new JButton("Room Management");
		btnRoomManagement.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				RoomMain roomFrame;
				try {
					roomFrame = new RoomMain();
					frame.setVisible(false);
					roomFrame.frame.setVisible(true);
					roomFrame.userRole=userRole;
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnRoomManagement.setBounds(22, 364, 207, 23);
		frame.getContentPane().add(btnRoomManagement);
		
		JButton btnLogout = new JButton("Logout");
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Login login=new Login();
				frame.setVisible(false);
				login.frame.setVisible(true);
			}
		});
		btnLogout.setBounds(598, 21, 89, 23);
		frame.getContentPane().add(btnLogout);
		
		JButton btnDegreeManagement = new JButton("Degree Management");
		btnDegreeManagement.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				DegreeMain degreeFrame;
				try {
					degreeFrame = new DegreeMain();
					frame.setVisible(false);
					degreeFrame.frame.setVisible(true);
					degreeFrame.userRole=userRole;
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnDegreeManagement.setBounds(22, 325, 206, 23);
		frame.getContentPane().add(btnDegreeManagement);
		
		JButton btnStudentImport = new JButton("Student Import");
		btnStudentImport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ImportMain importFrame;
				try {
					importFrame = new ImportMain();
					frame.setVisible(false);
					importFrame.frame.setVisible(true);
					importFrame.selectedOption="STUDENT";
					importFrame.showTitle();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnStudentImport.setBounds(275, 195, 189, 23);
		frame.getContentPane().add(btnStudentImport);
		
		JButton btnStuCourseImport = new JButton("Student Course Import");
		btnStuCourseImport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ImportMain importFrame;
				try {
					importFrame = new ImportMain();
					frame.setVisible(false);
					importFrame.frame.setVisible(true);
					importFrame.selectedOption="COURSE";
					importFrame.showTitle();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnStuCourseImport.setBounds(275, 240, 189, 23);
		frame.getContentPane().add(btnStuCourseImport);
		
		JButton btnUserManagement = new JButton("User Management");
		btnUserManagement.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				UserMain userFrame;
				try {
					userFrame = new UserMain();
					frame.setVisible(false);
					userFrame.frame.setVisible(true);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnUserManagement.setBounds(22, 150, 207, 23);
		frame.getContentPane().add(btnUserManagement);
		
		JButton btnDegreeManagemet = new JButton("Forecast Update");
		btnDegreeManagemet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				DegreeMainHome degreeh;
				try {
					degreeh = new DegreeMainHome();
					frame.setVisible(false);
					degreeh.frame.setVisible(true);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnDegreeManagemet.setBounds(22, 280, 207, 23);
		frame.getContentPane().add(btnDegreeManagemet);
		
		JButton btnNewButton_1 = new JButton("Schedule Management");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ScheduleMain schedule ;
				try {
					schedule =new ScheduleMain();
					frame.setVisible(false);
					schedule.frame.setVisible(true);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		btnNewButton_1.setBounds(493, 150, 194, 23);
		frame.getContentPane().add(btnNewButton_1);
		
	}
	
	public void showTitle(){
		JLabel lblLoggesAs = new JLabel("Logged in as "+userRole+" User");
		lblLoggesAs.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblLoggesAs.setBounds(36, 35, 216, 14);
		frame.getContentPane().add(lblLoggesAs);
		
	}
}
