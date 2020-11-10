package json;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;

import model.Entity;
import model.Storage;


public class JsonImportExport extends Storage{
	
	boolean isIt = false;
	int pogodak = 0;
	public void pretraziFajl(File file,String pretraga){
		String[] split = pretraga.split("\n"); 
		try {
			JsonReader reader = new JsonReader(new FileReader(file));
			reader.beginArray();
				pretraziEntitet(reader, split);
			reader.endArray();
			
			reader.close();
			
		}catch (Exception e) {
			
		}
	}
	
	public void pretraziEntitet(JsonReader reader,String[] pretraga) {
		//List<Entity> res = new ArrayList<Entity>();
		try {
			while(reader.hasNext()) {
				reader.beginObject();
				while(reader.peek() != JsonToken.END_OBJECT) {
					String key = reader.nextName();
					if(reader.peek() != JsonToken.BEGIN_OBJECT) {
						String value = reader.nextString();
						for(String s : pretraga) {
							String[] split = s.split(":");
							//System.out.println(value + " " + split[1]);
							if(key.equalsIgnoreCase(split[0]) && value.equalsIgnoreCase(split[1])) {
								//isIt = true;
								pogodak += 1;
							}
						}
						parser += key + ":" + value + "\n";
//						System.out.println(parser);
//						System.out.println("\n////////////////////////////////////////\n");
					}else {
						if(key.equalsIgnoreCase("simpleproperties")) {
							reader.beginObject();
							while(reader.peek() != JsonToken.END_OBJECT) {
								key = reader.nextName();
								String value = reader.nextString();
								for(String s : pretraga) {
									String[] split = s.split(":");
									if(key.equalsIgnoreCase(split[0]) && value.equalsIgnoreCase(split[1])) {
										//isIt = true;
										pogodak++;
									}
								}
								parser += key + ":" + value + "\n";
							}
//							System.out.println(parser);
//							System.out.println("\n////////////////////////////////////////\n");
							reader.endObject();
						}else {
							reader.beginObject();
							if(reader.peek() == JsonToken.END_OBJECT) {
								parser += "SS:entity\n";
								break;
							}
							key = reader.nextName();
							parser += key + ":entity\n";
							pretraziEntitet(reader, pretraga);
//							System.out.println(parser);
//							System.out.println("\n////////////////////////////////////////\n");
						}
					}
				}
				while(reader.peek() == JsonToken.END_OBJECT) {
					reader.endObject();
					//System.out.println("kraj");
				}
				if(pogodak == pretraga.length) {
					entities.add(createObjectFromString(parser));
					System.out.println(entities);
					//System.out.println("add" + res);
				}
				//isIt = false;
				pogodak = 0;
				parser = "";
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	public void save(Entity e) {
		
	}

	@Override
	public void save(List<Entity> entities) {
		
	}

	@Override
	public void makeNewEntity() {
		
	}

	@Override
	public List<Entity> search(int id) {
		List<Entity> res = new ArrayList<Entity>();
			try {
				JsonReader reader = new JsonReader(new FileReader(fileInUse));
				reader.beginArray();
				String parser = "";
				boolean isIt = true;
				while(reader.hasNext()) {
					reader.beginObject();
					while(reader.peek() != JsonToken.END_OBJECT) {
						String key = reader.nextName();
						String value = reader.nextString();
						if(key.compareToIgnoreCase("id") == 0 ) {
							if(Integer.parseInt(value) != id) {
								isIt = false;
							}
						}
						if(isIt) {
							if(reader.peek() != JsonToken.END_OBJECT)
								parser += key + ":" + value + ",";
							else {
								parser += key + ":" + value;
							}
						}
					}
					if(isIt) {
						Entity ent = createObjectFromString(parser);
						res.add(ent);
						System.out.println(res);
					}
					parser = "";
					isIt = true;
					reader.endObject();
				}
				
				reader.endArray();
				
				reader.close();
				
				
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return res;
	}

	@Override
	public List<Entity> search(String naziv) {
		List<Entity> res = new ArrayList<Entity>();
		try {
			JsonReader reader = new JsonReader(new FileReader(fileInUse));
			reader.beginArray();
			String parser = "";
			boolean isIt = true;
			while(reader.hasNext()) {
				reader.beginObject();
				while(reader.peek() != JsonToken.END_OBJECT) {
					String key = reader.nextName();
					String value = reader.nextString();
					if(key.compareToIgnoreCase("Naziv") == 0 ) {
						if(value.compareToIgnoreCase(naziv) != 0) {
							isIt = false;
						}
					}
					if(isIt) {
						if(reader.peek() != JsonToken.END_OBJECT)
							parser += key + ":" + value + ",";
						else {
							parser += key + ":" + value;
						}
					}
				}
				if(isIt) {
					Entity ent = createObjectFromString(parser);
					entities.add(ent);
					//System.out.println(ent);
				}
				parser = "";
				isIt = true;
				reader.endObject();
			}
			
			reader.endArray();
			
			reader.close();
			
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	return res;
	}

	@Override
	public List<Entity> DepthSerach(String slozeniUpit) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Entity e) {
		// TODO Auto-generated method stub
		
	}

	@Override
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


	
	

}
