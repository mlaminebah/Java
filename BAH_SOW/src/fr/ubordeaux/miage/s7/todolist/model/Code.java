package fr.ubordeaux.miage.s7.todolist.model;

public enum Code {
	BAD_DESCRIPTION("mauvaise description"), 
	TOO_SHORT_DESCRIPTION_TEXT("description trop courte"),
	TOO_LOW_PRIORITY("priorité trop faible"), 
	TOO_HIGH_PRIORITY("priorité trop élevée"),
	ALREADY_EXISTS("tâche existante"), 
	BAD_PRIORITY("mauvaise priorité"), 
	NOT_DEFINED("Pas définie");

	private String msg;

	Code(String msg) {
		this.msg = msg;
	}

	public String toString() {
		return msg;
	}

};

