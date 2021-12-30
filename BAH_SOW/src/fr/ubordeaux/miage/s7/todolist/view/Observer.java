package fr.ubordeaux.miage.s7.todolist.view;

/*Mamadou Lamine BAH
  Papa Laye SOW
*/

/*Question 3.a : création de l'interface Observer
*/

import fr.ubordeaux.miage.s7.todolist.model.Observable;

public interface Observer {
    public void update (Observable ob);
}
