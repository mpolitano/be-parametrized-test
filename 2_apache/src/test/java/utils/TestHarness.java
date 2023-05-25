package utils;

import java.util.*;

import org.apache.commons.collections4.list.NodeCachingLinkedList;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

import java.util.stream.Stream;

public class TestHarness {
	private static Serializer serializer;
	private static String pathFile;
	private static Random randomGen;
	private static int invalids;
	private static int tests;


	@BeforeAll
	public static void init() {
		Config.readConfig();
		invalids = 0;
		tests = 0;
		pathFile = Config.getPath();
		serializer = new Serializer();
		randomGen = new Random();
	}

/*    @BeforeEach
    public void before() {

    } */

	@AfterAll
	static void afterAll() {
		Reports.invalidsFile(invalids);
		Reports.testsFile(tests);
	}

	public static Stream<Object> readObjects() {
		return serializer.readObjectsFromFile(pathFile).stream();
	}

	public static List<Object> readObjects2() {
		return serializer.readObjectsFromFile(pathFile);
	}

	public static Object getElementFrom(NodeCachingLinkedList c) {
		List  mainList = new ArrayList();
		int i=0;
		while (i<c.size()) {
			mainList.add(c.get(i));
			i++;
		}
		int index = randomGen.nextInt(mainList.size());
		return c.get(index);
	}

	public static Object getCollection() {
		List<Object> coll = serializer.getCollection();
		Random rand = new Random();
		Object randomElement = coll.get(rand.nextInt(coll.size()));
		return randomElement;
	}

//	public static HashMap getCollection() {
//		List<HashMap> coll = (List<HashMap>) serializer.getCollection();
//		Random rand = new Random();
//		HashMap randomElement = coll.get(rand.nextInt(coll.size()+1));
//		return randomElement;
//	}


	public static int getInt(int min, int max) {
		return randomGen.nextInt((max+1)-min)+min;
	}

	public static Object getObj(Object t) {
		Object type = t.getClass();
		if (type == Integer.class) {
			return randomGen.nextInt(1000+1-1000)+1000;
		}
		if (type == Float.class) {
			return randomGen.nextFloat();
		}
		if (type == Double.class) {
			return randomGen.nextDouble();
		}
		if (type == Short.class) {
			return (short)randomGen.nextInt(Short.MAX_VALUE + 1);
		}
		if (type == Long.class) {
			return randomGen.nextLong();
		}
		if (type == Byte.class) {
			return getByte();
		}
		if (type == Character.class) {
			return getChar();
		}
		if (type == Boolean.class) {
			return randomGen.nextBoolean();
		}
		if (type == String.class) {
			return getString();
		}

		return null;
	}

	public static Object getChar() {
		String abc = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		char letter = abc.charAt(randomGen.nextInt(abc.length()));
		return letter;
	}

	public static Object getString() {
		String abc = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		return abc;
	}

	public static Object getByte() {
		byte[] arr = new byte[1];
		randomGen.nextBytes(arr);
		return arr[0];
	}

	public static boolean typeComparable(Object t) {
		if (t==null) return false;
		Object type = t.getClass();
		if (type == Integer.class||
				type == Float.class  ||
				type == Byte.class   ||
				type == Character.class)
			return true;
		return false;
	}


	public static void addInvalidTest() {
		invalids++;
	}

	public static void countTest() {
		tests++;
	}

}
