package utils;

import java.io.BufferedWriter;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Iterator;

public class ObjectsIterator  implements Iterator<Object>{
	
	FileInputStream fileTestUnit;
  	ObjectInputStream ois;
  	String pathFile;
  	int literals;
	private static int count = 0;
	
	public ObjectsIterator(String clazz){
			init(clazz);		
	}
	
	//Precondition, call in initBeforeAllTest
	public void init(String clazz) {
	  	Config.readEnvironmentVariables();
		literals = Config.literals;
		pathFile = "serialize/"+clazz+"/"+Config.scope+"/"+Config.tool+"/randoop.ser";
		try {
			fileTestUnit= new FileInputStream(pathFile);
			ois = new ObjectInputStream(fileTestUnit);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void end(String clazz) {
		File dir = new File("../scripts/results/1_java-util/"+Config.clazz+"/"+Config.tool+"/"+Config.scope);
		 if (! dir.exists()){
		        dir.mkdir();            
		 }
	   	File file = new File(dir + "/tests.txt");
	       try{
	           FileWriter fw = new FileWriter(file.getAbsoluteFile());
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
	
}
