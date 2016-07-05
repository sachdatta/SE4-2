package com.degree.main;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

import com.degree.Degree;
import com.degree.DegreeEvent;
import com.login.Login;
import com.login.UserException;
import com.login.UserViewMain;

import javax.swing.JTextField;

public class DegreeMainHome {

	public JFrame frame;
	public String userRole;
	private JList degreeList;
	private JScrollPane  scrollPane;
	private JTextField forecast;

	/**
	 * Launch the application.
	 
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DegreeMain window = new DegreeMain();
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
	public DegreeMainHome() throws IOException {
		initialize();
	}

	public DegreeMainHome(String userRole2) throws IOException {
		userRole=userRole2;
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
		
		JLabel lblCourseManagement = new JLabel("Forecast Management");
		lblCourseManagement.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblCourseManagement.setBounds(284, 38, 165, 29);
		frame.getContentPane().add(lblCourseManagement);
		
		/*JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					addDegree();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnAdd.setBounds(124, 302, 89, 23);
		frame.getContentPane().add(btnAdd);*/
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					updateDegree();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnUpdate.setBounds(313, 302, 89, 23);
		frame.getContentPane().add(btnUpdate);
		
		/*JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					delete();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		});
		btnDelete.setBounds(504, 302, 89, 23);
		frame.getContentPane().add(btnDelete);*/
		
	
		viewDegree();

	}

	private void viewDegree() throws IOException {
		DegreeEventHome ce=new DegreeEventHome();
		ArrayList<DegreeHome> avl=new ArrayList<DegreeHome>();
		avl=ce.getAllDegrees(); 
		String cList[] = new String[100];
		int i=0;
		for(DegreeHome c:avl){
			cList[i]=c.getDegreeCode()+"   - "+c.getForecast();
			i++; 
		}
		degreeList = new JList(cList);		
		degreeList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane=new JScrollPane(degreeList);
		scrollPane.setBounds(155, 131, 396, 124);
		frame.getContentPane().add(scrollPane);
	
		
	}
	
	private void delete() throws Exception {
		if(degreeList.getSelectedValue() != null)
			try {
				{
					DegreeEventHome fe=new DegreeEventHome();
					fe.deleteDegree(degreeList.getSelectedValue().toString().split(" ")[0]);
					dialog("Degree deleted sucessfully");
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		else
			dialog("Please select a Course");
		DegreeMainHome facultyFrame= new DegreeMainHome();
		frame.setVisible(false);
		facultyFrame.frame.setVisible(true);
		
	}
	
	private void dialog(String msg) {
		JOptionPane.showMessageDialog(frame, msg);
	}

	private void addDegree() throws Exception {
		DegreeViewHome facultyFrame= new DegreeViewHome("ADD");
		facultyFrame.reload();
		frame.setVisible(false);
		facultyFrame.frame.setVisible(true);
		facultyFrame.action="ADD";
		
	}
	
	private void updateDegree() throws Exception {
		if(degreeList.getSelectedValue() != null){
			JLabel lblForecast = new JLabel("Forecast");
			lblForecast.setFont(new Font("Tahoma", Font.PLAIN, 12));
			lblForecast.setBounds(226, 354, 104, 14);
			frame.getContentPane().add(lblForecast);
			
			forecast = new JTextField();
			forecast.setBounds(365, 352, 86, 20);
			frame.getContentPane().add(forecast);
			forecast.setColumns(10);
			
			DegreeEventHome facultyFrame= new DegreeEventHome();
			JButton btnSave = new JButton("Save");
			btnSave.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					try{
					if(forecast.getText().toString().equals("")||forecast.getText().toString().equals(null))
						throw new UserException("Please enter forcast value ");
					if(Pattern.matches("[a-zA-Z]+", forecast.getText().toString()) == true)
						throw new UserException("Degree capacity should be numaric ");
					DegreeHome d=new DegreeHome();
					d.setDegreeCode(degreeList.getSelectedValue().toString().split(" ")[0]);
					d.setForecast(forecast.getText());
					DegreeEventHome de= new DegreeEventHome();
					de.updateDegree(d);
					dialog("Forcast Updated");					 
					DegreeMainHome degreeh = new DegreeMainHome(userRole);
					frame.setVisible(false);
					degreeh.frame.setVisible(true);
					}catch(Exception e){
						dialog(e.getMessage().toString());
					}
				}
			});
			btnSave.setBounds(482, 351, 89, 23);
			frame.getContentPane().add(btnSave);
			
			
			
			forecast.updateUI();
			lblForecast.updateUI();
			btnSave.updateUI();

			String selected=degreeList.getSelectedValue().toString().split(" ")[0];
			DegreeHome d=new DegreeHome();
			d=facultyFrame.getDegree(selected);
			forecast.setText(d.getForecast());
			
	}else
		dialog("Please select a Course");
		
		
			}
}
