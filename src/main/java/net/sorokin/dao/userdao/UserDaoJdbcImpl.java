package net.sorokin.dao.userdao;

import net.sorokin.dao.daoexceptions.NotUniqueUserEmailException;
import net.sorokin.entity.User;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJdbcImpl implements UserDao{

    public static final String SELECT_ALL_SQL = "SELECT id, email, password, name, phoneNumber FROM users";
    public static final String INSERT_SQL = "INSERT INTO users(email, password, name, phoneNumber) values(?, ?, ?, ?)";
    public static final String SELECT_BY_EMAIL = "SELECT id, password, name, phoneNumber FROM users WHERE email = ?";

    public DataSource dataSource;
   /* public static final String CREATE_TABLE = "CREATE table users(id integer(10) AUTO_INCREMENT," +
                                              " email varchar(50) NOT NULL UNIQUE," +
                                              " password varchar(20) NOT NULL," +
                                              " name varchar(50) NOT NULL," +
                                              " phoneNumber VARCHAR (15) NOT NULL," +
                                              " PRIMARY KEY(id))";
    public static final String DELETE_ALL_SQL = "DELETE FROM users";
    public static final String DELETE_BY_ID_SQL = "DELETE FROM users WHERE id = ?";*/

    public void setDatasource(DataSource datasource) throws Exception {
        this.dataSource = datasource;
    }

    public List<User> selectAll() throws SQLException {
        Connection connection = null;
        Statement statement = null;
        ResultSet rs = null;
        List<User> users = null;
        try {
            connection = getConnection();
            statement = connection.createStatement();
            users = new ArrayList<User>();
            User user = null;
            rs = statement.executeQuery(SELECT_ALL_SQL);
            while (rs.next()) {
                user = new User();
                user.setId(rs.getInt("id"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                user.setName(rs.getString("name"));
                user.setPhoneNumber(rs.getString("phoneNumber"));
                users.add(user);
            }
        }finally {
            rs.close();
            statement.close();
        }
        return users;
    }

    public User selectByEmail(String email) throws SQLException {

        User user = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(SELECT_BY_EMAIL);
            preparedStatement.setString(1,email);

            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                user = new User();
                user.setEmail(email);
                user.setId(rs.getInt("id"));
                user.setPassword(rs.getString("password"));
                user.setName(rs.getString("name"));
                user.setPhoneNumber(rs.getString("phoneNumber"));
            }
        } finally {
            rs.close();
            preparedStatement.close();
        }
        return user;
    }

    public int insert(User user) {
        int result = 0;
        try {
            Connection connection = getConnection();
            PreparedStatement preparedStatement = null;
            try {
                if (existWithEmail(connection, user.getEmail())) {
                    throw new NotUniqueUserEmailException("Email" + user.getEmail() + "Already existed");
                }
                preparedStatement = connection.prepareStatement(INSERT_SQL);
                preparedStatement.setString(1, user.getEmail());
                preparedStatement.setString(2, user.getPassword());
                preparedStatement.setString(3, user.getName());
                preparedStatement.setString(4, user.getPhoneNumber());
                result = preparedStatement.executeUpdate();
            }finally {
                preparedStatement.close();
            }
        }catch (SQLException e) {
            e.printStackTrace();
        } catch (NotUniqueUserEmailException e) {
            e.printStackTrace();
        }
        return result;
    }

    private Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

    private boolean existWithEmail(Connection conn, String email) throws SQLException {

        PreparedStatement ps = conn.prepareStatement(SELECT_BY_EMAIL);
        ps.setString(1,email);
        ResultSet rs = ps.executeQuery();
        return rs.next();
    }

   /* //Util
    public static void createTable() throws SQLException{
        Connection connection = getConnection();
        Statement statement = connection.createStatement();
        statement.execute(CREATE_TABLE);
    }

    public void zeroOutAutoIncrement() throws SQLException {
        String zeroOut = "ALTER TABLE users ALTER COLUMN id RESTART WITH 1;";
        Connection connection = getConnection();
        Statement statement = connection.createStatement();
        statement.execute(zeroOut);
    }

    public void deleteAllUsers() throws SQLException {
        Connection connection = getConnection();
        Statement statement = connection.createStatement();
        statement.execute(DELETE_ALL_SQL);
        connection.commit();

        statement.close();
        connection.close();
    }

    public void deleteByID(int id) throws SQLException {
        Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(DELETE_BY_ID_SQL);
        preparedStatement.setInt(1,id);
        connection.commit();

        preparedStatement.close();
        connection.close();
    }*/


}
