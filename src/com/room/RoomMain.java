package com.room;

import java.awt.EventQueue;
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

public class RoomMain {

	public JFrame frame;
	public String userRole;
	private JList roomList;
	private JScrollPane  scrollPane;

	/**
	 * Launch the application.
	 
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RoomMain window = new RoomMain();
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
	public RoomMain() throws IOException {
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
		
		JLabel lblCourseManagement = new JLabel("Room Management");
		lblCourseManagement.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblCourseManagement.setBounds(284, 38, 165, 29);
		frame.getContentPane().add(lblCourseManagement);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					addRoom();
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
					updateRoom();
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
		
	
		viewRoom();

	}

	private void viewRoom() throws IOException {
		RoomEvent ce=new RoomEvent();
		ArrayList<Room> avl=new ArrayList<Room>();
		avl=ce.getAllRooms();
		String cList[] = new String[100];
		int i=0;
		for(Room c:avl){
			cList[i]=c.getRoomNumber()+"  "+c.getBuilding()+"   "+c.getCapacity();
			i++;
		}
		roomList = new JList(cList);		
		roomList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane=new JScrollPane(roomList);
		scrollPane.setBounds(155, 131, 396, 124);
		frame.getContentPane().add(scrollPane);
	
		
	}
	
	private void delete() throws Exception {
		if(roomList.getSelectedValue() != null)
			try {
				{
					RoomEvent fe=new RoomEvent();
					fe.deleteRoom(roomList.getSelectedValue().toString().split(" ")[0]);
					dialog("Room deleted sucessfully");
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		else
			dialog("Please select a Course");
		RoomMain facultyFrame= new RoomMain();
		frame.setVisible(false);
		facultyFrame.frame.setVisible(true);
		
	}
	
	private void dialog(String msg) {
		JOptionPane.showMessageDialog(frame, msg);
	}

	private void addRoom() throws Exception {
		RoomView facultyFrame= new RoomView();
		facultyFrame.reload();
		frame.setVisible(false);
		facultyFrame.frame.setVisible(true);
		facultyFrame.action="ADD";
		
	}
	
	private void updateRoom() throws Exception {
		RoomView facultyFrame= new RoomView();
		if(roomList.getSelectedValue() != null){
				String selected=roomList.getSelectedValue().toString().split(" ")[0];
				facultyFrame.viewSelected(selected);
				frame.setVisible(false);
				facultyFrame.frame.setVisible(true);
				facultyFrame.action="UPDATE";
		}else
			dialog("Please select a Course");
	}
	
	
}
