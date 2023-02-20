package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.Properties;

public class Util {
    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        Properties properties = new Properties();
        properties.setProperty("hibernate.connection.url","jdbc:postgresql://localhost:5432/gospodipomogi");
        properties.setProperty("hibernate.connection.username","postgres");
        properties.setProperty("hibernate.connection.password","Lvbnhbq111");
        properties.setProperty("hibernate.connection.driver_class","org.postgresql.Driver");
        try {
            sessionFactory = new Configuration().addAnnotatedClass(User.class).addProperties(properties).buildSessionFactory();
            System.out.println("CONNECTED");
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return sessionFactory;
    }
}

