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
	public static String pathFile = "beapi-tests/serialize/java2.util2.TreeMap/"+scope+"/objects.ser";
	
	
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
	
/*	@ParameterizedTest
	@MethodSource("provide_TMap_TMap_Parameters")
   	public void equals_test(TreeMap tmap, TreeMap tmap1) {
    	tmap.equals(tmap1);
    	assertTrue(tmap.repOK());
    	assertTrue(tmap1.repOK());
    }
*/
	
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
