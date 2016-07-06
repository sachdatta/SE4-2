package com.university;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.csvreader.CsvWriter;
import com.login.Login;
import com.login.UserViewMain;

import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JTextField;

public class UniversityUpdate {

	public JFrame frame;
	public String userRole;
	private JTextField name;
	private JTextField abbreviation;
	private static final String FILE_PATH = "C:/Input/University.csv";

	/**
	 * Launch the application.
	 
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UniversityUpdate window = new UniversityUpdate();
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
	public UniversityUpdate() {
		initialize();
	}

	public UniversityUpdate(String userRole2) {
		userRole=userRole2;
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
		
		JLabel lblUpdateUniversity = new JLabel("Update University");
		lblUpdateUniversity.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblUpdateUniversity.setBounds(270, 56, 244, 23);
		frame.getContentPane().add(lblUpdateUniversity);
		
		name = new JTextField();
		name.setBounds(270, 122, 296, 20);
		frame.getContentPane().add(name);
		name.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Abbreviation");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel.setBounds(171, 172, 81, 14);
		frame.getContentPane().add(lblNewLabel);
		
		abbreviation = new JTextField();
		abbreviation.setBounds(270, 170, 86, 20);
		frame.getContentPane().add(abbreviation);
		abbreviation.setColumns(10);
		
		JLabel lblName = new JLabel("Name");
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblName.setBounds(171, 124, 66, 14);
		frame.getContentPane().add(lblName);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(name.getText().equals("")||name.getText().equals(null))
					JOptionPane.showMessageDialog(frame, "Please enter University name");
				else if(abbreviation.getText().equals("")||abbreviation.getText().equals(null))
					JOptionPane.showMessageDialog(frame, "Please enter Abbreviation");
				else{
					File file = new File(FILE_PATH);
					file.delete();
			        try {
						CsvWriter csvOutput = new CsvWriter(new FileWriter(FILE_PATH, true), ',');
						csvOutput.write("Name");
				        csvOutput.write("Abbreviation");
				        csvOutput.endRecord();
				        csvOutput.write(name.getText());
				        csvOutput.write(abbreviation.getText());
				        csvOutput.endRecord();
				        csvOutput.close();
				        JOptionPane.showMessageDialog(frame, "University details updated");
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}  
				}
				
				
		        
			}
		});
		btnUpdate.setBounds(288, 246, 89, 23);
		frame.getContentPane().add(btnUpdate);
		
	}
}
