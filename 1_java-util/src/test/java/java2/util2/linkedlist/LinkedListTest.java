package java2.util2.linkedlist;

import utils.ObjectsIterator;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java2.util2.NoSuchElementException;
import java2.util2.hashmap.HashMap;
import java2.util2.linkedlist.LinkedList;

import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Stream;


public class LinkedListTest { 
    
	//Change with sedl 
	public static int literals;
	private static ObjectsIterator objIterator;
//	private static clazz = "java2.util2.linkedlist.LinkedList";


	
	@BeforeEach
	public void init() {
		objIterator = new ObjectsIterator("java2.util2.linkedlist.LinkedList");
		literals = objIterator.getLiterals();
	}
	
//	Aft
//	public void end() {
//		objIterator = new ObjectsIterator("java2.util2.linkedlist.LinkedList");
//	}
//	
	@AfterAll
    static void afterAll() {
		objIterator.end("java2.util2.linkedlist.LinkedList");
	}
//	
	@Test
	public void addTest() {			
		LinkedList list = (LinkedList)objIterator.next(); 
		while(list != null){
			objIterator.addCountTest();
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
			
			assertTrue(list.repOK());

			list = (LinkedList)objIterator.next();
		}
	}

	@Test
	public void iterTest() {
		LinkedList list = (LinkedList)objIterator.next(); 
		java2.util2.ListIterator result1 =null;
		java2.util2.ListIterator result=null;
		while(list != null){
			objIterator.addCountTest();
			int i = ThreadLocalRandom.current().nextInt(-2, literals + 1);
			try {	
				result =list.listIterator(i);
				result1 =list.listIterator();

			} catch (IndexOutOfBoundsException e) {
			}
			assertTrue(list.repOK());
			assertTrue(result!=null||!list.contains(i));
			assertTrue(result1!=null||!list.contains(i));
;
//			org.junit.Assert.assertNotNull(result1);

			list = (LinkedList)objIterator.next();

		}
	 }
//
	@Test
	public void addTest1() {
		LinkedList list = (LinkedList)objIterator.next(); 
		Object last = null;
		while(list != null){
			int i = ThreadLocalRandom.current().nextInt(-2, literals);
			Integer j = ThreadLocalRandom.current().nextInt(-2, literals);
			LinkedList before = list;
			int oldSize = 0;
			try {
				objIterator.addCountTest();
				oldSize = list.size();
				list.add(i,j);
			} catch (IndexOutOfBoundsException e) {
			}			
			assertTrue((list.size() == oldSize+1 && list.contains(j)|| (list.size() == oldSize)));
			assertTrue(((before.size() != i) )||( before.size() == i && list.getFirst() == j));
			assertTrue(list.repOK());
			list = (LinkedList)objIterator.next(); 
		}
	}
		
	
	@Test
	public void add_first_Test() {
		LinkedList list = (LinkedList)objIterator.next(); 
		while(list != null){
			int i = ThreadLocalRandom.current().nextInt(0, literals + 1);
			objIterator.addCountTest();
			int oldSize = list.size();
			list.addFirst(i);
			assertTrue(list.size() == oldSize+1);
			assertTrue(list.repOK());
			list = (LinkedList)objIterator.next(); 
		}
	 }
	
	@Test
	public void indexOf_Test() {
		LinkedList list = (LinkedList)objIterator.next(); 
		while(list != null) {
			objIterator.addCountTest();
			int i = ThreadLocalRandom.current().nextInt(-2, literals + 1);
	    	boolean containsPrevRemove = list.contains(i);
			int result1=list.indexOf(i);

			assertTrue(list.repOK());
			assertTrue((result1>=0 && containsPrevRemove ) || result1==-1 && !containsPrevRemove ) ;

			list = (LinkedList)objIterator.next(); 
		}
	 }
	
	@Test
	public void lastIndexOf_Test() {
		LinkedList list = (LinkedList)objIterator.next(); 
		while(list != null) {
			objIterator.addCountTest();
			int i = ThreadLocalRandom.current().nextInt(-2, literals + 1);
	    	boolean containsPrevRemove = list.contains(i);
			int result1=list.lastIndexOf(i);

			assertTrue(list.repOK());
			assertTrue((result1>=0 && containsPrevRemove ) || result1==-1 && !containsPrevRemove ) ;

			list = (LinkedList)objIterator.next(); 
		}
	 }
	
	@Test
	public void add_last_Test() {
		LinkedList list = (LinkedList)objIterator.next(); 
		while(list != null){
			objIterator.addCountTest();
			int i = ThreadLocalRandom.current().nextInt(0, literals + 1);
			list.addLast(i);
			assertTrue(list.repOK());
			list = (LinkedList)objIterator.next(); 
		}
	 }
//	
	@Test
	public void clear_Test() {
		LinkedList list = (LinkedList)objIterator.next(); 
		while(list != null){
			objIterator.addCountTest();
			list.clear();
			assertTrue(list.size() == 0);
			assertTrue(list.repOK());
			list = (LinkedList)objIterator.next(); 
		}
	 }
//	
	@Test
	public void clone_Test() {
		LinkedList list = (LinkedList)objIterator.next(); 
		while(list != null){
			objIterator.addCountTest();
			LinkedList list1 = (LinkedList) list.clone();
			assertTrue(list1.repOK());
			assertTrue(list.repOK());
			list = (LinkedList)objIterator.next(); 
		}
	 }
	
	@Test
	public void contains_Test() {
		LinkedList list = (LinkedList)objIterator.next(); 
		while(list != null){
			objIterator.addCountTest();

			int i = ThreadLocalRandom.current().nextInt(0, literals + 1);
			boolean result=list.contains(i);
	    	assertTrue(list.repOK());
	    	assertTrue(result && list.indexOf(i) != -1|| !result && list.indexOf(i) == -1);
	    	list = (LinkedList)objIterator.next();
		}
		}
	
	@Test
   	public void equals_test() {
		ObjectsIterator objIterator = new ObjectsIterator("java2.util2.linkedlist.LinkedList");
		ObjectsIterator objIterator1 = new ObjectsIterator("java2.util2.linkedlist.LinkedList");
		LinkedList list = (LinkedList)objIterator.next(); 
		LinkedList list1 = (LinkedList)objIterator1.next(); 
		while(list != null){
			while(list1 != null){
				objIterator.addCountTest();
				int i = ThreadLocalRandom.current().nextInt(0, literals + 1);
				list.equals(list1);
		    	assertTrue(list.repOK());
		    	assertTrue(list1.repOK());
				list = (LinkedList)objIterator.next(); 
				list1 = (LinkedList)objIterator1.next(); 
			}
		}
	}

	@Test
   	public void get_test() {
		LinkedList list = (LinkedList)objIterator.next(); 
		while(list != null){
			int i = ThreadLocalRandom.current().nextInt(-1, literals + 1);
			try {
				objIterator.addCountTest();
				list.get(i);
			}catch(IndexOutOfBoundsException e) {
		    	assertTrue(list.repOK());
			}
	    	assertTrue(list.repOK());
		
			list = (LinkedList)objIterator.next(); 
		}
    }

	@Test
   	public void getFirst_test() {
		LinkedList list = (LinkedList)objIterator.next(); 
		int index = 0;
		Object obj = null;
		int size;
		while(list != null){		
			objIterator.addCountTest();
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
				    	assertTrue(list.repOK());
					}catch(NoSuchElementException e) {
					}
				}
		list = (LinkedList)objIterator.next(); 
		}
    }
//	
   	@Test
   	public void getLast_test() {
		LinkedList list = (LinkedList)objIterator.next(); 
		int index = 0;
		Object obj = null;
		int size;
		while(list != null){		
			objIterator.addCountTest();
				if(list.size()>0) {
					try {
						size = list.size();
						index = list.indexOf(size-1);
						obj=list.getLast();
				    	assertTrue(list.contains(obj));
					}catch(NoSuchElementException e) {
		            // org.junit.Assert.fail("Expected exception of type java2.util2.NoSuchElementException; message: null");
					}
				}
				else {
					try {
						obj=list.getLast();
				    	assertTrue(list.repOK());
					}catch(NoSuchElementException e) {
					}

				}
		list = (LinkedList)objIterator.next(); 
		}
    }
//	
   	@Test
   	public void addFirst_test() {
		LinkedList list = (LinkedList)objIterator.next(); 
		while(list != null){
			int i = ThreadLocalRandom.current().nextInt(0, literals + 1);
			objIterator.addCountTest();
			list.addFirst(i);
	    	assertTrue(list.repOK());
//			assumeTrue(list.contains(i));
			list = (LinkedList)objIterator.next(); 
		}
    }
//	
   	@Test
   	public void addLast_test() {
		LinkedList list = (LinkedList)objIterator.next(); 
		while(list != null){
			int i = ThreadLocalRandom.current().nextInt(0, literals + 1);
			objIterator.addCountTest();
			list.addLast(i);;
	    	assertTrue(list.repOK());
			// assumeTrue(list.contains(i));
	    
			list = (LinkedList)objIterator.next(); 
		}	
	}
//		
//	
   	@Test
   	public void index_test() {
		LinkedList list = (LinkedList)objIterator.next(); 
			while(list != null){
				int i = ThreadLocalRandom.current().nextInt(0, literals + 1);
				objIterator.addCountTest();

		    	list.indexOf(i);
		    	list.indexOf(-1);

		    	assertTrue(list.repOK());
		    
				list = (LinkedList)objIterator.next(); 
			}
    }
//	
   	@Test
   	public void empty_test() {
		LinkedList list = (LinkedList)objIterator.next(); 
		while(list != null){
			objIterator.addCountTest();
	    	boolean result=list.isEmpty();
	    	assertTrue(list.repOK());
	    	assertTrue((result && list.size()==0)||(!result && list.size()>0));

			list = (LinkedList)objIterator.next(); 
		}

    }
//	
   	@Test
   	public void const_test() {
		objIterator.addCountTest();

    	LinkedList list1 = new LinkedList();
    	assertTrue(list1.repOK());
    }
//	
   	@Test
   	public void remove_test() {
		LinkedList list = (LinkedList)objIterator.next(); 
		while(list != null){
			boolean result = false;
			int size = -1;
			boolean result1= false; 
			LinkedList list1 = null;
			boolean containsPrevRemove = false;
			try {
				objIterator.addCountTest();
				size = list.size();
				Integer i = ThreadLocalRandom.current().nextInt(0, literals + 1);
				int indexToRemove = list.indexOf(i);
				System.out.println(list);
		    	containsPrevRemove = list.contains(i);

				result = list.remove(i);
//				list1= new LinkedList();
//				result1 = list1.remove(i);

//				System.out.println(list.get(indexToRemove).equals(i));		

//		        org.junit.Assert.assertTrue(indexToRemove != -1 && list.get(indexToRemove) != null? !list.get(indexToRemove).equals(i) : true );

		        java2.util2.linkedlist.LinkedList linkedList0 = new java2.util2.linkedlist.LinkedList();
		        boolean boolean2 = linkedList0.contains((java.lang.Object) (-1.0d));
		        boolean boolean4 = linkedList0.add((java.lang.Object) (short) 100);
		        java.lang.Object obj6 = linkedList0.remove(0);
		        boolean boolean7 = linkedList0.isEmpty();
		        // org.junit.Assert.assertTrue("'" + boolean4 + "' != '" + true + "'", boolean4 == true);
		        // org.junit.Assert.assertEquals("'" + obj6 + "' != '" + (short) 100 + "'", obj6, (short) 100);
		        // org.junit.Assert.assertTrue("'" + boolean7 + "' != '" + true + "'", boolean7 == true);

			} catch (IndexOutOfBoundsException e){
	            // org.junit.Assert.fail("Expected exception of type java2.util2.NoSuchElementException; message: null");
			}
	    	assertTrue(list.repOK());
			assertTrue((result && containsPrevRemove ) || !result && !containsPrevRemove ) ;

	    	assertTrue(size-1 == list.size() && result || size == list.size() && !result );

			list = (LinkedList)objIterator.next(); 
		}
	
    }
//	
   	@Test
   	public void remove_first_test() {
		LinkedList list = (LinkedList)objIterator.next(); 
		while(list != null){
			objIterator.addCountTest();
	    	if(list.size()>0) {
	    		Object first = list.get(0);
	    		int size = list.size();
	        	Object obj=list.removeFirst();
	        	assertTrue(list.repOK());
	        	assertTrue(size != list.size());

	        	assertTrue(first.equals(obj));
	    	}
	    	list = (LinkedList)objIterator.next(); 
		}
	}
//	
   	@Test
   	public void removeLast_test() {
    	LinkedList list = (LinkedList)objIterator.next(); 
		while(list != null){
		    	if(list.size()>0) {
					objIterator.addCountTest();
					int size = list.size();
		    		Object last = list.get(list.size()-1);
		        	Object obj=list.removeLast();
		        	assertTrue(list.repOK());
		        	assertTrue(last.equals(obj));
		        	assertTrue(list.size() == size-1);

		    	}
	    	list = (LinkedList)objIterator.next(); 
		}
		
		
    }
//	
	@Test
   	public void set_test() {
    	LinkedList list = (LinkedList)objIterator.next(); 
		while(list != null){
			objIterator.addCountTest();

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
	    	assertTrue(list.repOK());

	    	assertTrue((result == oldValue));
	    	assertTrue((result != null && objec == j) || (result == null));

	    	
    	list = (LinkedList)objIterator.next(); 

		 }	
    }
	
	@Test
   	public void set1_test() {
    	LinkedList list = (LinkedList)objIterator.next(); 
		while(list != null){

			int i = ThreadLocalRandom.current().nextInt(-2, literals + 1);
				Integer j = 0;
				Object result = null;
				Object objec = null;
				Object oldValue = null;
				Object resultTest = null;

			try {
				if(list.size() ==1) {
					oldValue = list.get(i);

					objIterator.addCountTest();
					 resultTest=list.set(0, -100);
				 }				 
				
			} catch (IndexOutOfBoundsException e){
			}
	    	assertTrue(list.repOK());

//	    	assertTrue((result == oldValue));
//	    	assertTrue((result != null && objec == j) || (result == null));
	    	assertTrue((resultTest == oldValue));

	    	
    	list = (LinkedList)objIterator.next(); 

		 }	
    }
	
//	
	@Test
   	public void size_test() {
    	LinkedList list = (LinkedList)objIterator.next(); 
		while(list != null){
			objIterator.addCountTest();
			list.size();
	    	assertTrue(list.repOK());
	    	list = (LinkedList)objIterator.next(); 
		}
    	
    }

	@Test
   	public void toarray_test() {
    	LinkedList list = (LinkedList)objIterator.next(); 

		while(list != null){
			objIterator.addCountTest();

	    	Object[] lArray = list.toArray();
	    	assertTrue(list.repOK());
	        // org.junit.Assert.assertNotNull(lArray);

	    	list = (LinkedList)objIterator.next(); 
		}
		
    }
//	
//	
//	
//	/*
//	 * Providers..
//	 */
//	
//
//	public static Object nextObject(ObjectInputStream ois) throws ClassNotFoundException, IOException {
//			try {
//				return ois.readObject();
//			} catch (EOFException eof) {
//				return null;
//			} catch (ClassNotFoundException e) {
//				throw e;
//			} catch (IOException e) {
//				throw e;
//			}
//		}    
    
}
