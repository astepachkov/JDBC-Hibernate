package jm.task.core.jdbc.dao;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class UserDaoHibernateImpl extends HibernateUtil implements UserDao {
    public UserDaoHibernateImpl() {
    }

    @Override
    public void createUsersTable() {
        try (Session session = getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            String sql = "CREATE TABLE IF NOT EXISTS users (" +
                    "id SERIAL PRIMARY KEY, " +
                    "name VARCHAR(50) NOT NULL, " +
                    "lastName VARCHAR(50) NOT NULL, " +
                    "age SMALLINT NOT NULL)";
            session.createSQLQuery(sql).executeUpdate();
            transaction.commit();
            System.out.println("Таблица успешно создана");
        } catch (HibernateException e) {
            System.out.println("Ошибка при создании");
            e.printStackTrace();
        }
    }



    @Override
    public void dropUsersTable() {
        try (Session session = getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            String sql = "DROP TABLE IF EXISTS users";
            session.createSQLQuery(sql).executeUpdate();
            transaction.commit();
            System.out.println("Таблица успешно удалена");
        } catch (HibernateException e) {
            System.out.println("Ошибка при удалении таблицы users");
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try (Session session = getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            User user = new User(name, lastName, age);
            session.save(user);
            transaction.commit();
            System.out.println("Новый пользователь добавлен в таблицу");
        } catch (HibernateException e) {
            System.out.println("Ошибка при добавлении пользователя");
            e.printStackTrace();
        }
    }

    @Override
    public void removeUserById(long id) {
        try (Session session = getSessionFactory().openSession())
        {Transaction transaction = session.beginTransaction();
            User user = session.get(User.class, id);
            if (user != null) {
            session.delete(user);
            transaction.commit();
            System.out.println("Пользователь с ID: " + id + " удален"); }
            else {System.out.println("Пользователь с ID: "  + id +  " не найден в таблице");}
        } catch (HibernateException e){
            System.out.println("Ошибка при удалении");
            e.printStackTrace();

        }
    }

    @Override
    public List<User> getAllUsers() {
        try (Session session = getSessionFactory().openSession()){
            return session.createQuery("from User", User.class).list();
        } catch (HibernateException e){
            System.out.println("Ошибка при получении пользователей");
        }
        return null;
    }

    @Override
    public void cleanUsersTable() {
        try (Session session = getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.createQuery("delete from User").executeUpdate();
            transaction.commit();
            System.out.println("Таблица успешно очищена");
        } catch (HibernateException e) {
            System.out.println("Ошибка при удалении таблицы Users");
            e.printStackTrace();
        }
    }
}
