package net.sorokin.controller;



import org.junit.Before;
import org.junit.Test;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.mockito.Mockito.*;

public class BookDeleteFromCartControllerTest {
    private HttpServletRequest req;
    private HttpServletResponse resp;
    private BookDeleteFromCartController bookController;
    private RequestDispatcher requestDispatcher;

    @Before
    public void setUp() {
        this.req = mock(HttpServletRequest.class);
        this.resp = mock(HttpServletResponse.class);
        this.requestDispatcher = mock(RequestDispatcher.class);
        this.bookController = new BookDeleteFromCartController();
    }

    @Test
    public void test_null_id() throws ServletException, IOException {
        //init
        when(req.getParameter("id")).thenReturn(null);
        when(req.getRequestDispatcher("main.jsp")).thenReturn(requestDispatcher);
        //use
        bookController.doPost(req, resp);
        //check
        verify(req).getParameter("id");
        verify(req).getRequestDispatcher("main.jsp");
        verify(requestDispatcher).forward(req,resp);
        verifyNoMoreInteractions(req, resp);
    }

    @Test
    public void test_bad_id() throws ServletException, IOException {
        //init
        when(req.getParameter("id")).thenReturn("abc");
        when(req.getRequestDispatcher("main.jsp")).thenReturn(requestDispatcher);
        //use
        bookController.doPost(req, resp);
        //check
        verify(req).getParameter("id");
        verify(req).getRequestDispatcher("main.jsp");
        verify(requestDispatcher).forward(req,resp);
        verifyNoMoreInteractions(req, resp);
    }
}
