package net.sorokin.controller;


import net.sorokin.dao.bookdao.BookDao;
import net.sorokin.dao.bookdao.BookDaoJdbcImpl;
import net.sorokin.entity.Book;
import net.sorokin.tx.TransactionManager;
import net.sorokin.tx.TransactionManagerImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.Callable;

public class BookDeleteFromCartController extends HttpServlet{

    private BookDao bookDao = new BookDaoJdbcImpl();
    private final String BOOK_IN_BUCKET = "bookInBucket";
    private TransactionManager txManager = new TransactionManagerImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idStr = req.getParameter("id");
        if (idStr != null && !idStr.equals("")) {
            try {
                bookDao.setDatasource(txManager);
                final Integer id = Integer.valueOf(idStr);
                Book book = txManager.doInTransaction(new Callable<Book>() {
                    public Book call() throws Exception {
                        return bookDao.selectByID(id);
                    }
                });
                HttpSession session = req.getSession(false);

                Map<Book, Integer> oldBucket = (Map<Book, Integer>) session.getAttribute(BOOK_IN_BUCKET);

                if (oldBucket == null) {
                    session.setAttribute(BOOK_IN_BUCKET, Collections.singletonMap(book, 1));
                } else {
                    Map<Book, Integer> newBucket = new LinkedHashMap<Book, Integer>(oldBucket);
                    int number = oldBucket.get(book);
                    if(number == 1){
                        newBucket.remove(book);
                    }
                    else {
                        newBucket.put(book, newBucket.get(book) - 1);
                    }
                    session.setAttribute(BOOK_IN_BUCKET, Collections.unmodifiableMap(newBucket));
                }
                resp.sendRedirect("/bucket");
                return;
            } catch (Exception e) {
                req.getRequestDispatcher("main.jsp").forward(req, resp);
                return;
            }
        }
        else{
            req.getRequestDispatcher("main.jsp").forward(req, resp);
            return;
        }

    }
}
