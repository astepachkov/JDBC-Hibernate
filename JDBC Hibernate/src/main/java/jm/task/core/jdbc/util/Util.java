package jm.task.core.jdbc.util;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/test-users1";
    private static final String DB_USER = "postgres";
    private static final String DB_PASS = "7778";

    public static Connection getConnection()  {
        try {
            return DriverManager.getConnection(DB_URL,DB_USER,DB_PASS);
        } catch (SQLException e) {
            throw new RuntimeException("Не получилось подключиться к базе данных", e);
        }
    }
}

// Тест подключения к DB:

//public static void main(String[] args) throws SQLException {
//    try {
//        Connection connection = getConnection();
//        if (connection.isValid(5)) {
//            System.out.println("Соединение с базой данных установлено успешно!");
//        } else {
//            System.out.println("Ошибка: Не удалось установить соединение с базой данных.");
//        }
//    } catch (SQLException e) {
//        System.out.println("Ошибка при подключении к базе данных: " + e.getMessage());
//    }
//}


    // реализуйте настройку соеденения с БД

