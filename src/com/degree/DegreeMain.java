package com.degree;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

import com.login.Login;
import com.login.UserViewMain;

public class DegreeMain {

	public JFrame frame;
	public String userRole;
	private JButton btnLogout;
	private JList degreeList;
	private JScrollPane  scrollPane;

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
	public DegreeMain() throws IOException {
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
				UserViewMain office=new UserViewMain();
				
				frame.setVisible(false);
				office.userRole=userRole;
				office.frame.setVisible(true);

			}
		});
		btnBack.setBounds(42, 11, 89, 23);
		frame.getContentPane().add(btnBack);
		
		JLabel lblCourseManagement = new JLabel("Degree Management");
		lblCourseManagement.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblCourseManagement.setBounds(284, 38, 165, 29);
		frame.getContentPane().add(lblCourseManagement);
		
		JButton btnAdd = new JButton("Add");
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
		frame.getContentPane().add(btnAdd);
		
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
		
		JButton btnDelete = new JButton("Delete");
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
		frame.getContentPane().add(btnDelete);
		
	
		viewDegree();

	}

	private void viewDegree() throws IOException {
		DegreeEvent ce=new DegreeEvent();
		ArrayList<Degree> avl=new ArrayList<Degree>();
		avl=ce.getAllDegree();
		String cList[] = new String[100];
		int i=0;
		for(Degree c:avl){
			cList[i]=c.getDegreeCode()+" "+c.getDegreeDescription();
			i++;
		}
		degreeList = new JList(cList);		
		degreeList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane=new JScrollPane(degreeList);
		scrollPane.setBounds(168, 117, 385, 124);
		frame.getContentPane().add(scrollPane);
	
		
	}
	
	private void delete() throws Exception {
		if(degreeList.getSelectedValue() != null)
			try {
				{
					DegreeEvent fe=new DegreeEvent();
					String code=degreeList.getSelectedValue().toString().split(" ")[0];
					String description=degreeList.getSelectedValue().toString().substring(code.length()+1, degreeList.getSelectedValue().toString().length());
					fe.deleteDegree(code,description);
					dialog("Degree deleted sucessfully");
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		else
			dialog("Please select a Course");
		DegreeMain degreeFrame= new DegreeMain();
		frame.setVisible(false);
		degreeFrame.frame.setVisible(true);
		
	}
	
	private void dialog(String msg) {
		JOptionPane.showMessageDialog(frame, msg);
	}

	private void addDegree() throws Exception {
		DegreeView degreeFrame= new DegreeView();
		degreeFrame.reload();
		frame.setVisible(false);
		degreeFrame.frame.setVisible(true);
		degreeFrame.action="ADD";
		
	}
	
	private void updateDegree() throws Exception {
		DegreeView degreeFrame= new DegreeView();
		if(degreeList.getSelectedValue() != null){
			String code=degreeList.getSelectedValue().toString().split(" ")[0];
			String description=degreeList.getSelectedValue().toString().substring(code.length()+1, degreeList.getSelectedValue().toString().length());
				degreeFrame.viewSelected(code,description);
				frame.setVisible(false);
				degreeFrame.frame.setVisible(true);
				degreeFrame.action="UPDATE";
		}else
			dialog("Please select a Degree");
	}
	
	
}
