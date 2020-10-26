package view;

import java.io.File;

import controller.NewStorController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import main.Main;

public class SearchView extends VBox {
	
	private TextField tf1;
	private TextField tf2;
	private Button btn1;
	private Button btn2;
	
	public SearchView() {
		
		this.tf1 = new TextField();
		this.tf2 = new TextField();
		this.btn1 = new Button("Search");
		this.btn2 = new Button("Add");
		addElements();
		addActions();
	
	}
	
	private void addActions() {
		
	}

	private void addElements() {
		GridPane gp = new GridPane();
		gp.add(new Label("Key: "), 0, 0);
		gp.add(tf1, 1, 0);
		gp.add(new Label("Value: "), 0, 1);
		gp.add(tf2, 1, 1);
		HBox hb = new HBox();
		hb.getChildren().add(btn1);
		hb.getChildren().add(btn2);
		this.getChildren().add(gp);
		this.getChildren().add(hb);
	}
	
	

}
