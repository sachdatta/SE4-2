package com.room;

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

public class RoomView {

	JFrame frame;
	public String action="ADD";
	public String userRole;
	private JTextField roomBuilding;
	private JTextField roomCapacity;
	private JTextField roomNumber;

	/**
	 * Launch the application.
	 
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RoomView window = new RoomView();
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
	public RoomView() {
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
				RoomMain room;
				try {
					room = new RoomMain();
					frame.setVisible(false);
					room.userRole=userRole;
					room.frame.setVisible(true);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		});
		btnBack.setBounds(10, 16, 89, 23);
		frame.getContentPane().add(btnBack);
		
		JLabel lblRoomManagement = new JLabel("Room Management");
		lblRoomManagement.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblRoomManagement.setBounds(286, 48, 177, 23);
		frame.getContentPane().add(lblRoomManagement);
		
		JLabel lblName = new JLabel("Building");
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblName.setBounds(84, 106, 46, 14);
		frame.getContentPane().add(lblName);
		
		roomBuilding = new JTextField();
		roomBuilding.setBounds(187, 104, 177, 20);
		frame.getContentPane().add(roomBuilding);
		roomBuilding.setColumns(10);
		
		JLabel lblTeachingLoad = new JLabel("Room Capacity");
		lblTeachingLoad.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblTeachingLoad.setBounds(84, 212, 100, 14);
		frame.getContentPane().add(lblTeachingLoad);
		
		roomCapacity = new JTextField();
		roomCapacity.setBounds(187, 210, 86, 20);
		frame.getContentPane().add(roomCapacity);
		roomCapacity.setColumns(10);
		
		
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
					if(action.equals("ADD")){
					addNewRoom();
					dialog("Room added sucessfully");
					RoomView courseFrame=new RoomView();
					frame.setVisible(false);
					courseFrame.frame.setVisible(true);
					}else if(action.equals("UPDATE")){
						updateRoom();
						dialog("Room updated sucessfully");
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
		
		roomNumber = new JTextField();
		roomNumber.setBounds(187, 157, 177, 20);
		frame.getContentPane().add(roomNumber);
		roomNumber.setColumns(10);
		
		JLabel lblRoomNumber = new JLabel("Room Number");
		lblRoomNumber.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblRoomNumber.setBounds(84, 160, 93, 14);
		frame.getContentPane().add(lblRoomNumber);
	}


	public void reload() {
		// TODO Auto-generated method stub
		
	}
	
	private void addNewRoom() throws IOException, UserException {
		Room room=new Room();
		room.setRoomNumber(roomNumber.getText());
		room.setBuilding(roomBuilding.getText());
		room.setCapacity(roomCapacity.getText());
		RoomEvent fe=new RoomEvent();
		fe.vaidateRoom(room);
		fe.addRoom(room);
	}
	
	private void updateRoom() throws UserException, IOException {
		Room room=new Room();
		room.setRoomNumber(roomNumber.getText());
		room.setBuilding(roomBuilding.getText());
		room.setCapacity(roomCapacity.getText());
		RoomEvent fe=new RoomEvent();
		fe.vaidateRoom(room);
		fe.updateRoom(room);
		
	}
	

	private void dialog(String msg) {
		JOptionPane.showMessageDialog(frame, msg);
	}

	public void viewSelected(String selected) throws IOException {
		RoomEvent ce=new RoomEvent();
		Room c=ce.getRoom(selected);
		roomBuilding.setText(c.getBuilding());
		roomNumber.setText(c.getRoomNumber());
		roomCapacity.setText(c.getCapacity());
		
	}
}
