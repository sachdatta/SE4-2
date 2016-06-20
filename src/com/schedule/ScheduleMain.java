package com.schedule;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

import com.login.Login;
import com.login.UserViewMain;
import javax.swing.JLabel;
import java.awt.Font;



public class ScheduleMain {

	public JFrame frame;
	public String userRole;

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
	 */
	public ScheduleMain() {
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
				UserViewMain office=new UserViewMain();
				
				frame.setVisible(false);				
				office.userRole=userRole;
				office.frame.setVisible(true);

			}
		});
		btnBack.setBounds(42, 11, 89, 23);
		frame.getContentPane().add(btnBack);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					addSchedule();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnAdd.setBounds(92, 313, 89, 23);
		frame.getContentPane().add(btnAdd);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.setBounds(300, 313, 89, 23);
		frame.getContentPane().add(btnUpdate);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.setBounds(462, 313, 89, 23);
		frame.getContentPane().add(btnDelete);
		
		JLabel lblScheduleManagement = new JLabel("Schedule Management");
		lblScheduleManagement.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblScheduleManagement.setBounds(256, 54, 219, 23);
		frame.getContentPane().add(lblScheduleManagement);

	}
	

	private void addSchedule()throws Exception {
		ScheduleView schV= new ScheduleView();		
		frame.setVisible(false);
		schV.frame.setVisible(true);
		schV.action="ADD";
		
	}
}
