package net.sorokin.controller;


import net.sorokin.dao.userdao.UserDao;
import net.sorokin.dao.userdao.UserDaoJdbcImpl;
import net.sorokin.entity.User;
import net.sorokin.tx.TransactionManager;
import net.sorokin.tx.TransactionManagerImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.Callable;

public class LoginController extends HttpServlet {

    private UserDao userDao = new UserDaoJdbcImpl();
    private TransactionManager txManager = new TransactionManagerImpl();

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {

        final String email = request.getParameter("email");
        final String password = request.getParameter("password");

        User user = null;
        try {
            userDao.setDatasource(txManager);
            user = txManager.doInTransaction(new Callable<User>() {
                public User call() throws Exception {
                    return userDao.selectByEmail(email);
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
        if (user == null) {
            request.setAttribute("errorLogin", "Sorry, this login and password don't find. Please enter correct login and password.");
            request.getRequestDispatcher("login.jsp").forward(request,response);
        } else {
            if(email.equals(user.getEmail()) && password.equals(user.getPassword())) {
                request.getSession().setAttribute("user", user);
                request.getRequestDispatcher("main.jsp").forward(request, response);
            }
            else{
                request.setAttribute("errorLogin", "Sorry, this login and password don't find. Please enter correct login and password.");
                request.getRequestDispatcher("login.jsp").forward(request,response);
            }
        }
    }

}
