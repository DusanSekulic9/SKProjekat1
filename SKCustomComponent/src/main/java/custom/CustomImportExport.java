package custom;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashMap;
import java.util.List;


import model.Entity;
import model.Storage;

public class CustomImportExport extends Storage {
	
	public static Storage customFactory(){
		return new CustomImportExport();
	}
	
	String parser = "";
	
	@Override
	public void pretraziFajl(File file) {
		try{FileReader fr = new FileReader(file);
		BufferedReader br = new BufferedReader(fr);
		pretraziEntitet(br);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public void pretraziEntitet(BufferedReader br) {
		String line = "";
		boolean create = false;
		try {
			while((line = br.readLine()) != null) {
				if(line.contains("_")) {
					create = true;
				}
				if(create) {
					createObjectFromString(parser);
					parser = "";
					create = false;
				}
				if(line.contains("entitet:")) {
					String key = br.readLine();
					if(key == null) {
						parser += "SS:entity\n";
					}else if(key.trim().equalsIgnoreCase("_")) {
						parser += "SS:entity\n";
						create = true;
					}else {
						parser += key + "entity\n";
					}
				}else {
					String[] keyValueSplit = line.split(":");
					parser += keyValueSplit[0] + ":" + keyValueSplit[1] + "\n";
				}
			}
			if(create) {
				createObjectFromString(parser);
				parser = "";
				create = false;
			}
			
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void save(Entity e, File f) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void save(List<Entity> entities) {
		// TODO Auto-generated method stub
		
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
