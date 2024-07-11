package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import javax.swing.plaf.nimbus.State;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
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
            System.out.println("Таблица users успешно создана (если она не существовала)");
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
            System.out.println("Командир, таблица успешно уничтожена, если она вообще была");
        } catch (SQLException e) {
            System.out.println("Ошбибка удаления таблицы");
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        String sql = "INSERT INTO users (name,lastname, age) VALUES (?,?,?)";

        createUsersTable();

       try (Connection connectToPostgres = Util.getConnection();
        PreparedStatement SaveUserStmt = connectToPostgres.prepareStatement(sql)){
           SaveUserStmt.setString(1,name);
           SaveUserStmt.setString(2,lastName);
           SaveUserStmt.setByte(3,age);

           int modifiedLines = SaveUserStmt.executeUpdate();

           if (modifiedLines > 0) {
               System.out.println("Командир, пользователеь с именем " + name + " добавлен в базу данных. Все четко");
           } else {
               System.out.println("Командир, что то пошло не по плану, пользователь по имени " + name + " сбежал из таблицы");
           }
       } catch (SQLException e) {
           System.out.println("Что то пошло не так при добавление пользователя в базу" + name);
           e.printStackTrace();
       }
    }

    public void removeUserById(long id) {

    }

    public List<User> getAllUsers() {
        return null;
    }

    public void cleanUsersTable() {

    }

    public static void main(String[] args) {
        new UserDaoJDBCImpl().saveUser("САНЯ","СТЕПАЧКОВ", (byte) 27);
}

}



