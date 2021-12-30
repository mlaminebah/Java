package fr.ubordeaux.miage.s7.todolist.model;
/*Mamadou Lamine BAH
  Papa Laye SOW
*/
public class Task implements Comparable <Task> {

	// Question 1.1  et 1.2 : Encapsulation des données et unicité de num géré par task lui même
	private Integer num;
	private static int d = 0;
	// Elles possèdent une description
	private String description;
	
	// Elles possèdent une priorité
	private Priorities priority;

	public Task(String description, Priorities priority) throws TaskException/* Question 5.1 */{
		//Question 5 :  
		if (priority == null || description.length() == 0)
			throw new TaskException (description,"BAD_DESCRIPTION"); //Question 5.1 
		else if (description.length() < 4) {
			throw new TaskException(description, "TOO_SHORT_DESCRIPTION_TEXT"); //Question 5.1
		} else if (priority.getValue() < 1) 
			throw new TaskException(description, "TOO_LOW_PRIORITY"); //Question 5.1
		else if (priority.getValue() > 4)
			throw new TaskException(description, "TOO_HIGH_PRIORITY"); //Question 5.1
		else {
			this.description = description;
			this.priority = priority;
			this.d ++; // Question 1.1 : génération automatique de num task pour chaque instance task créée
			this.num = d;
		}
	}

	/*
	 * Représentation d'une tâche sous forme de chaine
	 */
	@Override
	public String toString() {
		return description + " (" + priority.getText() + ")";
	}

	/*
	 * Donne la description d'une règle
	 */
	public String getDescription() {
		return description;
	}
	/* Donne la valeur de la priorité */
	public int getPrioValue (Task task) {
        return task.priority.getValue();
    }
	/* 
	* Pour que les éléments soient insérer selon une priorité nous avons redefinit CompareTo de l'interface Comparable
	* si deux priorités sont différentes : est prioritaire la tâche avec la plus petite value de priorité
	  si non on respecte le principe d'une file : première tâche arrivée première tâche traîtée
	*/
	@Override
	public int compareTo (Task task) {
		/*Question 2.1 : réponse à la question 2.1) */
		if (task == null) return -1;
		int d = getPrioValue(this) - task.getPrioValue(task);
		if (d != 0) return d; 
		/*Question 2.2 : réponse à la question 2.2 */
		return this.num - task.num;
	}


	/* Question 5 : on rédifinit le hashCode et equals pour garantir l'unicité du nom d'une tâche
	*/
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Task)) return false;
		Task p = (Task) obj;
		
		return p.getDescription().equals(this.getDescription());
	}

}
