package view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.Entity;

public class CreateUpdateView extends VBox{
	
	private static CreateUpdateView instance = null;
	private TextField tfId;
	private TextField tfNaziv;
	private TextArea taSimpleProperties;
	private ListView<Entity> lvEntityProperties;
	private Button save;
	private ObservableList<Entity> list = FXCollections.observableArrayList();
	private Button add;
	private Button edit;
	
	private CreateUpdateView() {
		init();
		addElements();
		addActions();
	}

	public static CreateUpdateView getInstance() {
		if(instance == null) {
			instance = new CreateUpdateView();
		}
		return instance;
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
		gp.add(lvEntityProperties, 1, 4);
		gp.setHgap(10);
		gp.setVgap(10);
		gp.setAlignment(Pos.CENTER);
		this.getChildren().add(gp);
		HBox hbox = new HBox();
		hbox.getChildren().add(add);
		hbox.getChildren().add(edit);
		edit.setDisable(true);
		hbox.setAlignment(Pos.CENTER);
		hbox.setSpacing(20);
		this.getChildren().add(hbox);
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
		this.lvEntityProperties = new ListView<Entity>();
		this.lvEntityProperties.setItems(list);
		this.save = new Button("Save");
		this.add = new Button("Add");
		this.edit = new Button("Edit");
	}

	public TextField getTfId() {
		return tfId;
	}

	public void setTfId(TextField tfId) {
		this.tfId = tfId;
	}

	public TextField getTfNaziv() {
		return tfNaziv;
	}

	public void setTfNaziv(TextField tfNaziv) {
		this.tfNaziv = tfNaziv;
	}

	public TextArea getTaSimpleProperties() {
		return taSimpleProperties;
	}

	public void setTaSimpleProperties(TextArea taSimpleProperties) {
		this.taSimpleProperties = taSimpleProperties;
	}

	public ListView<Entity> getLvEntityProperties() {
		return lvEntityProperties;
	}

	public void setLvEntityProperties(ListView<Entity> lvEntityProperties) {
		this.lvEntityProperties = lvEntityProperties;
	}

	public Button getSave() {
		return save;
	}

	public void setSave(Button save) {
		this.save = save;
	}

	public ObservableList<Entity> getList() {
		return list;
	}

	public void setList(ObservableList<Entity> list) {
		this.list = list;
	}

	public static void setInstance(CreateUpdateView instance) {
		CreateUpdateView.instance = instance;
	}
	
	
	
}
