package yaml;

import java.io.BufferedOutputStream;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.yaml.snakeyaml.Yaml;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import model.Entity;
import model.Storage;

public class YamlImportExport extends Storage {

	
	@Override
	public void pretraziFajl(File file, String pretraga) {
		/*
		 * 
		 * I ended up using SnakeYaml and made some split strings to solve my issue.
			Loaded the yaml file to Object and then into a Map, then split the result from the Map into String[] and then in a for loop I read out the name from the String[]. I did the same with groups.
		 * 
		 * 
		 * 
		 */
		String[] split = pretraga.split("\n");		// id : 2
		ObjectMapper om = new ObjectMapper(new YAMLFactory());
		try {
			
			List<Entity> en = om.readValue(file, new TypeReference<List<Entity>>() {});
			System.out.println(en);
			/*
			 *  Yaml yaml = new Yaml();
				InputStream inputStream = new FileInputStream(file);;
				Map<String, Object> obj = yaml.load(inputStream);  //{firstName=John, lastName=Doe, age=20}
			 * 
			 * 
			 */
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	/*	String[] split = pretraga.split("\n");
		try {
			JsonReader reader = new JsonReader(new FileReader(file));
			reader.beginArray();
				pretraziEntitet(reader, split);
			reader.endArray();
			
			reader.close();
		}catch (Exception e) {
			
		} */
	}
	
	
	public void pretraziEntitet(File file) {
		
		
	/*	ObjectMapper om = new ObjectMapper(new YAMLFactory());
		try {
			
			List<Entity> en = om.readValue(file, new TypeReference<List<Entity>>() {});
			System.out.println(en);
		}catch(Exception e) {
			e.printStackTrace();
		} */
		
		
	/*	// import file to object
		//snake yaml jar
		Yaml yaml = new Yaml();
		try {
			InputStream in = new FileInputStream(file);
			Object o = yaml.loadAs(in, Entity.class); //objekat koji loadujemo je instanceof te klase
		//	return (Entity)o;
		}catch(Exception e ) {
			e.printStackTrace();
		}
		
		*/
	}

	private void stringToFile(String s) {
		try {
			FileOutputStream fos = new FileOutputStream(fileInUse);
			BufferedOutputStream bos = new BufferedOutputStream(fos);
			byte[] bytes = s.getBytes();
			bos.write(bytes); //upisuje byte array u file
			bos.close();
			fos.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
	}
	
	
	@Override
	public void save(Entity e) {
		// create object to file
		//export obj u  File
		//yaml jackson jar
		
		try {
			ObjectMapper mapper = new ObjectMapper();
			String yaml = mapper.writeValueAsString(e);//string u yaml dala formatu koji predst Entity 
			stringToFile(yaml);
			
			
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		
	}

	@Override
	public void save(List<Entity> entities) {
		
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
