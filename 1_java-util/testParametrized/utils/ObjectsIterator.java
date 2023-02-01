package utils;

import java.io.BufferedWriter;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


import com.thoughtworks.xstream.XStream;


public class ObjectsIterator{
	
	FileInputStream fileTestUnit;
  	ObjectInputStream ois;
  	String pathFile;
  	List<Object> objDeserializer;
  	int literals;
	
	public ObjectsIterator(String clazz){
			init(clazz);		
	}
	
	public void init(String clazz) {
	  	Config.readEnvironmentVariables();
		literals = Config.literals;
		pathFile = "serialize/"+clazz+"/"+Config.scope+"/"+Config.tool+"/randoop.xml";
		objDeserializer = deserialize(pathFile);
	}
	
	public static List<Object> deserialize(String file) {
	    XStream xstream = new XStream();
	    xstream.allowTypesByRegExp(new String[] { ".*" });
	    List<Object> objs = new ArrayList<>();
	    try {
	      ObjectInputStream ois = xstream.createObjectInputStream(new FileInputStream(file));
	      Object o;
	      try {
	        while (true) {
	          o = ois.readObject();
	          objs.add(o);
	        }
	      } catch (EOFException e) {
	        /* The loop ends here */ }
	      ois.close();
	    } catch (IOException | ClassNotFoundException e) {
	      e.printStackTrace();
	      throw new RuntimeException("Cannot deserialize file: " + file);
	    }

		Reports.saveAmountObjectsToFile(objs,"objects.txt");
	    return objs;
	  }       
   
   public int getLiterals() {
	   return literals;
   }

	public List<Object> getObjects() {
		return objDeserializer;
	}
	
}
