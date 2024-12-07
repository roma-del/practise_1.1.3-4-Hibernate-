package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Environment;


import java.lang.module.Configuration;
import java.sql.*;
import java.util.Properties;


public class Util {
    private static SessionFactory sessionFactory;
    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            StandardServiceRegistry ssr = null;
            try {
                ssr = new StandardServiceRegistryBuilder()
                        .applySetting(Environment.DRIVER, "com.mysql.cj.jdbc.Driver")
                        .applySetting(Environment.URL, "jdbc:mysql://localhost:3306/testdatabase?useSSL=false&serverTimezone=UTC")
                        .applySetting(Environment.USER, user)
                        .applySetting(Environment.PASS, password)
                        .applySetting(Environment.DIALECT, "org.hibernate.dialect.MySQL8Dialect")
                        .applySetting(Environment.SHOW_SQL, true)
                        .applySetting(Environment.HBM2DDL_AUTO, "none")
                        .build();


                Metadata meta = new MetadataSources(ssr)
                        .addAnnotatedClass(User.class)
                        .getMetadataBuilder().build();

                sessionFactory = meta.getSessionFactoryBuilder().build();

            } catch (Exception e) {
                e.printStackTrace();
                if (ssr != null) {
                    StandardServiceRegistryBuilder.destroy(ssr);
                }
            }
        }
        return sessionFactory;
    }
    public static void shutdown() {
        if (sessionFactory != null) {
            sessionFactory.close();
        }
    }


    static String url = "jdbc:mysql://localhost:3306/testdatabase";
    static String user = "root";
    static String password = "root";

    public static Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

}
