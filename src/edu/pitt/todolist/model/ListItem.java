package edu.pitt.todolist.model;

public class ListItem {
	private String description;
	private int listKey;
	
	public ListItem(){}
	
	public ListItem(int listKey, String description) {
		this.description = description;
		//this.setListKey(listKey);
		this.listKey = listKey;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getListKey() {
		return listKey;
	}

	public void setListKey(int listKey) {
		this.listKey = listKey;
	}
}
