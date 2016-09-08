package net.sorokin.dao.bookdao;

import net.sorokin.dao.daoexceptions.NotUniqueBookInDBException;
import net.sorokin.entity.Book;
import javax.sql.DataSource;
import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookDaoJdbcImpl implements BookDao{

    public static final String SELECT_ALL_SQL = "SELECT id, name, author, year, desc FROM books";
    public static final String INSERT_SQL = "INSERT INTO books(name, author, year, desc, image) values(?,?,?,?,?)";
    public static final String SELECT_EXISTS = "SELECT id FROM books WHERE name = ? AND author = ? AND year = ?";
    public static final String SELECT_BY_ID = "SELECT name, author, year, desc FROM books WHERE id = ?";
    public static final String SELECT_IMAGE_BY_ID = "SELECT image FROM books WHERE id = ?";

    /*public static final String CREATE_TABLE = "CREATE table books(id integer(10) AUTO_INCREMENT," +
                                              " name varchar(50) NOT NULL," +
                                              " author varchar(50) NOT NULL," +
                                              " year integer(10) NOT NULL,"+
                                              " desc TEXT,"+
                                              " image BLOB,"+
                                              " PRIMARY KEY(id))";
    public static final String DELETE_ALL_SQL = "DELETE FROM books";
    public static final String DELETE_BY_ID_SQL = "DELETE FROM books WHERE id = ?";
    public static final String SELECT_BY_NAME = "SELECT id, name, author, year FROM books WHERE name = ?";
    public static final String SELECT_BY_AUTHOR = "SELECT id, name, author, year FROM books WHERE author = ?";
    public static final String SELECT_BY_YEAR = "SELECT id, name, author, year FROM books WHERE year = ?";*/

    public DataSource dataSource;

    public void setDatasource(DataSource datasource) throws Exception {
        this.dataSource = datasource;
    }

    public List<Book> selectAll() throws SQLException, IOException {
        Connection connection = null;
        Statement statement = null;
        ResultSet rs = null;
        List<Book> books = null;
        try {
            connection = dataSource.getConnection();
            statement = connection.createStatement();
            rs = statement.executeQuery(SELECT_ALL_SQL);
            System.out.println("rs");
            books = selectWithResulSet(rs);
        }
        finally {
            rs.close();
            statement.close();
        }
        return books;
    }

    public Book selectByID(int id) throws SQLException{
        Book book = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(SELECT_BY_ID);
            preparedStatement.setInt(1, id);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                book = new Book();
                book.setId(id);
                book.setName(rs.getString("name"));
                book.setAuthor(rs.getString("author"));
                book.setYear(rs.getInt("year"));
                book.setDesc(rs.getString("desc"));
            }
        }finally {
            rs.close();
            preparedStatement.close();
        }
        return book;
    }

    public byte[] selectImageByID(int id) throws SQLException, IOException{
        byte [] array = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(SELECT_IMAGE_BY_ID);
            preparedStatement.setInt(1, id);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                byte[] buffer = new byte[1];
                int n = 0;
                InputStream in = rs.getBinaryStream(1);
                while ((n=in.read(buffer))>=0) {
                    baos.write(buffer, 0, n);
                }
                in.close();
                array = baos.toByteArray();
            }
        }finally {
            rs.close();
            preparedStatement.close();
        }
        return array;
    }

    public void insert(Book book) throws SQLException, NotUniqueBookInDBException, FileNotFoundException {

        Connection connection = getConnection();
        PreparedStatement preparedStatement = null;

        if(existThisBookInDB(connection, book.getName(), book.getAuthor(), book.getYear())){
            throw  new NotUniqueBookInDBException("Book" + book.getName() + "already existed in bookstorage");
        }

        preparedStatement = connection.prepareStatement(INSERT_SQL);

        preparedStatement.setString(1, book.getName());
        preparedStatement.setString(2, book.getAuthor());
        preparedStatement.setInt(3, book.getYear());
        preparedStatement.setString(4,book.getDesc());

        ByteArrayInputStream bout = new ByteArrayInputStream(book.getImage());
        preparedStatement.setBinaryStream(5, bout, (int) book.getImage().length);
        preparedStatement.executeUpdate();

        preparedStatement.close();
    }

    public Connection getConnection() throws SQLException {
        return dataSource.getConnection();
        /*return DriverManager.getConnection("jdbc:h2:~/test", "","");*/
    }

    private static List<Book> selectWithResulSet(ResultSet rs) throws SQLException, IOException {

        List<Book> books = new ArrayList<Book>();
        Book book = null;
        while(rs.next()){
            book = new Book();
            book.setId(rs.getInt("id"));
            String name = rs.getString("name");
            book.setName(name);
            book.setAuthor(rs.getString("author"));
            book.setYear(rs.getInt("year"));
            book.setDesc(rs.getString("desc"));
            books.add(book);
        }
        return books;
    }

    private boolean existThisBookInDB(Connection conn, String name, String author, int year) throws SQLException {

        PreparedStatement ps = conn.prepareStatement(SELECT_EXISTS);
        ps.setString(1,name);
        ps.setString(2,author);
        ps.setInt(3,year);
        ResultSet rs = ps.executeQuery();

        return rs.next();
    }

     /*public List<Book> selectByName() throws SQLException, IOException {
        Connection connection = getConnection();

        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(SELECT_BY_NAME);
        List<Book> books = selectWithResulSet(rs);

        rs.close();
        statement.close();
        connection.close();

        return books;
    }

    public List<Book> selectByAuthor() throws SQLException, IOException {
        Connection connection = getConnection();

        Statement statement = connection.createStatement();

        ResultSet rs = statement.executeQuery(SELECT_BY_AUTHOR);
        List<Book> books = selectWithResulSet(rs);

        rs.close();
        statement.close();
        connection.close();

        return books;
    }

    public List<Book> selectByYear() throws SQLException, IOException {
        Connection connection = getConnection();

        Statement statement = connection.createStatement();

        ResultSet rs = statement.executeQuery(SELECT_BY_YEAR);
        List<Book> books = selectWithResulSet(rs);

        rs.close();
        statement.close();
        connection.close();

        return books;
    }*/

    /*//Util
    public  void createTable() throws SQLException{
        Connection connection = dataSource.getConnection();
        Statement statement = connection.createStatement();
        statement.execute(CREATE_TABLE);
    }*/

   /* public void zeroOutAutoIncrement() throws SQLException {
        String zeroOut = "ALTER TABLE books ALTER COLUMN id RESTART WITH 1;";
        Connection connection = getConnection();
        connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
        connection.setAutoCommit(false);
        Statement statement = connection.createStatement();
        statement.execute(zeroOut);

        statement.close();
        connection.close();
    }

     public void deleteAllBooks() throws SQLException {
        Connection connection = getConnection();
        connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
        connection.setAutoCommit(false);

        Statement statement = connection.createStatement();
        statement.execute(DELETE_ALL_SQL);
        connection.commit();


        statement.close();
        connection.close();
    }*/
}
