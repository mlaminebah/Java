package fr.ubordeaux.miage.s7.todolist.model;

import java.lang.Exception;

public class TaskException extends Exception {
	private String tache;
	private String descError;
	
	public TaskException (String tache,String descError) {
		this.tache = tache;
		this.descError = descError;
	}
	public String error () {
		return "["+this.descError+":"+this.tache+"]";
	}
}
