package fr.ubordeaux.miage.s7.todolist.model;

/*Mamadou Lamine BAH
  Papa Laye SOW
*/

/*
    Question 4) l'état ProceedsState de l'automate
*/

public class ProceedsState implements State {
    private String etat = "PROCEED";

    public String getEtat () {
        return this.etat;
    }

    @Override
    public void handle (ModelTodoList model) {
        System.out.println (this.getEtat()+"-->");//pour voir les états de l'automate pour chaque action
        model.setState(new EditingState ());
    }
}
