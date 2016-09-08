package net.sorokin.controller;



import org.junit.Before;
import org.junit.Test;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.mockito.Mockito.*;

public class BookControllerTest {


    private HttpServletRequest req;
    private HttpServletResponse resp;
    private BookController bookController;

    @Before
    public void setUp(){
       this.req = mock(HttpServletRequest.class);
       this.resp = mock(HttpServletResponse.class);
       this.bookController = new BookController();
    }

    @Test
    public void test_null_id() throws ServletException, IOException {
        //init
        when(req.getParameter("id")).thenReturn(null);
        //use
        bookController.doGet(req,resp);
        //check
        verify(req).getParameter("id");
        verify(resp).sendRedirect("/books");
        verifyNoMoreInteractions(req,resp);
    }

    @Test
    public void test_bad_id() throws ServletException, IOException {
        //init
        when(req.getParameter("id")).thenReturn("abc");
        //use
        bookController.doGet(req,resp);
        //check
        verify(req).getParameter("id");
        verify(resp).sendRedirect("/books");
        verifyNoMoreInteractions(req,resp);
    }
}
