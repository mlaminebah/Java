package fr.ubordeaux.miage.s7.todolist.view;

import fr.ubordeaux.miage.s7.todolist.controller.ButtonEventHandler;
import fr.ubordeaux.miage.s7.todolist.controller.KeyEventHandler;
import fr.ubordeaux.miage.s7.todolist.controller.MenuItemEventHandler;
import fr.ubordeaux.miage.s7.todolist.model.ModelTodoList;
import fr.ubordeaux.miage.s7.todolist.model.Task;
import fr.ubordeaux.miage.s7.todolist.model.Observable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class View implements Observer /* Question 3.c : view implements Observer */ {

	// fenêtre principale
	private Stage stage;
	// Zone de texte éditable
	private TextField task_tf;
	// Bouton "Ajouter..."
	private Button adds_btn;
	// Bouton du menu déroulant des priorités 
	private MenuButton menuButton;
	// Bouton "Traiter..."
	private Button proceeds_btn;
	// Zone de texte non éditable des tâches à accomplir
	private TextArea todo_ta;
	// 	Événement associé aux menus
	private MenuItemEventHandler menuItemEventHandler;
	// 	Événement associé à la zone de texte éditable
	private KeyEventHandler keyEventHandler;
	// 	Événement associé aux boutons
	private ButtonEventHandler buttonEventHandler;
	
	// fenêtre dialogue
	private Stage dialog;
	// Label de la fenêtre de dialogue
	private Label dialog_lb;
	// Texte affiché dans la fenêtre de dialogue
	private Text dialog_txt;
	// Bouton de la fenêtre de dialogue
	private Button dialog_btn;

	// Constructeur de la vue
	// On y agrège les événements liées aux éléments interactifs 
	public View(Stage stage, KeyEventHandler keyEventHandler, MenuItemEventHandler menuItemEventHandler,
			ButtonEventHandler buttonEventHandler) {
		this.stage = stage;
		this.keyEventHandler = keyEventHandler;
		this.menuItemEventHandler = menuItemEventHandler;
		this.buttonEventHandler = buttonEventHandler;
		init();
	}

	// Initialisation:
	// On ouvre la fenêtre et on y inscrit l'ensemble des éléments
	// graphiques
	private void init() {
		// Fenêtre
		this.stage.setTitle("To do list");
		GridPane grid = new GridPane();
		// Contenu sous forme de matrice
		grid.setAlignment(Pos.CENTER);
		grid.setHgap(4);
		grid.setVgap(4);
		BorderPane borderpane = new BorderPane();
		BorderPane.setAlignment(grid, Pos.CENTER);
		BorderPane.setMargin(grid, new Insets(24, 24, 24, 24));
		borderpane.setCenter(grid);

		// Zone de texte "Tâche"
		Label task_lb = new Label("Tâche");
		grid.add(task_lb, 0, 0);
		task_tf = new TextField();
		task_tf.setOnKeyTyped(keyEventHandler);
		grid.add(task_tf, 0, 1);

		// Menu déroulant "Priorité"
		Label priority_lb = new Label("Priorité");
		grid.add(priority_lb, 0, 2);
		menuButton = new MenuButton("Priorité trop haute");
		MenuItem menuItemTooHight = new MenuItem("Priorité trop haute");
		menuItemTooHight.setId("menuItemTooHight");
		menuItemTooHight.setOnAction(menuItemEventHandler);
		menuButton = new MenuButton("Priorité haute");
		MenuItem menuItemHight = new MenuItem("Priorité haute");
		menuItemHight.setId("menuItemHight");
		menuItemHight.setOnAction(menuItemEventHandler);
		MenuItem menuItemMedium = new MenuItem("Priorité moyenne");
		menuItemMedium.setId("menuItemMedium");
		menuItemMedium.setOnAction(menuItemEventHandler);
		MenuItem menuItemLow = new MenuItem("Priorité basse");
		menuItemLow.setId("menuItemLow");
		menuItemLow.setOnAction(menuItemEventHandler);
		menuButton.getItems().addAll(menuItemTooHight, menuItemHight, menuItemMedium, menuItemLow);
		grid.add(menuButton, 0, 3);

		// Bouton "Ajouter..."
		adds_btn = new Button("Ajouter la tâche dans la liste");
		adds_btn.setId("adds_btn");
		adds_btn.setOnAction(buttonEventHandler);
		adds_btn.setMaxWidth(Double.MAX_VALUE);
		adds_btn.setMaxHeight(Double.MAX_VALUE);
		grid.add(adds_btn, 1, 1);

		// Bouton "Traiter..."
		proceeds_btn = new Button();
		proceeds_btn.setId("proceeds_btn");
		proceeds_btn.setOnAction(buttonEventHandler);
		proceeds_btn.setMaxWidth(Double.MAX_VALUE);
		proceeds_btn.setMaxHeight(Double.MAX_VALUE);
		grid.add(proceeds_btn, 1, 2);

		// Zone de texte non éditable "Liste des tâches à réaliser"
		Label todo_lb = new Label("Liste des tâches à réaliser");
		grid.add(todo_lb, 0, 4);
		todo_ta = new TextArea();
		todo_ta.setEditable(false);
		grid.add(todo_ta, 0, 5);

		// On place tout
		Scene scene = new Scene(borderpane);
		stage.setScene(scene);
		stage.setResizable(false);

		// Fenêtre de dialogue
		dialog = new Stage();
		dialog.initModality(Modality.APPLICATION_MODAL);
		dialog.initOwner(stage);
		VBox dialogVBoxPane = new VBox();
		dialogVBoxPane.setAlignment(Pos.CENTER);
		dialog_lb = new Label();
		dialog_txt = new Text();
		dialog_btn = new Button();
		dialog_btn.setId("dialog_btn");
		dialog_btn.setOnAction(buttonEventHandler);
		dialogVBoxPane.setPadding(new Insets(15, 20, 10, 10));
		VBox.setMargin(dialog_txt, new Insets(12, 12, 12, 12));
		dialogVBoxPane.getChildren().add(dialog_lb);
		dialogVBoxPane.getChildren().add(dialog_txt);
		dialogVBoxPane.getChildren().add(dialog_btn);
		Scene dialogScene = new Scene(dialogVBoxPane, 240, 160);
		dialog.setScene(dialogScene);
	}

	// Affichage de la fenêtre principale
	public void show() {
		stage.show();
	}

	// Affichage de la fenêtre dialogue
	public void showModalWindow(String title, String message, String btn_text) {
		dialog_lb.setText(title);
		dialog_txt.setText(message);
		dialog_btn.setText(btn_text);
		dialog.show();
	}
	//Question 3.c : View implemente Observer en définissant la méthode upadate
	@Override
	public void update (Observable observable) {
		ModelTodoList model = (ModelTodoList) observable;
		proceeds_btn.setText("Traiter une tâche (reste " + model.size() + ")");
		if (model.size() == 0)
			proceeds_btn.setDisable(true);
		else
			proceeds_btn.setDisable(false);
		todo_ta.clear();
		/*Nous avons rajouté cela pour bien affiché les données dans l'ordre de priorité.
		  Avec l'ancien affichage les taches ne s'affichaient dans l'odre car une des particularités des
		  PriorityQueue il faut utiliser poll pour afficher les éléments convenablement et cela reviendra à vider la fille hors ce
		  n'est pas forcément ce que l'on souhaite.
		*/
		Task [] array = new Task[model.getTasks().size()];
		model.getTasks().toArray(array);
		Arrays.sort(array);
		for (Task task : array) {
			todo_ta.appendText(task.toString() + "\n");
		}

		menuButton.setText(model.getPriority().getText());
	}
	// Cache la fenêtre dialogue
	public void hideModalWindow() {
		dialog.hide();
	}

}