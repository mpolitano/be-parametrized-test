package builders;

import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.concurrent.ThreadLocalRandom;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import utils.Config;
import utils.ObjectsIterator;

public class ScheduleTest {

	//Change with sedl 
	public static int scope;
	public static String pathFile;

	//Change with sedl 
	public static int literals;
	private static ObjectsIterator objIterator;

	
	@BeforeEach
	public void init() {
		objIterator = new ObjectsIterator("builders.Schedule");
		literals = objIterator.getLiterals();
	}
	
	@AfterAll
    static void afterAll() {
		objIterator.end("builders.Schedule");
	}
	
    @Test
	public void test_init() {
		Schedule sch = new Schedule();
		assertTrue(sch.repOK());
	}
	
	@Test
	    public void test_init1() {
		Schedule sch = new Schedule(1,1,1);
		assertTrue(sch.repOK());
	}
	
	   @Test
		public void test_add() {
	
		   Schedule sch = (Schedule)objIterator.next(); 

			while(sch != null){
				objIterator.addCountTest();
				int i = ThreadLocalRandom.current().nextInt(0, literals + 1);
				
				try {
					sch.addProcess(i);
				} catch (java.lang.IllegalArgumentException e) {
					assertTrue(sch.repOK());       
				}
				assertTrue(sch.repOK());

				sch = (Schedule)objIterator.next();

			}
	}
	
	   @Test
	public void test_block() {
		
	   Schedule sch = (Schedule)objIterator.next(); 

		while(sch != null){
			objIterator.addCountTest();
			int i = ThreadLocalRandom.current().nextInt(0, literals + 1);
			
			sch.blockProcess();
			assertTrue(sch.repOK());
			sch = (Schedule)objIterator.next();

		}

	}
	
	   @Test
	public void test_initPrioQueue_good() {
		
	   Schedule sch = (Schedule)objIterator.next(); 

		while(sch != null){
			objIterator.addCountTest();
			int i = ThreadLocalRandom.current().nextInt(-2, literals + 1);
			int j = ThreadLocalRandom.current().nextInt(-2, literals + 1);

			try {
				sch.initPrioQueue(j,i);
		} catch (java.lang.IllegalArgumentException e) {
				assertTrue(sch.repOK());
			}
			assertTrue(sch.repOK());
			sch = (Schedule)objIterator.next();

			}
	}
	
	   @Test
	public void test_initPrioQueue_bad() {
	   Schedule sch = (Schedule)objIterator.next(); 
		while(sch != null){
			objIterator.addCountTest();
			int i = ThreadLocalRandom.current().nextInt(-2, literals + 1);
			int j = ThreadLocalRandom.current().nextInt(-2, literals + 1);
			try {
				sch.initPrioQueue(i,i);
			} catch (java.lang.IllegalArgumentException e) {
				assertTrue(sch.repOK());
			}
				assertTrue(sch.repOK());
				sch = (Schedule)objIterator.next();

		}		
		}
		
	   @Test
	   public void test_finishAll() {
		   Schedule sch = (Schedule)objIterator.next(); 
		   while(sch != null){
			   objIterator.addCountTest();
			   try {
					sch.finishAllProcesses();
					assertTrue(sch.repOK());
			   } catch (java.lang.IllegalArgumentException e) {
				   	assertTrue(sch.repOK());
			   }
				assertTrue(sch.repOK());
				sch = (Schedule)objIterator.next();
	
		}
	}

	   @Test
	   public void test_finishProcess() {
		   Schedule sch = (Schedule)objIterator.next(); 
		   while(sch != null){
				objIterator.addCountTest();
					
				sch.finishProcess();
				assertTrue(sch.repOK());
				sch = (Schedule)objIterator.next();

		   }
	   }
	   
	
		@Test
		public void test_quantum() {
			Schedule sch = (Schedule)objIterator.next(); 
				while(sch != null){
					objIterator.addCountTest();
						
					sch.quantumExpire();
					assertTrue(sch.repOK());
					sch = (Schedule)objIterator.next();
				}	
		}

	
		@Test
	public void test_unblockProcess() {
			Schedule sch = (Schedule)objIterator.next(); 
			while(sch != null){
				objIterator.addCountTest();
				int i = ThreadLocalRandom.current().nextInt(-2, literals + 1);

				try {
					sch.unblockProcess(i);

		        } catch (java.lang.IllegalArgumentException e) {
					assertTrue(sch.repOK());
		        }
				assertTrue(sch.repOK());
				sch = (Schedule)objIterator.next();
			}	
	}
	
		@Test
	public void test_upgradeProcess_good() {
			Schedule sch = (Schedule)objIterator.next(); 
			while(sch != null){
				objIterator.addCountTest();
				int i = ThreadLocalRandom.current().nextInt(-2, literals + 1);
				int j = ThreadLocalRandom.current().nextInt(-2, literals + 1);

				try {
					sch.upgradeProcessPrio(i,j);
		        } catch (java.lang.IllegalArgumentException | java.lang.StackOverflowError e) {
					assertTrue(sch.repOK());
		        }
				assertTrue(sch.repOK());
				sch = (Schedule)objIterator.next();

			}				
	}
	
	@Test
	public void test_upgradeProcess_nogood() {
		Schedule sch = (Schedule)objIterator.next(); 
		while(sch != null){
			objIterator.addCountTest();
			int i = ThreadLocalRandom.current().nextInt(-2, literals + 1);

			try {
				sch.upgradeProcessPrio(0,i);
	        } catch (java.lang.IllegalArgumentException | java.lang.StackOverflowError e) {
				assertTrue(sch.repOK());
	        }
			assertTrue(sch.repOK());
			sch = (Schedule)objIterator.next();

		}	
		
	}
	
	
}
