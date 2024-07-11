package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.model.User;

import java.util.List;

public class UserServiceHiber implements UserService {

    private static final UserDao hiberDao = new UserDaoHibernateImpl();

    @Override
    public void createUsersTable() {
        hiberDao.createUsersTable();
    }

    @Override
    public void dropUsersTable() {
        hiberDao.dropUsersTable();

    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        hiberDao.saveUser(name, lastName, age);
    }

    @Override
    public void removeUserById(long id) {
        hiberDao.removeUserById(id);
    }

    @Override
    public List<User> getAllUsers() {
        return hiberDao.getAllUsers();
    }

    @Override
    public void cleanUsersTable() {
        hiberDao.cleanUsersTable();
    }
}
