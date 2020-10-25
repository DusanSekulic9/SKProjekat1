package model;

import java.util.HashMap;

public class Entity {
	
	private int id;
	private String naziv;
	private HashMap<String, Object> atributi;
	
	
	public Entity(int id, String naziv, HashMap<String, Object> atributi) {
		this.id = id;
		this.naziv = naziv;
		this.atributi = atributi;
	}
	
	
	
}
