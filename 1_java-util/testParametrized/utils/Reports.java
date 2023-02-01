package utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class Reports {
	
	private static int count = 0;


	public static void saveAmountObjectsToFile(List<Object> objs, String file) {
		//For amount objects
	    File dir = new File("../scripts/results/1_java-util/"+Config.clazz+"/"+Config.tool+"/"+Config.scope);
		if (! dir.exists()){
	        dir.mkdir();            
		}
		String fileName = dir +"/"+ file;
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
	}
	
	public static void end(String clazz) {
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
	
	public static void addCountTest() {
		   count++;
	   }
}
