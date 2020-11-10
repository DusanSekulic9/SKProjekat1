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
import model.StorageBase;

public class SearchView extends VBox {
	
	private TextField tfKey;
	private TextField tfValue;
	private Button btnSearch;
	private Button btnAdd;
	private String parser = "";
	
	public SearchView() {
		
		this.tfKey = new TextField();
		this.tfValue = new TextField();
		this.btnSearch = new Button("Search");
		this.btnAdd = new Button("Add");
		addElements();
		addActions();
	
	}
	
	private void addActions() {
		btnAdd.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				parser += tfKey.getText().trim() + ":" + tfValue.getText().trim() + "\n";
				tfKey.setText("");
				tfValue.setText("");
				//ispis- singlton
			}
		});
		
		btnSearch.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				parser += tfKey.getText().trim() + ":" + tfValue.getText().trim() + "";
//				if(parser.contains("id")) {
//					String[] split = parser.split(":");
//					int id = Integer.parseInt(split[1]);
//					StorageBase.getInstance().getStorage().pretraziFajl(parser);
//				}
//				if(parser.contains("naziv")) {
//					String[] split = parser.split(":");
//					StorageBase.getInstance().getStorage().pretraziFajl(parser);
//				}
				StorageBase.getInstance().getStorage().pretrazi(parser);;
				tfKey.setText("");
				tfValue.setText("");
				parser = "";
			}
		});
	}

	private void addElements() {
		GridPane gp = new GridPane();
		gp.add(new Label("Key: "), 0, 0);
		gp.add(tfKey, 1, 0);
		gp.add(new Label("Value: "), 0, 1);
		gp.add(tfValue, 1, 1);
		HBox hb = new HBox();
		hb.getChildren().add(btnSearch);
		hb.getChildren().add(btnAdd);
		this.getChildren().add(gp);
		this.getChildren().add(hb);
	}
	
	

}
