package java2.util2.linkedlist;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import utils.Reports;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumeTrue;
import org.junit.jupiter.api.Test;
import utils.TestHarness;
import java.util.List;
import java.util.ListIterator;
import java.util.concurrent.ThreadLocalRandom;

public class LinkedListTest extends TestHarness {

	@ParameterizedTest
	@MethodSource("readObjects")
	public void elementsDoesNotBelongToList(Object o) {
		LinkedList l = (LinkedList) o;
		int e = getInt(-1000, 1000);
		if (!l.contains(e)) {
			int oldSize = l.size();
			boolean res = l.add(e);
			assertTrue(res);
			assertTrue(l.contains(e));
			assertEquals(oldSize + 1, l.size());
		}
	}

	@ParameterizedTest
	@MethodSource("readObjects")
	public void elementsBelongToList(Object o) {
		LinkedList l = (LinkedList) o;
		if (l.isEmpty()) return;
		Object e = getElementFrom(l);
		if (l.contains(e)) {
			int oldSize = l.size();
			boolean res = l.add(e);
			assertFalse(res);
			assertFalse(l.contains(e));
			assertEquals(oldSize, l.size());
		}
	}


/*
	@Test
	public void addAll() {			
		List<Object> linkedlist = objIterator.getObjects();
		ListIterator it = linkedlist.listIterator();
		while(it.hasNext()) {
			LinkedList list = (LinkedList)it.next(); 	
			int oldSize = list.size();
			boolean result=list.addAll(list);
			assertTrue(list.size() != oldSize && result || list.size() == oldSize && !result);
		}
	}

	@Test
	public void addTest() {			
		List<Object> linkedlist = objIterator.getObjects();
		ListIterator it = linkedlist.listIterator();
		while(it.hasNext()) {
			LinkedList list = (LinkedList)it.next(); 

			Reports.addCountTest();
			int i = ThreadLocalRandom.current().nextInt(0, literals + 1);
			int oldSize = list.size();
			boolean result=list.add(i);

			assertTrue(list.size() == oldSize+1 && result || list.size() == oldSize && !result);
			oldSize = list.size();

			result=list.add(0);
			assertTrue(list.size() == oldSize+1 && result || list.size() == oldSize && !result);
			oldSize = list.size();
			
			LinkedList list1 = new LinkedList();
			try {
			result=list1.add(-1);
		} catch (IndexOutOfBoundsException e) {
            // org.junit.Assert.fail("Expected exception of type java2.util2.IndexOutOfBoundsException; message: null");
		}
			assertTrue(list1.size() == 1 && result || list1.size() == 0 && !result);
					}
	}

//
	@Test
	public void addTest1() {
		Object last = null;
		List<Object> linkedlist = objIterator.getObjects();
		ListIterator it = linkedlist.listIterator();
		while(it.hasNext()) {
			LinkedList list = (LinkedList)it.next(); 	

			int i = ThreadLocalRandom.current().nextInt(-2, literals);
			Integer j = ThreadLocalRandom.current().nextInt(-2, literals);
			LinkedList before = list;
			int oldSize = 0;
			try {
				Reports.addCountTest();
				oldSize = list.size();
				list.add(i,j);
			} catch (IndexOutOfBoundsException e) {
			}			
			assertTrue((list.size() == oldSize+1 && list.contains(j)|| (list.size() == oldSize)));
			assertTrue(((before.size() != i) )||( before.size() == i && list.getFirst() == j));
		}
	}
		
	
	@Test
	public void add_first_Test() {
		List<Object> linkedlist = objIterator.getObjects();
		ListIterator it = linkedlist.listIterator();
		while(it.hasNext()) {
			LinkedList list = (LinkedList)it.next(); 
			int i = ThreadLocalRandom.current().nextInt(0, literals + 1);
			Reports.addCountTest();
			int oldSize = list.size();
			list.addFirst(i);
			assertTrue(list.size() == oldSize+1);
		}
	 }
	
	@Test
	public void indexOf_Test() {
		List<Object> linkedlist = objIterator.getObjects();
		ListIterator it = linkedlist.listIterator();
		while(it.hasNext()) {
			LinkedList list = (LinkedList)it.next(); 
			Reports.addCountTest();
			int i = ThreadLocalRandom.current().nextInt(-2, literals + 1);
	    	boolean containsPrevRemove = list.contains(i);
			int result1=list.indexOf(i);

			assertTrue((result1>=0 && containsPrevRemove ) || result1==-1 && !containsPrevRemove ) ;
		}
	 }
	
	@Test
	public void lastIndexOf_Test() {
		List<Object> linkedlist = objIterator.getObjects();
		ListIterator it = linkedlist.listIterator();
		while(it.hasNext()) {
			LinkedList list = (LinkedList)it.next(); 
			if(list != null) {
				Reports.addCountTest();
				int i = ThreadLocalRandom.current().nextInt(-2, literals + 1);
		    	boolean containsPrevRemove = list.contains(i);
				int result1=list.lastIndexOf(i);
				assertTrue((result1>=0 && containsPrevRemove ) || result1==-1 && !containsPrevRemove ) ;
			}
	 }
	}
	
	@Test
	public void add_last_Test() {
		List<Object> linkedlist = objIterator.getObjects();
		ListIterator it = linkedlist.listIterator();
		while(it.hasNext()) {
			LinkedList list = (LinkedList)it.next(); 
			Reports.addCountTest();
			int i = ThreadLocalRandom.current().nextInt(0, literals + 1);
			list.addLast(i);
		}
	 }
//	
	@Test
	public void clear_Test() {
		List<Object> linkedlist = objIterator.getObjects();
		ListIterator it = linkedlist.listIterator();
		while(it.hasNext()) {
			LinkedList list = (LinkedList)it.next(); 
			Reports.addCountTest();
			list.clear();
			assertTrue(list.size() == 0);
		}
	 }
//	
	@Test
	public void clone_Test() {
		List<Object> linkedlist = objIterator.getObjects();
		ListIterator it = linkedlist.listIterator();
		while(it.hasNext()) {
			LinkedList list = (LinkedList)it.next(); 
			Reports.addCountTest();
			LinkedList list1 = (LinkedList) list.clone();

		}
	 }
	
	@Test
	public void contains_Test() {
		List<Object> linkedlist = objIterator.getObjects();
		ListIterator it = linkedlist.listIterator();
		while(it.hasNext()) {
			LinkedList list = (LinkedList)it.next(); 
			Reports.addCountTest();

			int i = ThreadLocalRandom.current().nextInt(0, literals + 1);
			boolean result=list.contains(i);
	    	assertTrue(result && list.indexOf(i) != -1|| !result && list.indexOf(i) == -1);
		}
		}
	
	@Test
   	public void equals_test() {
		List<Object> linkedlist = objIterator.getObjects();
		ListIterator it = linkedlist.listIterator();
		ListIterator it2 = linkedlist.listIterator();

		while(it.hasNext()) {
			LinkedList list = (LinkedList)it.next(); 
			while(it2.hasNext()) {
				LinkedList list1 = (LinkedList)it2.next(); 
				Reports.addCountTest();
				int i = ThreadLocalRandom.current().nextInt(0, literals + 1);
				list.equals(list1);

			}
		}
	}

	@Test
   	public void get_test() {
		List<Object> linkedlist = objIterator.getObjects();
		ListIterator it = linkedlist.listIterator();
		while(it.hasNext()) {
			LinkedList list = (LinkedList)it.next(); 
			int i = ThreadLocalRandom.current().nextInt(-1, literals + 1);
			try {
				Reports.addCountTest();
				list.get(i);
			}catch(IndexOutOfBoundsException e) {
				continue;
			}
			//assert
		}
    }

	@Test
   	public void getFirst_test() {
		int index = 0;
		Object obj = null;
		int size;
		List<Object> linkedlist = objIterator.getObjects();
		ListIterator it = linkedlist.listIterator();
		while(it.hasNext()) {
			LinkedList list = (LinkedList)it.next(); 	
			Reports.addCountTest();
				if(list.size()>0) {
					try {
						size = list.size();
						index = list.indexOf(size-1);
						obj=list.getFirst();
				    	assertTrue(list.contains(obj));
					}catch(NoSuchElementException e) {
			            // org.junit.Assert.fail("Expected exception of type java2.util2.NoSuchElementException; message: null");
					}
				}
				else {
					try {
						obj=list.getFirst();
					}catch(NoSuchElementException e) {
				    	assertTrue(!list.contains(obj));
					}
				}
		}
    }
//	
   	@Test
   	public void getLast_test() {
		int index = 0;
		Object obj = null;
		int size;
		List<Object> linkedlist = objIterator.getObjects();
		ListIterator it = linkedlist.listIterator();
		while(it.hasNext()) {
			LinkedList list = (LinkedList)it.next(); 	
			Reports.addCountTest();
			if(list.size()>0) {
				try {
					size = list.size();
					index = list.indexOf(size-1);
					obj=list.getLast();
			    	assertTrue(list.contains(obj));
				}catch(NoSuchElementException e) {
			    	assertTrue(!list.contains(obj));

	            // org.junit.Assert.fail("Expected exception of type java2.util2.NoSuchElementException; message: null");
				}
			}
			else {
				try {
					obj=list.getLast();
				}catch(NoSuchElementException e) {
				}

				}
		}
    }
//	
   	@Test
   	public void addFirst_test() {
		List<Object> linkedlist = objIterator.getObjects();
		ListIterator it = linkedlist.listIterator();
		while(it.hasNext()) {
			LinkedList list = (LinkedList)it.next(); 
			int i = ThreadLocalRandom.current().nextInt(0, literals + 1);
			Reports.addCountTest();
			list.addFirst(i);
			assumeTrue(list.contains(i));
		}
    }
//	
   	@Test
   	public void addLast_test() {
		List<Object> linkedlist = objIterator.getObjects();
		ListIterator it = linkedlist.listIterator();
		while(it.hasNext()) {
			LinkedList list = (LinkedList)it.next(); 
			int i = ThreadLocalRandom.current().nextInt(0, literals + 1);
			Reports.addCountTest();
			list.addLast(i);;
			// assumeTrue(list.contains(i));
		}	
	}
//		
//	
   	@Test
   	public void index_test() {
		List<Object> linkedlist = objIterator.getObjects();
		ListIterator it = linkedlist.listIterator();
		while(it.hasNext()) {
			LinkedList list = (LinkedList)it.next(); 
			int i = ThreadLocalRandom.current().nextInt(0, literals + 1);
			Reports.addCountTest();

	    	list.indexOf(i);
	    	list.indexOf(-1);
		}
    }
//	
   	@Test
   	public void empty_test() {
		List<Object> linkedlist = objIterator.getObjects();
		ListIterator it = linkedlist.listIterator();
		while(it.hasNext()) {
			LinkedList list = (LinkedList)it.next(); 
			Reports.addCountTest();
	    	boolean result=list.isEmpty();
	    	assertTrue((result && list.size()==0)||(!result && list.size()>0));
		}

    }
//	
   	@Test
   	public void const_test1() {
   		Reports.addCountTest();
   	   LinkedList list1 = new LinkedList();
	   LinkedList list2 = new LinkedList(list1);

    }

       	@Test
   	public void const_test() {
   		Reports.addCountTest();
    	LinkedList list1 = new LinkedList();

    }
//	
   	@Test
   	public void remove_test() {
		List<Object> linkedlist = objIterator.getObjects();
		ListIterator it = linkedlist.listIterator();
		while(it.hasNext()) {
			LinkedList list = (LinkedList)it.next(); 
			boolean result = false;
			int size = -1;
			boolean result1= false; 
			LinkedList list1 = null;
			boolean containsPrevRemove = false;
			try {
				Reports.addCountTest();
				size = list.size();
				Integer i = ThreadLocalRandom.current().nextInt(0, literals + 1);
				int indexToRemove = list.indexOf(i);
		    	containsPrevRemove = list.contains(i);

				result = list.remove(i);
			} catch (IndexOutOfBoundsException e){
	            // org.junit.Assert.fail("Expected exception of type java2.util2.NoSuchElementException; message: null");
			}
			assertTrue((result && containsPrevRemove ) || !result && !containsPrevRemove ) ;

	    	assertTrue(size-1 == list.size() && result || size == list.size() && !result );

		}
	
    }
//	
   	@Test
   	public void remove_first_test() {
		List<Object> linkedlist = objIterator.getObjects();
		ListIterator it = linkedlist.listIterator();
		while(it.hasNext()) {
			LinkedList list = (LinkedList)it.next(); 
			Reports.addCountTest();
	    	if(list.size()>0) {
	    		Object first = list.get(0);
	    		int size = list.size();
	        	Object obj=list.removeFirst();
	        	assertTrue(size != list.size());

	        	assertTrue(first.equals(obj));
	    	}
	    	else{
				try {
					Object index = list.removeFirst();
				}catch(NoSuchElementException e) {
		            // org.junit.Assert.fail("Expected exception of type java2.util2.NoSuchElementException; message: null");
				}
			}
		}
	}

   	@Test
   	public void removeLast_test() {
		List<Object> linkedlist = objIterator.getObjects();
		ListIterator it = linkedlist.listIterator();
		while(it.hasNext()) {
			LinkedList list = (LinkedList)it.next(); 
	    	if(list.size()>0) {
	    		Reports.addCountTest();
				int size = list.size();
	    		Object last = list.get(list.size()-1);
	        	Object obj=list.removeLast();
	        	assertTrue(last.equals(obj));
	        	assertTrue(list.size() == size-1);

	    	}
		}
		
		
    }

	@Test
   	public void set_test() {
		List<Object> linkedlist = objIterator.getObjects();
		ListIterator it = linkedlist.listIterator();
		while(it.hasNext()) {
			LinkedList list = (LinkedList)it.next(); 
			Reports.addCountTest();

			int i = ThreadLocalRandom.current().nextInt(-2, literals + 1);
				Integer j = 0;
				Object result = null;
				Object objec = null;
				Object oldValue = null;

			try {
				oldValue = list.get(i);
				 result=list.set(i,j);
				 objec = list.get(i);
				 
				
			} catch (IndexOutOfBoundsException e){
			}

	    	assertTrue((result == oldValue));
	    	assertTrue((result != null && objec == j) || (result == null));
		 }	
    }
	
	@Test
   	public void set1_test() {
		List<Object> linkedlist = objIterator.getObjects();
		ListIterator it = linkedlist.listIterator();
		while(it.hasNext()) {
			LinkedList list = (LinkedList)it.next(); 
			int i = ThreadLocalRandom.current().nextInt(-2, literals + 1);
			Integer j = 0;
			Object result = null;
			Object objec = null;
			Object oldValue = null;
			Object resultTest = null;

			try {
				if(list.size() ==1) {
					oldValue = list.get(i);
					Reports.addCountTest();
					resultTest=list.set(0, -100);
				 }				 
				
			} catch (IndexOutOfBoundsException e){
			}
			assertTrue((resultTest == oldValue));

		 }	
    }
	
//	
	@Test
   	public void size_test() {
		List<Object> linkedlist = objIterator.getObjects();
		ListIterator it = linkedlist.listIterator();
		while(it.hasNext()) {
			LinkedList list = (LinkedList)it.next(); 
			Reports.addCountTest();
			list.size();
		}
    	
    }

	@Test
   	public void toarray_test() {
		List<Object> linkedlist = objIterator.getObjects();
		ListIterator it = linkedlist.listIterator();
		while(it.hasNext()) {
			LinkedList list = (LinkedList)it.next(); 
			Reports.addCountTest();

	    	Object[] lArray = list.toArray();
	        // org.junit.Assert.assertNotNull(lArray);
		}
		
    }

 */
}
