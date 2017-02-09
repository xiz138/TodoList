package edu.pitt.todolist.view;

import java.awt.Dimension;
import java.awt.List;
import java.awt.TextField;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import edu.pitt.todolist.controller.Controller;
import edu.pitt.todolist.model.ListItem;
import edu.pitt.todolist.model.User;

public class View {
	private JFrame window;
	private JButton addButton;
	private JButton deleteButton;
	private List userTaskList;
	private TextField textField;
	private JComboBox<String> userName;
	
	// add param list -> default list
	public View(HashMap<Integer, User> userList) {
		window = new JFrame("ToDoList");
		JPanel panel = new JPanel();
		panel.setLayout(null);

	    JPanel listPanel = new JPanel();
	    
	    userTaskList = new List(20,true);
	    // add item here
	    
//	    for (int i = 0; i < defaultList.size();i++){
//	    	todoList.add(defaultList.get(i).getDescription());
//	    }
	    
	    userTaskList.setSize(450,400);
	    listPanel.setBounds(400,50, 450, 300);
	    listPanel.add(userTaskList);
	    
	    panel.add(listPanel);
	    panel.setBounds(0,0,900,500);
	    
	    JLabel label1 = new JLabel("Please select users here: ");
	    label1.setBounds(100, 50, 250, 40);
	    panel.add(label1);
	    this.userName = new JComboBox<String>();
	    userName.setBounds(100, 100, 250, 40);
	    userName.addItem("----Select Users----");
	    for(User user : userList.values()){
	    	userName.addItem(user.getID() + "-" + user.getFirstName() + " " + user.getLastName());
	    }
	    panel.add(userName);

		JLabel label2 = new JLabel("Please enter item here:");
		label2.setBounds(100, 150, 250, 40);
		panel.add(label2);
		textField = new TextField("");
		textField.setBounds(100,200,250,40);
		panel.add(textField);

		addButton = new JButton();
		addButton.setText("Add Items");
		addButton.setBounds(100, 280, 250, 40);
		panel.add(addButton);
	    
		deleteButton = new JButton();
		deleteButton.setText("Delete Items");
		deleteButton.setBounds(100,360,250,40);
		panel.add(deleteButton);
	    
		window.add(panel);
		window.setSize(900, 500);
		window.setLocationRelativeTo(null);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setVisible(true);
	}

	public JButton getAddButton() {
		return addButton;
	}

	public JButton getDeleteButton() {
		return deleteButton;
	}

	public List getUserTaskList() {
		return userTaskList;
	}

	public TextField getInput() {
		return textField;
	}
	
	public String getUserName() {
		String selectedString = (String) this.userName.getModel().getSelectedItem();
		return selectedString.split("-")[1];
	}
	
	public Integer getUserID() {
		String selectedString = (String) this.userName.getModel().getSelectedItem();
		return new Integer(selectedString.split("-")[0]);
	}
	

	public void addTaskToList(String name,String description) {
		userTaskList.add(name + ": " + description);
	}
	
	public void addTaskToList(String description) {
		userTaskList.add(description);
	}
	
	public String getSelectedTask(){
		return userTaskList.getSelectedItem().split(": ")[1];
	}

	public void removeFromList(Vector<String> selectedItems) {
		Vector<String> list = new Vector<String>();
		for (String listItem : userTaskList.getItems()) {
			if (!selectedItems.contains(listItem)) {
				list.add(listItem);
			}
		}
		userTaskList.removeAll();
		for (String item : list) {
			userTaskList.add(item);
		}
	}
}
