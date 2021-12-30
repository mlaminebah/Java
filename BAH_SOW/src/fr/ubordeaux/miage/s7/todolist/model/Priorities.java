package fr.ubordeaux.miage.s7.todolist.model;

/*
 * La liste des priorités
 * Exemple d'usage:
 * 
 * Priorities p = Priorities.HIGH;
 * p.getValue() donne 1
 * p.toString donne "haute"
 * 
 * Plus la valeur est faible, plus la tâche est de grande priorité
 */
public enum Priorities {
	
	HIGH(1, "haute"), 
	MEDIUM(2, "moyenne"), 
	LOW(3, "basse"), 
	TOOHIGH(0, "trop haute");

	private int value;
	private String text;

	Priorities(int value, String text) {
		this.value = value;
		this.text = text;
	}

	public int getValue() {
		return value;
	}
	
	public String getText() {
		return text;
	}

}
