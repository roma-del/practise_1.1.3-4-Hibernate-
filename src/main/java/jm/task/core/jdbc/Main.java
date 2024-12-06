package jm.task.core.jdbc;


import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

public class Main {
    public static void main(String[] args) {

        UserServiceImpl use = new UserServiceImpl();
        use.createUsersTable();
        use.saveUser("Romik","Zavalkin", (byte) 24);
        use.saveUser("Vlad","Kobyakov", (byte) 29);
        use.saveUser("Rurik","First", (byte) 127);
        use.saveUser("Nogano","Dead", (byte) 7);
        use.getAllUsers();
        use.cleanUsersTable();
        use.dropUsersTable();
        // реализуйте алгоритм здесь
    }
}
