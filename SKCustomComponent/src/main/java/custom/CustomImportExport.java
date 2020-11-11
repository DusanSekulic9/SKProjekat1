package custom;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashMap;
import java.util.List;

import model.Entity;
import model.Storage;

public class CustomImportExport extends Storage {

	@Override
	public void pretraziFajl(File file) {
		try {
			FileReader fr = new FileReader(file);
			BufferedReader bf = new BufferedReader(fr);
			String parser = "";
			String line = bf.readLine();
			Entity e;
			while(line != null) {
				String[] split = line.split(":");
				if(split[0].equalsIgnoreCase("_id")) {
					e = new Entity();
					e.setId(Integer.parseInt(split[1]));
					line = bf.readLine();
					split = line.split(":");
					if(split[0].equalsIgnoreCase("naziv")) {
						e.setNaziv(split[1].substring(0, split[1].length()-1));
						line = bf.readLine();
						split = line.split(":");
					}
					if(split[0].equalsIgnoreCase("simpleproperties")) {
						line = bf.readLine();
						split = line.split(":");
						while(!split[0].equalsIgnoreCase("_id") || !split[0].equalsIgnoreCase("entityproperties")) {
							HashMap<String, Object> simple = new HashMap<String, Object>();
							String value = split[1];
							if(split[1].startsWith("'")) {
								value = split[1].substring(0, split[1].length()-1);
							}
							simple.put(split[0], (Object)value);
							
							line = bf.readLine();
							split = line.split(":");
						}
					}
					if(split[0].equalsIgnoreCase("entityproperties")) {
						line = bf.readLine();
						split = line.split(":");
						
					}
					
				}
				
				line = bf.readLine();
			}
			bf.close();
			fr.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void save(Entity e) {
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
