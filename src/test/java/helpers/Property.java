package helpers;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class Property {
    public static String getProperty(String propertyName) throws IOException {
        Properties prop = new Properties();
        prop.load(new FileReader("src/test/resources/application.properties"));

        return (String) prop.get(propertyName);
    }
}
