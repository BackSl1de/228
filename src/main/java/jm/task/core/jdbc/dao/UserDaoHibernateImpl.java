package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

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
        Query query = session.createSQLQuery("CREATE TABLE IF NOT EXISTS users " +
                "(id BIGINT NOT NULL GENERATED ALWAYS AS IDENTITY (INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 9223372036854775807 CACHE 1), " +
                "name VARCHAR(50) NOT NULL, lastName VARCHAR(50) NOT NULL, " +
                "age SMALLINT NOT NULL)");
        System.out.println("Таблица создана");
        query.executeUpdate();
        tr.commit();
        session.close();
    }

    @Override
    public void dropUsersTable() {
        Session session = sessionFactory.openSession();
        Transaction tr = session.beginTransaction();
        Query query = session.createSQLQuery("DROP TABLE IF EXISTS users");
        System.out.println("Таблица удалена");
        query.executeUpdate();
        tr.commit();
        session.close();
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Session session = sessionFactory.openSession();
        Transaction tr = session.beginTransaction();
        Query query = session.createSQLQuery("INSERT INTO users(name,lastName,age) " + "VALUES(?,?,?)");
        query.setString(1, name);
        query.setString(2, lastName);
        query.setByte(3, age);
        query.executeUpdate();
        tr.commit();
        session.close();
    }

    @Override
    public void removeUserById(long id) {
        Session session = sessionFactory.openSession();
        Transaction tr = session.beginTransaction();
        Query query = session.createSQLQuery("DELETE FROM users WHERE id =" + id);
        query.executeUpdate();
        tr.commit();
        session.close();
    }

    @Override
    public List<User> getAllUsers() {
        Session session = sessionFactory.openSession();
        Transaction tr = session.beginTransaction();
        List<User> users = session.createQuery("from User", User.class).getResultList();
        System.out.println(users);
        tr.commit();
        session.close();
        return users;
    }

    @Override
    public void cleanUsersTable() {
        String sql = "TRUNCATE TABLE users";
        Session session = sessionFactory.openSession();
        Transaction tr = session.beginTransaction();
        Query query = session.createSQLQuery(sql);
        System.out.println("Таблица очищена");
        query.executeUpdate();
        tr.commit();
        session.close();
    }
}
