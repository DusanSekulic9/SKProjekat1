package model;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public abstract class Storage {
	
	protected List<Entity> entities = new ArrayList<Entity>();
	protected boolean autoincrement = true;
	protected int maxFiles;
	protected File fileInUse = new File("");
	protected String parser = "";
	protected List<Entity> searched = new ArrayList<>();
	
	public abstract void pretraziFajl(File file);
	
	public abstract void save(Entity e);
	
	public abstract void save(List<Entity> entities);
	
	public void save(int id, String naziv, HashMap<String, Object> simpleProperties,
			HashMap<String, Entity> entityProperties) {
		Entity e = new Entity(id, naziv,simpleProperties,entityProperties);
		save(e);
	}
	
	public List<Entity> getEntities() {
		return entities;
	}

	public boolean isAutoincrement() {
		return autoincrement;
	}

	public void setAutoincrement(boolean autoincrement) {
		if(this.autoincrement)
			this.autoincrement = autoincrement;
	}

	public Entity createObjectFromString(String string) {
		Entity entity = new Entity();
		String[] splitByComa = string.split("\n");
		boolean newEntity = false;
		String parse = "";
		String key = "";
		System.out.println(string);
		for(String s : splitByComa) {
			String[] keyValueSplit = s.split(":");
			if(newEntity) {
				parse += s + "\n";
				if(keyValueSplit[1].equalsIgnoreCase("entity") && keyValueSplit[0].equalsIgnoreCase("SS")) {
					entity.getEntityProperties().put(key, createObjectFromString(parse));
				}
			}else if(keyValueSplit[1].equalsIgnoreCase("entity")) {
				key = keyValueSplit[0];
				newEntity = true;
			}else{
				if(keyValueSplit[0].equalsIgnoreCase("id")) {
					entity.setId(Integer.parseInt(keyValueSplit[1]));
				}else if(keyValueSplit[0].equalsIgnoreCase("naziv")) {
					entity.setNaziv(keyValueSplit[1]);
				}else {
					entity.getSimpleProperties().put(keyValueSplit[0], keyValueSplit[1]);
				}
			}
			
			
		}
		System.out.println(entity);
		return entity;
	}
	
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
	
	public File getFileInUse() {
		return fileInUse;
	}

	public void setFileInUse(File fileInUse) {
		this.fileInUse = fileInUse;
	}
	
	public boolean pretraga(Entity e, String str){
		String[] split = str.split("\n");
		boolean provere = true;
		for(String s : split) {
			String[] sp = s.split(":");
				if(sp[0].equalsIgnoreCase("id")) {
					if(Integer.parseInt(sp[1]) != e.getId()) {
						provere = false;
					}
				}
				if(sp[0].equalsIgnoreCase("naziv")) {
					if(!sp[1].equalsIgnoreCase(e.getNaziv())){
						provere = false;
					}
				}
				if(provere) {
					searched.add(e);
					return provere; 
				}
				if(e.getSimpleProperties().size() > 0) {
					boolean bo = true;
					for(String key : e.getSimpleProperties().keySet()) {
						if(key.equalsIgnoreCase(sp[0])) {
							bo=false;
							break;
						}
					}
					for(Object value : e.getSimpleProperties().values()) {
						if(value.toString().equalsIgnoreCase(sp[1])) {
							bo=false;
							break;
						}
					}
					if(bo)
						provere = false;
				}
				if(e.getEntityProperties().size() > 0) {
					boolean bo=true;
					for(String ss : e.getEntityProperties().keySet()) {
						if(ss.equalsIgnoreCase(sp[0])) {
							bo = false;
							break;
						}
					}
					for(Entity ent : e.getEntityProperties().values()) {
						if(pretraga(ent, str)) {
							bo=false;
							break;
						}
					}
					if(bo)
						provere = false;
					}
				else {
					provere = false;
				}
				}
		if(provere) {
			searched.add(e);
		}		
		return provere;	
		}
	
	
	
	public List<Entity> pretrazi(String s) {
		entities.clear();
		for(File f : fileInUse.listFiles()) {
			pretraziFajl(f);
		}
		for(Entity e : entities) {
			pretraga(e,s);
		}
		return searched;
	}
	
	
	
	
}
