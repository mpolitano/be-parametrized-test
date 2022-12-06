package java2.util2.hashmap;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import utils.Config;
import utils.ObjectsIterator;

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
	public static int literals;
	private static ObjectsIterator objIterator;
//	private static clazz = "java2.util2.linkedlist.LinkedList";


	
	@BeforeEach
	public void init() {
		objIterator = new ObjectsIterator("java2.util2.hashmap.HashMap");
		literals = objIterator.getLiterals();
	}
	
//	Aft
//	public void end() {
//		objIterator = new ObjectsIterator("java2.util2.linkedlist.LinkedList");
//	}
//	
	@AfterAll
    static void afterAll() {
		objIterator.end("java2.util2.hashmap.HashMap");
	}

	
		@Test
		public void clear_Test() {
			
			HashMap hmap = (HashMap)objIterator.next(); 

			while(hmap != null) {
			objIterator.addCountTest();
				hmap.clear();
				assertTrue(hmap.size() == 0);
				assertTrue(hmap.repOK());
				hmap = (HashMap)objIterator.next();
			}
		 }
	    
		@Test(expected = IllegalArgumentException.class)
		public void constructor_Test() {
	    	for (int i = -1; i<literals-1;i++) {
		    	for (int j = -1; j<literals-1;j++) {
				objIterator.addCountTest();
				HashMap h = null;
	    	try {
	    		h = new HashMap(i,j);
			}catch(IllegalArgumentException e) {
//				assertTrue(h.repOK());
			}
	    	if(h!=null)
				assertTrue(h.repOK());
		      	}
	    	}
		 }
	    
	    @Test
		public void contains_key_null_Test() {
			HashMap hmap = (HashMap)objIterator.next(); 
					objIterator.addCountTest();
					try {

						hmap.containsKey(null);
						hmap.eq(null, null);
					}catch(ArrayIndexOutOfBoundsException e){
				    	assertTrue(hmap.repOK());

					}
			    	assertTrue(hmap.repOK());
					hmap = (HashMap)objIterator.next();
				}
	    
	    
	    @Test
		public void keyset_Test() {
			HashMap hmap = (HashMap)objIterator.next(); 
				while(hmap != null){
					objIterator.addCountTest();
					hmap.keySet();
					assertTrue(hmap.repOK());
					hmap = (HashMap)objIterator.next();
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
				objIterator.addCountTest();

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
					objIterator.addCountTest();

				int i = ThreadLocalRandom.current().nextInt(0, literals + 1);
				h = new HashMap(i);
			}catch(IllegalArgumentException e) {
			}
	    	if(h!=null)
	    		assertTrue(h.repOK());
	    	
		 }
	    
	    @Test
		public void clone_Test() {
			HashMap hmap = (HashMap)objIterator.next(); 
				while(hmap != null){
					objIterator.addCountTest();
					HashMap hmap1 = (HashMap) hmap.clone();
					assertTrue(hmap1.repOK());
					assertTrue(hmap.repOK());
					hmap = (HashMap)objIterator.next();
				}
			
//			assertTrue(hmap.equals(hmap1));
		 }
		
	    
	    @Test
		public void contains_key_Test() {
			HashMap hmap = (HashMap)objIterator.next(); 

				while(hmap != null){
					objIterator.addCountTest();

					int i = ThreadLocalRandom.current().nextInt(0, literals + 1);
					hmap.containsKey(i);
			    	assertTrue(hmap.repOK());
					hmap = (HashMap)objIterator.next();
				}

	    	
		 }
		
	    @Test
		public void contains_value_Test() {
				HashMap hmap = (HashMap)objIterator.next(); 

				while(hmap != null){
					objIterator.addCountTest();

					int i = ThreadLocalRandom.current().nextInt(-1, literals );
				  	hmap.containsValue(i);
			    	assertTrue(hmap.repOK());
			    	
					hmap = (HashMap)objIterator.next();

			}
			
		}
	    
	    
	    @Test
		public void entry_set_Test() {
				HashMap hmap = (HashMap)objIterator.next(); 

				while(hmap != null){
					objIterator.addCountTest();

					Set s = hmap.entrySet();
			    	assertTrue(hmap.repOK());
					hmap = (HashMap)objIterator.next();
				}
	    	
		 }
	
	
	    @Test
	   	public void get_test() {
				HashMap hmap = (HashMap)objIterator.next(); 

				while(hmap != null){
					objIterator.addCountTest();

					int key = ThreadLocalRandom.current().nextInt(0, literals + 1);
//					if()
			    	Object v =  (Object)hmap.get(key);
			    	assertTrue(hmap.repOK());
//			    	System.out.println(hmap);
//			    	System.out.println(v);
//			    	System.out.println(key);

			    	//Esto se cumple porque se que Value no es null.
			    	assertTrue((v!=null && hmap.containsKey(key)) || 
			    			(v==null && (!hmap.containsKey(key)||hmap.get(key)==null)));
					hmap = (HashMap)objIterator.next();
				}

	    }
	
		
	    @Test
	   	public void empty_test() {
				HashMap hmap = (HashMap)objIterator.next(); 

				while(hmap != null){
					objIterator.addCountTest();

					boolean p = hmap.isEmpty();
			    	assertTrue(hmap.repOK());
			    	assertTrue((p ==true && hmap.size() ==0) || (p ==false && hmap.size() !=0));

					hmap = (HashMap)objIterator.next();
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
				HashMap hmap = (HashMap)objIterator.next(); 

				while(hmap != null){
					objIterator.addCountTest();

					int oldSize = hmap.size();
					int key = ThreadLocalRandom.current().nextInt(-1, literals );
					int value = ThreadLocalRandom.current().nextInt(-1, literals );

					boolean b = hmap.containsKey(key);
					
					hmap.put(key,value);
					
					assertTrue((!b && hmap.size() == oldSize+1) ||(b && hmap.size() == oldSize));
					assertTrue(hmap.repOK());
					assertTrue(hmap.containsKey(key) && hmap.containsValue(value));

					hmap = (HashMap)objIterator.next();
				}
			
		 }

	    @Test
	   	public void remove_test() {
			HashMap hmap = (HashMap)objIterator.next(); 

				while(hmap != null){
					objIterator.addCountTest();

					int key = ThreadLocalRandom.current().nextInt(0, literals + 1);

					hmap.remove(key);
			    	
			    	assertTrue(hmap.repOK());
			    	assertTrue(!hmap.containsKey(key));
			    
					hmap = (HashMap)objIterator.next();
				}
	    }
			
	
		@Test
	   	public void size_test() {
				HashMap hmap = (HashMap)objIterator.next(); 

				while(hmap != null){
					objIterator.addCountTest();

					int s = hmap.size();
			       	assertTrue(hmap.repOK());
					assertTrue((s==0 && hmap.isEmpty()) ||(s!=0 && !hmap.isEmpty()));

					hmap = (HashMap)objIterator.next();
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
	
	   

    
}
