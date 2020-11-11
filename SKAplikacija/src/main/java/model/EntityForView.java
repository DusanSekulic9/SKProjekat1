package model;

public class EntityForView {

	private String key;
	private Entity entity;
	
	public EntityForView(String key, Entity entity) {
		super();
		this.key = key;
		this.entity = entity;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public Entity getEntity() {
		return entity;
	}
	public void setEntity(Entity entity) {
		this.entity = entity;
	}
	
	@Override
	public String toString() {
		return key + ":" + entity.toString();
	}
	
	
}
