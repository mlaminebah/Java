package fr.ubordeaux.miage.s7.todolist.controller;

import fr.ubordeaux.miage.s7.todolist.model.ModelTodoList;
import fr.ubordeaux.miage.s7.todolist.view.View;
import javafx.stage.Stage;

/*
 * Le contrôleur compose le modèle et la vue
 * 
 * On construit un lien de type observeur 
 * entre les éléments interactifs de la vue 
 * (boutons, zone de texte, etc) et le contrôleur (les XXXEventHandler)
 * 
*/
public class Controller {
	private ModelTodoList model;
	private View view;

	public Controller(Stage stage) {
		model = new ModelTodoList();
		ButtonEventHandler buttonEventHandler = new ButtonEventHandler(model);
		MenuItemEventHandler menuItemEventHandler = new MenuItemEventHandler(model);
		
		view = new View(stage, 
				new KeyEventHandler(model), 
				menuItemEventHandler,
				buttonEventHandler);
		
		buttonEventHandler.setView(view);
		menuItemEventHandler.setView(view);
		view.update(model);
		view.show();
		//Question 3.c 
		model.addObserver (view);
	}

	public View getView() {
		return view;
	}

}
