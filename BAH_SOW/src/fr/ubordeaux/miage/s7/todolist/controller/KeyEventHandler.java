package fr.ubordeaux.miage.s7.todolist.controller;

import fr.ubordeaux.miage.s7.todolist.model.ModelTodoList;
import javafx.event.EventHandler;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

public class KeyEventHandler implements EventHandler<KeyEvent> {

	private ModelTodoList model;

	public KeyEventHandler(ModelTodoList model) {
		this.model = model;
	}

	@Override
	public void handle(KeyEvent keyEvent) {
		/*
		 * On récupère l'objet qui a émis cet événement keyEvent
		 * On on récupère le texte pour dire que c'est la description
		 * de la prochaine tâche.
		 */
		TextField textField = (TextField) keyEvent.getTarget();
		model.setDescription(textField.getText());
	}

}
