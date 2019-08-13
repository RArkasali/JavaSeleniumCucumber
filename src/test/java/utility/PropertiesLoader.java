package utility;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


public class PropertiesLoader {

    private String propertyFile;

    public PropertiesLoader(String propertyFile) {
        this.propertyFile = propertyFile;
    }

    public Properties load() {
        InputStream propertyStream = null;
        try {
            propertyStream = new FileInputStream(propertyFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        Properties properties = new Properties();
        try {
            properties.load(propertyStream);
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }

        return properties;
    }


}