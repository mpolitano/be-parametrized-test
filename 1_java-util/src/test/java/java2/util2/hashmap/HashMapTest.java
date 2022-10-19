package java2.util2.hashmap;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import utils.Config;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.BufferedWriter;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.concurrent.ThreadLocalRandom;
import java2.util2.Set;



public class HashMapTest { 
    
	
	//Change with sedl 
		public static int scope;
		public static String pathFile;
		private static int count = 0;

		@BeforeAll
	    static void initAll() {
	    	Config.readEnvironmentVariables();
	    	scope = Config.scope;
	    	pathFile = "serialize/java2.util2.hashmap.HashMap/"+Config.scope+"/objects.ser";
	    }
		
		@AfterAll
	    static void afterAll() {
			File dir = new File("../scripts/reportBEAPI/java2.util2.hashmap.HashMap/"+Config.scope);
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
	
		@Test
		public void clear_Test() {
			
			FileInputStream fileTestUnit;
		  	ObjectInputStream ois;
			try {
				fileTestUnit= new FileInputStream(pathFile);
				ois = new ObjectInputStream(fileTestUnit);
				HashMap hmap = (HashMap)nextObject(ois);
				while(hmap != null) {
					count++;	
					hmap.clear();
					assertTrue(hmap.size() == 0);
					assertTrue(hmap.repOK());
					hmap = (HashMap)nextObject(ois);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			}
			

		 }
	    
	    @Test
		public void constructor_Test() {
			HashMap h = null;

	    	try {
				int i = ThreadLocalRandom.current().nextInt(-3, scope + 1);
				int j = ThreadLocalRandom.current().nextInt(-3, scope + 1);

	    		h = new HashMap(i,j);
			}catch(IllegalArgumentException e) {
//				assertTrue(h.repOK());
			}
	    	if(h!=null)
				assertTrue(h.repOK());

		 }
	    
	    @Test
		public void keyset_Test() {
	    	FileInputStream fileTestUnit;
		  	ObjectInputStream ois;
			try {
				fileTestUnit= new FileInputStream(pathFile);
				ois = new ObjectInputStream(fileTestUnit);
				HashMap hmap = (HashMap)nextObject(ois);
				while(hmap != null){
					count++;	
					hmap.keySet();
					assertTrue(hmap.repOK());
					hmap = (HashMap)nextObject(ois);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			}
			
//			assertTrue(hmap.equals(hmap1));
		 }
	    
//	    @Test
//		public void ketItset_Test() {
//	    	FileInputStream fileTestUnit;
//		  	ObjectInputStream ois;
//			try {
//				fileTestUnit= new FileInputStream(pathFile);
//				ois = new ObjectInputStream(fileTestUnit);
//				HashMap hmap = (HashMap)nextObject(ois);
//				while(hmap != null){
//					count++;	
//					HashMap hmap1 = (HashMap) hmap.key();
//					assertTrue(hmap.repOK());
//					hmap = (HashMap)nextObject(ois);
//				}
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			 catch (ClassNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			}
//			
////			assertTrue(hmap.equals(hmap1));
//		 }
		 
	    
	    @Test
		public void constructor1_Test() {
			HashMap h = null;

	    	try {
	    		h = new HashMap();
			}catch(IllegalArgumentException e) {
//				assertTrue(h.repOK());
			}
	    	if(h!=null)
				assertTrue(h.repOK());

		 }
	    
	    @Test
		public void constructor2_Test() {
	    		HashMap h = null;
	    	try {
				int i = ThreadLocalRandom.current().nextInt(0, scope + 1);
				h = new HashMap(i);
			}catch(IllegalArgumentException e) {
			}
	    	if(h!=null)
	    		assertTrue(h.repOK());
	    	
		 }
	    
	    @Test
		public void clone_Test() {
	    	FileInputStream fileTestUnit;
		  	ObjectInputStream ois;
			try {
				fileTestUnit= new FileInputStream(pathFile);
				ois = new ObjectInputStream(fileTestUnit);
				HashMap hmap = (HashMap)nextObject(ois);
				while(hmap != null){
					count++;	
					HashMap hmap1 = (HashMap) hmap.clone();
					assertTrue(hmap1.repOK());
					assertTrue(hmap.repOK());
					hmap = (HashMap)nextObject(ois);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			}
			
//			assertTrue(hmap.equals(hmap1));
		 }
		
	    
	    @Test
		public void contains_key_Test() {
	    	FileInputStream fileTestUnit;
		  	ObjectInputStream ois;
			try {
				fileTestUnit= new FileInputStream(pathFile);
				ois = new ObjectInputStream(fileTestUnit);
				HashMap hmap = (HashMap)nextObject(ois);
				while(hmap != null){
					count++;	
					System.out.println(count);

					int i = ThreadLocalRandom.current().nextInt(0, scope + 1);
					hmap.containsKey(i);
			    	assertTrue(hmap.repOK());
					hmap = (HashMap)nextObject(ois);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			}
	    	
		 }
		
	    @Test
		public void contains_value_Test() {
			FileInputStream fileTestUnit;
		  	ObjectInputStream ois;
			try {
				fileTestUnit= new FileInputStream(pathFile);
				ois = new ObjectInputStream(fileTestUnit);
				HashMap hmap = (HashMap)nextObject(ois);
				while(hmap != null){
					int i = ThreadLocalRandom.current().nextInt(0, scope + 1);
				  	hmap.containsValue(i);
			    	assertTrue(hmap.repOK());
			    	
					hmap = (HashMap)nextObject(ois);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			}
			
		}
	    
	    
	    @Test
		public void entry_set_Test() {
			FileInputStream fileTestUnit;
		  	ObjectInputStream ois;
			try {
				fileTestUnit= new FileInputStream(pathFile);
				ois = new ObjectInputStream(fileTestUnit);
				HashMap hmap = (HashMap)nextObject(ois);
				while(hmap != null){
					Set s = hmap.entrySet();
			    	assertTrue(hmap.repOK());
					hmap = (HashMap)nextObject(ois);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			}
	    	
		 }
	
	
	    @Test
	   	public void get_test() {
			FileInputStream fileTestUnit;
		  	ObjectInputStream ois;
			try {
				fileTestUnit= new FileInputStream(pathFile);
				ois = new ObjectInputStream(fileTestUnit);
				HashMap hmap = (HashMap)nextObject(ois);
				while(hmap != null){
					int key = ThreadLocalRandom.current().nextInt(0, scope + 1);
			    	Integer v =  (Integer)hmap.get(key);
			    	assertTrue(hmap.repOK());
			    	
			    	//Esto se cumple porque se que Value no es null.
			    	assertTrue((v!=null && hmap.containsKey(key)) || 
			    			(v==null && !hmap.containsKey(key)));
					hmap = (HashMap)nextObject(ois);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			}
			

	    }
	
		
	    @Test
	   	public void empty_test() {
			FileInputStream fileTestUnit;
		  	ObjectInputStream ois;
			try {
				fileTestUnit= new FileInputStream(pathFile);
				ois = new ObjectInputStream(fileTestUnit);
				HashMap hmap = (HashMap)nextObject(ois);
				while(hmap != null){
					boolean p = hmap.isEmpty();
			    	assertTrue(hmap.repOK());
			    	assertTrue((p ==true && hmap.size() ==0) || (p ==false && hmap.size() !=0));

					hmap = (HashMap)nextObject(ois);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			}
				    }
		
	
		
//		@ParameterizedTest
//		@MethodSource("provide_HMap_Parameters")
//	   	public void key_set_test(HashMap hmap) {
//	    	Set s = hmap.keySet();
//	    	assertTrue(hmap.repOK());
//	    	assertTrue(s.size() == hmap.size());
//	    
//	    }
	
		
	    @Test
		public void put_test() {
			FileInputStream fileTestUnit;
		  	ObjectInputStream ois;
			try {
				fileTestUnit= new FileInputStream(pathFile);
				ois = new ObjectInputStream(fileTestUnit);
				HashMap hmap = (HashMap)nextObject(ois);
				while(hmap != null){
					int oldSize = hmap.size();
					int key = ThreadLocalRandom.current().nextInt(0, scope + 1);
					int value = ThreadLocalRandom.current().nextInt(0, scope + 1);

					boolean b = hmap.containsKey(key);
					
					hmap.put(key,value);
					
					assertTrue((!b && hmap.size() == oldSize+1) ||(b && hmap.size() == oldSize));
					assertTrue(hmap.repOK());
					assertTrue(hmap.containsKey(key) && hmap.containsValue(value));

					hmap = (HashMap)nextObject(ois);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			}
			
		 }

	    @Test
	   	public void remove_test() {
			FileInputStream fileTestUnit;
		  	ObjectInputStream ois;
			try {
				fileTestUnit= new FileInputStream(pathFile);
				ois = new ObjectInputStream(fileTestUnit);
				HashMap hmap = (HashMap)nextObject(ois);
				while(hmap != null){
					int key = ThreadLocalRandom.current().nextInt(0, scope + 1);

					hmap.remove(key);
			    	
			    	assertTrue(hmap.repOK());
			    	assertTrue(!hmap.containsKey(key));
			    
					hmap = (HashMap)nextObject(ois);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			}
	    }
			
	
		@Test
	   	public void size_test() {
			FileInputStream fileTestUnit;
		  	ObjectInputStream ois;
			try {
				fileTestUnit= new FileInputStream(pathFile);
				ois = new ObjectInputStream(fileTestUnit);
				HashMap hmap = (HashMap)nextObject(ois);
				while(hmap != null){
					int s = hmap.size();
			       	assertTrue(hmap.repOK());
					assertTrue((s==0 && hmap.isEmpty()) ||(s!=0 && !hmap.isEmpty()));

					hmap = (HashMap)nextObject(ois);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			}
	    	
	    }
		

//		@ParameterizedTest
//		@MethodSource("provide_HMap_Parameters")
//	   	public void toString_test(HashMap hmap) {
//	    	String s = hmap.toString();
//	       	assertTrue(hmap.repOK());
//
//	    }
		
		
//		@ParameterizedTest
//		@MethodSource("provide_HMap_Parameters")
//		public void value_test(HashMap hmap) {
//			Collection l = hmap.values();
//			assertTrue(hmap.repOK());
//			assertTrue((l.isEmpty() && hmap.isEmpty()) || (!l.isEmpty() && !hmap.isEmpty()) );
//					
//		 }
		
	
/*	@ParameterizedTest
	@MethodSource("provide_HMap_HMap_Parameters")
   	public void equals_test(HashMap hmap, HashMap hmap1) {
    	hmap.equals(hmap1);
    	assertTrue(hmap.repOK());
    	assertTrue(hmap1.repOK());
    }
*/
		
	
	/*
	 * Providers..
	 */
	
	   

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
    
}
