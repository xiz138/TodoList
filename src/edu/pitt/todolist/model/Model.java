package edu.pitt.todolist.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;
import java.sql.*;

public class Model {
	
	//private Vector<ListItem> todoList;
	private HashMap<Integer,ListItem> todoList;
	private HashMap<Integer,TaskUser> task_user;
	private HashMap<Integer,User> user;
	private int taskID;
	
	public Model() {
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

	// Add input task to DB.ToDoList and return the last added task.id
	public int addListItemToDB(String description) {
		// Add input task to DB.ToDoList
		try {
	    	Connection con = DriverManager.getConnection("jdbc:mysql://sis-teach-01.sis.pitt.edu/xiz138is1017","xiz138is1017","xiz138@pitt.edu");
	    	String insert1 = "INSERT INTO ToDoList (description) VALUES "+"('"+ description+"')";
	    	Statement statement = con.createStatement();
	    	statement.executeQuery(insert1);
		} catch (SQLException e) {
			e.printStackTrace();	
		}
		//return the last added task.id
		try {
	    	Connection con = DriverManager.getConnection("jdbc:mysql://sis-teach-01.sis.pitt.edu/xiz138is1017","xiz138is1017","xiz138@pitt.edu");
	    	//String select1 = "SELECT id FROM ToDuList WHERE"+"description = '"+ description+"'";
	    	String id = "select LAST_INTERT_ID()";
	    	Statement statement = con.createStatement();
	    	ResultSet rs11 = statement.executeQuery(id);
	    	int taskID = rs11.getInt("id");
		} catch (SQLException e) {
			e.printStackTrace();	
		}
		return taskID;
	}
	
	// Add input task to todoList 
	public void addListItem() {
		//this.todoList.add(new ListItem(description));
		
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
	    	String insert2 = "INSERT INTO task_user (task_id,user_id) VALUES "+"("+taskidKey+"'"+ useridKey+"')";
	    	Statement statement = con.createStatement();
	    	statement.execute(insert2);

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
		    	String delete = "DELETE FROM ToDoList WHERE description="+"('"+ descri+"')";
		    	Statement statement = con.createStatement();
		    	statement.execute(delete);
		    	
			} catch (SQLException e) {
			e.printStackTrace();	
			}
	}
	
	//Delete task_user from HashMap TaskUser
	public void deletrTaskUser(int taskid) {
		HashMap<Integer,TaskUser> taskUserToDelete = new HashMap<Integer, TaskUser>();
		for(TaskUser taskuser: task_user.values()){
			if (taskuser.getTask_id() = taskid){
				taskUserToDelete.put(taskUserToDelete.size(), taskuser);
			}
		}
		for (TaskUser taskuser : taskUserToDelete.values()){
			task_user.remove(taskuser);
		}
	}
		
	// Delete task_id from DB.task_user table
	public void deleteTaskUserFromDB(int taskidKey){	
		
		try {
	    	Connection con = DriverManager.getConnection("jdbc:mysql://sis-teach-01.sis.pitt.edu/xiz138is1017","xiz138is1017","xiz138@pitt.edu");
	    	String delete = "DELETE FROM task_user WHERE task_id="+"("+ taskidKey+")";
	    	Statement statement = con.createStatement();
	    	statement.execute(delete);	    	
		} catch (SQLException e) {
		e.printStackTrace();	
		}

	}
		

	public HashMap<Integer,User> getNameList() {	
		return user ;
	}
}
