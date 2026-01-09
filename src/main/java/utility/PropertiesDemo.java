package utility;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropertiesDemo {

    public static void main(String[] args) throws IOException {
        // 1. read the file
        FileInputStream fis = new FileInputStream("config/config.properties");

        // 2. create the object of Properties class
        Properties prop = new Properties();

        // 3. load the file using properties object
        prop.load(fis);

        // 4. get the value of the key using loaded properties object
        System.out.println(prop.getProperty("url"));
        System.out.println(prop.getProperty("username"));
        System.out.println(prop.getProperty("password"));
    }
}