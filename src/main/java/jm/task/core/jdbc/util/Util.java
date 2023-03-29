package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import java.util.Properties;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    // реализуйте настройку соеденения с БД
    private static SessionFactory factory;
    public static SessionFactory getFactory() {
        if (factory == null) {
            try {
                Configuration config = new Configuration();
                Properties settings = new Properties();
                settings.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
                settings.put(Environment.URL, "jdbc:mysql://localhost:3306/dbmysql");
                settings.put(Environment.USER, "root");
                settings.put(Environment.PASS, "Bulagat82");
                settings.put(Environment.DIALECT, "org.hibernate.dialect.MySQL5Dialect");
                settings.put(Environment.SHOW_SQL, "true");
                settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
                settings.put(Environment.HBM2DDL_AUTO, "");

                config.setProperties(settings)
                        .addAnnotatedClass(User.class);

                ServiceRegistry service = new StandardServiceRegistryBuilder()
                        .applySettings(config.getProperties()).build();

                factory = config.buildSessionFactory(service);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return factory;
    }
}