package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import java.sql.*;
import org.hibernate.service.ServiceRegistry;

public class Util {
    private static final String URL = "jdbc:mysql://localhost:3306/userdb";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";
    private static SessionFactory sessionFactory = null;
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DIALECT = "org.hibernate.dialect.MySQLDialect";

    public static Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName(DRIVER);
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return connection;

    }

    public static org.hibernate.SessionFactory hibernateConnection() {
        if (sessionFactory == null){
            try {
                Configuration configuration = new Configuration()
                        .setProperty("hibernate.connection.driver_class", DRIVER)
                        .setProperty("hibernate.connection.url", URL)
                        .setProperty("hibernate.connection.username", USERNAME)
                        .setProperty("hibernate.connection.password", PASSWORD)
                        .setProperty("hibernate.dialect", DIALECT)
                        .addAnnotatedClass(User.class);
                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties()).build();
                sessionFactory = configuration.buildSessionFactory(serviceRegistry);
            } catch (HibernateException e) {
                e.printStackTrace();
            }
        }
        return sessionFactory;
    }
}



