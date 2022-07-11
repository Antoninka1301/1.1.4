package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import java.util.Properties;


public class Util {
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static String dbURL2 = "jdbc:mysql://localhost/base2";
    private static String dbUsername = "root";
    private static String dbPassword = "Antonchik0507";
    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration cfg = new Configuration();
                Properties properties = new Properties();
                properties.put(Environment.DRIVER, DRIVER);
                properties.put(Environment.URL, dbURL2);
                properties.put(Environment.USER, dbUsername);
                properties.put(Environment.PASS, dbPassword);
                properties.put(Environment.SHOW_SQL, "true");
                properties.put(Environment.DIALECT, "org.hibernate.dialect.MySQLDialect");

                cfg.setProperties(properties);
                cfg.addAnnotatedClass(User.class);

                ServiceRegistry registry = new StandardServiceRegistryBuilder()
                        .applySettings(cfg.getProperties()).build();
                sessionFactory = cfg.buildSessionFactory(registry);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return sessionFactory;
    }
}






