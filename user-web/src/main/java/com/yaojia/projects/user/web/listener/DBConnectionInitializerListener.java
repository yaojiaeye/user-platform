package com.yaojia.projects.user.web.listener;
import com.yaojia.projects.user.context.ComponentContext;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class DBConnectionInitializerListener implements ServletContextListener {

    private ServletContext servletContext;


    @Override
    public void contextInitialized(ServletContextEvent sce) {
        this.servletContext = sce.getServletContext();
        ComponentContext context = new ComponentContext();
        context.init(servletContext);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }

    protected Connection getConnection(){
        Context context = null;
        Connection connection = null;
        try {
            context = new InitialContext();
            Context envContext = (Context) context.lookup("java:comp/env");
            DataSource dataSource = (DataSource) envContext.lookup("jdbc/UserPlatformDB");
            connection = dataSource.getConnection();
            if (connection !=null){
                servletContext.log("获取jndi成功" + connection);
            }
        } catch (NamingException | SQLException e) {
            throw new RuntimeException(e);
        } finally {

        }
        return connection;
    }
}
