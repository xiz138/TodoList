package edu.pitt.todolist.view;

import java.awt.Dimension;
import java.awt.List;
import java.awt.TextField;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import edu.pitt.todolist.controller.Controller;
import edu.pitt.todolist.model.ListItem;

public class View {
	private JFrame window;
	private JButton addButton;
	private JButton deleteButton;
	private List todoList;
	private TextField textField;
	
	// add param list -> default list
	public View(Vector<ListItem> defaultList) {
		window = new JFrame("ToDoList");
		JPanel panel = new JPanel();
		panel.setLayout(null);

	    JPanel listPanel = new JPanel();
	    
	    todoList = new List(20,true);
	    // add item here
	    // todoList.add(defaultList.elementAt(0).getDescription());
	    for (int i = 0; i < defaultList.size();i++){
	    	todoList.add(defaultList.get(i).getDescription());
	    }
	    
	    todoList.setSize(300,400);
	    listPanel.setBounds(400,50, 250, 300);
	    listPanel.add(todoList);
	    
	    panel.add(listPanel);
	    panel.setBounds(0,0,700,500);

		JLabel label = new JLabel("Please enter item here:");
		label.setBounds(100, 50, 250, 40);
		panel.add(label);
		textField = new TextField("");
		textField.setBounds(100,100,250,40);
		panel.add(textField);

		addButton = new JButton();
		addButton.setText("Add Items");
		addButton.setBounds(100, 200, 250, 40);
		panel.add(addButton);
	    
		deleteButton = new JButton();
		deleteButton.setText("Delete Items");
		deleteButton.setBounds(100,300,250,40);
		panel.add(deleteButton);
	    
		window.add(panel);
		window.setSize(700, 500);
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

	public List getTodoList() {
		return todoList;
	}

	public TextField getInput() {
		return textField;
	}

	public void addToList(String description) {
		todoList.add(description);
	}

	public void removeFromList(Vector<String> selectedItems) {
		Vector<String> list = new Vector<String>();
		for (String listItem : todoList.getItems()) {
			if (!selectedItems.contains(listItem)) {
				list.add(listItem);
			}
		}
		todoList.removeAll();
		for (String item : list) {
			todoList.add(item);
		}
	}
}
