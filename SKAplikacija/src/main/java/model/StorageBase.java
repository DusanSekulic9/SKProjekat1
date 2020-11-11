package model;

import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class StorageBase {

	private static StorageBase instance;
	private Storage storage;
	private ObservableList<EntityForView> efv = FXCollections.observableArrayList();
	private Entity inUse;
	
	private StorageBase() {

	}

	
	
	public Entity getInUse() {
		return inUse;
	}



	public void setInUse(Entity inUse) {
		this.inUse = inUse;
	}



	public static StorageBase getInstance() {
		if(instance == null) {
			instance = new StorageBase();
		}
		return instance;
	}
	
	public Storage getStorage() {
		return storage;
	}

	public void setStorage(Storage storage) {
		this.storage = storage;
	}

	public ObservableList<EntityForView> getEfv() {
		return efv;
	}

	public void setEfv(ObservableList<EntityForView> efv) {
		this.efv = efv;
	}
	
	
	
	
	
}
