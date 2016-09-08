package net.sorokin.controller;


import net.sorokin.dao.bookdao.BookDaoJdbcImpl;
import net.sorokin.tx.TransactionManager;
import net.sorokin.tx.TransactionManagerImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.concurrent.Callable;

public class ImageController extends HttpServlet {

    private BookDaoJdbcImpl bookDao = new BookDaoJdbcImpl();
    private TransactionManager txManager = new TransactionManagerImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
        String idStr = req.getParameter("id");
        OutputStream oImage;
        byte[] sImageBytes;
            bookDao.setDatasource(txManager);
            if (idStr != null && !idStr.equals("")) {
                final Integer id = Integer.valueOf(idStr);
                sImageBytes = txManager.doInTransaction(new Callable<byte[]>() {
                    public byte[] call() throws Exception {
                        System.out.println("before selectAll");
                        return bookDao.selectImageByID(id);
                    }
                });
                resp.setContentType("image/gif");
                oImage=resp.getOutputStream();
                oImage.write(sImageBytes);
                oImage.flush();
                oImage.close();
            }
        }
        catch(Exception ex){
            System.out.println("error :"+ex);
        }
    }
}
