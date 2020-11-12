package view;

import java.io.File;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import controller.NewStorController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import main.Main;
import model.Entity;
import model.EntityForView;
import model.StorageBase;

public class NewStorView extends VBox {
	
	private static NewStorView instance = null;
	private Button create;
	private Button update;
	private Button delete;
	private Button search;
	private Button save;
	private TableView<Entity> tw;
	private ObservableList<Entity> list = FXCollections.observableArrayList();
	
	
	private NewStorView() {
		this.create = new Button("Add");
		this.update = new Button("Update");
		this.delete = new Button("Delete");
		this.search = new Button("Search");
		this.save = new Button("Save");
		this.tw = new TableView<Entity>();
		addElements();
		addActions();
	}
	
	public TableView<Entity> getTw() {
		return tw;
	}



	public void setTw(TableView<Entity> tw) {
		this.tw = tw;
	}

	public static NewStorView getInstance() {
		if(instance == null) {
			instance = new NewStorView();
		}
		return instance;
	}

	public ObservableList<Entity> getList() {
		return list;
	}

	public void setList(ObservableList<Entity> list) {
		this.list = list;
	}

	private void addActions() {
		create.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				CreateView cv = new CreateView();
				if(StorageBase.getInstance().getStorage().isAutoincrement())
					cv.getTfId().setDisable(true);
				StorageBase.getInstance().getEfv().clear();
				Scene scene = new Scene(cv, 400, 400);
				Main.window2.setScene(scene);
				Main.window2.show();
			}
		});
		update.setOnAction(new EventHandler<ActionEvent>() {
		
			@Override
			public void handle(ActionEvent event) {
				Entity e = tw.getSelectionModel().getSelectedItem();
				if(e == null) {
					Alert a = new Alert(AlertType.ERROR);
					a.setContentText("Selektujte nesto u tabeli");
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
				if(StorageBase.getInstance().getStorage().isAutoincrement()) {
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
		search.setOnAction(new EventHandler<ActionEvent>() {
			
			public void handle(ActionEvent event) {
				Scene scene = new Scene(new SearchView(), 300, 300);
				Main.window2.setScene(scene);
				Main.window2.show();
			}
		});
		
		save.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				List<Entity> ent = new ArrayList<>();
				ent.addAll(StorageBase.getInstance().getStorage().getEntities());
				for(Entity newEnt : StorageBase.getInstance().getStorage().getNewEntities()) {
					boolean exist = true;
					for(Entity e:  StorageBase.getInstance().getStorage().getEntities()) {
						if(e.equals(newEnt)) {
							exist = false;
							break;
						}
					}
					if(exist) {
						ent.add(newEnt);
					}
				}
				int n = 0;
				StorageBase.getInstance().getStorage().setMaxFiles(2);
				System.out.println("save" + ent);
				for(File f : StorageBase.getInstance().getStorage().getFileInUse().listFiles()) {
					for(int i = 0 ; i < StorageBase.getInstance().getStorage().getMaxFiles() ; i++) {
						StorageBase.getInstance().getStorage().save(ent.get(n+i),f);
						System.out.println("Cuva se entitet:" + ent.get(n + i));
					}
					n += StorageBase.getInstance().getStorage().getMaxFiles();
				}
			//	for(Entity e: ent) {
			//		StorageBase.getInstance().getStorage().save(e);
			//	}
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
		TableColumn<Entity, String> id = new TableColumn<Entity, String>("Id");
		TableColumn<Entity, String>  naziv = new TableColumn<Entity, String>("Naziv");
		TableColumn<Entity, HashMap<String, String>>  atributi = new TableColumn<Entity,  HashMap<String, String>>("Atributi");
		TableColumn<Entity, HashMap<String, Entity>> entiteti = new TableColumn<Entity, HashMap<String, Entity>>("Entiteti");
		id.setCellValueFactory(new PropertyValueFactory<>("id"));
		naziv.setCellValueFactory(new PropertyValueFactory<>("naziv"));
		atributi.setCellValueFactory(new PropertyValueFactory<>("simpleProperties"));
		entiteti.setCellValueFactory(new PropertyValueFactory<>("entityProperties"));
		
		this.tw.getColumns().addAll(id, naziv,atributi,entiteti);
		tw.setItems(list);
		this.getChildren().add(tw);
		this.getChildren().add(save);
		this.setSpacing(20);
		this.setAlignment(Pos.CENTER);
	}
	
	
	

}
