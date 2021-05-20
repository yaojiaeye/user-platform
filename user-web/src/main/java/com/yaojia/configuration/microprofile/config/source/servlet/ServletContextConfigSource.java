package com.yaojia.configuration.microprofile.config.source.servlet;

import com.yaojia.configuration.microprofile.config.source.MapBasedConfigSource;

import javax.servlet.ServletContext;
import java.util.Enumeration;
import java.util.Map;

import static java.lang.String.format;

/**
 * @author yaojia
 */
public class ServletContextConfigSource extends MapBasedConfigSource {

    private final ServletContext servletContext;

    public ServletContextConfigSource(ServletContext servletContext) {
        super(format("ServletContext[path:%s] Init Parameters", servletContext.getContextPath()), 500);
        this.servletContext = servletContext;
    }

    @Override
    public void prepareConfigData(Map configData) throws Throwable {
        Enumeration<String> parameterNames = servletContext.getInitParameterNames();
        while (parameterNames.hasMoreElements()){
            String elementName = parameterNames.nextElement();
            configData.put(elementName, servletContext.getInitParameter(elementName));
        }
    }
}
