package main.settings;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Properties;

public class ReadPropertyFile {

    private static final String propertiesPath = "src\\main\\settings\\config.properties";
    private static final String defaultPropertiesPath = "src\\main\\settings\\default.properties";
    
    public static Properties readFile(){

        Properties properties = new Properties();
        FileInputStream input = null;
        
        try {
            input = new FileInputStream(propertiesPath);
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            System.out.println("Error, config.properties not found");

            //Generate default config file if this occurs, then reread the input
            //TODO
            try {
                resetProperties();
                input = new FileInputStream("src\\main\\settings\\config.properties");
            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        }

        try {
            properties.load(input);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return properties;
    }

    private static void resetProperties() throws IOException{
        Path defaultProperties = Paths.get(defaultPropertiesPath);
        Path properties = Paths.get(propertiesPath);

        Files.copy(defaultProperties, properties, StandardCopyOption.REPLACE_EXISTING);
    }
    
}
