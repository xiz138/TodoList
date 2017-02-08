package edu.pitt.todolist.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

public class DeleteButtonListener implements ActionListener {
	Controller controller;
	
	public DeleteButtonListener(Controller controller) {
		this.controller = controller;
	}
	
	public void actionPerformed(ActionEvent e) {
		String[] selectedItems = controller.getView().getUserTaskList().getSelectedItems();
		//int [] selectedTaskID = controller.getView().getUserTaskList().getSelectedItem()
		int taskID;
		Vector<String> selectedItemVector = new Vector<String>();
		for (String selectedItem : selectedItems) {
			controller.getModel().deleteListItem(selectedItem);
			controller.getModel().deleteListItemFromDB(selectedItem); 
			//Delete task_id from DB.task_user Table
			controller.getModel().deleteTaskUserFromDB(taskID);
			selectedItemVector.add(selectedItem);
		}
		controller.getView().removeFromList(selectedItemVector);
    }
}
