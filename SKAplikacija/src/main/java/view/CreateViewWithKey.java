package view;

import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import main.Main;
import model.Entity;
import model.EntityForView;
import model.StorageBase;

public class CreateViewWithKey extends VBox{
	
	private static CreateViewWithKey instance = null;
	private TextField tfId;
	private TextField tfKey;
	private TextField tfNaziv;
	private TextArea taSimpleProperties;
	private ListView<EntityForView> lvEntityProperties;
	private Button save;
	private ObservableList<EntityForView> list = FXCollections.observableArrayList();
	private Button add;
	
	public CreateViewWithKey() {
		init();
		addElements();
		addActions();
	}

	public static CreateViewWithKey getInstance() {
		if(instance == null) {
			instance = new CreateViewWithKey();
		}
		return instance;
	}
	
	private void addActions() {
		add.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				CreateViewWithKey cv = new CreateViewWithKey();
				cv.getAdd().setDisable(true);
				Scene scene = new Scene(cv, 400, 400);
				Main.window4.setScene(scene);
				Main.window4.show();
			}
		});
		save.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				int id = Integer.parseInt(tfId.getText());
				String naziv = tfNaziv.getText();
				String atributi = taSimpleProperties.getText();
				//ObservableList<EntityForView> entities = lvEntityProperties.getItems();
				
				Entity newEntity = new Entity();
				newEntity.setId(id);
				newEntity.setNaziv(naziv);
				String[] split = atributi.split("\n");
				for(String s : split) {
					String[] keyValueSplit = s.split(":");
					newEntity.getSimpleProperties().put(keyValueSplit[0], keyValueSplit[1]);
				}
				StorageBase.getInstance().getInUse().getEntityProperties().put(tfKey.getText(), newEntity);
				EntityForView efv = new EntityForView(tfKey.getText(), newEntity);
				StorageBase.getInstance().getEfv().add(efv);
				
			}
		});
	}
	
	public Button getAdd() {
		return add;
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
		gp.add(new Label("Key:"), 0, 5);
		gp.add(tfKey, 1, 5);
		gp.setHgap(10);
		gp.setVgap(10);
		gp.setAlignment(Pos.CENTER);
		this.getChildren().add(gp);
		HBox hbox = new HBox();
		//hbox.getChildren().add(add);
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
		this.lvEntityProperties = new ListView<EntityForView>();
		//this.lvEntityProperties.setItems(StorageBase.getInstance().getEfv());
		this.save = new Button("Save");
		this.add = new Button("Add Entity");
		this.tfKey = new TextField();
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

	public ListView<EntityForView> getLvEntityProperties() {
		return lvEntityProperties;
	}

	public void setLvEntityProperties(ListView<EntityForView> lvEntityProperties) {
		this.lvEntityProperties = lvEntityProperties;
	}

	public Button getSave() {
		return save;
	}

	public void setSave(Button save) {
		this.save = save;
	}

	public ObservableList<EntityForView> getList() {
		return list;
	}

	public void setList(ObservableList<EntityForView> list) {
		this.list = list;
	}

	public static void setInstance(CreateViewWithKey instance) {
		CreateViewWithKey.instance = instance;
	}
	
	
	
}
