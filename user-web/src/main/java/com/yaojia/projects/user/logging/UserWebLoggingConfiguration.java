package com.yaojia.projects.user.logging;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

/**
 * @author yaojia
 */
public class UserWebLoggingConfiguration {

    public UserWebLoggingConfiguration() throws UnsupportedEncodingException {
        Logger logger = Logger.getLogger("com.yaojia");
        ConsoleHandler consoleHandler = new ConsoleHandler();
        consoleHandler.setEncoding("UTF-8");
        consoleHandler.setLevel(Level.WARNING);
        logger.addHandler(consoleHandler);
    }

    public static void main(String[] args) throws IOException {
        Logger logger = Logger.getLogger("com.yaojia");
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream("META-INF/logging.properties");
        LogManager logManager = LogManager.getLogManager();
        // 读取日志配置
        logManager.readConfiguration(inputStream);
        logger.info("Hello,World");
        logger.warning("2021");
    }
}
