package model;

public class StorageBase {

	private static StorageBase instance;
	private Storage storage;
	
	private StorageBase() {

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
	
	
	
}
