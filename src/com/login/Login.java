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
import java.io.IOException;

import javax.swing.JPasswordField;

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
						UserViewMain office=new UserViewMain();
						frame.setVisible(false);						
						office.frame.setVisible(true);
						office.userRole=role;
						office.showTitle();
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
}
