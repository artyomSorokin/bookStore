package net.sorokin.controller;



import org.junit.Before;
import org.junit.Test;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static org.mockito.Mockito.*;

public class LogoutControllerTest {
    private HttpServletRequest req;
    private HttpServletResponse resp;
    private LogoutController logoutController;
    private RequestDispatcher requestDispatcher;
    private HttpSession session;

    @Before
    public void setUp() {
        this.req = mock(HttpServletRequest.class);
        this.resp = mock(HttpServletResponse.class);
        this.requestDispatcher = mock(RequestDispatcher.class);
        this.logoutController = new LogoutController();
        this.session = mock(HttpSession.class, withSettings().defaultAnswer(RETURNS_SMART_NULLS));
    }

    @Test
    public void test_noNull_session() throws ServletException, IOException {
        //init
        when(req.getSession(false)).thenReturn(session);
        when(req.getRequestDispatcher("main.jsp")).thenReturn(requestDispatcher);

        //use
        logoutController.doGet(req,resp);

        //check
        verify(req).getSession(false);
        verify(session).invalidate();
        verify(req).getRequestDispatcher("main.jsp");
        verify(requestDispatcher).forward(req,resp);
        verifyNoMoreInteractions(req,resp);
    }

    @Test
    public void test_exception_session() throws ServletException, IOException {
        //init
        when(req.getSession(false)).thenThrow(new RuntimeException());
        when(req.getRequestDispatcher("main.jsp")).thenReturn(requestDispatcher);

        //use
        logoutController.doGet(req,resp);

        //check
        verify(req).getSession(false);
        verify(req).getRequestDispatcher("main.jsp");
        verify(requestDispatcher).forward(req,resp);
        verifyNoMoreInteractions(req,resp);
    }

    @Test
    public void test_null_session() throws ServletException, IOException {
        //init
        when(req.getSession(false)).thenReturn(null);
        when(req.getRequestDispatcher("main.jsp")).thenReturn(requestDispatcher);

        //use
        logoutController.doGet(req,resp);

        //check
        verify(req).getSession(false);
        verify(req).getRequestDispatcher("main.jsp");
        verify(requestDispatcher).forward(req,resp);
        verifyNoMoreInteractions(req,resp);
    }

}
