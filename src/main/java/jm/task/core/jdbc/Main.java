package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoHibernateImpl;

import java.sql.SQLException;

public class Main {

    public static void main(String[] args) throws SQLException {
        UserDaoHibernateImpl ud = new UserDaoHibernateImpl();
        ud.dropUsersTable();
        ud.createUsersTable();
        ud.saveUser("dsfsdf", "weqweqw", (byte) 22);
        ud.removeUserById(1);
    }
}




