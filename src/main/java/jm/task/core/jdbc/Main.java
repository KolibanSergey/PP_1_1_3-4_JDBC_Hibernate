package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    private static final UserService userService = new UserServiceImpl();

    public static void main(String[] args) {


        userService.createUsersTable();
        userService.saveUser("Tor", "Marvelev", (byte) 25);

        userService.saveUser("Batman", "Dici", (byte) 48);

        userService.saveUser("Neo", "Matrix", (byte) 30);

        userService.saveUser("Banny", "Looney", (byte) 13);

        userService.removeUserById(3);
        userService.getAllUsers();
        userService.cleanUsersTable();
        userService.dropUsersTable();


    }
}
