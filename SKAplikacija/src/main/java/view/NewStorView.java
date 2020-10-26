package view;

import controller.CreateUpdateController;
import controller.NewStorController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import main.Main;

public class NewStorView extends VBox {
	
	
	private Button create;
	private Button update;
	private Button delete;
	private Button search;
	private Button save;
	
	
	public NewStorView() {
		this.create = new Button("Add");
		this.update = new Button("Update");
		this.delete = new Button("Delete");
		this.search = new Button("Search");
		this.save = new Button("Save");
		addElements();
		addActions();
	}
	
	private void addActions() {
		create.setOnAction(new CreateUpdateController());
		update.setOnAction(new CreateUpdateController());
		search.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
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
	}
	
	
	

}
