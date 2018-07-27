package shippo.global;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ConfigLoader {
    private Logger LOG = LoggerFactory.getLogger(ConfigLoader.class);
    static ConfigLoader instance = null;
    static Properties properties = new Properties();

    public static ConfigLoader getInstance(){
        if(instance == null){
            instance = new ConfigLoader();
        }
        return instance;
    }

    public ConfigLoader(){
        String currentPath = "";
        String confPath = "";
        try {
            currentPath = new File(".").getCanonicalPath();
            LOG.info("Current path: {}",currentPath);
            confPath = currentPath+"/config/config.properties";
            properties.load(new FileReader(confPath));
        } catch (IOException e) {
            LOG.error("Load properties file error ",e);

        }
    }

    public static Properties getProperties(){
        return properties;
    }
}
