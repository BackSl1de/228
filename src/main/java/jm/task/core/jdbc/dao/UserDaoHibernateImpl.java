package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    private final SessionFactory sessionFactory;

    public UserDaoHibernateImpl() {
        this.sessionFactory = Util.getSessionFactory();
    }

    @Override
    public void createUsersTable() {
        Session session = sessionFactory.openSession();
        Transaction tr = session.beginTransaction();
        session.createSQLQuery("CREATE TABLE IF NOT EXISTS users " +
                "(id BIGINT NOT NULL GENERATED ALWAYS AS IDENTITY (INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 9223372036854775807 CACHE 1), " +
                "name VARCHAR(50) NOT NULL, lastName VARCHAR(50) NOT NULL, " +
                "age SMALLINT NOT NULL)").executeUpdate();
        System.out.println("Таблица создана");
        tr.commit();
    }

    @Override
    public void dropUsersTable() {
        Session session = sessionFactory.openSession();
        Transaction tr = session.beginTransaction();
        session.createSQLQuery("DROP TABLE IF EXISTS users").executeUpdate();
        System.out.println("Таблица удалена");
        tr.commit();
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Session session = sessionFactory.openSession();
        Transaction tr = session.beginTransaction();
        User user = new User(name, lastName, age);
        session.save(user);
        System.out.println("пользователь с id " + user.getId() + "создан");
        tr.commit();
    }

    @Override
    public void removeUserById(long id) {
        Session session = sessionFactory.openSession();
        Transaction tr = session.beginTransaction();
        User user = new User();
        user.setId(id);
        session.delete(user);
        System.out.println("пользователь с id " + id + " удалён");
        tr.commit();
    }

    @Override
    public List<User> getAllUsers() {
        Session session = sessionFactory.openSession();
        Transaction tr = session.beginTransaction();
        List<User> users = session.createQuery("from User", User.class).list();
        System.out.println(users);
        tr.commit();
        return users;
    }

    @Override
    public void cleanUsersTable() {
        Session session = sessionFactory.openSession();
        Transaction tr = session.beginTransaction();
        session.createQuery("delete from User").executeUpdate();
        System.out.println("Таблица очищена");
        tr.commit();
    }
}
