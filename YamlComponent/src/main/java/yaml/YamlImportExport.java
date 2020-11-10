package yaml;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.yaml.snakeyaml.Yaml;

import com.fasterxml.jackson.databind.ObjectMapper;

import model.Entity;
import model.Storage;

public class YamlImportExport extends Storage {

	@Override
	public List<Entity> pretraziFajl(String pretraga) {
		List<Entity> ent = new ArrayList<Entity>();
		String[] split = pretraga.split("\n");
		try {
			
			
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return null;
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
	public Entity createObjectFromString(String path) {
		// import file to object
		//snake yaml jar
		Yaml yaml = new Yaml();
		try {
			InputStream in = new FileInputStream(new File(path));
			Object o = yaml.loadAs(in, Entity.class); //objekat koji loadujemo je instanceof te klase
			return (Entity)o;
		}catch(Exception e ) {
			e.printStackTrace();
			return null;
		}
		
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
