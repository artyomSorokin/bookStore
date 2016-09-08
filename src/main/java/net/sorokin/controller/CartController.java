package net.sorokin.controller;

import net.sorokin.entity.Book;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;


public class CartController extends HttpServlet {

    private final String BOOK_IN_BUCKET = "bookInBucket";

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            HttpSession session = req.getSession(false);
            if (session == null) {
                req.getRequestDispatcher("bucketClear.jsp").forward(req, resp);
            } else {
                Map<Book, Integer> oldBucket = (Map<Book, Integer>) session.getAttribute(BOOK_IN_BUCKET);
                if (oldBucket == null || oldBucket.size() == 0) {
                    req.getRequestDispatcher("bucketClear.jsp").forward(req, resp);
                    return;
                } else {
                    req.getRequestDispatcher("bucketFull.jsp").forward(req, resp);
                    return;
                }
            }
        } catch (Exception e) {
            req.getRequestDispatcher("main.jsp").forward(req, resp);
            return;
        }
    }
}
