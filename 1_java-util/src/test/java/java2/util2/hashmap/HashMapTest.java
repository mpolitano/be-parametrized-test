package java2.util2.hashmap;

import org.junit.Test;
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
import java2.util2.Set;
import java2.util2.Collection;


public class HashMapTest { 
    
	
	//Change with sedl 
		public static int scope;
		public static String pathFile;
		
		@BeforeAll
	    static void initAll() {
	    	Config.readEnvironmentVariables();
	    	scope = Config.scope;
	    	pathFile = "serialize/java2.util2.hashmap.HashMap/"+Config.scope+"/objects.ser";
	    }
	
	    @ParameterizedTest
		@MethodSource("provide_HMap_Parameters")
		public void clear_Test(HashMap hmap) {
			assertTrue(hmap.repOK());
			hmap.clear();
			assertTrue(hmap.size() == 0);
			assertTrue(hmap.repOK());
		 }
		   
	    @ParameterizedTest
		@MethodSource("provide_Int_Int_Parameters")
		public void constructor_Test(Integer i, Integer j) {
			HashMap h = null;

	    	try {
	    		h = new HashMap(i,j);
			}catch(IllegalArgumentException e) {
//				assertTrue(h.repOK());
			}
	    	if(h!=null)
				assertTrue(h.repOK());

		 }
	    
	    @Test
		public void constructor1_Test(Integer i, Integer j) {
			HashMap h = null;

	    	try {
	    		h = new HashMap();
			}catch(IllegalArgumentException e) {
//				assertTrue(h.repOK());
			}
	    	if(h!=null)
				assertTrue(h.repOK());

		 }
	    
	    @ParameterizedTest
		@MethodSource("provide_Int_Parameters")
		public void constructor1_Test(Integer i) {
	    		HashMap h = null;
	    	try {
				h = new HashMap(i);
			}catch(IllegalArgumentException e) {
			}
	    	if(h!=null)
	    		assertTrue(h.repOK());
	    	
		 }
	    
	    
	    @ParameterizedTest
		@MethodSource("provide_HMap_Parameters")
		public void clone_Test(HashMap hmap) {
			HashMap hmap1 = (HashMap) hmap.clone();
			assertTrue(hmap1.repOK());
			assertTrue(hmap.repOK());
//			assertTrue(hmap.equals(hmap1));
		 }
		
	    
	    @ParameterizedTest
		@MethodSource("provide_HMap_Int_Parameters")
		public void contains_key_Test(HashMap hmap, Integer i) {
		  	hmap.containsKey(i);
	    	assertTrue(hmap.repOK());
		 }
		
		@ParameterizedTest
		@MethodSource("provide_HMap_Int_Parameters")
		public void contains_value_Test(HashMap hmap, Integer i) {
		  	hmap.containsValue(i);
	    	assertTrue(hmap.repOK());
		}
	    
	    
		@ParameterizedTest
		@MethodSource("provide_HMap_Parameters")
		public void entry_set_Test(HashMap hmap) {
		  	Set s = hmap.entrySet();
	    	assertTrue(hmap.repOK());
		 }
	
	
	
		@ParameterizedTest
		@MethodSource("provide_HMap_Int_Parameters")
	   	public void get_test(HashMap hmap, Integer key) {

	    	Integer v =  (Integer)hmap.get(key);
	    	assertTrue(hmap.repOK());
	    	
	    	//Esto se cumple porque se que Value no es null.
	    	assertTrue((v!=null && hmap.containsKey(key)) || 
	    			(v==null && !hmap.containsKey(key)));

	    }
	
		
		@ParameterizedTest
		@MethodSource("provide_HMap_Parameters")
	   	public void empty_test(HashMap hmap) {
	    	boolean p = hmap.isEmpty();
	    	assertTrue(hmap.repOK());
	    	assertTrue((p ==true && hmap.size() ==0) || (p ==false && hmap.size() !=0));
	    }
		
	
		
//		@ParameterizedTest
//		@MethodSource("provide_HMap_Parameters")
//	   	public void key_set_test(HashMap hmap) {
//	    	Set s = hmap.keySet();
//	    	assertTrue(hmap.repOK());
//	    	assertTrue(s.size() == hmap.size());
//	    
//	    }
	
		
		@ParameterizedTest
		@MethodSource("provide_HMap_Int_Int_Parameters")
		public void put_test(HashMap hmap, Integer key,Integer value) {
			int oldSize = hmap.size();
			boolean b = hmap.containsKey(key);
			
			hmap.put(key,value);
			
			assertTrue((!b && hmap.size() == oldSize+1) ||(b && hmap.size() == oldSize));
			assertTrue(hmap.repOK());
			assertTrue(hmap.containsKey(key) && hmap.containsValue(value));

		 }
		
		
		
		
		@ParameterizedTest
		@MethodSource("provide_HMap_Int_Parameters")
	   	public void remove_test(HashMap hmap, Integer key) {
	    	hmap.remove(key);
	    	
	    	assertTrue(hmap.repOK());
	    	assertTrue(!hmap.containsKey(key));
	    }
	
		
		@ParameterizedTest
		@MethodSource("provide_HMap_Parameters")
	   	public void size_test(HashMap hmap) {
	    	int s = hmap.size();
	       	assertTrue(hmap.repOK());
			assertTrue((s==0 && hmap.isEmpty()) ||(s!=0 && !hmap.isEmpty()));

	    }
		

//		@ParameterizedTest
//		@MethodSource("provide_HMap_Parameters")
//	   	public void toString_test(HashMap hmap) {
//	    	String s = hmap.toString();
//	       	assertTrue(hmap.repOK());
//
//	    }
		
		
//		@ParameterizedTest
//		@MethodSource("provide_HMap_Parameters")
//		public void value_test(HashMap hmap) {
//			Collection l = hmap.values();
//			assertTrue(hmap.repOK());
//			assertTrue((l.isEmpty() && hmap.isEmpty()) || (!l.isEmpty() && !hmap.isEmpty()) );
//					
//		 }
		
	
/*	@ParameterizedTest
	@MethodSource("provide_HMap_HMap_Parameters")
   	public void equals_test(HashMap hmap, HashMap hmap1) {
    	hmap.equals(hmap1);
    	assertTrue(hmap.repOK());
    	assertTrue(hmap1.repOK());
    }
*/
		
	
	/*
	 * Providers..
	 */
	
	    private static Stream<Arguments> provide_HMap_Parameters() {
		  	Stream<Arguments> stream = Stream.empty();
		   	
			  	FileInputStream fileTestUnit;
		    	ObjectInputStream ois;
				try {
					fileTestUnit= new FileInputStream(pathFile);
					ois = new ObjectInputStream(fileTestUnit);
				
					HashMap hmap = (HashMap)nextObject(ois);
					while(hmap != null){
						stream = Stream.concat(Stream.of(Arguments.of(hmap)), stream);
						hmap = (HashMap)nextObject(ois);
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
	    
	    
	    private static Stream<Arguments> provide_HMap_Int_Parameters() {
		  	Stream<Arguments> stream = Stream.empty();
		   	
			  	FileInputStream fileTestUnit;
		    	ObjectInputStream ois;
		    	for(int i = 0; i <scope; i++) {
						try {
							fileTestUnit= new FileInputStream(pathFile);
							ois = new ObjectInputStream(fileTestUnit);
						
							HashMap hmap = (HashMap)nextObject(ois);
							
							while(hmap != null){
								stream = Stream.concat(Stream.of(Arguments.of(hmap,i)), stream);
								hmap = (HashMap)nextObject(ois);
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
	    
	    
	    private static Stream<Arguments> provide_HMap_Int_Int_Parameters() {
		  	Stream<Arguments> stream = Stream.empty();
		   	
			  	FileInputStream fileTestUnit;
		    	ObjectInputStream ois;
		    	for(int i = 0; i <scope; i++) {
			    	for(int j = 0; j <scope; j++) {

						try {
							fileTestUnit= new FileInputStream(pathFile);
							ois = new ObjectInputStream(fileTestUnit);
						
							HashMap hmap = (HashMap)nextObject(ois);
							
							while(hmap != null){
								stream = Stream.concat(Stream.of(Arguments.of(hmap,i,j)), stream);
								hmap = (HashMap)nextObject(ois);
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
	    
	    private static Stream<Arguments> provide_Int_Int_Parameters() {
		  	Stream<Arguments> stream = Stream.empty();
		   	
		    	for(int i = -1; i <scope-1; i++) {
			    	for(int j = 0 ;j <scope; j++) {
							
								stream = Stream.concat(Stream.of(Arguments.of(i,j)), stream);
							}
			    	}	
			    		
		  	return stream;
		  }
	    
	    private static Stream<Arguments> provide_Int_Parameters() {
		  	Stream<Arguments> stream = Stream.empty();
		   	
		    	for(int i = -1; i <scope-1; i++) {
							
								stream = Stream.concat(Stream.of(Arguments.of(i)), stream);
							}
			    	
			    		
		  	return stream;
		  }
	   
	private static Stream<Arguments> provide_HMap_HMap_Parameters() {
	  	Stream<Arguments> stream = Stream.empty();
	   	
		  	FileInputStream fileTestUnit;
		  	FileInputStream fileTestUnit1;
	    	ObjectInputStream ois;
	    	ObjectInputStream ois1;

			try {
				fileTestUnit= new FileInputStream(pathFile);
				ois = new ObjectInputStream(fileTestUnit);
				
				HashMap hmap = (HashMap)nextObject(ois);
				HashMap hmap1;
				while(hmap != null){
					fileTestUnit1= new FileInputStream(pathFile);
					ois1 = new ObjectInputStream(fileTestUnit1);
					hmap1 = (HashMap)nextObject(ois1);
					while(hmap1 != null){
						stream = Stream.concat(Stream.of(Arguments.of(hmap,hmap1)), stream);
						hmap1 = (HashMap)nextObject(ois1);

					}
					hmap = (HashMap)nextObject(ois);

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
