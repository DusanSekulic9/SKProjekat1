package view;

import controller.CreateUpdateController;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class CreateUpdateView extends VBox{
	
	private TextField tfId;
	private TextField tfNaziv;
	private TextArea taSimpleProperties;
	private TextArea taEntityProperties;
	private Button save;
	
	
	public CreateUpdateView() {
		init();
		addElements();
		addActions();
		
	}

	private void addActions() {
		
	}

	private void addElements() {
		GridPane gp = new GridPane();
		gp.add(new Label("ID:"), 0, 0);
		gp.add(tfId, 1, 0);
		gp.add(new Label("Naziv:"), 0, 1);
		gp.add(tfNaziv, 1, 1);
		gp.add(new Label("Simple properties: "), 0, 3);
		gp.add(taSimpleProperties, 1, 3);
		gp.add(new Label("Entity properties: "), 0, 4);
		gp.add(taEntityProperties, 1, 4);
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
		this.tfNaziv = new TextField();
		this.tfNaziv.setMaxWidth(100);
		this.taSimpleProperties = new TextArea();
		this.taSimpleProperties.setMaxWidth(300);
		this.taEntityProperties = new TextArea();
		this.taEntityProperties.setMaxWidth(300);
		taEntityProperties.setText("For example: \n" + "entityProperties\": {\r\n"
				+ "			\"profesor\": {\r\n"
				+ "				\"id\": 4,\r\n"
				+ "				\"naziv\": \"profesor\",\r\n"
				+ "				\"simpleProperties\": {\r\n"
				+ "					\"ime\": \"Ana\",\r\n"
				+ "					\"prezime\": \"Anic\"\r\n"
				+ "				},\r\n"
				+ "				\"entityProperties\": {\r\n"
				+ "					\"kolega\": { \r\n"
				+ "					\"id\": 5,\r\n"
				+ "					\"naziv\": \"kolega\",\r\n"
				+ "					\"simpleProperties\": {\r\n"
				+ "					\"ime\": \"Marko\",\r\n"
				+ "					\"prezime\": \"Markovic\"\r\n"
				+ "					}\r\n"
				+ "					}\r\n"
				+ "				}\r\n"
				+ "				}\r\n"
				+ "			}");
		this.save = new Button("Save");
	}
	
}
