package json;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;

import model.Entity;
import model.Storage;


public class JsonImportExport extends Storage{
	
	private Gson gson = new Gson();
	
	
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
					parser += "{";
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
								parser += key + ":" + value + "}";
							}
						}
					}
					if(isIt) {
						Entity ent = createObjectFromString(parser);
						res.add(ent);
					}else {
						parser = "";
					}
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
		// TODO Auto-generated method stub
		return null;
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
		String json = string;
		Object object = gson.fromJson(json, Entity.class);

		return (Entity) object;
	}


	
	

}
