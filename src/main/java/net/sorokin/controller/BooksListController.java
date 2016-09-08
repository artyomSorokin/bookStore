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
import java.io.IOException;
import java.util.List;
import java.util.concurrent.Callable;

public class BooksListController extends HttpServlet {

    private BookDao bookDao = new BookDaoJdbcImpl();
    private TransactionManager txManager = new TransactionManagerImpl();

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            bookDao.setDatasource(txManager);
            System.out.println("set tx maneger");
            List<Book> books = txManager.doInTransaction(new Callable<List<Book>>() {
                public List<Book> call() throws Exception {
                    System.out.println("before selectAll");
                    return bookDao.selectAll();
                }
            });
            req.setAttribute("books", books);
            req.getRequestDispatcher("booksList.jsp").forward(req, resp);
            return;
        } catch (Exception e) {
            req.getRequestDispatcher("main.jsp").forward(req, resp);
            return;
        }
    }
}
