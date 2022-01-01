package fr.ubordeaux.miage.s7.todolist.model;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.ArrayList;
import java.util.List;
import fr.ubordeaux.miage.s7.todolist.view.Observer;

public class ModelTodoList implements TodoList,Observable /* Question 3.b : implementation de Observable  */ {

	// Liste de priorité de tâches
	private PriorityQueue<Task> tasks;

	// Description de la tâche en cours d'édition
	private String description;

	// Priorité de la tâche en cours d'édition
	private Priorities priority;

	// La tâche actuellement réalisée (null si aucune)
	private Task currentTask;

	// Question 1.2 :  on commente numTask car cela est géré par task maintenant.
	//private int numTask;

	/// Question 3.b
	private List<Observer> observers;
	//Question 4: La gestion de l'automate
	private State state;
	private Action action;

	/*
	 * Constructeur du modèle
	 */
	public ModelTodoList() {
		// On initialise la liste des tâches en indiquant
		// - La capacité initiale de 31 (elle augmentera automatiquement si besoin)
		// - La comparaison appliquée entre deux tâches

		//Question 2 : cette comparaison est faite par task qui implémente Comparable et rédifini la méthode CompareTo
		tasks = new PriorityQueue<Task>(31);

		// On initialise la priorité de la tâche en cours d'édition
		// à medium
		priority = Priorities.MEDIUM;

		//Question 3.b 
		observers = new ArrayList<>();
		//Question 4 : on définit l'état initial de l'automate
		this.state = new EditingState ();
		
	}

	/*
	 * Donne la taille de la liste des tâches à faire
	 */
	public int size() {
		return tasks.size();
	}

	/*
	 * Modifie la description de la tâche en cours d'édition
	 */
	public void setDescription(String description) {
		this.description = description;
		System.out.println("Model: description: " + this.getDescription());
	}

	/*
	 * Modifie la priorité de la tâche en cours d'édition
	 */
	public void setPriority(Priorities priority) {
		this.priority = priority;
		System.out.println("Model: priority: " + this.priority);
	}

	/*
	 * Ajoute la tâche en cours d'édition à la liste des tâches à faire
	 */
	private void push(Task task) throws TaskException /* Question 5.1 */ {
		tasks.add(task);
		//Question 3.c
		this.notifyAllObservers();
	}
	public void push() throws TaskException /* Question 3.1*/ {
		//Question 5.2
		Task t = new Task(getDescription(), priority);
		if (tasks.contains(t)) throw new TaskException(description, "ALREADY_EXIST");
		else
			push (t);
		System.out.println("Model: push(): " + size());
	}

	/*
	 * Supprime la tâche la plus prioritaire de la liste de tâches Cette tâche est
	 * la tâche courante
	 */
	@Override
	public void pop() {
		System.out.println("Model: pop(): " + this);
		currentTask = tasks.poll();
		//Question 3.c
		this.notifyAllObservers();
	}

	/*
	 * Récupère la liste des tâches
	 */
	// Question 1.3 : on renvoie une copie du tableau tasks pour éviter la modification des données ailleurs
	public PriorityQueue<Task> getTasks() {
        PriorityQueue<Task> taches = tasks;
		return taches;
	}

	/*
	 * Donne la priorité de la tâche en cours d'édition
	 */
	public Priorities getPriority() {
		return priority;
	}

	/*
	 * Donne la tâche actuellement réalisée
	 */
	public Task getCurrentTask() {
		return currentTask;
	}

	/*
	 * Fournit la tâche actuellement réalisée
	 */
	public void setCurrentTask(Task currentTask) {
		this.currentTask = currentTask;
	}

	/*
	 * Donne la description de la tâche en cours d'édition
	 */
	public String getDescription() {
		return description;
	}

	/* Question 3.b : implementation des méthodes notifyAllObservers et addObserver
	*/
	@Override
	public void notifyAllObservers () {
		for (Observer obs: observers) {
			obs.update(this);
		}
	}
	@Override
    public void addObserver (Observer p) {
		observers.add(p);
	}
	//Question 4 : différente actions liées à l'automate
	public Action getAction () {
		return this.action;
	}
	public State getState () {
		return this.state;
	}
	public void setState (State state) {
		this.state = state;
	}
	public void setAction (Action action) {
		if (action != null) {
			this.action = action;
			this.state.handle(this);
			this.notifyAllObservers();
		}
	}
}
