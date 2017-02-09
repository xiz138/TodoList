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
		String selectedTask = controller.getView().getSelectedTask();
		int selectedTaskID = controller.getModel().getSelectedTaskID(selectedTask) + 1;
		
		controller.getModel().deleteTaskUserFromDB(selectedTaskID); // Delete task_id from DB.task_user Table
		controller.getModel().deleteListItemFromDB(selectedTask);
		
		Vector<String> selectedItemVector = new Vector<String>();
		for (String selectedItem : selectedItems) {
			controller.getModel().deleteListItem(selectedItem);		
			selectedItemVector.add(selectedItem);
		}
		controller.getView().removeFromList(selectedItemVector);
    }
}
