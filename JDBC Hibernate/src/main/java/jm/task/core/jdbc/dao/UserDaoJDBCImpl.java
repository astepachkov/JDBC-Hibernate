package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import javax.swing.plaf.nimbus.State;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {
    }

    // работает
    public void createUsersTable() {
        String sql = "CREATE TABLE IF NOT EXISTS users (" +
                "id SERIAL PRIMARY KEY, " +
                "name VARCHAR(50) NOT NULL, " +
                "lastName VARCHAR(50) NOT NULL, " +
                "age SMALLINT NOT NULL)";

        try (Connection connectToPostgres = Util.getConnection();
             Statement createTableStmt = connectToPostgres.createStatement()) {
            createTableStmt.executeUpdate(sql);
            System.out.println("Таблица users успешно создана");
        } catch (SQLException e) {
            System.out.println("Ошибка при создании таблицы Users");
            e.printStackTrace();
        }
    }
    // работает
    public void dropUsersTable() {
        String sql = "DROP TABLE IF EXISTS users";

        try(Connection connectToPostgres = Util.getConnection();
        Statement DeleteTableStmt = connectToPostgres.createStatement()){
            DeleteTableStmt.executeUpdate(sql);
            System.out.println("Таблица стерта");
        } catch (SQLException e) {
            System.out.println("Ошбибка удаления таблицы");
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        String sql = "INSERT INTO users (name,lastname, age) VALUES (?,?,?)";


       try (Connection connectToPostgres = Util.getConnection();
        PreparedStatement SaveUserStmt = connectToPostgres.prepareStatement(sql)){
           SaveUserStmt.setString(1,name);
           SaveUserStmt.setString(2,lastName);
           SaveUserStmt.setByte(3,age);

           int modifiedLines = SaveUserStmt.executeUpdate();

           if (modifiedLines > 0) {
               System.out.println("Пользователеь с именем: " + name + " добавлен в базу данных.");
           } else {
               System.out.println("Мы не смогли добавить пользователя: " + name);
           }
       } catch (SQLException e) {
           System.out.println("Что то пошло не так при добавление пользователя в базу" + name);
           e.printStackTrace();
       }
    }

    public void removeUserById(long id) {

            String sql = "DELETE FROM users WHERE id = ?";
            try (Connection connectToPosgres = Util.getConnection();
                 PreparedStatement pstmt = connectToPosgres.prepareStatement(sql)) {
                pstmt.setLong(1, id);
                int modifiedLines = pstmt.executeUpdate();
                if (modifiedLines > 0) {
                    System.out.println("Пользователь уничтожен - его бывший ID " + id);
                } else {
                    System.out.println("Мы не нашли пользлвателя по такому ID: " + id);
                }
            } catch (SQLException e) {
                System.out.println("Ошибка удаления пользователя");
                e.printStackTrace();
            }
    }

    public List<User> getAllUsers() { List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM users";

        try (var conn = Util.getConnection();
             var stmt = conn.createStatement();
             var resultSet = stmt.executeQuery(sql)) {
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setAge(resultSet.getByte("age"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastname"));
                users.add(user);
            }
        } catch (SQLException e) {
            System.out.println();
        }
        return users;
    }

    public void cleanUsersTable() {
        String sql = "TRUNCATE TABLE users";
        try (
        Connection connectToPostgres = Util.getConnection();
        PreparedStatement truncateTableStmt = connectToPostgres.prepareStatement(sql))
        {
            truncateTableStmt.executeUpdate();
            System.out.println("Таблица users успешно очищена");

        } catch (SQLException e) {
            System.out.println("Ошибка при очистке таблицы users");
            e.printStackTrace();
        }
    }
}



