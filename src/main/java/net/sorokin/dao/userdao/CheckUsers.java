package net.sorokin.dao.userdao;


import net.sorokin.dao.daoexceptions.NotUniqueUserEmailException;
import net.sorokin.entity.User;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CheckUsers {

    public static void main(String[] args) throws SQLException {


        //doWorkWithUsers();

        UserDao userDaoJdbc = new UserDaoJdbcImpl();

        User user = userDaoJdbc.selectByEmail("mail4@gmail.com");
        System.out.println(user);
    }

    private static void doWorkWithUsers() {

        User user = null;

        List<User> users = new ArrayList<User>();

        UserDaoJdbcImpl userDaoJdbc = new UserDaoJdbcImpl();

        try {

            /*userDaoJdbc.deleteAllUsers();
            userDaoJdbc.zeroOutAutoIncrement();*/

            long start = System.nanoTime();
            for (int i = 1; i < 200; i++) {
                user = new User();
                user.setEmail("mail" + i + "@gmail.com");
                user.setPassword("Cucumber" + i);
                user.setName("Name" + i);
                user.setPhoneNumber("+38099700000" + i);
                userDaoJdbc.insert(user);
            }
            long end = System.nanoTime();

            System.out.println();
            System.out.println((end - start) / 1000000000);
            System.out.println();

            users = userDaoJdbc.selectAll();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        for (User userDao : users) {
            System.out.println(userDao);
        }
    }
}
