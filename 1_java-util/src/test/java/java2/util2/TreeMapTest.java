package java2.util2;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.stream.Stream;


public class TreeMapTest { 
    
	//Change with sed
	public static int scope = 3;
	public static String pathFile = "beapi-tests/java2.util2.TreeMap/"+scope+"/objects.ser";
	
	
	@ParameterizedTest
	@MethodSource("provide_TMap_Parameters")
	public void clear_Test(TreeMap tmap) {
		tmap.clear();
		assertTrue(tmap.size() == 0);
		assertTrue(tmap.repOK());
	 }
	
	@ParameterizedTest
	@MethodSource("provide_TMap_Parameters")
	public void clone_Test(TreeMap tmap) {
		TreeMap tmap1 = (TreeMap) tmap.clone();
		assertTrue(tmap1.repOK());
		assertTrue(tmap.repOK());
		assertTrue(tmap.equals(tmap1));
	 }
	
	@ParameterizedTest
	@MethodSource("provide_TMap_Parameters")
	public void comparator_Test(TreeMap tmap) {
		Comparator c =  tmap.comparator();
		assertTrue(tmap.repOK());
	 }
	
	
	@ParameterizedTest
	@MethodSource("provide_TMap_Int_Parameters")
	public void contains_key_Test(TreeMap tmap, Integer i) {
	  	tmap.containsKey(i);
    	assertTrue(tmap.repOK());
	 }
	
	@ParameterizedTest
	@MethodSource("provide_TMap_Int_Parameters")
	public void contains_value_Test(TreeMap tmap, Integer i) {
	  	tmap.containsValue(i);
    	assertTrue(tmap.repOK());
	 }
	
	
	@ParameterizedTest
	@MethodSource("provide_TMap_Parameters")
	public void entry_set_Test(TreeMap tmap) {
	  	Set s = tmap.entrySet();
    	assertTrue(tmap.repOK());
	 }
	
	@ParameterizedTest
	@MethodSource("provide_TMap_TMap_Parameters")
   	public void equals_test(TreeMap tmap, TreeMap tmap1) {
    	tmap.equals(tmap1);
    	assertTrue(tmap.repOK());
    	assertTrue(tmap1.repOK());
    }
	
	
	@ParameterizedTest
	@MethodSource("provide_TMap_Parameters")
   	public void first_key_test(TreeMap tmap) {
    	assumeTrue(tmap.size()>0);
    	Integer k = (Integer) tmap.firstKey();
    	assertTrue(tmap.repOK());
    	assertTrue(tmap.containsKey(k));
    	
    }
	
	
	
	@ParameterizedTest
	@MethodSource("provide_TMap_Int_Parameters")
   	public void head_map_test(TreeMap tmap, Integer i) {
    	TreeMap tmap1 =  tmap.headMap(i);
    	assertTrue(tmap.repOK());
    	assertTrue(tmap1.repOK());   	
    }
	
	@ParameterizedTest
	@MethodSource("provide_TMap_Parameters")
   	public void empty_test(TreeMap tmap) {
    	boolean p = tmap.isEmpty();
    	assertTrue(tmap.repOK());
    	assertTrue((p ==true && tmap.size() ==0) || (p ==false && tmap.size() !=0));
    }
	
	
	@ParameterizedTest
	@MethodSource("provide_TMap_Parameters")
   	public void key_set_test(TreeMap tmap) {
    	Set s = tmap.keySet();
    	assertTrue(tmap.repOK());
    	assertTrue(s.size() == tmap.size());
    
    }
	
	@ParameterizedTest
	@MethodSource("provide_TMap_Parameters")
   	public void last_key_test(TreeMap tmap) {
    	assumeTrue(tmap.size()>0);
    	Integer k = (Integer) tmap.lastKey();
    	assertTrue(tmap.repOK());
    	assertTrue(tmap.containsKey(k));
    	
    }
	
	@ParameterizedTest
	@MethodSource("provide_TMap_Int_Int_Parameters")
	public void put_test(TreeMap tmap, Integer key,Integer value) {
		int oldSize = tmap.size();
		boolean b = tmap.containsKey(key);
		
		tmap.put(key,value);
		
		assertTrue((!b && tmap.size() == oldSize+1) ||(b && tmap.size() == oldSize));
		assertTrue(tmap.repOK());
		assertTrue(tmap.containsKey(key) && tmap.containsValue(value));

	 }
	
	
	
	/*
	
	@ParameterizedTest
	@MethodSource("provide_List_Int_Parameters")
	public void addTest(TreeMap tmap, Integer i) {
		int oldSize = list.size();
		list.add(i);
		assertTrue(list.size() == oldSize+1);
		assertTrue(list.repOK());
	 }
	
	@ParameterizedTest
	@MethodSource("provide_List_Int_Int_Parameters")
	public void addTest1(LinkedList list, Integer i,Integer j) {
    	assumeTrue(i<list.size());
		int oldSize = list.size();
		list.add(i,j);
		assertTrue(list.size() == oldSize+1);
		assertTrue(list.repOK());

	 }
	
	@ParameterizedTest
	@MethodSource("provide_List_Int_Parameters")
	public void add_first_Test(LinkedList list, Integer i) {
    	assumeTrue(i<list.size());
		int oldSize = list.size();
		list.add(i);
		assertTrue(list.size() == oldSize+1);
		assertTrue(list.repOK());

	 }
	
	@ParameterizedTest
	@MethodSource("provide_List_Int_Parameters")
	public void add_last_Test(LinkedList list, Integer i) {
    	assumeTrue(i<list.size());
		int oldSize = list.size();
		list.add(i);
		assertTrue(list.size() == oldSize+1);
		assertTrue(list.repOK());

	 }
	
	
	
	@ParameterizedTest
	@MethodSource("provide_List_Parameters")
	public void clone_Test(LinkedList list) {
		LinkedList list1 = (LinkedList) list.clone();
		assertTrue(list1.repOK());
		assertTrue(list.repOK());
		assertTrue(list.equals(list1));
	 }
	
	@ParameterizedTest
	@MethodSource("provide_List_Int_Parameters")
	public void contains_Test(LinkedList list, Integer i) {
	  	list.contains(i);
    	assertTrue(list.repOK());
	 }
	
	@ParameterizedTest
	@MethodSource("provide_List_List_Parameters")
   	public void equals_test(LinkedList list, LinkedList list1) {
    	list.equals(list1);
    	assertTrue(list.repOK());
    	assertTrue(list1.repOK());
    }
	
	@ParameterizedTest
	@MethodSource("provide_List_Int_Parameters")
   	public void get_test(LinkedList list, Integer i) {
    	assumeTrue(i<list.size());
    	list.get(i);
    	assertTrue(list.repOK());
    	
    }
	
	@ParameterizedTest
	@MethodSource("provide_List_Int_Parameters")
   	public void getFirst_test(LinkedList list, Integer i) {
		assumeTrue(list.size()>0);
    	Object obj=list.getFirst();
    	assertTrue(list.repOK());
    	assertTrue(obj.equals(list.get(0)));
    }
	
	@ParameterizedTest
	@MethodSource("provide_List_Int_Parameters")
   	public void getLast_test(LinkedList list, Integer i) {
		assumeTrue(list.size()>0);
    	Object obj=list.getLast();
    	assertTrue(list.repOK());
    	assertTrue(obj.equals(list.get(list.size()-1)));
    }
	
	@ParameterizedTest
	@MethodSource("provide_List_Int_Parameters")
   	public void index_test(LinkedList list, Integer i) {
    	list.indexOf(i);
    	assertTrue(list.repOK());
    }
	
	@ParameterizedTest
	@MethodSource("provide_List_Int_Parameters")
   	public void empty_test(LinkedList list, Integer i) {
    	list.isEmpty();
    	assertTrue(list.repOK());
    }
	
	@ParameterizedTest
	@MethodSource("provide_List_Int_Parameters")
   	public void const_test(LinkedList list, Integer i) {
    	LinkedList list1 = new LinkedList();
    	assertTrue(list1.repOK());
    }
	
	@ParameterizedTest
	@MethodSource("provide_List_Int_Parameters")
   	public void remove_test(LinkedList list, Integer i) {
    	list.remove(i);
    	assertTrue(list.repOK());
    }
	
	@ParameterizedTest
	@MethodSource("provide_List_Parameters")
   	public void remove_first_test(LinkedList list) {
    	assumeTrue(list.size()>0);

		Object first = list.get(0);
    	Object obj=list.removeFirst();
    	assertTrue(list.repOK());
    	assertTrue(first.equals(obj));
    }
	
	@ParameterizedTest
	@MethodSource("provide_List_Parameters")
   	public void removeLast_test(LinkedList list) {
    	assumeTrue(list.size()>0);

		Object last = list.get(list.size()-1);
    	Object obj=list.removeLast();
    	assertTrue(list.repOK());
    	assertTrue(last.equals(obj));
    }
	
	@ParameterizedTest
	@MethodSource("provide_List_Int_Int_Parameters")
   	public void set_test(LinkedList list, Integer i, Integer value) {
    	assumeTrue(i<list.size());
    	list.set(i,value);
    	assertTrue(list.repOK());
    }
	
	@ParameterizedTest
	@MethodSource("provide_List_Parameters")
   	public void size_test(LinkedList list) {
    	list.size();
    	assertTrue(list.repOK());
    }
	
	@ParameterizedTest
	@MethodSource("provide_List_Int_Int_Parameters")
   	public void sublist_test(LinkedList list,Integer i, Integer j) {
    	assumeTrue(i>0 && j<list.size() && i<list.size());
    	list.subList(i,j);
    	assertTrue(list.repOK());
    }
	
	@ParameterizedTest
	@MethodSource("provide_List_Parameters")
   	public void toarray_test(LinkedList list) {
    	Object[] lArray = list.toArray();
    	assertTrue(list.repOK());
//    	assertTrue(lArray[0].equals(list.getFirst()));

    }
	
	*/
	
	/*
	 * Providers..
	 */
	
	
	private static Stream<Arguments> provide_TMap_Parameters() {
	  	Stream<Arguments> stream = Stream.empty();
	   	
		  	FileInputStream fileTestUnit;
	    	ObjectInputStream ois;
			try {
				fileTestUnit= new FileInputStream(pathFile);
				ois = new ObjectInputStream(fileTestUnit);
			
				TreeMap tmap = (TreeMap)nextObject(ois);
				while(tmap != null){
					stream = Stream.concat(Stream.of(Arguments.of(tmap)), stream);
					tmap = (TreeMap)nextObject(ois);
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
	
	
	private static Stream<Arguments> provide_TMap_Int_Parameters() {
	  	Stream<Arguments> stream = Stream.empty();
	   	
		  	FileInputStream fileTestUnit;
	    	ObjectInputStream ois;
	    	for(int i = 1; i <scope; i++) {
					try {
						fileTestUnit= new FileInputStream(pathFile);
						ois = new ObjectInputStream(fileTestUnit);
					
						TreeMap tmap = (TreeMap)nextObject(ois);
						
						while(tmap != null){
							stream = Stream.concat(Stream.of(Arguments.of(tmap,i)), stream);
							tmap = (TreeMap)nextObject(ois);
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
	
	private static Stream<Arguments> provide_TMap_Int_Int_Parameters() {
	  	Stream<Arguments> stream = Stream.empty();
	   	
		  	FileInputStream fileTestUnit;
	    	ObjectInputStream ois;
	    	for(int i = 1; i <scope; i++) {
		    	for(int j = 1; j <scope; j++) {

					try {
						fileTestUnit= new FileInputStream(pathFile);
						ois = new ObjectInputStream(fileTestUnit);
					
						TreeMap tmap = (TreeMap)nextObject(ois);
						
						while(tmap != null){
							stream = Stream.concat(Stream.of(Arguments.of(tmap,i,j)), stream);
							tmap = (TreeMap)nextObject(ois);
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
		    }		
	  	return stream;
	  }
	
	private static Stream<Arguments> provide_TMap_TMap_Parameters() {
	  	Stream<Arguments> stream = Stream.empty();
	   	
		  	FileInputStream fileTestUnit;
		  	FileInputStream fileTestUnit1;
	    	ObjectInputStream ois;
	    	ObjectInputStream ois1;

			try {
				fileTestUnit= new FileInputStream(pathFile);
				ois = new ObjectInputStream(fileTestUnit);
				
				TreeMap tmap = (TreeMap)nextObject(ois);
				TreeMap tmap1;
				while(tmap != null){
					fileTestUnit1= new FileInputStream(pathFile);
					ois1 = new ObjectInputStream(fileTestUnit1);
					tmap1 = (TreeMap)nextObject(ois1);
					while(tmap1 != null){
						stream = Stream.concat(Stream.of(Arguments.of(tmap,tmap1)), stream);
						tmap1 = (TreeMap)nextObject(ois1);

					}
					tmap = (TreeMap)nextObject(ois);

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
