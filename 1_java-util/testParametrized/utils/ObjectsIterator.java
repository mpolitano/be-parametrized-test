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


public class ObjectsIterator  implements Iterator<Object>{
	
	FileInputStream fileTestUnit;
  	ObjectInputStream ois;
  	String pathFile;
  	List<Object> objDeserializer;
  	int literals;
	private static int count = 0;
	
	public ObjectsIterator(String clazz){
			init(clazz);		
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
			//For amount objects
		    File dir = new File("../scripts/results/1_java-util/"+Config.clazz+"/"+Config.tool+"/"+Config.scope);
			if (! dir.exists()){
		        dir.mkdir();            
			}
		   	String fileName = dir + "/objects.txt";
		       try{
		           File file1 = new File(fileName);
		           file1.getParentFile().mkdirs();
		           FileWriter fw = new FileWriter(file1);
	
		           BufferedWriter bw = new BufferedWriter(fw);
		           bw.write(String.valueOf(objs.size()) );
		           bw.close();
		       }
		       catch (IOException e){
		           e.printStackTrace();
		           System.exit(-1);
		       }
		    return objs;
		  }
	
	//Precondition, call in initBeforeAllTest
	public void init(String clazz) {
	  	Config.readEnvironmentVariables();
		literals = Config.literals;
		pathFile = "serialize/"+clazz+"/"+Config.scope+"/"+Config.tool+"/randoop.xml";
		objDeserializer = deserialize(pathFile);
	}
	
	public void end(String clazz) {
		File dir = new File("../scripts/results/1_java-util/"+Config.clazz+"/"+Config.tool+"/"+Config.scope);
		 if (! dir.exists()){
		        dir.mkdir();            
		 }
	   	String fileName = dir + "/tests.txt";
	       try{
	           File file = new File(fileName);
	           file.getParentFile().mkdirs();
	           FileWriter fw = new FileWriter(file);

	           BufferedWriter bw = new BufferedWriter(fw);
	           bw.write(String.valueOf(count) );
	           bw.close();
	       }
	       catch (IOException e){
	           e.printStackTrace();
	           System.exit(-1);
	       }
	
	}
	
	public static Object nextObject(ObjectInputStream ois) throws ClassNotFoundException, IOException {
		try {
			return ois.readObject();
		} catch (EOFException eof) {
			return null;
		} catch (ClassNotFoundException e) {
			throw e;
		} catch (IOException e) {
			throw e;
		}
	}    

       
   @Override
   public boolean hasNext() { 
	   	boolean result = false;
		try {
			result = ObjectsIterator.nextObject(ois) != null;
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result; 
   };
   
   @Override
   public Object next() { 
	   try {
		   return ObjectsIterator.nextObject(ois);
	   } catch (ClassNotFoundException | IOException e) {
		   // TODO Auto-generated catch block
		   e.printStackTrace();
	   }
	   return fileTestUnit;
   };
	   
   @Override
   public void remove()
   { 
	   throw new UnsupportedOperationException("Remove not supported!"); 
   }
   
   public void addCountTest() {
	   count++;
   }
   
   public int getLiterals() {
	   return literals;
   }

public List<Object> getObjects() {
	return objDeserializer;
}
	
}
