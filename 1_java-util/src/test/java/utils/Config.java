package utils;
import java.io.FileInputStream;
import java.util.Properties;

public class Config {
    
	public static int scope;

    public static void readEnvironmentVariables() {

        String propFile = System.getProperty("properties", "config.properties");
        Properties props = new Properties();
        try {
            props.load(new FileInputStream(propFile));
            scope = Integer.parseInt(props.getProperty("scope"));
         
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            System.exit(1);
        }

    }

 

}
