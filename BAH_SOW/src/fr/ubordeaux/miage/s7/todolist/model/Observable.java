package fr.ubordeaux.miage.s7.todolist.model;

/*Mamadou Lamine BAH
  Papa Laye SOW
*/
/*
    Question 3.b : Création d'une interface Observable 
*/
import fr.ubordeaux.miage.s7.todolist.view.Observer;

public interface Observable {
    public void notifyAllObservers ();
    public void addObserver (Observer p);
}
