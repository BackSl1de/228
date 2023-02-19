package jm.task.core.jdbc.util;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Util {
    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        Configuration configuration = new Configuration();
        try {
            sessionFactory = configuration.configure("hibernate.cfg.xml").buildSessionFactory();
            System.out.println("CONNECTED");
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return sessionFactory;
    }
}

