//package jm.task.core.jdbc.dao;
//
//
//import jm.task.core.jdbc.model.User;
//import jm.task.core.jdbc.util.Util;
//
//import java.sql.*;
//import java.util.ArrayList;
//import java.util.List;
//
//public class UserDaoJDBCImpl implements UserDao {
//    private final Connection connection;
//
//
//    public UserDaoJDBCImpl() {
//        this.connection = Util.getConnection();
//
//    }
//
//    public void createUsersTable() {
//        String sql = "CREATE TABLE IF NOT EXISTS users " +
//                "(id BIGINT NOT NULL GENERATED ALWAYS AS IDENTITY (INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 9223372036854775807 CACHE 1), " +
//                "name VARCHAR(50) NOT NULL, lastName VARCHAR(50) NOT NULL, " +
//                "age SMALLINT NOT NULL)";
//        try (Statement statement = connection.createStatement()) {
//            statement.executeUpdate(sql);
//            System.out.println("САДИТЕСЬ ГОСПОДА");
//        } catch (SQLException e) {
//            System.out.println(e);
//        }
//
//
//    }
//
//    public void dropUsersTable() {
//        String sql = "DROP TABLE IF EXISTS users";
//        try (PreparedStatement statement = connection.prepareStatement(sql)) {
//            statement.executeUpdate();
//            System.out.println("СТОЛ УДАЛЁН");
//        } catch (SQLException e) {
//            System.out.println(e);
//        }
//
//    }
//
//    public void saveUser(String name, String lastName, byte age) {
//        String sql = "INSERT INTO " + "users(name,lastName,age) " + "VALUES(?,?,?)";
//        try (PreparedStatement statement = connection.prepareStatement(sql)) {
//            statement.setString(1, name);
//            statement.setString(2, lastName);
//            statement.setByte(3, age);
//            statement.executeUpdate();
//        } catch (SQLException e) {
//            System.out.println(e);
//        }
//    }
//
//
//    public void removeUserById(long id) {
//        String sql = "DELETE FROM users WHERE id =" + id;
//        try (PreparedStatement statement = connection.prepareStatement(sql)) {
//            statement.executeUpdate();
//        } catch (SQLException e) {
//            System.out.println(e);
//        }
//    }
//
//    public List<User> getAllUsers() {
//        List<User> users = new ArrayList<>();
//        String sql = "SELECT * FROM users";
//        try (Statement statement = connection.createStatement();
//             ResultSet rs = statement.executeQuery(sql)) {
//            while (rs.next()) {
//                User user = new User();
//                user.setId(rs.getLong("id"));
//                user.setName(rs.getString("name"));
//                user.setLastName(rs.getString("lastName"));
//                user.setAge(rs.getByte("age"));
//
//                users.add(user);
//                System.out.println(user);
//            }
//        } catch (SQLException e) {
//            System.out.println(e);
//        }
//        return users;
//    }
//
//
//    public void cleanUsersTable() {
//        String sql = "TRUNCATE TABLE users";
//        try (PreparedStatement statement = connection.prepareStatement(sql)) {
//            statement.executeUpdate();
//            System.out.println("ТАБЛИЦА ОЧИЩЕНА");
//        } catch (SQLException e) {
//            System.out.println(e);
//
//        }
//
//    }
//}
//
