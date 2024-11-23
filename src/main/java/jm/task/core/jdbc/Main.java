package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.util.Util;

public class Main {
    public static void main(String[] args) {
        Util.getConnection();
        UserDao userDao = new UserDaoJDBCImpl();

        userDao.createUsersTable();

        userDao.saveUser("Zaur", "Pedro", (byte) 18);
        userDao.saveUser("Abdul", "Doter", (byte) 20);
        userDao.saveUser("Zagid", "Espresso", (byte) 19);
        userDao.saveUser("Israpil", "Marshutka", (byte) 38);

        userDao.removeUserById(1);
        userDao.getAllUsers();
        userDao.cleanUsersTable();
        userDao.dropUsersTable();
    }
}
