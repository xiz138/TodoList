package edu.pitt.todolist.model;

public class TaskUser {
	private int task_id;
	private int user_id;
	
	public TaskUser(){}
	public TaskUser(int task_id, int user_id){
		this.task_id = task_id;
		this.user_id = user_id;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public int getTask_id() {
		return task_id;
	}

	public void setTask_id(int task_id) {
		this.task_id = task_id;
	}

}
