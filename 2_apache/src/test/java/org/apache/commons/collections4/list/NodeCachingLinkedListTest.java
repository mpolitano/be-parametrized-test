package org.apache.commons.collections4.list;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import utils.Config;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.stream.Stream;


public class NodeCachingLinkedListTest { 
    
	
	//Change with sedl 
		public static int scope;
		public static String pathFile;
		
	    @BeforeAll
	    static void initAll() {
	    	Config.readEnvironmentVariables();
	    	scope = Config.scope;
	    	pathFile = "beapi-tests/serialize/org.apache.commons.collections4.list.NodeCachingLinkedList/"+scope+"/objects.ser";

	    }
	
	
	    
	
	@ParameterizedTest
	@MethodSource("provide_NCL_Parameters")
	public void clear_test(NodeCachingLinkedList list) {
		list.clear();
		assertTrue(list.repOK());
	 }
	
	
	@ParameterizedTest
	@MethodSource("provide_NCL_Int_Parameters")
	public void add_test(NodeCachingLinkedList list, Integer i) {
		int oldSize = list.size();
		list.add(i);
		assertTrue(list.repOK());
		assertTrue(list.contains(i));
		assertTrue(list.size()== oldSize +1);

	 }

	
	
	@ParameterizedTest
	@MethodSource("provide_NCL_Int_Parameters")
	public void get_test(NodeCachingLinkedList list, Integer i) {
		assumeTrue(i<list.size());
		
		Integer o = (Integer)list.get(i);
		assertTrue(list.repOK());
		assertTrue(list.contains(o));

	 }
	
	
	
	
	@ParameterizedTest
	@MethodSource("provide_NCL_Parameters")
	public void size_test(NodeCachingLinkedList list) {
		
		Integer o = (Integer)list.size();
		assertTrue(list.repOK());

	}
	

	@ParameterizedTest
	@MethodSource("provide_NCL_Parameters")
	public void empty_test(NodeCachingLinkedList list) {
		
		boolean o = list.isEmpty();
		assertTrue(list.repOK());

	}
	

	@ParameterizedTest
	@MethodSource("provide_NCL_Int_Parameters")
	public void index_of_test(NodeCachingLinkedList list, Integer i) {	
		boolean o = list.isEmpty();
		assertTrue(list.repOK());

	}
	
	
	//org.apache.commons.collections4.list.AbstractLinkedList.contains(java.lang.Integer)
	@ParameterizedTest
	@MethodSource("provide_NCL_Int_Parameters")
	public void contains_test(NodeCachingLinkedList list, Integer i) {	
		boolean b = list.contains(i);
		assertTrue(list.repOK());

	}
	
	//org.apache.commons.collections4.list.AbstractLinkedList.getFirst()
	@ParameterizedTest
	@MethodSource("provide_NCL_Parameters")
	public void get_first_test(NodeCachingLinkedList list) {	
		assumeTrue(list.size()>0);
		
		Integer b = list.getFirst();
		assertTrue(list.repOK());
		assertTrue(list.contains(b));

	}
	//org.apache.commons.collections4.list.AbstractLinkedList.getLast()
	@ParameterizedTest
	@MethodSource("provide_NCL_Parameters")
	public void get_last_test(NodeCachingLinkedList list) {	
		assumeTrue(list.size()>0);

		Integer b = list.getLast();
		assertTrue(list.repOK());
		assertTrue(list.contains(b));

	}
	
	
	 
	
	//org.apache.commons.collections4.list.AbstractLinkedList.addFirst(java.lang.Integer)
	@ParameterizedTest
	@MethodSource("provide_NCL_Int_Parameters")
	public void add_first_test(NodeCachingLinkedList list, Integer i) {
		int oldSize = list.size();
		list.addFirst(i);
		assertTrue(list.repOK());
		assertTrue(list.contains(i));
		assertTrue(list.size()== oldSize +1);

	 }
	
	
	
	//org.apache.commons.collections4.list.AbstractLinkedList.addLast(java.lang.Integer)
	@ParameterizedTest
	@MethodSource("provide_NCL_Int_Parameters")
	public void add_last_test(NodeCachingLinkedList list, Integer i) {
		int oldSize = list.size();
		list.addLast(i);
		assertTrue(list.repOK());
		assertTrue(list.contains(i));
		assertTrue(list.size()== oldSize +1);

	 }
	
	//org.apache.commons.collections4.list.NodeCachingLinkedList.getMaximumCacheSize()
	@ParameterizedTest
	@MethodSource("provide_NCL_Parameters")
	public void getMaximumCacheSize_test(NodeCachingLinkedList list) {	

		Integer b = list.getMaximumCacheSize();
		assertTrue(list.repOK());

	}
	
	//org.apache.commons.collections4.list.NodeCachingLinkedList.isCacheFull()
	@ParameterizedTest
	@MethodSource("provide_NCL_Parameters")
	public void cache_full_test(NodeCachingLinkedList list) {	

		boolean b = list.isCacheFull();
		assertTrue(list.repOK());
	
	}
	
	
	//org.apache.commons.collections4.list.AbstractLinkedList.lastIndexOf(java.lang.Integer)
	@ParameterizedTest
	@MethodSource("provide_NCL_Int_Parameters")
	public void lastIndexOf_test(NodeCachingLinkedList list, Integer i) {
		int index = list.lastIndexOf(i);
		assertTrue(list.repOK());
		assertTrue((list.contains(i) && index >=0) ||  (!list.contains(i) && index ==-1) );

	 }
	
	
	
	//org.apache.commons.collections4.list.AbstractLinkedList.remove(int)
	@ParameterizedTest
	@MethodSource("provide_NCL_Int_Parameters")
	public void remove_test(NodeCachingLinkedList list, Integer i) {
		int oldSize = list.size();
		boolean b = list.remove(i);
		assertTrue(list.repOK());
		assertTrue((b && list.size() == oldSize-1 ) || (!b && list.size() == oldSize));

	 }
	
	
	//org.apache.commons.collections4.list.AbstractLinkedList.equals(java.lang.Object)
		//org.apache.commons.collections4.list.AbstractLinkedList.add(int,java.lang.Integer)
	    //org.apache.commons.collections4.list.AbstractLinkedList.iterator()
		//org.apache.commons.collections4.list.AbstractLinkedList.listIterator()
		//org.apache.commons.collections4.list.AbstractLinkedList.listIterator(int)

	//org.apache.commons.collections4.list.AbstractLinkedList.remove(java.lang.Integer)
//org.apache.commons.collections4.list.NodeCachingLinkedList.removeAllNodes()
//org.apache.commons.collections4.list.AbstractLinkedList.removeFirst()
//org.apache.commons.collections4.list.AbstractLinkedList.removeLast()
//
//org.apache.commons.collections4.list.NodeCachingLinkedList.repOK()
//org.apache.commons.collections4.list.AbstractLinkedList.set(int,java.lang.Integer)
//org.apache.commons.collections4.list.NodeCachingLinkedList.setMaximumCacheSize(int)
//org.apache.commons.collections4.list.NodeCachingLinkedList.shrinkCacheToMaximumSize()
//org.apache.commons.collections4.list.AbstractLinkedList.toArray()
//org.apache.commons.collections4.list.AbstractLinkedList.toArray([Ljava.lang.Integer;)
//org.apache.commons.collections4.list.NodeCachingLinkedList.toString()

	   
	  
	
	
	
	
	
	
	
	
	/*
	 * Providers..
	 */
	
	
	private static Stream<Arguments> provide_NCL_Parameters() {
	    	Stream<Arguments> stream = Stream.empty();
		  	FileInputStream fileTestUnit;
	    	ObjectInputStream ois;
			try {
				fileTestUnit= new FileInputStream(pathFile);
				ois = new ObjectInputStream(fileTestUnit);
			
				NodeCachingLinkedList ncl = (NodeCachingLinkedList)nextObject(ois);
				while(ncl != null){
					stream = Stream.concat(Stream.of(Arguments.of(ncl)), stream);
					ncl = (NodeCachingLinkedList)nextObject(ois);
					
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
   		 catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
  		}
	  	return stream;
	  }
	
	
	private static Stream<Arguments> provide_NCL_Int_Parameters() {
	  	Stream<Arguments> stream = Stream.empty();
	   	
		  	FileInputStream fileTestUnit;
	    	ObjectInputStream ois;
	    	for(int i = 1; i <scope; i++) {
					try {
						fileTestUnit= new FileInputStream(pathFile);
						ois = new ObjectInputStream(fileTestUnit);
					
						NodeCachingLinkedList list = (NodeCachingLinkedList)nextObject(ois);
						
						while(list != null){
							stream = Stream.concat(Stream.of(Arguments.of(list,i)), stream);
							list = (NodeCachingLinkedList)nextObject(ois);
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
	  	return stream;
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
    
}
