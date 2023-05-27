package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

import javax.transaction.Transactional;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;


public class Main {
    private static final UserService userService = new UserServiceImpl();
    private static Util dataSource;
    @Transactional
    public static void main(String[] args) {

        Connection connection = dataSource.getConnection(); // (1)

        try (connection) {
            connection.setAutoCommit(false); // (2)
            Scanner scanner = new Scanner(System.in);


            connection.commit(); // (3)

        } catch (SQLException e) {
            try {
                connection.rollback(); // (4)
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }
        userService.createUsersTable();
        userService.saveUser("Tor", "Marvelev", (byte) 25);

        userService.saveUser("Batman", "Dici", (byte) 48);

        userService.saveUser("Neo", "Matrix", (byte) 30);

        userService.saveUser("Banny", "Looney", (byte) 13);

        userService.removeUserById(1);
        userService.getAllUsers();
        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}
