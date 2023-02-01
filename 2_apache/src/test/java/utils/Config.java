package utils;
import java.io.FileInputStream;
import java.util.Properties;

public class Config {

    
    
	public static int scope;
	public static int literals;

	public static String path;
    public static String tool;
    public static String clazz;

    public static void readEnvironmentVariables() {

        String propFile = System.getProperty("properties", "config.properties");
        Properties props = new Properties();
        try {
            props.load(new FileInputStream(propFile));
            scope = Integer.parseInt(props.getProperty("scope"));
            literals = Integer.parseInt(props.getProperty("literals"));
            tool = props.getProperty("tool");
            clazz = props.getProperty("clazz");

            path = props.getProperty("path");
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            System.exit(1);
        }

    }

 

}
