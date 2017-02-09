package edu.pitt.todolist.model;

import java.util.HashMap;
import java.sql.*;

public class Model {
	
	//private Vector<ListItem> todoList;
	private HashMap<Integer,ListItem> todoList;
	private HashMap<Integer,TaskUser> task_user;
	private HashMap<Integer,User> user;
	private int taskID;
	private int selectedTaskID;
	
	public Model() { // Add user from DB to HashMap
		//this.todoList = new Vector<ListItem>();
		this.todoList = new HashMap<Integer,ListItem>();
		this.task_user = new HashMap<Integer,TaskUser>();
		this.user = new HashMap<Integer, User>();

	// Establish connection to sql	
	try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e1) {
			e1.printStackTrace();
		}
	// Return table user to Java
	try {
    	Connection con = DriverManager.getConnection("jdbc:mysql://sis-teach-01.sis.pitt.edu/xiz138is1017","xiz138is1017","xiz138@pitt.edu");
    	String query1 = "SELECT * FROM user";
    	Statement statement = con.createStatement();
    	ResultSet rs1 = statement.executeQuery(query1);
    	
    	while(rs1.next()){ 		
    		User u = new User();
    		u.setID(rs1.getInt("id"));
    		u.setFirstName(rs1.getString("first_name"));
    		u.setLastName(rs1.getString("last_name"));
    		user.put(u.getID(), u);
    	}
	} catch (SQLException e) {
		e.printStackTrace();	
	}
	
	} // Constructor ends

	// Add input task to DB.ToDoList
	public void addListItemToDB(String description) {
		try {
	    	Connection con = DriverManager.getConnection("jdbc:mysql://sis-teach-01.sis.pitt.edu/xiz138is1017","xiz138is1017","xiz138@pitt.edu");
	    	String insert1 = "INSERT INTO ToDoList (description) VALUES ( '" + description + "' )";
	    	Statement statement = con.createStatement();
	    	statement.executeUpdate(insert1);
		} catch (SQLException e) {
			e.printStackTrace();	
		}
	}
	
	// Add input task to todoList 
	public void addListItem() {
		
		try {
	    	Connection con = DriverManager.getConnection("jdbc:mysql://sis-teach-01.sis.pitt.edu/xiz138is1017","xiz138is1017","xiz138@pitt.edu");
	    	String query8 = "SELECT * FROM ToDoList";
	    	Statement statement = con.createStatement();
	    	ResultSet rs8 = statement.executeQuery(query8);
	    	while(rs8.next()){ 	
	    		ListItem l = new ListItem();
	    		l.setDescription(rs8.getString("description"));
	    		todoList.put(l.getListKey(), l);
	    	}
		}catch (SQLException e) {
		e.printStackTrace();	
		}
	}
		
	// Add input task id + selected name id into DB.user_task table
	public void addTaskUserToDB(int taskidKey, int useridKey){
		try {
	    	Connection con = DriverManager.getConnection("jdbc:mysql://sis-teach-01.sis.pitt.edu/xiz138is1017","xiz138is1017","xiz138@pitt.edu");
	    	String insert2 = "INSERT INTO task_user (task_id,user_id) VALUES (" + taskidKey + "," + useridKey + ")";
	    	Statement statement = con.createStatement();
	    	statement.executeUpdate(insert2);

		} catch (SQLException e) {
			e.printStackTrace();	
		}
		
	}
	
	// Add task_user to HashMap TaskUser
	public void addTaskUser(){
		try {
			Connection con = DriverManager.getConnection("jdbc:mysql://sis-teach-01.sis.pitt.edu/xiz138is1017","xiz138is1017","xiz138@pitt.edu");
			String returnTaskID = "SELECT * FROM task_user";
			Statement statement = con.createStatement();
			ResultSet rs12 = statement.executeQuery(returnTaskID);
			while(rs12.next()){ 
				TaskUser tu = new TaskUser();
				tu.setTask_id(rs12.getInt("task_id"));
				tu.setUser_id(rs12.getInt("user_id"));
				task_user.put(tu.getTask_id(), tu);
			}
		} catch (SQLException e) {
			e.printStackTrace();	
		}
	}

	
	// Delete task from todoList
	public void deleteListItem(String description) {
		HashMap<Integer,ListItem> itemsToDelete = new HashMap<Integer, ListItem>();
		for(ListItem item: todoList.values()){
			if (item.getDescription().equals(description)){
				itemsToDelete.put(itemsToDelete.size(), item);
			}
		}
		for (ListItem item : itemsToDelete.values()){
			todoList.remove(item);
		}
	}
	
	// Delete task from DB.ToDoList table
	public void deleteListItemFromDB(String descri){
			try {
		    	Connection con = DriverManager.getConnection("jdbc:mysql://sis-teach-01.sis.pitt.edu/xiz138is1017","xiz138is1017","xiz138@pitt.edu");
		    	String delete1 = "DELETE FROM ToDoList WHERE description= ('"+ descri+"')";
		    	Statement statement = con.createStatement();
		    	statement.executeUpdate(delete1);
		    	
			} catch (SQLException e) {
			e.printStackTrace();	
			}
	}
	
		
	// Delete task_id from DB.task_user table
	public void deleteTaskUserFromDB(int taskid){	
		
		try {
	    	Connection con = DriverManager.getConnection("jdbc:mysql://sis-teach-01.sis.pitt.edu/xiz138is1017","xiz138is1017","xiz138@pitt.edu");
	    	String delete2 = "DELETE FROM task_user WHERE task_id= "+ taskid;
	    	Statement statement = con.createStatement();
	    	statement.executeUpdate(delete2);	    	
		} catch (SQLException e) {
		e.printStackTrace();	
		}

	}
		
	// Return the last added task.id
	public int getAddedTaskID(){	
		try {
	    	Connection con = DriverManager.getConnection("jdbc:mysql://sis-teach-01.sis.pitt.edu/xiz138is1017","xiz138is1017","xiz138@pitt.edu");
	    	String id = "SELECT MAX(id) from ToDoList";
	    	Statement statement = con.createStatement();
	    	ResultSet rs11 = statement.executeQuery(id);
	    	while(rs11.next()){
	    		taskID = rs11.getInt("MAX(id)")+1;
	    	}
		} catch (SQLException e) {
			e.printStackTrace();	
		}
		return taskID;
	}
	
	// Return the selected taskID in Table ToDoList
	public Integer getSelectedTaskID(String description){
		try {
	    	Connection con = DriverManager.getConnection("jdbc:mysql://sis-teach-01.sis.pitt.edu/xiz138is1017","xiz138is1017","xiz138@pitt.edu");
	    	String select = "SELECT id from ToDoList WHERE description = '" + description +"'";
	    	Statement statement = con.createStatement();
	    	ResultSet rs12 = statement.executeQuery(select);
	    	while(rs12.next()){
	    		selectedTaskID = rs12.getInt(1)-1;
	    	}
		} catch (SQLException e) {
			e.printStackTrace();	
		}
		return selectedTaskID;
	}
	
	
	public HashMap<Integer,User> getNameList() {	
		return user ;
	}
}
