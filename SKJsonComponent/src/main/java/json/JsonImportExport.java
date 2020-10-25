package json;

import java.io.InputStreamReader;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import model.Entity;
import model.Storage;

public class JsonImportExport extends Storage{
	
	Gson gson = new Gson();
	
	
	@Override
	public void save(Entity e) {
	}

	@Override
	public void save(List<Entity> entities) {
		
	}

	@Override
	public void makeNewEntity() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Entity> search(int id) {
		// TODO Auto-generated method stub
		return null;
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

}
