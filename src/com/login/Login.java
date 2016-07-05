package com.login;

import java.awt.EventQueue;

import com.courses.CourseView;
import com.courses.CourseView;
import com.faculty.FacultyMain;
import com.login.service.LoginValidate;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.swing.JPasswordField;

import org.apache.commons.io.FileUtils;

public class Login {

	public JFrame frame;
	private JTextField userName;
	private JPasswordField password;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login window = new Login();
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
	public Login() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 705, 497);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel welcomeLbl = new JLabel("Welcome");
		welcomeLbl.setFont(new Font("Tahoma", Font.PLAIN, 16));
		welcomeLbl.setBounds(342, 70, 96, 24);
		frame.getContentPane().add(welcomeLbl);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblUsername.setBounds(196, 159, 75, 24);
		frame.getContentPane().add(lblUsername);
		
		userName = new JTextField();
		userName.setFont(new Font("Tahoma", Font.PLAIN, 12));
		userName.setBounds(380, 161, 126, 20);
		frame.getContentPane().add(userName);
		userName.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Password");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel.setBounds(196, 217, 75, 14);
		frame.getContentPane().add(lblNewLabel);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent arg0) {
				LoginValidate loginCheck=new LoginValidate();
				try {
					String role=loginCheck.validate(userName.getText(),password.getText());
					if(!role.equals("")&&!role.equals(null)){
						copyFile();
						UserViewMain office=new UserViewMain(role);
						frame.setVisible(false);						
						office.frame.setVisible(true);
						
					}else
						JOptionPane.showMessageDialog(frame, "Invalid Username/Password");
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}				
					
				
		}


		});
		btnLogin.setBounds(317, 274, 89, 23);
		frame.getContentPane().add(btnLogin);
		
		password = new JPasswordField();
		password.setBounds(378, 215, 128, 20);
		frame.getContentPane().add(password);
	}
	
	private void copyFile() {
		File source = new File("C:/Input/Source");
		File dest = new File("C:/Input");
		try {
		    
		    File file1 = new File("C:/Input/Input3/CourseDetails.csv");
			file1.delete();
			
		    File file2 = new File("C:/Input/DegreeCourseDetails.csv");
			file2.delete();
			
			File file3 = new File("C:/Input/DegreeDetails.csv");
			file3.delete();
			
			File file4= new File("C:/Input/FacultyDetails.csv");
			file4.delete();
			
			File file5= new File("C:/Input/RoomDetails.xlsx");
			file5.delete();
			
			//File file6= new File("C:/Input/Schedule.csv");
			//file6.delete();			
			
			File file7= new File("C:/Input/SemesterDetails.csv");
			file7.delete();
			
			File file8= new File("C:/Input/StudentCourseDetails.csv");
			file8.delete();
			
			File file9= new File("C:/Input/StudentDetails.csv");
			file9.delete();
			
			File file10= new File("C:/Input/University.csv");
			file10.delete();
			
			FileUtils.copyDirectory(source, dest);
			
			
			
		} catch (IOException e) {
		    e.printStackTrace();
		}
		
	}
	
	/*private  void copyFile(File srcFile, File destFile) throws IOException 
    {
            InputStream oInStream = new FileInputStream(srcFile);
            OutputStream oOutStream = new FileOutputStream(destFile);

            // Transfer bytes from in to out
            byte[] oBytes = new byte[1024];
            int nLength;
            BufferedInputStream oBuffInputStream = 
                            new BufferedInputStream( oInStream );
            while ((nLength = oBuffInputStream.read(oBytes)) > 0) 
            {
                oOutStream.write(oBytes, 0, nLength);
            }
            oInStream.close();
            oOutStream.close();
    }*/

}
