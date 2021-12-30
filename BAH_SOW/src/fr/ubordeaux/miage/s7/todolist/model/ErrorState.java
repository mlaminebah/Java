package fr.ubordeaux.miage.s7.todolist.model;

/*Mamadou Lamine BAH
  Papa Laye SOW
*/

/*
    Question 4) l'état ErrorState de l'automate
*/

public class ErrorState implements State {
    private String etat = "ERREUR";

    public String getEtat () {
        return this.etat;
    }

    @Override
    public void handle (ModelTodoList model) {
        System.out.println (this.getEtat()+"-->");
        model.setState(new EditingState());
    }
}
