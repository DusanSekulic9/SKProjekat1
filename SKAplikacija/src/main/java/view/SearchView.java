package view;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;

import controller.NewStorController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import main.Main;
import model.Entity;
import model.StorageBase;

public class SearchView extends VBox {
	
	private TextField tfKey;
	private TextField tfValue;
	private Button btnSearch;
	private Button btnAdd;
	private String parser = "";
	private CheckBox cb = new CheckBox();
	private ComboBox<String> cbRastuceOpadajuce = new ComboBox<String>();
	
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
			
			public void handle(ActionEvent event) {
				parser += tfKey.getText().trim() + ":" + tfValue.getText().trim() + "\n";
				tfKey.setText("");
				tfValue.setText("");
				//ispis- singlton
			}
		});
		
		btnSearch.setOnAction(new EventHandler<ActionEvent>() {
			
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
				NewStorView.getInstance().getList().clear();
				NewStorView.getInstance().getTw().refresh();
				ArrayList<Entity> pretraga = (ArrayList<Entity>) StorageBase.getInstance().getStorage().pretrazi(parser);
				if(cb.isSelected()) {
					String upit = cbRastuceOpadajuce.getValue();
					if(upit.equalsIgnoreCase("rastuce")) {
						Collections.sort(pretraga);
					}else {
						Collections.reverse(pretraga);
					}
				}
				NewStorView.getInstance().getList().addAll(pretraga);
				tfKey.setText("");
				tfValue.setText("");
				parser = "";
				Main.window2.close();
			}
		});
	}

	private void addElements() {
		GridPane gp = new GridPane();
		gp.add(new Label("Key: "), 0, 0);
		gp.add(tfKey, 1, 0);
		gp.add(new Label("Value: "), 0, 1);
		gp.add(tfValue, 1, 1);
		gp.setAlignment(Pos.CENTER);
		gp.setHgap(10);
		gp.setVgap(10);
		HBox hb = new HBox();
		hb.setSpacing(10);
		hb.setAlignment(Pos.CENTER);
		hb.getChildren().add(btnSearch);
		hb.getChildren().add(btnAdd);
		this.getChildren().add(gp);
		this.getChildren().add(hb);
		this.setSpacing(20);
		this.setAlignment(Pos.CENTER);
		HBox hb1 = new HBox();
		hb1.getChildren().add(new Label("Sort"));
		hb1.getChildren().add(cb);
		hb1.getChildren().add(cbRastuceOpadajuce);
		cbRastuceOpadajuce.getItems().add("Opadajuce");
		cbRastuceOpadajuce.getItems().add("Rastuce");
		cbRastuceOpadajuce.setValue("Opadajuce");
		hb1.setSpacing(10);
		hb1.setAlignment(Pos.CENTER);
		this.getChildren().add(hb1);
	}
	
	

}
