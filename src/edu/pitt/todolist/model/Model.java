package edu.pitt.todolist.model;

import java.util.Vector;
import java.sql.*;

public class Model {
	private Vector<ListItem> todoList;
	
	public Model() {
		this.todoList = new Vector<ListItem>();

		
	try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e1) {
			e1.printStackTrace();
		}
	try {
    	Connection con = DriverManager.getConnection("jdbc:mysql://sis-teach-01.sis.pitt.edu/xiz138is1017","xiz138is1017","xiz138@pitt.edu");
    	String query = "SELECT description FROM ToDoList";
    	Statement statement = con.createStatement();
    	
    	ResultSet rs = statement.executeQuery(query);
    	while(rs.next()){
    		//System.out.println(rs.getString("description"));
    		todoList.addElement(new ListItem(rs.getString("description")));
    	}

	} catch (SQLException e) {
		e.printStackTrace();	
	}

	}

	public void addListItem(String description) {
		this.todoList.add(new ListItem(description));
	}
	
	public void addListItemToDB(String description) {
		// estabilish db connection
		try {
	    	Connection con = DriverManager.getConnection("jdbc:mysql://sis-teach-01.sis.pitt.edu/xiz138is1017","xiz138is1017","xiz138@pitt.edu");
	    	String insert = "INSERT INTO ToDoList (description) VALUES "+"('"+ description+"')";
	    	Statement statement = con.createStatement();
	    	statement.execute(insert);

		} catch (SQLException e) {
			e.printStackTrace();	
		}
		
	}
	
	public void deleteListItem(String description) {
		Vector<ListItem> itemsToDelete = new Vector<ListItem>();
		for (ListItem item : todoList) {
			if (item.getDescription().equals(description)) {
				itemsToDelete.add(item);
			}
		}
		for (ListItem item : itemsToDelete) {
			todoList.remove(item);
		}
		
	}
	
	public void deleteListItemFromDB(String description){
		try {
	    	Connection con = DriverManager.getConnection("jdbc:mysql://sis-teach-01.sis.pitt.edu/xiz138is1017","xiz138is1017","xiz138@pitt.edu");
	    	String delete = "DELETE FROM ToDoList WHERE description="+"('"+ description+"')";
	    	Statement statement = con.createStatement();
	    	statement.execute(delete);
		} catch (SQLException e) {
		e.printStackTrace();	
		}
	}

	public Vector<ListItem> getList() {	
		return todoList;
	}
}
