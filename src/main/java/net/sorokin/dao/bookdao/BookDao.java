package net.sorokin.dao.bookdao;


import net.sorokin.dao.daoexceptions.NotUniqueBookInDBException;
import net.sorokin.entity.Book;


import javax.sql.DataSource;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface BookDao {

    public List<Book> selectAll() throws SQLException, IOException;

    public Book selectByID(int id) throws SQLException;

    public void setDatasource(DataSource datasource) throws Exception;

    /*public List<Book> selectByName() throws SQLException, IOException;

    public List<Book> selectByAuthor() throws SQLException, IOException;

    public List<Book> selectByYear() throws SQLException, IOException;*/

    public void insert(Book book) throws SQLException, NotUniqueBookInDBException, FileNotFoundException;

}
