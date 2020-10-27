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


	public HashMap<String, Object> getAtributi() {
		return atributi;
	}


	public void setAtributi(HashMap<String, Object> atributi) {
		this.atributi = atributi;
	}
	
	
	
	
	
}
