package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceHiber;
import jm.task.core.jdbc.service.UserServiceImpl;
import java.util.List;

public class Main {

    private final static UserService userService = new UserServiceImpl();
    private final static UserService hiberService = new UserServiceHiber();
    public static void main(String[] args) {
        // алгоритм JDBC
//        userService.createUsersTable();
//        userService.saveUser("FFF","Afaf",(byte)24);
//        userService.saveUser("DDD","Afaf",(byte)24);
//        userService.saveUser("CCC","Afaf",(byte)24);
//        userService.saveUser("BBB","Afaf",(byte)24);
//        userService.saveUser("AAA","Afaf",(byte)24);
//        userService.removeUserById(3);
//        userService.getAllUsers().forEach(System.out::println);
//        userService.cleanUsersTable();
//        userService.dropUsersTable();

        // алгоритм Hibernate
        hiberService.createUsersTable();
        hiberService.saveUser("Саня","Иванов",(byte) 20);
        hiberService.saveUser("Витя","Алексеев",(byte) 23);
        hiberService.saveUser("Лена","Петросян",(byte) 25);
        hiberService.saveUser("Маша","Ложкина",(byte) 35);
        hiberService.getAllUsers().forEach(System.out::println);
        hiberService.removeUserById(3);
        hiberService.cleanUsersTable();
        hiberService.dropUsersTable();

    }
}
