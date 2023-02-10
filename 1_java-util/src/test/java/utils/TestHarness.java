package utils;

import java2.util2.Collection;
import java.util.Random;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import utils.Config;
import utils.Reports;
import utils.Serializer;

import java2.util2.Collections;
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
    
    public static Collection getCollection() {
    	List<Collection> coll = serializer.getCollection();
    	Random rand = new Random();
    	Collection randomElement = coll.get(rand.nextInt(coll.size()));   
    	return randomElement;
    }

    public static int getInt(int min, int max) {
        return randomGen.nextInt((max+1)-min)+min;
    }

}
