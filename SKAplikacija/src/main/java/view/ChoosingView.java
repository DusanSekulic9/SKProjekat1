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
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import main.Main;

public class ChoosingView extends HBox{
	
	private Button newStor;
	private Button openStor;
	
	public ChoosingView() {
		this.newStor = new Button("New storage");
		this.openStor = new Button("Open storage");
		addElements();
		addActions();
	}
	
	
	private void addActions() {
		this.newStor.setOnAction(new NewStorController());
		this.openStor.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				
				FileChooser fc = new FileChooser();
				File f = fc.showOpenDialog(Main.window);
				if(f != null) {
					Scene scene = new Scene(new NewStorView(), 500, 500);
					Main.window.setScene(scene);
				}				
			}
		});
	}

	private void addElements() {
		this.getChildren().add(newStor);
		this.getChildren().add(openStor);
		this.setSpacing(5);
		this.setAlignment(Pos.CENTER);
	}

}
