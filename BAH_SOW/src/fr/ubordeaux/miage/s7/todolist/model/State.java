package fr.ubordeaux.miage.s7.todolist.model;

public interface State {

	void handle(ModelTodoList model);

}
