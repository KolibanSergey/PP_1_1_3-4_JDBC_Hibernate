package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;

import java.util.List;

public class UserServiceImpl implements UserService {
    UserDao userDaoJDBC = new UserDaoJDBCImpl();
    UserDao userDaoHibernate = new UserDaoHibernateImpl();

    public void createUsersTable() {
        userDaoJDBC.createUsersTable();
        System.out.println("Таблица users успешно создана");
    }

    public void dropUsersTable() {
        userDaoJDBC.dropUsersTable();
        System.out.println("Таблица users успешно удалена");
    }

    public void saveUser(String name, String last_name, byte age) {
        userDaoJDBC.saveUser(name, last_name, age);
        System.out.println("User с именем - " + name + " добавлен в базу данных");
    }

    public void removeUserById(long id) {
        userDaoJDBC.removeUserById(id);
        System.out.println("User с id - " + id + " удален из базы данных");

    }

    public List<User> getAllUsers() {

        List<User> users = userDaoJDBC.getAllUsers();
        for (User user : users) {
            System.out.println(user);
        }
        return users;
    }

    public void cleanUsersTable() {
        userDaoJDBC.cleanUsersTable();
        System.out.println("Таблица users успешно очищена");
    }

}

