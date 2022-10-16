package builders;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import utils.Config;

public class TestSchedule {

	//Change with sedl 
		public static int scope;
		public static String pathFile;
		
	    @BeforeAll
	    static void initAll() {
	    	Config.readEnvironmentVariables();
	    	scope = Config.scope;
	    	pathFile = "serialize/builders.Schedule/4/objects.ser";
	    }
	
	
	    @Test
	public void test_init() {
		Schedule sch = new Schedule();
		assertTrue(sch.repOK());
	}
	
//	@ParameterizedTest
//	@MethodSource("provide_Int_Int_Int_Parameters")
	    @Test
	    public void test_init1() {
		Schedule sch = new Schedule(1,1,1);
		assertTrue(sch.repOK());
	}
	
	@ParameterizedTest
	@MethodSource("provide_Sch_Int_Parameters")
	public void test_add(Schedule sch, Integer i) {
		try {
			sch.addProcess(i);
		} catch (java.lang.IllegalArgumentException e) {
			assertTrue(sch.repOK());       
		}
		assertTrue(sch.repOK());       
	}
	
	@ParameterizedTest
	@MethodSource("provide_Sch_Parameters")
	public void test_block(Schedule sch) {
		sch.blockProcess();
		assertTrue(sch.repOK());
	}
	
	@ParameterizedTest
	@MethodSource("provide_Sch_Int_Parameters")
	public void test_initPrioQueue_good(Schedule sch, Integer i) {
		//workaround for stream stackoverflow
		try {
				sch.initPrioQueue(-1,i);
		} catch (java.lang.IllegalArgumentException e) {
			assertTrue(sch.repOK());
		}
		assertTrue(sch.repOK());

	}
	
	@ParameterizedTest
	@MethodSource("provide_Sch_Int_Parameters")
	public void test_initPrioQueue_bad(Schedule sch,Integer i) {
		try {	
			sch.initPrioQueue(-1,i);
		} catch (java.lang.IllegalArgumentException e) {
			assertTrue(sch.repOK());
		}
		assertTrue(sch.repOK());
	}
	
	@ParameterizedTest
	@MethodSource("provide_Sch_Parameters")
	public void test_finishAll(Schedule sch) {
		sch.finishAllProcesses();
		assertTrue(sch.repOK());
	}
	
	@ParameterizedTest
	@MethodSource("provide_Sch_Parameters")
	public void test_finishProcess(Schedule sch) {
		sch.finishProcess();
		assertTrue(sch.repOK());
	}
	
	@ParameterizedTest
	@MethodSource("provide_Sch_Parameters")
	public void test_quantum(Schedule sch) {
		sch.quantumExpire();
		assertTrue(sch.repOK());
	}
	
	@ParameterizedTest
	@MethodSource("provide_Sch_Int_Parameters")
	public void test_unblockProcess(Schedule sch, Integer i) {
		try {
			sch.unblockProcess(i);
//		assertThrows(IllegalArgumentException.class,
//	            ()->{
//	            //do whatever you want to do here
//	            //ex : objectName.thisMethodShoulThrowNullPointerExceptionForNullParameter(null);
//	            });
        } catch (java.lang.IllegalArgumentException e) {
			assertTrue(sch.repOK());
        }
		assertTrue(sch.repOK());

	}
	
	@ParameterizedTest
	@MethodSource("provide_Sch_Int_Parameters")
	public void test_upgradeProcess_good(Schedule sch, Integer i) {
		try {
			sch.upgradeProcessPrio(i,0);
        } catch (java.lang.IllegalArgumentException | java.lang.StackOverflowError e) {
			assertTrue(sch.repOK());
        }
		assertTrue(sch.repOK());

	}
	
	@ParameterizedTest
	@MethodSource("provide_Sch_Int_Parameters")
	public void test_upgradeProcess_nogood(Schedule sch, Integer i) {
		try {
			sch.upgradeProcessPrio(0,i);
        } catch (java.lang.IllegalArgumentException | java.lang.StackOverflowError e) {
			assertTrue(sch.repOK());
        }
		assertTrue(sch.repOK());
	}
	
	/*
	 * Providers..
	 */
	
	
	private static Stream<Arguments> provide_Sch_Parameters() {
	  	Stream<Arguments> stream = Stream.empty();
	   	
		  	FileInputStream fileTestUnit;
	    	ObjectInputStream ois;
			try {
				fileTestUnit= new FileInputStream(pathFile);
				ois = new ObjectInputStream(fileTestUnit);
			
				Schedule list = (Schedule)nextObject(ois);
				while(list != null){
					stream = Stream.concat(Stream.of(Arguments.of(list)), stream);
					list = (Schedule)nextObject(ois);
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
	
	
	@SuppressWarnings("resource")
	private static Stream<Arguments> provide_Sch_Int_Parameters() {
		Stream<Arguments> stream = Stream.empty();
	   	
	  	FileInputStream fileTestUnit;
    	ObjectInputStream ois;
    	for(int i = 0; i <scope; i++) {
				try {
					fileTestUnit= new FileInputStream(pathFile);
					ois = new ObjectInputStream(fileTestUnit);
				
					Schedule list = (Schedule)nextObject(ois);
					
					while(list != null){
//						Random r = new Random();
//						float random = 0 + r.nextFloat() * (1 - 0);

						stream = Stream.concat(Stream.of(Arguments.of(list,i)), stream);
						list = (Schedule)nextObject(ois);
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

	  
	private static Stream<Arguments> provide_Sch_Float_Parameters() {
	  	Stream<Arguments> stream = Stream.empty();
	   	
		  	FileInputStream fileTestUnit;
	    	ObjectInputStream ois;
					try {
						fileTestUnit= new FileInputStream(pathFile);
						ois = new ObjectInputStream(fileTestUnit);
					
						Schedule list = (Schedule)nextObject(ois);
						
						while(list != null){	
					    	for(int i = 0; i <scope; i++) {

	//							Random r = new Random();
	//							float random = 0 + r.nextFloat() * (1 - 0);
	
								stream = Stream.concat(Stream.of(Arguments.of(list,i)), stream);
								list = (Schedule)nextObject(ois);
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
//		  	}		
	  	return stream;
	  }
	
	private static Stream<Arguments> provide_Sch_Int_Float_Parameters() {
	  	Stream<Arguments> stream = Stream.empty();
	   	
		  	FileInputStream fileTestUnit;
	    	ObjectInputStream ois;
	    	for(int i = 0; i <scope; i++) {
		    	for(int j = -1; j <0; j++) {

					try {
						fileTestUnit= new FileInputStream(pathFile);
						ois = new ObjectInputStream(fileTestUnit);
					
						Schedule list = (Schedule)nextObject(ois);
						
						while(list != null){
							Random r = new Random();
							float random = 0 + r.nextFloat() * (1 - 0);
							stream = Stream.concat(Stream.of(Arguments.of(list,i,j)), stream);
							list = (Schedule)nextObject(ois);
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
