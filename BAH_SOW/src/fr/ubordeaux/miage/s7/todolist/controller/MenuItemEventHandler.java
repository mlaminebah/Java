package fr.ubordeaux.miage.s7.todolist.controller;

import fr.ubordeaux.miage.s7.todolist.model.ModelTodoList;
import fr.ubordeaux.miage.s7.todolist.model.Priorities;
import fr.ubordeaux.miage.s7.todolist.view.View;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.MenuItem;

public class MenuItemEventHandler implements EventHandler<ActionEvent> {

	private ModelTodoList model;
	private View view;

	public MenuItemEventHandler(ModelTodoList model) {
		this.model = model;
	}

	public void setView(View view) {
		this.view = view;
	}

	@Override
	public void handle(ActionEvent actionEvent) {

		/*
		 * On récupère l'objet qui a émis cet événement actionEvent.
		 * Il s'agit d'un item de menu déroulant identifié grâce à getId().
		 * Selon cet identifiant, on modifie le modèle en conséquence.
		 * 
		 * Si rien ne matche, on affiche l'identifiant de l'item non prévu.
		 * 
		 */

		MenuItem menuItem = (MenuItem) actionEvent.getSource();
		switch (menuItem.getId()) {

		// Priorité trop haute (juste pour provoquer une erreur)
		case "menuItemTooHight":
			model.setPriority(Priorities.TOOHIGH);
			break;

		// Priorité haute
		case "menuItemHight":
			model.setPriority(Priorities.HIGH);
			break;

		// Priorité moyenne
		case "menuItemMedium":
			model.setPriority(Priorities.MEDIUM);
			break;

		// Priorité basse
		case "menuItemLow":
			model.setPriority(Priorities.LOW);
			break;

		default:
			System.out.println("menuItem.getId(): " + menuItem.getId());
		}

		// On met à jour la vue
		view.update(model);
	}

}
