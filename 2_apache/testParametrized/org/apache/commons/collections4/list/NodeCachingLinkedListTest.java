package org.apache.commons.collections4.list;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import utils.Config;
import utils.ObjectsIterator;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

import java.util.Random;


public class NodeCachingLinkedListTest { 


	//Change with sedl 
	public static int scope;
	public static String pathFile;

	//Change with sedl 
	public static int literals;
	private static ObjectsIterator objIterator;
//	private static clazz = "java2.util2.linkedlist.LinkedList";


	
	@BeforeEach
	public void init() {
		objIterator = new ObjectsIterator("org.apache.commons.collections4.list.NodeCachingLinkedList");
		literals = objIterator.getLiterals();
	}
	
//	Aft
//	public void end() {
//		objIterator = new ObjectsIterator("java2.util2.linkedlist.LinkedList");
//	}
//	
	@AfterAll
    static void afterAll() {
		objIterator.end("org.apache.commons.collections4.list.NodeCachingLinkedList");
	}


	@Test
	public void cont_test() {
		objIterator.addCountTest();
		NodeCachingLinkedList ncl = new NodeCachingLinkedList();
		ncl = new NodeCachingLinkedList(100);

	}
	
	@Test
	public void getNodeFromCache_test() {
		NodeCachingLinkedList ncl = (NodeCachingLinkedList)objIterator.next(); 

			while(ncl != null){
				objIterator.addCountTest();
				ncl.getNodeFromCache();
				assertTrue(ncl.repOK());
				ncl = (NodeCachingLinkedList)objIterator.next();
			}
	

	}
	
	@Test
	public void createNode_test() {

		NodeCachingLinkedList ncl = (NodeCachingLinkedList)objIterator.next(); 

		while(ncl != null){
			objIterator.addCountTest();
			Random r = new Random();
			int i = r.nextInt(literals);	

			ncl.createNode(i);
			assertTrue(ncl.repOK());
			ncl = (NodeCachingLinkedList)objIterator.next();
		}

	}
	
	@Test
	public void clear_test() {
		NodeCachingLinkedList ncl = (NodeCachingLinkedList)objIterator.next(); 
		while(ncl != null){
			objIterator.addCountTest();
			ncl.clear();
			assertTrue(ncl.repOK());
			ncl = (NodeCachingLinkedList)objIterator.next();
		}
	}


	@Test
	public void add_test() {
		NodeCachingLinkedList ncl = (NodeCachingLinkedList)objIterator.next(); 
		while(ncl != null){
			objIterator.addCountTest();
			Random r = new Random();
			int i = r.nextInt(literals);	

			int oldSize = ncl.size();
			ncl.add(i);
			assertTrue(ncl.repOK());
			assertTrue(ncl.contains(i));
			assertTrue(ncl.size()== oldSize +1);

			ncl = (NodeCachingLinkedList)objIterator.next();
		}

	}

	@Test
	public void get_test() {
		NodeCachingLinkedList ncl = (NodeCachingLinkedList)objIterator.next(); 

		while(ncl != null){
			objIterator.addCountTest();
			Random r = new Random();
			int i = r.nextInt(literals);	
			//assumeTrue(i<ncl.size());
			if(i<ncl.size()) {
				Object o = (Object)ncl.get(i);
				assertTrue(ncl.repOK());
				assertTrue(ncl.contains(o));

			}
			ncl = (NodeCachingLinkedList)objIterator.next();

		}
	}




	@Test
	public void size_test() {
		NodeCachingLinkedList ncl = (NodeCachingLinkedList)objIterator.next(); 
		while(ncl != null){
			objIterator.addCountTest();
			Integer o = (Integer)ncl.size();
			assertTrue(ncl.repOK());
			ncl = (NodeCachingLinkedList)objIterator.next();
		}
	}




	@Test
	public void empty_test() {
		NodeCachingLinkedList ncl = (NodeCachingLinkedList)objIterator.next(); 
		while(ncl != null){
			objIterator.addCountTest();
			boolean o = ncl.isEmpty();
			assertTrue(ncl.repOK());
			ncl = (NodeCachingLinkedList)objIterator.next();
		}
	}


//	@Test
//	public void index_of_test() {
//
//		FileInputStream fileTestUnit;
//		ObjectInputStream ois;
//		try {
//			fileTestUnit= new FileInputStream(pathFile);
//			ois = new ObjectInputStream(fileTestUnit);
//
//			NodeCachingLinkedList ncl = (NodeCachingLinkedList)nextObject(ois);
//			while(ncl != null){
//				count++;
//				Random r = new Random();
//				int value = r.nextInt(literals);	
//
//				Integer index = ncl.indexOf(value);
//				assertTrue(ncl.repOK());
//				assertTrue( (ncl.contains(value) && index >0 && index < ncl.size())||
//						(!ncl.contains(value) && index == -1));
//
//
//				ncl = (NodeCachingLinkedList)nextObject(ois);
//
//			}
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		catch (ClassNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}

//	}



	//org.apache.commons.collections4.list.AbstractLinkedList.contains(java.lang.Integer)

	@Test
	public void contains_test() {
		NodeCachingLinkedList ncl = (NodeCachingLinkedList)objIterator.next(); 
		while(ncl != null){
			objIterator.addCountTest();
			Random r = new Random();
			int value = r.nextInt(literals);	

			boolean b = ncl.contains(value);
			assertTrue(ncl.repOK());

			ncl = (NodeCachingLinkedList)objIterator.next();

		}
	}

	@Test
	public void getfirst_test() {

		NodeCachingLinkedList ncl = (NodeCachingLinkedList)objIterator.next(); 
		while(ncl != null){
			objIterator.addCountTest();
			if(ncl.size()>0) {
				Object b = ncl.getFirst();
				assertTrue(ncl.repOK());
				assertTrue(ncl.contains(b));
			}
			ncl = (NodeCachingLinkedList)objIterator.next();
		}
	}


	//org.apache.commons.collections4.list.AbstractLinkedList.getLast()
	@Test
	public void getlast_test() {
		NodeCachingLinkedList ncl = (NodeCachingLinkedList)objIterator.next(); 

		while(ncl != null){
			objIterator.addCountTest();
			if(ncl.size()>0) {
				Object b = ncl.getLast();
				assertTrue(ncl.repOK());
				assertTrue(ncl.contains(b));
			}
			ncl = (NodeCachingLinkedList)objIterator.next();
		}
	}

	//org.apache.commons.collections4.list.AbstractLinkedList.addFirst(java.lang.Integer)

	@Test
	public void addFirst_test() {

		NodeCachingLinkedList ncl = (NodeCachingLinkedList)objIterator.next(); 

		while(ncl != null){
			Random r = new Random();
			int value = r.nextInt(literals);
			
			objIterator.addCountTest();

			int oldSize = ncl.size();
			ncl.addFirst(value);
			assertTrue(ncl.repOK());
			assertTrue(ncl.contains(value));
			assertTrue(ncl.size()== oldSize +1);	
				
			ncl = (NodeCachingLinkedList)objIterator.next();

			}
	}

	
	//org.apache.commons.collections4.list.AbstractLinkedList.addLast(java.lang.Integer)

	@Test
	public void addLast_test() {

		NodeCachingLinkedList ncl = (NodeCachingLinkedList)objIterator.next(); 

		while(ncl != null){
			Random r = new Random();
			int value = r.nextInt(literals);
			System.out.println(ncl);
			objIterator.addCountTest();
			
			int oldSize = ncl.size();
			ncl.addLast(value);
			assertTrue(ncl.repOK());
			assertTrue(ncl.contains(value));
			assertTrue(ncl.size()== oldSize +1);
			
			
			ncl = (NodeCachingLinkedList)objIterator.next();

		}
	}
	
	
	
	//org.apache.commons.collections4.list.NodeCachingLinkedList.getMaximumCacheSize()

	@Test
	public void getMaximumCacheSize_test() {

		NodeCachingLinkedList ncl = (NodeCachingLinkedList)objIterator.next(); 

		while(ncl != null){
			objIterator.addCountTest();
			Integer b = ncl.getMaximumCacheSize();
			assertTrue(ncl.repOK());
			
			ncl = (NodeCachingLinkedList)objIterator.next();

		}
	}

	//org.apache.commons.collections4.list.NodeCachingLinkedList.isCacheFull()
	@Test
	public void isCacheFull_test() {
		NodeCachingLinkedList ncl = (NodeCachingLinkedList)objIterator.next(); 
		while(ncl != null){
			objIterator.addCountTest();
			
			boolean b = ncl.isCacheFull();
			assertTrue(ncl.repOK());

			ncl = (NodeCachingLinkedList)objIterator.next();

		}
	}
	
	//org.apache.commons.collections4.list.AbstractLinkedList.lastIndexOf(java.lang.Integer)
	@Test
	public void lastIndexOf_test() {

		NodeCachingLinkedList ncl = (NodeCachingLinkedList)objIterator.next(); 

		while(ncl != null){
			Random r = new Random();
			int value = r.nextInt(literals);
			
			objIterator.addCountTest();
			int index = ncl.lastIndexOf(value);
			assertTrue(ncl.repOK());
			assertTrue((ncl.contains(value) && index >=0) ||  (!ncl.contains(value) && index ==-1) );
					
			ncl = (NodeCachingLinkedList)objIterator.next();

			}
	}
	
	
	//org.apache.commons.collections4.list.AbstractLinkedList.remove(int)
	@Test
	public void remove_index_test() {

		NodeCachingLinkedList ncl = (NodeCachingLinkedList)objIterator.next(); 

		while(ncl != null){
			Random r = new Random();
			int index = r.nextInt(literals);
			objIterator.addCountTest();
			if(index > 0 && index < ncl.size()) {
				int oldSize = ncl.size();
				Object b = ncl.remove(index);
				assertTrue(ncl.repOK());
				assertTrue(ncl.size() == oldSize-1);

			}
			ncl = (NodeCachingLinkedList)objIterator.next();

		}
	}

	
	//org.apache.commons.collections4.list.AbstractLinkedList.remove(java.lang.Integer)
	
	@Test
	public void remove_value_test() {

		NodeCachingLinkedList ncl = (NodeCachingLinkedList)objIterator.next(); 

		while(ncl != null){
			Random r = new Random();
			int value = r.nextInt(literals);
			objIterator.addCountTest();
			int oldSize = ncl.size();
			boolean b = ncl.remove(new Integer(value));
			assertTrue(ncl.repOK());
			assertTrue((b && ncl.size() == oldSize-1) || (!b && ncl.size() == oldSize));

			ncl = (NodeCachingLinkedList)objIterator.next();

		}
	}

	//org.apache.commons.collections4.list.AbstractLinkedList.removeFirst()
	@Test
	public void removeFirst_test() {

		NodeCachingLinkedList ncl = (NodeCachingLinkedList)objIterator.next(); 

		while(ncl != null){
			Random r = new Random();
			int value = r.nextInt(literals);
			
			objIterator.addCountTest();

			if(ncl.size() > 0) {
				int oldSize = ncl.size();
				Object b = ncl.removeFirst();
				assertTrue(ncl.repOK());
				assertTrue(ncl.size() == oldSize-1);
			}
			
			ncl = (NodeCachingLinkedList)objIterator.next();

		}
	}
	
	
	

	//org.apache.commons.collections4.list.AbstractLinkedList.removeLast()
	
	@Test
	public void removeLast_test() {
		NodeCachingLinkedList ncl = (NodeCachingLinkedList)objIterator.next(); 

		while(ncl != null){
			
			objIterator.addCountTest();

			if(ncl.size() > 0) {
				int oldSize = ncl.size();
				Object b = ncl.removeLast();
				assertTrue(ncl.repOK());
				assertTrue(ncl.size() == oldSize-1);
			}
			
			ncl = (NodeCachingLinkedList)objIterator.next();

		}
	}
	

	//org.apache.commons.collections4.list.AbstractLinkedList.add(int,java.lang.Integer)
	@Test
	public void add_on_position_test() {

		NodeCachingLinkedList ncl = (NodeCachingLinkedList)objIterator.next(); 

		while(ncl != null){
			Random r = new Random();
			int value = r.nextInt(literals);
			
			int index = r.nextInt(literals);
			objIterator.addCountTest();

			if(index >0 && index <= ncl.size()) {
				int oldSize = ncl.size();
				ncl.add(index, value);
				assertTrue(ncl.repOK());
				assertTrue(ncl.size() == oldSize+1);
			}
			
			ncl = (NodeCachingLinkedList)objIterator.next();

		}
	}
	
	
	//org.apache.commons.collections4.list.AbstractLinkedList.set(int,java.lang.Integer)
	@Test
	public void set_test() {
		NodeCachingLinkedList ncl = (NodeCachingLinkedList)objIterator.next(); 

		while(ncl != null){
			Random r = new Random();
			int value = r.nextInt(literals);
			
			int index = r.nextInt(literals);
			
			objIterator.addCountTest();

			if(index >0 && index < ncl.size()) {
				int oldSize = ncl.size();
				ncl.set(index, value);
				assertTrue(ncl.repOK());
				assertTrue(ncl.size() == oldSize);
				assertTrue(ncl.contains(value));
			}
			
			ncl = (NodeCachingLinkedList)objIterator.next();

		}
	}
	
	//org.apache.commons.collections4.list.NodeCachingLinkedList.removeAllNodes()
	@Test
	public void removeAllNodes_test() {

		NodeCachingLinkedList ncl = (NodeCachingLinkedList)objIterator.next(); 

		while(ncl != null){
			objIterator.addCountTest();
			ncl.removeAllNodes();
			assertTrue(ncl.repOK());
			assertTrue(ncl.isEmpty());
			
			ncl = (NodeCachingLinkedList)objIterator.next();

		}
	}

	
	
	//org.apache.commons.collections4.list.NodeCachingLinkedList.setMaximumCacheSize(int)
	@Test
	public void setMaximumCacheSize_test() {

		NodeCachingLinkedList ncl = (NodeCachingLinkedList)objIterator.next(); 

		while(ncl != null){
			Random r = new Random();
			int value = r.nextInt(literals);
			
			objIterator.addCountTest();

			ncl.setMaximumCacheSize(value);
			assertTrue(ncl.repOK());
		
			ncl = (NodeCachingLinkedList)objIterator.next();

		}
	}
	
	//org.apache.commons.collections4.list.AbstractLinkedList.toArray()
	
	@Test
	public void toArray_test() {

		NodeCachingLinkedList ncl = (NodeCachingLinkedList)objIterator.next(); 

		while(ncl != null){
			objIterator.addCountTest();
			int size = ncl.size();
			Object [] a = ncl.toArray();
			assertTrue(ncl.repOK());
			assertTrue(a.length == size);
			
			ncl = (NodeCachingLinkedList)objIterator.next();

		}
	}
	
	
	//org.apache.commons.collections4.list.AbstractLinkedList.toArray([Ljava.lang.Integer;)

	@Test
	public void toArray2_test() {

		NodeCachingLinkedList ncl = (NodeCachingLinkedList)objIterator.next(); 

		while(ncl != null){
			objIterator.addCountTest();
			int size = ncl.size();
			Object [] a = new Object[ncl.size()];
			a = ncl.toArray(a);
			assertTrue(ncl.repOK());
			assertTrue(a.length == size);
			
			ncl = (NodeCachingLinkedList)objIterator.next();

		}
	}
	
	//org.apache.commons.collections4.list.NodeCachingLinkedList.shrinkCacheToMaximumSize()
	@Test
	public void shrinkCacheToMaximumSize_test() {

		NodeCachingLinkedList ncl = (NodeCachingLinkedList)objIterator.next(); 

		while(ncl != null){
			objIterator.addCountTest();
			ncl.shrinkCacheToMaximumSize();
			assertTrue(ncl.repOK());

			ncl = (NodeCachingLinkedList)objIterator.next();
		}
	
	}
	

	
	//org.apache.commons.collections4.list.AbstractLinkedList.iterator()
	@Test
	public void Iterator_test() {

		NodeCachingLinkedList ncl = (NodeCachingLinkedList)objIterator.next(); 

		while(ncl != null){
			
			objIterator.addCountTest();
			java.util.Iterator i = ncl.iterator();
			assertTrue(ncl.repOK());

			ncl = (NodeCachingLinkedList)objIterator.next();

		}
	}
	
	
	
	//org.apache.commons.collections4.list.AbstractLinkedList.listIterator()
	@Test
	public void ListIterator_test() {

		NodeCachingLinkedList ncl = (NodeCachingLinkedList)objIterator.next(); 
	
		while(ncl != null){
			objIterator.addCountTest();
			java.util.ListIterator i = ncl.listIterator();
			assertTrue(ncl.repOK());

			ncl = (NodeCachingLinkedList)objIterator.next();

		}
	}
	
	
	
	//org.apache.commons.collections4.list.AbstractLinkedList.listIterator(int)
	@Test
	public void ListIterator_Int_test() {
		NodeCachingLinkedList ncl = (NodeCachingLinkedList)objIterator.next(); 

		while(ncl != null){
			
			Random r = new Random();
			int i = r.nextInt(literals);
			
			objIterator.addCountTest();
			if(i<ncl.size()) {
				java.util.ListIterator listIter = ncl.listIterator(i);
				assertTrue(ncl.repOK());
			}
			ncl = (NodeCachingLinkedList)objIterator.next();

		}
	}
	

}
