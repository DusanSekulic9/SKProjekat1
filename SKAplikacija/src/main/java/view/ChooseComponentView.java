package view;

import custom.CustomImportExport;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import json.JsonImportExport;
import main.Main;
import model.Storage;
import model.StorageBase;
import yaml.YamlImportExport;

public class ChooseComponentView extends VBox{

	private ComboBox<String> cb = new ComboBox<String>();
	private Button btn = new Button("Start");
	
	public ChooseComponentView() {
		this.getChildren().add(cb);
		cb.getItems().add("JSON");
		cb.getItems().add("YAML");
		cb.getItems().add("CUSTOM");
		cb.setValue("JSON");
		this.getChildren().add(btn);
		this.setSpacing(20);
		this.setAlignment(Pos.CENTER);
		this.setPadding(new Insets(20));
		addActions();
	}
	
	private void addActions(){
		btn.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				String comp = cb.getValue();
				Storage storage;
				if(comp.equalsIgnoreCase("json")) {
					storage = JsonImportExport.JsonFactory();
				}else if(comp.equalsIgnoreCase("yaml")) {
					storage = YamlImportExport.YamlFactory();
				}else {
					storage = CustomImportExport.customFactory();
				}
				StorageBase.getInstance().setStorage(storage);
				ChoosingView cw = new ChoosingView();
				Scene scene = new Scene(cw, 400, 100);
				Main.window.setScene(scene);
				Main.window.setTitle("Choose file");
				Main.window2.initModality(Modality.APPLICATION_MODAL);
				Main.window.show();
			}
		});
	}
	
	
	
}
