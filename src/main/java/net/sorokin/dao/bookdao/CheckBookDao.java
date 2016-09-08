package net.sorokin.dao.bookdao;


import net.sorokin.dao.daoexceptions.NotUniqueBookInDBException;
import net.sorokin.entity.Book;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CheckBookDao {

    public static void main(String[] args) throws SQLException, IOException, NotUniqueBookInDBException {


        //CheckBookDao.selectAll();
        //CheckBookDao.selectByID(1);
        /*BookDaoJdbcImpl bookDao = new BookDaoJdbcImpl();
        bookDao.selectImageByID(1);*/
        //CheckBookDao.selectImageByID(1);
        /*bookDao.deleteAllBooks();
        bookDao.zeroOutAutoIncrement();*/
        CheckBookDao.insert();

    }

    public static void insert() throws IOException, SQLException, NotUniqueBookInDBException {
       Book book = new Book();
        book.setName("Две встречи в Париже");
        book.setAuthor("\tДжоджо Мойес");
        book.setYear(2016);
        book.setDesc("В книгу входят две повести. \"Медовый месяц в Париже\" – это предыстория событий, которые разворачиваются в романе Мойес \"Девушка, которую ты покинул\". Лив и Софи разделяют почти сто лет, но они обе стоят на пороге семейной жизни, обе надеются на счастливый медовый месяц с любимым мужчиной...");
        Path path = Paths.get("D:\\eshop\\3.jpg");
        byte[] data = Files.readAllBytes(path);
        book.setImage(data);
        //book.setImage(new File("C:\\Users\\sorokin_av\\Downloads\\book.jpg"));
        BookDao bookDao = new BookDaoJdbcImpl();
        bookDao.insert(book);
    }

    public static void selectAll() throws IOException, SQLException {
        BookDao bookDao = new BookDaoJdbcImpl();
        List<Book> list =  bookDao.selectAll();
        File image = new File("D:\\image123.jpg");
        FileOutputStream fos = new FileOutputStream(image);
        for(Book book : list){
            System.out.println(book);
            System.out.println(book.getImage());
            fos.write(book.getImage());
        }
    }

    public static void selectByID(int id) throws IOException, SQLException {
        BookDao bookDao = new BookDaoJdbcImpl();
        Book book = bookDao.selectByID(id);
        File image = new File("D:\\image12345.jpg");
        FileOutputStream fos = new FileOutputStream(image);
        System.out.println();
        fos.write(book.getImage());
        System.out.println(book);

    }

    public static void selectImageByID(int id) throws IOException, SQLException {
        BookDaoJdbcImpl bookDao = new BookDaoJdbcImpl();
        byte[] buffer = bookDao.selectImageByID(id);
        File image = new File("D:\\image1234567.jpg");
        FileOutputStream fos = new FileOutputStream(image);
        System.out.println();
        fos.write(buffer);
        System.out.println();
    }

}
