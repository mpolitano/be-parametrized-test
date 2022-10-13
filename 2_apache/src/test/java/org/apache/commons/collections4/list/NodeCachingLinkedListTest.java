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
	public void test(NodeCachingLinkedList list) {
		assertTrue(list.repOK());
	 }
	
	
	
	
	
	
	
	
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
