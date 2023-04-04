package utils;

import java2.util2.Collection;
import java.util.Random;

import java2.util2.hashmap.HashMap;
import java2.util2.treeset.TreeSet;
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

    @BeforeAll
    public static void init() {
        Config.readConfig();
        pathFile = Config.getPath();
        serializer = new Serializer();
        randomGen = new Random();
    }

    @BeforeEach
    public void before() {

    }

    @AfterAll
    static void afterAll() {
        Reports.end(Config.clazz);
    }

    public static Stream<Object> readObjects() {
        return serializer.readObjectsFromFile(pathFile).stream();
    }

    public static Object getElementFrom(Collection c) {
        Object [] arr = c.toArray();
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

    public static Object getCollection() {
    	List<Object> coll = serializer.getCollection();
    	Random rand = new Random();
    	Object randomElement = coll.get(rand.nextInt(coll.size()+1));
    	return randomElement;
    }

    public static int getInt(int min, int max) {
        return randomGen.nextInt((max+1)-min)+min;
    }

    public static Object getObj(Object t, int min, int max) {
    	Object type = t.getClass();
    	if (type == Integer.class) {
            return randomGen.nextInt((max+1)-min)+min;
    	}
    	if (type == Float.class) {
    		return randomGen.nextFloat();
    	}
    	if (type == Byte.class) {
    		return getByte(t,min,max);
    	}
    	if (type == Character.class) {
    		return getChar(t,min,max);
    	}
    	return null;
    }

    public static Object getChar(Object t, int min, int max) {
    	String abc = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    	char letter = abc.charAt(randomGen.nextInt(abc.length()));
    	return letter;
    }

    public static Object getByte(Object t, int min, int max) {
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



}
