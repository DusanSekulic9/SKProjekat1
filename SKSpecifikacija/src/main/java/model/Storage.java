package model;

import java.util.HashMap;
import java.util.List;

public abstract class Storage {
	
	private List<Entity> entities;
	private boolean autoincrement = true;
	private int maxFiles;
	
	public abstract void save(Entity e);
	
	public abstract void save(List<Entity> entities);
	
	public void save(int id, String naziv,  HashMap<String, Object> atributi) {
		Entity e = new Entity(id, naziv,atributi);
		save(e);
	}

	public boolean isAutoincrement() {
		return autoincrement;
	}

	public void setAutoincrement(boolean autoincrement) {
		if(this.autoincrement)
			this.autoincrement = autoincrement;
	}
	
	public abstract Entity createObjectFromString(String string);
	
	public abstract void makeNewEntity();
	
	public abstract List<Entity> search(int id);
	
	public abstract List<Entity> search(String naziv);
	
	public abstract List<Entity> DepthSerach(String slozeniUpit);
	
	public abstract void delete(Entity e);

	public int getMaxFiles() {
		return maxFiles;
	}

	public void setMaxFiles(int maxFiles) {
		this.maxFiles = maxFiles;
	}
	
	

	
}
