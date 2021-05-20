package com.yaojia.projects.user.sql;

import com.yaojia.projects.user.context.ComponentContext;
import com.yaojia.projects.user.domain.User;

import javax.annotation.Resource;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.sql.DataSource;
import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBConnectionManager {

    private final Logger logger = Logger.getLogger(DBConnectionManager.class.getName());

    @Resource(name = "jdbc/UserPlatformDB")
    private DataSource dataSource;

    @Resource(name = "bean/EntityManager")
    private EntityManager entityManager;

    public EntityManager getEntityManager() {
        logger.info("当前 EntityManager 实现类：" + entityManager.getClass().getName());
        return entityManager;
    }

    public Connection getConnection() {
        // 依赖查找
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
        } catch (SQLException e) {
            logger.log(Level.SEVERE, e.getMessage());
        }
        if (connection != null) {
            logger.log(Level.INFO, "获取 JNDI 数据库连接成功！");
        }
        return connection;
    }

//    public static final String DROP_USERS_TABLE_DDL_SQL = "DROP TABLE users";


}
