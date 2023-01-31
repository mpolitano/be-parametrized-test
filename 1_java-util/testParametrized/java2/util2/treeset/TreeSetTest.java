package java2.util2.treeset;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java2.util2.linkedlist.LinkedList;
import utils.Config;
import utils.ObjectsIterator;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.concurrent.ThreadLocalRandom;


public class TreeSetTest { 
    
	
	public static int literals;
	private static ObjectsIterator objIterator;
	private static List<Object> pool = null;
	private static List objects;
	private static ListIterator it;

	public List<Object> getImput(ListIterator list) {
		List<Object> pool = new ArrayList<Object>();
		while (list.hasNext()) {
			TreeMap t=(TreeMap)list.next();
//			list.set(t);// wrong
			pool.add(t);
		}
		return pool;
	}

	@BeforeAll
	public static void init() {
		objIterator = new ObjectsIterator("java2.util2.treeset.TreeSet");
		literals = objIterator.getLiterals();
		objects = objIterator.getObjects();
	}
	
	@BeforeEach
	public void beforeEach() {
		it = objects.listIterator();
	}
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
		while(it.hasNext()) {					
			objIterator.addCountTest();
			TreeSet tset = (TreeSet)it.next();
			System.out.println(tset);
//				int i = ThreadLocalRandom.current().nextInt(0, literals + 1);
				int oldSize = tset.size();
				boolean b = false;
				objIterator.addCountTest();
				int value = ThreadLocalRandom.current().nextInt(0, 10);
				Object i = objects.get(1);
	//			int value = ThreadLocalRandom.current().nextInt(0, literals + 1);
//				int oldSize = tmap.size();
//				boolean b;
	
				b = tset.contains(i);
				boolean result=tset.add(i);
				
				assertTrue((!result && tset.size() == oldSize+1) ||(result && tset.size() == oldSize));
				assertTrue(tset.repOK());
				assertTrue(tset.contains(i));
			}	
		}
	 
	
		@Test
		public void clear_Test() {
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
			while(it.hasNext()) {		
			objIterator.addCountTest();
			TreeSet tset = (TreeSet)it.next();
			tset.comparator();
			assertTrue(tset.repOK());
		}
	}
	
	@Test
	public void contain_Test() {
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
		while(it.hasNext()) {		
			TreeSet tset = (TreeSet)it.next();
			if(tset.size()>1) {
				objIterator.addCountTest();
				Object k = null;
				try {
					k = (Object) tset.first();
				}
				catch(java2.util2.NoSuchElementException e) {
					continue;		
				}		    	
				assertTrue(tset.repOK());
		    	assertTrue(tset.contains(k) == true);
			}
		}
	 }
	
	@Test
	public void empty_Test() {
		while(it.hasNext()) {	
			TreeSet tset = (TreeSet)it.next();
			boolean p = tset.isEmpty();
	    	assertTrue(tset.repOK());
	    	assertTrue((p ==true && tset.size() ==0) || (p ==false && tset.size() !=0));
		}
    }
	
	@Test
   	public void size_test() {
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
		while(it.hasNext()) {		
			TreeSet tset = (TreeSet)it.next();
			if(tset.size()>1) {
				objIterator.addCountTest();
				Object k = null;

				try {
					k = (Object) tset.last();
				}
				catch(java2.util2.NoSuchElementException e) {
					continue;		
				}		    	
				assertTrue(tset.repOK());
		    	assertTrue(tset.contains(k) == true);
			}
		}	
    }
	
	@Test
   	public void remove_test() {
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

	@Test
   	public void toString() {
		while(it.hasNext()) {				
			objIterator.addCountTest();
			TreeSet tset = (TreeSet)it.next();
			boolean result = false;
			try{
				result=tset.toString();
			}catch (NullPointerException|ClassCastException e){
				continue;
			}
	    	assertTrue(tset.repOK());
		}
	}   

}
