package model;

import java.util.HashMap;

public class Entity {
	
	private int id;
	private String naziv;
	private HashMap<String, Object> simpleProperties = new HashMap<String, Object>();
	private HashMap<String, Entity> EntityProperties = new HashMap<String, Entity>();
	
	
	
	
	public Entity(int id, String naziv, HashMap<String, Object> simpleProperties,
			HashMap<String, Entity> entityProperties) {
		this.id = id;
		this.naziv = naziv;
		this.simpleProperties = simpleProperties;
		EntityProperties = entityProperties;
	}

	@Override
	public String toString() {
		return "id: " + id + "\tnaziv: " + naziv + "\tatributi: " + simpleProperties.toString() + "\tentiteti:" + EntityProperties.toString();
	}
	
	public Entity() {
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public HashMap<String, Object> getSimpleProperties() {
		return simpleProperties;
	}

	public void setSimpleProperties(HashMap<String, Object> simpleProperties) {
		this.simpleProperties = simpleProperties;
	}

	public HashMap<String, Entity> getEntityProperties() {
		return EntityProperties;
	}

	public void setEntityProperties(HashMap<String, Entity> entityProperties) {
		EntityProperties = entityProperties;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Entity other = (Entity) obj;
		if (id != other.id)
			return false;
		return true;
	}

	
	
	
	
}
