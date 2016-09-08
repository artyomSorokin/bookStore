package net.sorokin.controller;



import org.junit.Before;
import org.junit.Test;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.mockito.Mockito.*;

public class CartControllerTest {
    private HttpServletRequest req;
    private HttpServletResponse resp;
    private CartController cartController;
    private RequestDispatcher requestDispatcher;

    @Before
    public void setUp() {
        this.req = mock(HttpServletRequest.class);
        this.resp = mock(HttpServletResponse.class);
        this.requestDispatcher = mock(RequestDispatcher.class);
        this.cartController = new CartController();
    }

    @Test
    public void test_session_null() throws ServletException, IOException {
        //init
        when(req.getSession(false)).thenReturn(null);
        when(req.getRequestDispatcher("bucketClear.jsp")).thenReturn(requestDispatcher);
        //use
        cartController.doGet(req, resp);
        //check
        verify(req).getSession(false);
        verify(req).getRequestDispatcher("bucketClear.jsp");
        verify(requestDispatcher).forward(req,resp);
        verifyNoMoreInteractions(req, resp);
    }

    @Test
    public void test_session_exception() throws ServletException, IOException {
        //init
        when(req.getSession(false)).thenThrow(new RuntimeException());
        when(req.getRequestDispatcher("main.jsp")).thenReturn(requestDispatcher);
        //use
        cartController.doGet(req, resp);
        //check
        verify(req).getSession(false);
        verify(req).getRequestDispatcher("main.jsp");
        verify(requestDispatcher).forward(req,resp);
        verifyNoMoreInteractions(req, resp);
    }
}
