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
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import json.JsonImportExport;
import main.Main;
import model.Storage;
import model.StorageBase;
import yaml.YamlImportExport;

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
			
			public void handle(ActionEvent event) {
				
				DirectoryChooser dc = new DirectoryChooser();
				File f = dc.showDialog(Main.window);
				//File f = new File("C:\\Users\\Dusan\\git\\SKProjekat1\\SKAplikacija\\data\\data.json"); //fc.showOpenDialog(Main.window);
				if(f != null) {
					Storage storage = new JsonImportExport();
				//	Storage storage = new YamlImportExport();
					storage.setFileInUse(f);
					StorageBase.getInstance().setStorage(storage);
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
