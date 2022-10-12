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


public class LinkedListTest { 
    
	//Change with sed
	public static int scope = 3;
	public static String pathFile = "beapi-tests/java2.util2.LinkedList/"+scope+"/objects.ser";
	
	
	@ParameterizedTest
	@MethodSource("provide_List_Int_Parameters")
	public void addTest(LinkedList list, Integer i) {
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
	public void clear_Test(LinkedList list) {
		list.clear();
		assertTrue(list.size() == 0);
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
	
	
	
	/*
	 * Providers..
	 */
	
	
	private static Stream<Arguments> provide_List_Parameters() {
	  	Stream<Arguments> stream = Stream.empty();
	   	
		  	FileInputStream fileTestUnit;
	    	ObjectInputStream ois;
			try {
				fileTestUnit= new FileInputStream(pathFile);
				ois = new ObjectInputStream(fileTestUnit);
			
				LinkedList list = (LinkedList)nextObject(ois);
				while(list != null){
					stream = Stream.concat(Stream.of(Arguments.of(list)), stream);
					list = (LinkedList)nextObject(ois);
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
	
	
	private static Stream<Arguments> provide_List_Int_Parameters() {
	  	Stream<Arguments> stream = Stream.empty();
	   	
		  	FileInputStream fileTestUnit;
	    	ObjectInputStream ois;
	    	for(int i = 1; i <scope; i++) {
					try {
						fileTestUnit= new FileInputStream(pathFile);
						ois = new ObjectInputStream(fileTestUnit);
					
						LinkedList list = (LinkedList)nextObject(ois);
						
						while(list != null){
							stream = Stream.concat(Stream.of(Arguments.of(list,i)), stream);
							list = (LinkedList)nextObject(ois);
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
	
	private static Stream<Arguments> provide_List_Int_Int_Parameters() {
	  	Stream<Arguments> stream = Stream.empty();
	   	
		  	FileInputStream fileTestUnit;
	    	ObjectInputStream ois;
	    	for(int i = 1; i <scope; i++) {
		    	for(int j = 1; j <scope; j++) {

					try {
						fileTestUnit= new FileInputStream(pathFile);
						ois = new ObjectInputStream(fileTestUnit);
					
						LinkedList list = (LinkedList)nextObject(ois);
						
						while(list != null){
							stream = Stream.concat(Stream.of(Arguments.of(list,i,j)), stream);
							list = (LinkedList)nextObject(ois);
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
	
	private static Stream<Arguments> provide_List_List_Parameters() {
	  	Stream<Arguments> stream = Stream.empty();
	   	
		  	FileInputStream fileTestUnit;
		  	FileInputStream fileTestUnit1;
	    	ObjectInputStream ois;
	    	ObjectInputStream ois1;

			try {
				fileTestUnit= new FileInputStream(pathFile);
				fileTestUnit1= new FileInputStream(pathFile);
				ois = new ObjectInputStream(fileTestUnit);
				ois1 = new ObjectInputStream(fileTestUnit1);

				LinkedList list = (LinkedList)nextObject(ois);
				LinkedList list1 = (LinkedList)nextObject(ois1);

				while(list != null){
					while(list1 != null){
					stream = Stream.concat(Stream.of(Arguments.of(list,list1)), stream);
					list = (LinkedList)nextObject(ois);
					list1 = (LinkedList)nextObject(ois1);

					}
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
