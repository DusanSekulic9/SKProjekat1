package view;

import controller.CreateUpdateController;

import controller.NewStorController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import main.Main;
import model.Entity;

public class NewStorView extends VBox {
	
	
	private Button create;
	private Button update;
	private Button delete;
	private Button search;
	private Button save;
	private TableView<Entity> tw;
	
	
	public NewStorView() {
		this.create = new Button("Add");
		this.update = new Button("Update");
		this.delete = new Button("Delete");
		this.search = new Button("Search");
		this.save = new Button("Save");
		this.tw = new TableView<>();
		addElements();
		addActions();
	}
	
	private void addActions() {
		create.setOnAction(new CreateUpdateController());
		update.setOnAction(new CreateUpdateController());
		search.setOnAction(new EventHandler<ActionEvent>() {
			
			public void handle(ActionEvent event) {
				Scene scene = new Scene(new SearchView(), 400, 400);
				Main.window2.setScene(scene);
				Main.window2.show();
			}
		});
		
	}

	private void addElements() {
		HBox hb = new HBox();
		hb.getChildren().add(create);
		hb.getChildren().add(update);
		hb.getChildren().add(delete);
		hb.getChildren().add(search);
		hb.setSpacing(5);
		hb.setAlignment(Pos.CENTER);
		this.getChildren().add(hb);
		this.getChildren().add(save);
		TableColumn<String, String> id = new TableColumn<String, String>("Id");
		TableColumn<String, String>  naziv = new TableColumn<String, String>("Naziv");
		TableColumn<String, String>  atributi = new TableColumn<String, String>("Atributi");
		
		id.setCellValueFactory(new PropertyValueFactory<>("id"));
		naziv.setCellValueFactory(new PropertyValueFactory<>("naziv"));
		atributi.setCellValueFactory(new PropertyValueFactory<>("atributi"));
		
		this.tw.getColumns().addAll();
		this.getChildren().add(tw);
	}
	
	
	

}
