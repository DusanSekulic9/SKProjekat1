package json;

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
	
	public List<Entity> pretraziFajl(String pretraga){
		List<Entity> res = new ArrayList<Entity>();
		String[] split = pretraga.split("\n");
		try {
			JsonReader reader = new JsonReader(new FileReader(fileInUse));
			reader.beginArray();
				res = pretraziEntitet(reader, split);
			reader.endArray();
			
			reader.close();
			
		}catch (Exception e) {
			
		}
		return res;
	}
	
	public List<Entity> pretraziEntitet(JsonReader reader,String[] pretraga) {
		List<Entity> res = new ArrayList<Entity>();	
		boolean isIt = true;
		try {
			while(reader.hasNext()) {
				reader.beginObject();
				while(reader.peek() != JsonToken.END_OBJECT) {
					String key = reader.nextName();
					if(reader.peek() != JsonToken.BEGIN_OBJECT) {
						String value = reader.nextString();
						for(String s : pretraga) {
							String[] split = s.split(":");
							if(key.equalsIgnoreCase(split[0]) && value.equalsIgnoreCase(split[1])) {
								isIt = false;
							}
						}
						parser += key + ":" + value + "\n";
					}else {
						if(key.equalsIgnoreCase("simpleproperties")) {
							reader.beginObject();
							while(reader.peek() != JsonToken.END_OBJECT) {
								key = reader.nextName();
								String value = reader.nextString();
								parser += key + ":" + value + "\n";
							}
							reader.endObject();
						}else {
							reader.beginObject();
							String secondKey = reader.nextName();
							parser += key + ":" + "noviEntitet";
							res = pretraziEntitet(reader, pretraga);
						}
					}
				}
				reader.endObject();
				if(isIt) {
					res.add(createObjectFromString(parser));
				}
				isIt = true;
				parser = "";
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res;
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
					res.add(ent);
					System.out.println(ent);
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
		for(String s : splitByComa) {
			String[] keyValueSplit = s.split(":");
			if(keyValueSplit[0].equalsIgnoreCase("id")) {
				entity.setId(Integer.parseInt(keyValueSplit[1]));
			}else if(keyValueSplit[0].equalsIgnoreCase("naziv")) {
				entity.setNaziv(keyValueSplit[1]);
			}else {
				entity.getSimpleProperties().put(keyValueSplit[0], keyValueSplit[1]);
			}
			
		}
		

		return entity;
	}


	
	

}
