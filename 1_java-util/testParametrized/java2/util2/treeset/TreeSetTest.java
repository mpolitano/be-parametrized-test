package java2.util2.treeset;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java2.util2.linkedlist.LinkedList;
import utils.Config;
import utils.ObjectsIterator;

import static org.junit.jupiter.api.Assertions.assertTrue;


import java.util.List;
import java.util.ListIterator;
import java.util.concurrent.ThreadLocalRandom;


public class TreeSetTest { 
    
	
	public static int literals;
	private static ObjectsIterator objIterator;
//	private static clazz = "java2.util2.linkedlist.LinkedList";


	
	@BeforeEach
	public void init() {
		objIterator = new ObjectsIterator("java2.util2.treeset.TreeSet");
		literals = objIterator.getLiterals();
	}
	
//	Aft
//	public void end() {
//		objIterator = new ObjectsIterator("java2.util2.linkedlist.LinkedList");
//	}
//	
	@AfterAll
    static void afterAll() {
		objIterator.end("java2.util2.treeset.TreeSet");
	}

		@Test
		public void constructro_Test() {
			TreeSet tset = new TreeSet();
			assertTrue(tset.repOK());
		}


		@Test
		public void add_Test() {
			List<Object> treeset = objIterator.getObjects();
			ListIterator it = treeset.listIterator();
			while(it.hasNext()) {					
				objIterator.addCountTest();
				TreeSet tset = (TreeSet)it.next();
				int i = ThreadLocalRandom.current().nextInt(0, literals + 1);
				int oldSize = tset.size();
				boolean b = false;
				try {
					b = tset.contains(i);
					tset.add(i);
				}catch (NullPointerException|ClassCastException e){
					continue;
				}
				assertTrue((!b && tset.size() == oldSize+1) ||(b && tset.size() == oldSize));
				assertTrue(tset.repOK());
				assertTrue(tset.contains(i));
			}	
	 }
	
		@Test
		public void clear_Test() {
			List<Object> treeset = objIterator.getObjects();
			ListIterator it = treeset.listIterator();
			while(it.hasNext()) {					
				objIterator.addCountTest();
				TreeSet tset = (TreeSet)it.next();
				tset.clear();
				assertTrue(tset.size() == 0);
				assertTrue(tset.repOK());	
			}
		}
	
	
	
		@Test
	public void comparator_Test() {
		List<Object> treeset = objIterator.getObjects();
		ListIterator it = treeset.listIterator();
		while(it.hasNext()) {					
			objIterator.addCountTest();
			TreeSet tset = (TreeSet)it.next();
			tset.comparator();
			assertTrue(tset.repOK());
		}
	}
	
	@Test
	public void contain_Test() {
			List<Object> treeset = objIterator.getObjects();
			ListIterator it = treeset.listIterator();
			while(it.hasNext()) {					
				objIterator.addCountTest();
				TreeSet tset = (TreeSet)it.next();
				int i = ThreadLocalRandom.current().nextInt(0, literals + 1);
				boolean contains=false;
				try {
					contains=tset.contains(i);
				}catch (NullPointerException|ClassCastException e){
					continue;
				}
				assertTrue(tset.repOK());
				assertTrue(contains && tset.size()>0 || !contains );
				

			}


	 }

	@Test
	public void LastExce_Test() {
		List<Object> treeset = objIterator.getObjects();
		ListIterator it = treeset.listIterator();
		while(it.hasNext()) {		
			TreeSet tset = (TreeSet)it.next();
			if(tset.size()<1) {
				boolean result = false;
				objIterator.addCountTest();
				try {
					Object k = (Object) tset.last();
				}
				catch(java2.util2.NoSuchElementException e) {
					result = true;
				}
		    	assertTrue(result);
			}
		}
	 }
	
	
	@Test
	public void firstExce_Test() {
		List<Object> treeset = objIterator.getObjects();
		ListIterator it = treeset.listIterator();
		while(it.hasNext()) {		
			TreeSet tset = (TreeSet)it.next();
			if(tset.size()<1) {
				boolean result = false;
				objIterator.addCountTest();
				try {
					Object k = (Object) tset.first();
				}
				catch(java2.util2.NoSuchElementException e) {
					result = true;
				}
		    	assertTrue(result);
		    }
		}
	 }
	
	@Test
	public void first_Test() {
		List<Object> treeset = objIterator.getObjects();
		ListIterator it = treeset.listIterator();
		while(it.hasNext()) {	
			TreeSet tset = (TreeSet)it.next();
			if(tset.size()>1) {
				objIterator.addCountTest();
				Object k = (Object) tset.first();
		    	assertTrue(tset.repOK());
		    	assertTrue(tset.contains(k) == true);
			}
		}
	 }
	
	@Test
	public void empty_Test() {
		List<Object> treeset = objIterator.getObjects();
		ListIterator it = treeset.listIterator();
		while(it.hasNext()) {	
			TreeSet tset = (TreeSet)it.next();
			boolean p = tset.isEmpty();
	    	assertTrue(tset.repOK());
	    	assertTrue((p ==true && tset.size() ==0) || (p ==false && tset.size() !=0));
		}
    }
	
	@Test
   	public void size_test() {
		List<Object> treeset = objIterator.getObjects();
		ListIterator it = treeset.listIterator();
		while(it.hasNext()) {					
			objIterator.addCountTest();
			TreeSet tset = (TreeSet)it.next();
			int s = tset.size();
	       	assertTrue(tset.repOK());
	       	assertTrue((s>0&& !tset.isEmpty()) || (s==0 && tset.isEmpty()));
		}
	
    }
//	
	@Test
   	public void last_test() {
		List<Object> treeset = objIterator.getObjects();
		ListIterator it = treeset.listIterator();
		while(it.hasNext()) {		
			TreeSet tset = (TreeSet)it.next();
			if(tset.size()>1) {
				objIterator.addCountTest();
				Object k = (Object) tset.last();
		    	assertTrue(tset.repOK());
		    	assertTrue(tset.contains(k) == true);
			}
		}	
    }
	
	@Test
   	public void remove_test() {
		List<Object> treeset = objIterator.getObjects();
		ListIterator it = treeset.listIterator();
		while(it.hasNext()) {					
			objIterator.addCountTest();
			TreeSet tset = (TreeSet)it.next();
			int i = ThreadLocalRandom.current().nextInt(0, literals + 1);
			boolean result = false;
			try{
				result=tset.remove(i);
			}catch (NullPointerException|ClassCastException e){
				continue;
			}
	    	assertTrue(tset.repOK());
	    	assertTrue((!tset.contains(i) && result) || (!result && tset.contains(i)||  (!result && !tset.contains(i))));

		}
	}   
}
