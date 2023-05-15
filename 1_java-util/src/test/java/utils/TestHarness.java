package utils;

import java2.util2.Collection;
import java.util.Random;

import java2.util2.hashmap.HashMap;
import java2.util2.hashmap.HashSet;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import utils.Config;
import utils.Reports;
import utils.Serializer;

import java2.util2.Collections;
import java2.util2.Set;
import java2.util2.treemap.TreeMap;

import java.util.List;
import java.util.stream.Stream;

public class TestHarness {
    private static Serializer serializer;
    private static String pathFile;
    private static Random randomGen;
	private static int invalids;

    @BeforeAll
    public static void init() {
        Config.readConfig();
		invalids = 0;
        pathFile = Config.getPath();
        serializer = new Serializer();
        randomGen = new Random();
    }

    @BeforeEach
    public void before() {

    }

    @AfterAll
    static void afterAll() {
        Reports.end(invalids);
    }

    public static Stream<Object> readObjects() {
        return serializer.readObjectsFromFile(pathFile).stream();
    }

    public static Object getElementFrom(Collection c) {
        Object [] arr = c.toArray();
        int index = randomGen.nextInt(arr.length);
        return arr[index];
    }

	public static Object getElementFrom(HashSet c) {
		Object [] arr = c.toArray();
		int index = randomGen.nextInt(arr.length);
		return arr[index];
	}

	public static Object getElementFrom(HashMap c) {
		Object [] arr = c.keySet().toArray();
		int index = randomGen.nextInt(arr.length);
		return arr[index];
	}
	public static Object getElementFrom(TreeMap c) {
		Object [] arr = c.keySet().toArray();
		int index = randomGen.nextInt(arr.length);
		return arr[index];
	}

	public static Object getElementFromValues(TreeMap c) {
		Object [] arr = c.entrySet().toArray();
		int index = randomGen.nextInt(arr.length);
		return arr[index];
	}

	public static Object getElementFromValues(HashMap c) {
		Object [] arr = c.entrySet().toArray();
		int index = randomGen.nextInt(arr.length);
		return arr[index];
	}


	public static Object getKeyFrom(TreeMap t) {
        Set setKey = t.keySet();
        Object [] arr = setKey.toArray();
        if (arr.length > 0 ) {
        	int index = randomGen.nextInt(arr.length);

        	return arr[index];
        }
        return 0;
    }

		public static Object getKeyFrom(HashMap t) {
		Set setKey = t.keySet();
		Object [] arr = setKey.toArray();
		if (arr.length > 0 ) {
			int index = randomGen.nextInt(arr.length);

			return arr[index];
		}
		return 0;
	}

	public static Object getEntryFrom(TreeMap t) {
		Set entrySet = t.entrySet();
		Object [] arr = entrySet.toArray();
		if (arr.length > 0 ) {
			int index = randomGen.nextInt(arr.length);

			return arr[index];
		}
		return 0;
	}

//    public static Object getCollection() {
//    	List<Object> coll = serializer.getCollection();
//    	Random rand = new Random();
//    	Object randomElement = coll.get(rand.nextInt(coll.size()+1));
//    	return randomElement;
//    }

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
            return randomGen.nextInt((1000+1)-1000)+1000;
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
		System.out.println(type);
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


}
