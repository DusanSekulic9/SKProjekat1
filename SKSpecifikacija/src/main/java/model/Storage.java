package model;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public abstract class Storage {

	private List<Entity> newEntities = new ArrayList<Entity>();
	protected List<Entity> entities = new ArrayList<Entity>();
	protected boolean autoincrement = true;
	protected int maxFiles;
	protected File fileInUse = new File("");
	protected String parser = "";
	protected List<Entity> searched = new ArrayList<>();
	protected static Storage json;
	protected static Storage yaml;
	protected static Storage custom;
	protected static int auto=0;

	
	/**
	 * pretraziFajl metoda za pretragu Entiteta u fajlu
	 * 
	 * @param file
	 */
	
	
	public abstract void pretraziFajl(File file);

	/**
	 * Save metoda za snimanje entiteta u fajl
	 * 
	 * @param e
	 * @param f
	 * 
	 * 
	 */
	
	
	public abstract void save(Entity e, File f);

	public abstract void save(List<Entity> entities);

	public void save(int id, String naziv, HashMap<String, Object> simpleProperties,
			HashMap<String, Entity> entityProperties) {
		Entity e = new Entity(id, naziv, simpleProperties, entityProperties);
		save(e, new File(""));
	}
	
	public static Storage ComponentFactory(String component) {
		if(component.equalsIgnoreCase("Json")) return json;
		if(component.equalsIgnoreCase("yaml")) return yaml;
		return custom;
	}

	public List<Entity> getEntities() {
		return entities;
	}

	public boolean isAutoincrement() {
		return autoincrement;
	}

	public void setAutoincrement(boolean autoincrement) {
		if (this.autoincrement)
			this.autoincrement = autoincrement;
	}

	public Entity createObjectFromString(String string) {
		Entity entity = new Entity();
		String[] splitByComa = string.split("\n");
		boolean newEntity = false;
		String parse = "";
		String key = "";
		for (String s : splitByComa) {
			String[] keyValueSplit = s.split(":");
			if (newEntity) {
				parse += s + "\n";
				if (keyValueSplit[1].equalsIgnoreCase("entity") && keyValueSplit[0].equalsIgnoreCase("SS")) {
					entity.getEntityProperties().put(key, createObjectFromString(parse));
				}
			} else if (keyValueSplit[1].equalsIgnoreCase("entity")) {
				key = keyValueSplit[0];
				newEntity = true;
			} else {
				if (keyValueSplit[0].equalsIgnoreCase("id")) {
					entity.setId(Integer.parseInt(keyValueSplit[1].trim()));
				} else if (keyValueSplit[0].equalsIgnoreCase("naziv")) {
					entity.setNaziv(keyValueSplit[1]);
				} else {
					entity.getSimpleProperties().put(keyValueSplit[0], keyValueSplit[1]);
				}
			}

		}
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

	public boolean pretraga(Entity e, String str) {
		String[] split = str.split("\n");
		boolean provere = false;
		int iter = 0;
		for (String s : split) {
			String[] sp = s.split(":");
			proveri(e, sp, iter);
			iter++;
		}
		return provere;
	}

	private void proveri(Entity e, String[] sp, int iter) {
		boolean provere = false;
		if (sp[0].equalsIgnoreCase("id")) {
			if (Integer.parseInt(sp[1]) == e.getId()) {
				System.out.println("nasao id" + iter);
				provere = true;
			}
		}
		if (sp[0].equalsIgnoreCase("naziv")) {
			if (sp[1].equalsIgnoreCase(e.getNaziv())) {
				System.out.println("nasao naziv" + iter);
				provere = true;
			}
		}

		for (String key : e.getSimpleProperties().keySet()) {
			if (key.equalsIgnoreCase(sp[0])) {
				if (e.getSimpleProperties().get(key).toString().equalsIgnoreCase(sp[1])) {
					System.out.println("nasao s" + iter);
					provere = true;
				}
			}
		}

		for (Entity ent : e.getEntityProperties().values()) {
			proveri(ent, sp, iter);
		}
		if(provere && iter == 0) {
			System.out.println("dodaje entitet "+iter + e);
			searched.add(e);
		}else if(!provere){
			if(searched.contains(e)) {
				System.out.println("brise entitet "+iter + e);
				searched.remove(searched.indexOf(e));
			}
		}
	}
	
	/**
	 * pretrazi metoda za pretrtagu fajla po odredjenim parametrima
	 * 
	 * @param s
	 * @return
	 */

	public List<Entity> pretrazi(String s) {
		entities.clear();
		searched.clear();
		for (File f : fileInUse.listFiles()) {
			pretraziFajl(f);
		}
		System.out.println(entities);
		entities.addAll(newEntities);
	/*	auto+=entities.size();
		for(Entity e : entities) {
			System.out.println("entityProp "+e.getEntityProperties().size());
	//		auto += e.getEntityProperties().size();
		} */
		System.out.println("broj id u pretrazi: " + auto);
		if(!s.equalsIgnoreCase(":")) {
			for (Entity e : entities) {
				pretraga(e, s);
			}
		}
		return searched;
	}

	public List<Entity> getNewEntities() {
		return newEntities;
	}

	public void setNewEntities(List<Entity> newEntities) {
		this.newEntities = newEntities;
	}

	public int getAuto() {
		return auto;
	}

	public void setAuto(int auto) {
		this.auto = auto;
	}
	
	

}
