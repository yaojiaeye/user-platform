package com.yaojia.configuration.microprofile.config.source;

import java.io.InputStream;
import java.net.URL;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Logger;

public class DefaultResourceConfigSource extends MapBasedConfigSource{

    private static final String configFileLocation = "META-INF/microprofile-config.properties";

    private final Logger logger = Logger.getLogger(this.getClass().getName());

    public DefaultResourceConfigSource(String name, int ordinal) {
        super("Default Config File", 200);
    }

    @Override
    public void prepareConfigData(Map configData) throws Throwable {
        ClassLoader classLoader = getClass().getClassLoader();
        URL resource = classLoader.getResource(configFileLocation);
        if (resource == null){
            logger.info("The default config file can't be found in the classpath : " + configFileLocation);
            return;
        }
        try(InputStream inputStream = resource.openStream()) {
            Properties properties = new Properties();
            properties.load(inputStream);
            configData.putAll(properties);
        }
    }
}
