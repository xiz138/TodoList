package edu.pitt.todolist.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;

public class AddButtonListener implements ActionListener {
	Controller controller;
	
	public AddButtonListener(Controller controller) {
		this.controller = controller;
	}
	
	public void actionPerformed(ActionEvent e) {
		
		String desc = controller.getView().getInput().getText();
		String name = controller.getView().getUserName();
		
		int taskid = controller.getModel().getAddedTaskID();
		int userid = controller.getView().getUserID();
		
		controller.getModel().addListItemToDB(desc);// Add input task to DB.ToDoList table 	
		controller.getView().addTaskToList(name, desc); // Add selected name + input task to List panel		
		controller.getModel().addTaskUserToDB(taskid, userid); // Add input task id + selected name id into DB.user_task table
		
		
		
		controller.getView().getInput().setText(""); // reset the input textfield
    }
}
