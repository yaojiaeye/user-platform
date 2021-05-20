package com.yaojia.projects.user.orm.jpa;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import com.yaojia.projects.user.domain.User;
import javax.persistence.*;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

public class JpaDemo {

    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory =
                Persistence.createEntityManagerFactory("emf", getProperties());
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        User user = new User();
        user.setName("小马哥");
        user.setPassword("******");
        user.setEmail("mercyblitz@gmail.com");
        user.setPhoneNumber("123456789");
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(user);
        transaction.commit();

        System.out.println(entityManager.find(User.class, user.getId()));
    }

    private static Map<String, Object> getProperties() {
        Map<String, Object> properties = new HashMap<>();
        properties.put("hibernate.dialect", "org.hibernate.dialect.DerbyDialect");
        properties.put("hibernate.id.new_generator_mappings", false);
        properties.put("hibernate.connection.datasource", getDataSource());
        return properties;
    }

    private static DataSource getDataSource() {
        MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setUser("root");
        dataSource.setPassword("mysqladmin");
        dataSource.setURL("jdbc:mysql://localhost:3306/user-platform");
        dataSource.setCreateDatabaseIfNotExist(true);
        return dataSource;
    }
}
