package com.degree.main;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.login.Login;
import com.login.UserException;

public class DegreeViewHome {

	JFrame frame;
	public String action="ADD";
	public String userRole;
	private JTextField degreeCode;
	private JTextField degreeName;
	private JTextField gradSchool;
	private JTextField forecast;

	/**
	 * Launch the application.
	 
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DegreeView window = new DegreeView();
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
	public DegreeViewHome() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 728, 498);
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
				DegreeMainHome degree;
				try {
					degree = new DegreeMainHome();
					frame.setVisible(false);
					degree.userRole=userRole;
					degree.frame.setVisible(true);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		});
		btnBack.setBounds(10, 16, 89, 23);
		frame.getContentPane().add(btnBack);
		
		JLabel lblDegreeManagement = new JLabel("Forecast Management");
		lblDegreeManagement.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblDegreeManagement.setBounds(286, 48, 177, 23);
		frame.getContentPane().add(lblDegreeManagement);
		
		JLabel lblName = new JLabel("Degree Code");
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblName.setBounds(84, 106, 93, 14);
		frame.getContentPane().add(lblName);
		
		degreeCode = new JTextField();
		degreeCode.setBounds(187, 104, 177, 20);
		frame.getContentPane().add(degreeCode);
		degreeCode.setColumns(10);
		
		JLabel lblTeachingLoad = new JLabel("Degree Name");
		lblTeachingLoad.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblTeachingLoad.setBounds(84, 212, 100, 14);
		frame.getContentPane().add(lblTeachingLoad);
		
		degreeName = new JTextField();
		degreeName.setBounds(187, 210, 297, 20);
		frame.getContentPane().add(degreeName);
		degreeName.setColumns(10);
		
		
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
					if(action.equals("ADD")){
					addNewDegree();
					dialog("Degree added sucessfully");
					DegreeViewHome courseFrame=new DegreeViewHome();
					frame.setVisible(false);
					courseFrame.frame.setVisible(true);
					}else if(action.equals("UPDATE")){
						updateDegree();
						dialog("Degree updated sucessfully");
					}
				}catch(UserException e1){
					if(!e1.getMessage().toString().equals("Invalid input"))
						dialog(e1.getMessage());
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}


		});
		btnSave.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnSave.setBounds(289, 413, 89, 23);
		frame.getContentPane().add(btnSave);
		
		gradSchool = new JTextField();
		gradSchool.setBounds(187, 157, 177, 20);
		frame.getContentPane().add(gradSchool);
		gradSchool.setColumns(10);
		
		JLabel lblDegreeNumber = new JLabel("Grad School");
		lblDegreeNumber.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblDegreeNumber.setBounds(84, 160, 93, 14);
		frame.getContentPane().add(lblDegreeNumber);
		
		JLabel lblForeCast = new JLabel("Forecast");
		lblForeCast.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblForeCast.setBounds(84, 267, 93, 14);
		frame.getContentPane().add(lblForeCast);
		
		forecast = new JTextField();
		forecast.setBounds(187, 265, 86, 20);
		frame.getContentPane().add(forecast);
		forecast.setColumns(10);
	}


	public void reload() {
		// TODO Auto-generated method stub
		
	}
	
	private void addNewDegree() throws IOException, UserException {
		DegreeHome degree=new DegreeHome();
		degree.setDegreeCode(degreeCode.getText());
		degree.setGradSchool(gradSchool.getText());
		degree.setDegreeName(degreeName.getText());
		degree.setForecast(forecast.getText());
		DegreeEventHome fe=new DegreeEventHome();
		fe.vaidateDegree(degree);
		fe.addDegree(degree);
	}
	
	private void updateDegree() throws UserException, IOException {
		DegreeHome degree=new DegreeHome();
		degree.setDegreeCode(degreeCode.getText());
		degree.setGradSchool(gradSchool.getText());
		degree.setDegreeName(degreeName.getText());
		degree.setForecast(forecast.getText());
		DegreeEventHome fe=new DegreeEventHome();
		fe.vaidateDegree(degree);
		fe.updateDegree(degree);
		
	}
	

	private void dialog(String msg) {
		JOptionPane.showMessageDialog(frame, msg);
	}

	public void viewSelected(String selected) throws IOException {
		DegreeEventHome ce=new DegreeEventHome();
		DegreeHome c=ce.getDegree(selected);
		degreeCode.setText(c.getDegreeCode());
		degreeCode.setEditable(false);
		gradSchool.setText(c.getGradSchool());
		degreeName.setText(c.getDegreeName());
		forecast.setText(c.getForecast());
		
	}
}
