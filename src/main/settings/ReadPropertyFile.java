package main.settings;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Properties;
import java.util.logging.Level;

import main.Main;

public class ReadPropertyFile {

    private static final String PROPERTIES_PATH = "src\\main\\settings\\config.properties";
    private static final String DEFAULT_PROPERTIES_PATH = "src\\main\\settings\\default.properties";

    private ReadPropertyFile() {}
    
    public static Properties readFile(){

        Properties properties = new Properties();
        FileInputStream input = null;
        
        try {
            input = new FileInputStream(PROPERTIES_PATH);
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            Main.logger.log(Level.INFO, "Error, config.properties not found");

            //Generate default config file if this occurs, then reread the input
            //TODO
            try {
                resetProperties();
                input = new FileInputStream(PROPERTIES_PATH);
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
        Path defaultProperties = Paths.get(DEFAULT_PROPERTIES_PATH);
        Path properties = Paths.get(PROPERTIES_PATH);

        Files.copy(defaultProperties, properties, StandardCopyOption.REPLACE_EXISTING);
    }
    
}
