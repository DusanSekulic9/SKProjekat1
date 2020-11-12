package view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import main.Main;
import model.Entity;
import model.EntityForView;
import model.StorageBase;

public class UpdateView extends VBox{
	
	private static UpdateView instance = null;
	private TextField tfId;
	private TextField tfNaziv;
	private TextArea taSimpleProperties;
	private ListView<EntityForView> lvEntityProperties;
	private Button save;
	private ObservableList<Entity> list = FXCollections.observableArrayList();
	private Button add;
	private Button edit;
	
	public UpdateView() {
		init();
		addElements();
		addActions();
	}

	public static UpdateView getInstance() {
		if(instance == null) {
			instance = new UpdateView();
		}
		return instance;
	}
	
	private void addActions() {
	save.setOnAction(new EventHandler<ActionEvent>() {
		
		@Override
		public void handle(ActionEvent event) {
			int id = Integer.parseInt(tfId.getText());
			String naziv = tfNaziv.getText();
			String atributi = taSimpleProperties.getText();
			ObservableList<EntityForView> entities = lvEntityProperties.getItems();
			
			Entity newEntity = StorageBase.getInstance().getInUse();
			newEntity.setId(id);
			newEntity.setNaziv(naziv);
			String[] split = atributi.split("\n");
			for(String s : split) {
				String[] keyValueSplit = s.split(":");
				newEntity.getSimpleProperties().put(keyValueSplit[0], keyValueSplit[1]);
			}
			for(EntityForView e : entities) {
				if(newEntity.getEntityProperties().containsKey(e.getKey()))
					newEntity.getEntityProperties().replace(e.getKey(), e.getEntity());
				else
					newEntity.getEntityProperties().put(e.getKey(), e.getEntity());
			}
			Main.window2.close();
			NewStorView.getInstance().getTw().refresh();
		}
	});
	edit.setOnAction(new EventHandler<ActionEvent>() {
		
		@Override
		public void handle(ActionEvent event) {
			Entity e = lvEntityProperties.getSelectionModel().getSelectedItem().getEntity();
			if(e == null) {
				Alert a = new Alert(AlertType.ERROR);
				a.setContentText("Selektujte nesto u listi");
				a.show();
				return;
			}
			StorageBase.getInstance().setInUse(e);
			StorageBase.getInstance().getEfv().clear();
			for(String s : e.getEntityProperties().keySet()) {
				EntityForView efv = new EntityForView(s, e.getEntityProperties().get(s));
				StorageBase.getInstance().getEfv().add(efv);
			}
			UpdateView uv = new UpdateView();
			uv.getTfId().setText(""+e.getId());
			if(!StorageBase.getInstance().getStorage().isAutoincrement()) {
				uv.getTfId().setDisable(true);
			}
			uv.getTfNaziv().setText(e.getNaziv());
			for(String s : e.getSimpleProperties().keySet()) {
				uv.getTaSimpleProperties().appendText(s + ":" + e.getSimpleProperties().get(s) + "\n");
			} 
			Scene scene = new Scene(uv, 400, 400);
			Main.window2.setScene(scene);
			Main.window2.show();
		}
	});
	
	add.setOnAction(new EventHandler<ActionEvent>() {
		
		@Override
		public void handle(ActionEvent event) {
			StorageBase.getInstance().getEfv().clear();
			CreateViewWithKey cv = new CreateViewWithKey();
			Scene scene = new Scene(cv, 400, 400);
			Main.window3.setScene(scene);
			Main.window3.show();
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
		gp.setHgap(10);
		gp.setVgap(10);
		gp.setAlignment(Pos.CENTER);
		this.getChildren().add(gp);
		HBox hbox = new HBox();
		hbox.getChildren().add(add);
		hbox.getChildren().add(edit);
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
		this.lvEntityProperties.setItems(StorageBase.getInstance().getEfv());
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

	public ObservableList<Entity> getList() {
		return list;
	}

	public void setList(ObservableList<Entity> list) {
		this.list = list;
	}

	public static void setInstance(UpdateView instance) {
		UpdateView.instance = instance;
	}
	
	
	
}

