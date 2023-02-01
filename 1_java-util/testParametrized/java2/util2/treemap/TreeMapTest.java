package java2.util2.treemap;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java2.util2.Comparator;
import java2.util2.Set;
import java2.util2.linkedlist.LinkedList;
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
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.concurrent.ThreadLocalRandom;


public class TreeMapTest { 
    
	//Change with sedl 
	public static int literals;
	private static ObjectsIterator objIterator;
//	private static clazz = "java2.util2.linkedlist.LinkedList";

    public List<Object> getImput(ListIterator list) {
	List<Object> pool = new ArrayList<Object>();
	while (list.hasNext()) {
		TreeMap t=(TreeMap)list.next();
		list.set(t);// wrong
		pool.add(t);
	}
	return pool;
}

	@BeforeEach
	public void init() {
		objIterator = new ObjectsIterator("java2.util2.treemap.TreeMap");
		literals = objIterator.getLiterals();
	}

	@AfterAll
    static void afterAll() {
		objIterator.end("java2.util2.treemap.TreeMap");
	}
//	
	
	@Test
	public void clear_Test() {
		List<Object> treemap = objIterator.getObjects();
		ListIterator it = treemap.listIterator();
		while(it.hasNext()) {
			TreeMap tmap = (TreeMap)it.next(); 	

			objIterator.addCountTest();
				tmap.clear();
				assertTrue(tmap.size() == 0);
				assertTrue(tmap.repOK());
			}
	}
	
//	@ParameterizedTest
//	@MethodSource("provide_TMap_Parameters")
//	public void clone_Test(TreeMap tmap) {
//		TreeMap tmap1 = (TreeMap) tmap.clone();
//		assertTrue(tmap1.repOK());
//		assertTrue(tmap.repOK());
//		assertTrue(tmap.equals(tmap1));
//	 }
	
	@Test
	public void comparator_Test() {
		List<Object> treemap = objIterator.getObjects();
		ListIterator it = treemap.listIterator();
		while(it.hasNext()) {
			TreeMap tmap = (TreeMap)it.next(); 	

			objIterator.addCountTest();
			Comparator c =  tmap.comparator();
			assertTrue(tmap.repOK());
			}
	 }
	
	@Test
	public void toString_Test() {
		List<Object> treemap = objIterator.getObjects();
		ListIterator it = treemap.listIterator();
		while(it.hasNext()) {
			TreeMap tmap = (TreeMap)it.next(); 	

			objIterator.addCountTest();
			tmap.toString();
			assertTrue(tmap.repOK());
			}
	 }
	
	@Test
	public void constructor_Test() {
		FileInputStream fileTestUnit;
	  	ObjectInputStream ois;
		TreeMap t = new TreeMap();

	 }
	
	
	@Test
	public void contains_key_Test() {
		List<Object> treemap = objIterator.getObjects();
		ListIterator it = treemap.listIterator();
		while(it.hasNext()) {
			TreeMap tmap = (TreeMap)it.next(); 	

			Object i = ThreadLocalRandom.current().nextInt(0, literals + 1);
			try {
				tmap.containsKey(i);
			}catch (NullPointerException|ClassCastException e){
				continue;
			}
		  	assertTrue(tmap.repOK());
		}
	 }
	
	@Test
	public void contains_value_Test() {
		List<Object> treemap = objIterator.getObjects();
		ListIterator it = treemap.listIterator();
		while(it.hasNext()) {
			TreeMap tmap = (TreeMap)it.next(); 	

			objIterator.addCountTest();
			int i = ThreadLocalRandom.current().nextInt(0, literals + 1);
		  	tmap.containsValue(i);
	    	assertTrue(tmap.repOK());
			}
	 }
	
	public void entry_set_Test() {
		List<Object> treemap = objIterator.getObjects();
		ListIterator it = treemap.listIterator();
		while(it.hasNext()) {
			TreeMap tmap = (TreeMap)it.next(); 	

		  	Set s = tmap.entrySet();
	    	assertTrue(tmap.repOK());
		}
	 }
	
/*	@ParameterizedTest
	@MethodSource("provide_TMap_TMap_Parameters")
   	public void equals_test(TreeMap tmap, TreeMap tmap1) {
    	tmap.equals(tmap1);
    	assertTrue(tmap.repOK());
    	assertTrue(tmap1.repOK());
    }
*/
	
	@Test
   	public void first_key_test() {
		List<Object> treemap = objIterator.getObjects();
		ListIterator it = treemap.listIterator();
		while(it.hasNext()) {
			TreeMap tmap = (TreeMap)it.next(); 	

			if(tmap.size()>0) {
				objIterator.addCountTest();
		    	Object k = (Object) tmap.firstKey();
		    	assertTrue(tmap.repOK());
//			    	assertTrue(tmap.containsKey(k));

			}
	    	assertTrue(tmap.repOK());
		}
    }
	
	
	
//	@ParameterizedTest
//	@MethodSource("provide_TMap_Int_Parameters")
//   	public void head_map_test(TreeMap tmap, Object i) {
//    	TreeMap tmap1 =  tmap.headMap(i);
//    	assertTrue(tmap.repOK());
//    	assertTrue(tmap1.repOK());   	
//    }
//	
	@Test
   	public void empty_test() {
		List<Object> treemap = objIterator.getObjects();
		ListIterator it = treemap.listIterator();
		while(it.hasNext()) {
			TreeMap tmap = (TreeMap)it.next(); 	

			boolean p = tmap.isEmpty();
	    	assertTrue(tmap.repOK());
	    	assertTrue((p ==true && tmap.size() ==0) || (p ==false && tmap.size() !=0));

		}
    }
	
	
//	@ParameterizedTest
//	@MethodSource("provide_TMap_Parameters")
//   	public void key_set_test(TreeMap tmap) {
//    	Set s = tmap.keySet();
//    	assertTrue(tmap.repOK());
//    	assertTrue(s.size() == tmap.size());
//    
//    }
	
	@Test
   	public void last_key_test() {
		List<Object> treemap = objIterator.getObjects();
		ListIterator it = treemap.listIterator();
		while(it.hasNext()) {
			TreeMap tmap = (TreeMap)it.next(); 	

			Object k =null;
	    	if(tmap.size()>0) {
		    	k = (Object) tmap.lastKey();
//			    	assertTrue(tmap.containsKey(k));
	    	}
	    	assertTrue(tmap.repOK());
			}
		
    }
	
	@Test
	public void put_test() {
		List<Object> treemap = objIterator.getObjects();
		ListIterator it = treemap.listIterator();
		while(it.hasNext()) {
			TreeMap tmap = (TreeMap)it.next(); 	
			int value = ThreadLocalRandom.current().nextInt(0, 10);
//			Object key = pool.get(value);
//			int value = ThreadLocalRandom.current().nextInt(0, literals + 1);
			int oldSize = tmap.size();
			boolean b;
			b = tmap.containsKey(tmap);
	
			tmap.put(tmap,value);
			
			assertTrue((!b && tmap.size() == oldSize+1) ||(b && tmap.size() == oldSize));
			assertTrue(tmap.repOK());
//			assertTrue(tmap.containsKey(key) && tmap.containsValue(0));

			}
	 }
	
	@Test
   	public void remove_test() {
		List<Object> treemap = objIterator.getObjects();
		ListIterator it = treemap.listIterator();
		while(it.hasNext()) {
			TreeMap tmap = (TreeMap)it.next(); 	

			Object key = ThreadLocalRandom.current().nextInt(-1, literals + 1);
			try {
				tmap.remove(key);
			}catch (NullPointerException|ClassCastException e){
				continue;
			}
	    	assertTrue(tmap.repOK());
//	    	assertTrue(!tmap.containsKey(key));
		}
		
    }

	//Si el elemento pertence al map, el remove da true, otherwise false. Obtain objects from structure, randomly 
	//Sino esta o si da excepcion, remove da false.
	//Add parameterized with list with stream.
	
	@Test
   	public void size_test() {
		List<Object> treemap = objIterator.getObjects();
		ListIterator it = treemap.listIterator();
		while(it.hasNext()) {
			TreeMap tmap = (TreeMap)it.next(); 	


			int key = ThreadLocalRandom.current().nextInt(0, literals + 1);

	    	int s = tmap.size();
	       	assertTrue(tmap.repOK());
			assertTrue((s==0 && tmap.isEmpty()) ||(s!=0 && !tmap.isEmpty()));

		}		


    }


	
}
