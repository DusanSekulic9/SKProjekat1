package custom;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashMap;
import java.util.List;

import com.google.gson.stream.JsonToken;

import model.Entity;
import model.Storage;

public class CustomImportExport extends Storage {

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
		try {
			String line = br.readLine();
			while(!line.equalsIgnoreCase("#")) {
				
				String split[] = line.split(":");
				String value=null;
				if(split[1].charAt(split[1].length()-1) != '-') {
					if(!split[1].contains("-")){
						if(split[1].contains("'")) {
							value = split[1].substring(0, split[1].length()-1);
						}
				}
					parser += split[0] + ":" + value + "\n";
				}else {
					if(split[0].equalsIgnoreCase("simpleproperties")) {
						line = br.readLine();
						split = line.split(":");
						while(!split[1].contains("-")) {
							
							
							line = br.readLine();
							split = line.split(":");
							}
							parser += key + ":" + value + "\n";
						}
						reader.endObject();
					}else {
						reader.beginObject();
						if(reader.peek() == JsonToken.END_OBJECT) {
							parser += "SS:entity\n";
							break;
						}
						key = reader.nextName();
						parser += key + ":entity\n";
						pretraziEntitet(reader);

					}
				}
				
			
			
			
	
				
			
			
			}
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
