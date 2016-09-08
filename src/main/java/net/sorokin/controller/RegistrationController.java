package net.sorokin.controller;


import net.sorokin.dao.userdao.UserDao;
import net.sorokin.dao.userdao.UserDaoJdbcImpl;
import net.sorokin.entity.User;
import net.sorokin.tx.TransactionManager;
import net.sorokin.tx.TransactionManagerImpl;
import net.sorokin.utils.CheckUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.Callable;

public class RegistrationController extends HttpServlet {

    private UserDao userDao = new UserDaoJdbcImpl();
    private TransactionManager txManager = new TransactionManagerImpl();

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {

        final String email = request.getParameter("email");
        final String password = request.getParameter("password");
        final String name = request.getParameter("name");
        final String phoneNumber = request.getParameter("phoneNumber");

        if ((name == null || "".equals(name))
                || (email == null || "".equals(email))
                || (password == null || "".equals(password))
                || (phoneNumber == null || "".equals(phoneNumber))) {
            System.out.println("1");
           errorRegistration(request, response, "Sorry, you enter empty fild. Please, try again");
        } else {
            if (new CheckUtil().checkPassword(password)) {
                if (new CheckUtil().checkEmail(email)) {
                    try {
                        userDao.setDatasource(txManager);
                        User user = txManager.doInTransaction(new Callable<User>() {
                            public User call() throws Exception {
                                return userDao.selectByEmail(email);
                            }
                        });
                        if (user == null) {
                            final User newUser = new User();
                            newUser.setEmail(email.toLowerCase());
                            newUser.setPassword(password);
                            newUser.setName(name);
                            newUser.setPhoneNumber(phoneNumber);

                            int result = txManager.doInTransaction(new Callable<Integer>() {
                                public Integer call() throws Exception {
                                    return userDao.insert(newUser);
                                }
                            });
                            if (result == 1) {
                                request.getSession().setAttribute("user", newUser);
                                request.getRequestDispatcher("main.jsp").forward(request, response);
                            } else {
                                errorRegistration(request, response, "Sorry, it's a database error");
                            }
                        } else {
                            errorRegistration(request, response, "Sorry, this email already exists. Please, try again");
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                else{
                    errorRegistration(request, response, "Sorry, you email don't validate");
                }
            }
            else {
                errorRegistration(request, response, "Sorry, password must contains lowercase,uppercase characters and numbers.");
            }
        }
    }

    private static void errorRegistration(HttpServletRequest request, HttpServletResponse response, String text) throws ServletException, IOException {
        request.setAttribute("errorRegistered", text);
        request.getRequestDispatcher("registration.jsp").forward(request,response);
    }
}
