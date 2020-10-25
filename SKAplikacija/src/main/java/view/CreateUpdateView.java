package view;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class CreateUpdateView extends VBox{
	
	private TextField tfId;
	private TextArea taAtt;
	private Button save;
	
	public CreateUpdateView() {
		init();
		addElements();
		addActions();
		
	}

	private void addActions() {
		// TODO Auto-generated method stub
		
	}

	private void addElements() {
		GridPane gp = new GridPane();
		gp.add(new Label("ID:"), 0, 0);
		gp.add(tfId, 1, 0);
		gp.add(new Label("Atributi:"), 0, 1);
		gp.add(taAtt, 1, 1);
		gp.setHgap(10);
		gp.setVgap(10);
		gp.setAlignment(Pos.CENTER);
		this.getChildren().add(gp);
		this.getChildren().add(save);
		this.setSpacing(40);
		this.setAlignment(Pos.CENTER);
	}

	private void init() {
		this.tfId = new TextField();
		this.tfId.setMaxWidth(100);
		this.taAtt = new TextArea();
		this.taAtt.setMaxWidth(300);
		this.save = new Button("Save");
	}
	
}
