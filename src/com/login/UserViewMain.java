package com.login;

import com.faculty.FacultyMain;
import com.importfile.ImportMain;
import com.report.DegreePlanView;
import com.report.StudentReport;
import com.report.TeacherReport;
import com.room.RoomMain;
import com.schedule.ScheduleMain;
import com.testschedule.TestScheduleMain;
import com.university.UniversityUpdate;
import com.user.UserMain;

import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JButton;

import com.courses.CourseMain;
import com.csvreader.CsvReader;
import com.degree.DegreeMain;
import com.degree.main.DegreeMainHome;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.IOException;

public class UserViewMain {

	public JFrame frame;
	public static String  userRole;
	public String selectedOption;
	public static int count=0;
	private static final String FILE_PATH = "C:/Input/University.csv";
	

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
	 * @throws IOException 
	 */
	public UserViewMain() throws IOException {
		initialize();
	}

	public UserViewMain(String role) throws IOException {
		userRole=role;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * @throws IOException 
	 */
	private void initialize() throws IOException {
		frame = new JFrame();
		frame.setBounds(100, 100, 725, 495);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblWelcome = new JLabel(getUnivName());
		lblWelcome.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblWelcome.setBounds(211, 60, 318, 27);
		frame.getContentPane().add(lblWelcome);
		
		JButton btnCourseManagement = new JButton("Course Management");
		btnCourseManagement.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CourseMain courseFrame;
					try {
						courseFrame = new CourseMain(userRole);
						frame.setVisible(false);
						courseFrame.frame.setVisible(true);
						courseFrame.userRole=userRole;
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			}
		});
		btnCourseManagement.setBounds(22, 206, 207, 23);
		frame.getContentPane().add(btnCourseManagement);
		
		JButton btnNewButton = new JButton("Faculty Management");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				FacultyMain facultyFrame;
				try {
					facultyFrame = new FacultyMain(userRole);
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
					roomFrame = new RoomMain(userRole);
					frame.setVisible(false);
					roomFrame.frame.setVisible(true);
					roomFrame.userRole=userRole;
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnRoomManagement.setBounds(22, 342, 207, 23);
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
					degreeFrame = new DegreeMain(userRole);
					frame.setVisible(false);
					degreeFrame.frame.setVisible(true);
					//degreeFrame.userRole=userRole;
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnDegreeManagement.setBounds(22, 274, 206, 23);
		frame.getContentPane().add(btnDegreeManagement);
		
		JButton btnStudentImport = new JButton("Student Import");
		btnStudentImport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ImportMain importFrame;
				try {
					importFrame = new ImportMain(userRole);
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
		btnStudentImport.setBounds(275, 172, 189, 23);
		frame.getContentPane().add(btnStudentImport);
		
		JButton btnStuCourseImport = new JButton("Student Course Import");
		btnStuCourseImport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ImportMain importFrame;
				try {
					importFrame = new ImportMain(userRole);
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
		btnStuCourseImport.setBounds(275, 206, 189, 23);
		frame.getContentPane().add(btnStuCourseImport);
		
		JButton btnUserManagement = new JButton("User Management");
		btnUserManagement.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				UserMain userFrame;
				try {
					userFrame = new UserMain(userRole);
					frame.setVisible(false);
					userFrame.frame.setVisible(true);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnUserManagement.setBounds(22, 172, 207, 23);
		frame.getContentPane().add(btnUserManagement);
		
		JButton btnDegreeManagemet = new JButton("Forecast Update");
		btnDegreeManagemet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				DegreeMainHome degreeh;
				try {
					degreeh = new DegreeMainHome(userRole);
					frame.setVisible(false);
					degreeh.frame.setVisible(true);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnDegreeManagemet.setBounds(22, 308, 207, 23);
		frame.getContentPane().add(btnDegreeManagemet);
		
		JButton btnNewButton_1 = new JButton("Generate Schedule");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ScheduleMain schedule ;
				try {
					schedule =new ScheduleMain(userRole);					
					frame.setVisible(false);					
					schedule.frame.setVisible(true);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		btnNewButton_1.setBounds(492, 172, 194, 23);
		frame.getContentPane().add(btnNewButton_1);
		
		JLabel lblLoggesAs = new JLabel("Logged as "+userRole+" User");
		lblLoggesAs.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblLoggesAs.setBounds(36, 25, 129, 14);
		frame.getContentPane().add(lblLoggesAs);
		
		JButton btnNewButton_2 = new JButton("Test Schedule");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				TestScheduleMain schedule ;
				try {
					schedule =new TestScheduleMain(userRole);					
					frame.setVisible(false);					
					schedule.frame.setVisible(true);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNewButton_2.setBounds(492, 206, 195, 23);
		frame.getContentPane().add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("Update University Name");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UniversityUpdate up=new UniversityUpdate(userRole);
				frame.setVisible(false);
				up.frame.setVisible(true);
			}
		});
		btnNewButton_3.setBounds(22, 376, 207, 23);
		frame.getContentPane().add(btnNewButton_3);
		
		JButton btnTeacherReport = new JButton("Teacher Report");
		btnTeacherReport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				TeacherReport up=new TeacherReport(userRole);
				frame.setVisible(false);
				up.frame.setVisible(true);

			}
		});
		btnTeacherReport.setBounds(492, 296, 195, 23);
		frame.getContentPane().add(btnTeacherReport);
		
		JButton btnStudentReport = new JButton("Student Report");
		btnStudentReport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StudentReport up;
				try {
					up = new StudentReport(userRole);
					frame.setVisible(false);
					up.frame.setVisible(true);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				

			}
		});
		btnStudentReport.setBounds(492, 330, 195, 23);
		frame.getContentPane().add(btnStudentReport);
		
		JButton btnDegreePlan = new JButton("Degree Plan");
		btnDegreePlan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					DegreePlanView dpv=new DegreePlanView(userRole);
					frame.setVisible(false);
					dpv.frame.setVisible(true);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnDegreePlan.setBounds(492, 364, 195, 23);
		frame.getContentPane().add(btnDegreePlan);
	}

	private String getUnivName() throws IOException {
		CsvReader univ = new CsvReader(FILE_PATH);
		univ.readHeaders();
		String name="";
		while(univ.readRecord()){
			name=univ.get(0);
		}
		univ.close();
		return name;

	}
}
